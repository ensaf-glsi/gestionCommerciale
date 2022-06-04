package ma.ensaf.support.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ma.ensaf.support.dao.annotation.Transient;
import ma.ensaf.support.exceptions.TechnicalException;
import ma.ensaf.support.utils.ReflectUtils;
import ma.ensaf.support.utils.jdbc.SqlUtils;

public class GenericDao<T> {
	
	private Class<T> domainClass;
	
	private String tableName;
	
	public GenericDao() {
		// pour productDao je dois recuperer le type Product
		Class<T> d = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println("domain class " + d);
		setDomainClass(d);
	}
	
	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
		this.tableName = SqlUtils.getTableName(domainClass);
	}
	
	public T create(T entity) {
		return SqlUtils.create(entity);
	}
	public int update(T entity) {
		return SqlUtils.update(entity);
	}
	public void delete(Long id) {
		SqlUtils.delete(tableName, id);
	}
	public T findById(Long id) {
		return SqlUtils.findOne(tableName, id, this::convert);
	}
	public List<T> findAll() {
		return SqlUtils.findAll(tableName, this::convert);
	}

	protected T convert(ResultSet rs) {
		try {
			T result = ReflectUtils.newInstance(domainClass);
			List<Field> fields = ReflectUtils.getAllDeclaredFields(domainClass);
			for (Field field : fields) {
				if (!field.isAnnotationPresent(Transient.class)) {
					Object value = rs.getObject(SqlUtils.getFieldName(field));
					ReflectUtils.setValue(result, field, value);
				}
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}

}
