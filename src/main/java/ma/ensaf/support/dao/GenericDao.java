package ma.ensaf.support.dao;

import java.util.List;

import ma.ensaf.support.utils.jdbc.SqlUtils;

public class GenericDao<T> extends AbstractDao<T> {
	
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

}
