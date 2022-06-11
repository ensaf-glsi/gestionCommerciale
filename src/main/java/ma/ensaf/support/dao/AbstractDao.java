package ma.ensaf.support.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ma.ensaf.support.dao.annotation.Transient;
import ma.ensaf.support.exceptions.TechnicalException;
import ma.ensaf.support.utils.ReflectUtils;
import ma.ensaf.support.utils.jdbc.SqlUtils;

public class AbstractDao<T> {

	private Class<T> domainClass;
	protected String tableName;

	public AbstractDao() {
		// pour productDao je dois recuperer le type Product
		Class<?> clazz = getClass();
		Class<T> d = ReflectUtils.getParameterizedType(clazz, 0);
		System.out.println("The domain class : " + d);
		setDomainClass(d);
	}

	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
		this.tableName = SqlUtils.getTableName(domainClass);
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