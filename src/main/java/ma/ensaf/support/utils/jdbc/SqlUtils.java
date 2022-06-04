package ma.ensaf.support.utils.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import ma.ensaf.support.dao.annotation.Column;
import ma.ensaf.support.dao.annotation.Table;
import ma.ensaf.support.dao.annotation.Transient;
import ma.ensaf.support.exceptions.TechnicalException;
import ma.ensaf.support.utils.ReflectUtils;

public class SqlUtils {
	// trois couches
	// couche d'accès à la base de données (dao, repository)
	// couche metier ou service : une couche contenant les règles de gestion, des appels la couche dao
	// couche presentation ou web : on implemente MVC, on appel la couche metier 
	
	public static void delete(Object object) {
		String tableName = getTableName(object.getClass());
		Object id = ReflectUtils.getValue("id", object);
		delete(tableName, id);
	}

	public static <E> int update(E object) {
		String tableName = getTableName(object.getClass());
		Object id = ReflectUtils.getValue("id", object);
		return update(tableName, id, getFields(object));
	}

	// TODO transaction
	public static <E> E create(E object) {
		String tableName = getTableName(object.getClass());
		Long id = insert(tableName, getFields(object));
		ReflectUtils.setValue(object, "id", id);
		return object;
	}
	// update PRODUITS set designation = 'Clavier filaire', prix = 35, quantite = 30 where id = 1;
	// tableName = PRODUITS
	// id = 1
	// fields = {designation: 'Clavier filaire', prix: 35, quantite: 30 }
	
	// query = update produits set designaion = ?, prix = ?, quantite = ? wher id = ?
	// params = ['Clavier filaire', 35, 30, 1]
	/**
	 * mise à jour d'un enregistrement 
	 * 
	 * @param tableName : le nom de la table
	 * @param id : la clé primaire
	 * @param fields : une map contenant des clés/valeurs des champs à mettre à jour (exemple : {designation: 'Clavier filaire', prix: 35, quantite: 30 })
	 * @return nombre des enregistrements mis à jour
	 */
	public static int update(String tableName, Object id, Map<String, Object> fields) {
		// build query : update {tableName} set {field1} = ?, {field2} = ? ... where id = ?
		// build params : [{value1}, {value2}, {id}]
		Set<String> fieldKeys = fields.keySet();
		StringBuilder queryBuilder = new StringBuilder(3 + fieldKeys.size() * 3);
		queryBuilder.append("update ").append(tableName).append(" set ");
		// ajout des fields
		List<Object> params = new ArrayList<>(fieldKeys.size() + 1);
		/*
		boolean addComma = false;
		for (String key : fieldKeys) {
			if (addComma) {
				queryBuilder.append(", ");
			}
			queryBuilder.append(key).append(" = ? ");
			addComma = true;
			params.add(fields.get(key));
		}
		*/
		//initiale : query : update produits set ; params = []
		//it1: designation 
			// => query : update produits set designation = ?; params = ['Clavier filaire']
		//it2: prix
			// => query : update produits set designation = ?, prix = ?; params = ['Clavier filaire', 30]
		//it3: qte 
			// => query : update produits set designation = ?, prix = ?, qte = ?; params = ['Clavier filaire', 30, 45]
		fieldKeys.forEach(key -> {
			if (params.size() > 0) {
				queryBuilder.append(", ");
			}
			queryBuilder.append(key).append(" = ?");
			params.add(fields.get(key));
		});
		queryBuilder.append(" where id = ?");
		params.add(id);
		
		return executeUpdate(queryBuilder.toString(), params);
	}
	
	/**
	 * inserer un enregistrement 
	 * @param tableName
	 * @param fields : une map contenant des clés/valeurs des champs à inserer (exemple : {designation: 'Clavier filaire', prix: 35, quantite: 30 })
	 * @return
	 */
	public static Long insert(String tableName, Map<String, Object> fields) {
		// build query : insert into {tableName}({field1}, {field2} ...) values (?, ? ...)
		// build params : [{value1}, {value2} ...]
		Set<String> fieldKeys = fields.keySet();
		int size = fieldKeys.size();
		List<Object> params = new ArrayList<>(size);
		StringBuilder fieldsBuilder = new StringBuilder(2 * size - 1); // {field1}, {field2} ...
		StringBuilder valuesBuilder = new StringBuilder(2 * size - 1); // ?, ? ...
		fieldKeys.forEach(key -> {
			if (fieldsBuilder.length() > 0) {
				fieldsBuilder.append(',');
				valuesBuilder.append(',');
			}
			fieldsBuilder.append(key);
			valuesBuilder.append('?');
			params.add(fields.get(key));
		});
		
		StringBuilder queryBuilder = new StringBuilder(3 + fieldKeys.size() * 3);
		queryBuilder.append("insert into ").append(tableName).append(" ( ")
				.append(fieldsBuilder.toString()).append(") values (")
				.append(valuesBuilder.toString()).append(')');
		System.out.println("query : " + queryBuilder);
		return insert(queryBuilder.toString(), params);
	}
	public static Long insert(String query, List<Object> params) {
		Connection connection = JdbcConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					query, 
					Statement.RETURN_GENERATED_KEYS);
			setParams(ps, params);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}
			ps.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	
	// select * from {tablename} where id = ?
	public static <T> T findOne(String tableName, Object id, Function<ResultSet, T> rowMapper) {
		Connection connection = JdbcConnection.getConnection();
		try {
			String query = new StringBuilder(3).append("select * from ")
					.append(tableName).append(" where id = ?")
					.toString();
			System.out.println("findOne query : " + query);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setObject(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rowMapper.apply(rs);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	
	public static void setParams(PreparedStatement ps, List<Object> params) throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
		}
	}
	public static void setParams(PreparedStatement ps, Object[] params) throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
	}
	/**
	 * ex 1 - query : select * from produits ; params : null 
	 * ex 2 - query : select * from produits where designation like ? ; params : ["cl"] 
	 * @param <T>
	 * @param query
	 * @param params
	 * @return
	 */
	public static <T> List<T> findList(String query, Function<ResultSet, T> rowMapper, Object... params) {
		List<T> list = new ArrayList<>();
		Connection connection = JdbcConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			setParams(ps, params);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rowMapper.apply(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	public static <T> List<T> findAll(String tableName, Function<ResultSet, T> rowMapper) {
		StringBuilder queryBuilder = new StringBuilder(2);
		queryBuilder.append("select * from ").append(tableName);
		return findList(queryBuilder.toString(), rowMapper);
	}

	public static void delete(String tableName, Object id) {
		StringBuilder queryBuilder = new StringBuilder(3);
		queryBuilder.append("delete from ").append(tableName).append(" where id = ?");
		executeUpdate(queryBuilder.toString(), Arrays.asList(id));
	}

	public static int executeUpdate(String query, List<Object> params) {
		try {
			Connection connection = JdbcConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			setParams(ps, params);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin", e);
		}
	}

	public static String getFieldName(Field field) {
		if (field.isAnnotationPresent(Column.class)) {
			Column column = field.getAnnotation(Column.class);
			return column.name();
        }
		return field.getName();
	}
	public static String getTableName(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			return table.value();
		}
		return clazz.getSimpleName();
	}
	private static Map<String, Object> getFields(Object object) {
		List<Field> declaredFields = ReflectUtils.getAllDeclaredFields(object.getClass());
		Map<String, Object> fields = new HashMap<>();
		for (Field field : declaredFields) {
			if (!field.isAnnotationPresent(Transient.class)) {
				String fieldName = getFieldName(field);
				if (!Objects.equals(fieldName, "id")) {
					fields.put(fieldName, ReflectUtils.getValue(field, object));
				}
			}
		}
		return fields;
	}

}
