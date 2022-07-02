package ma.ensaf.support.service;

import java.util.List;

import lombok.Setter;
import ma.ensaf.support.dao.GenericDao;

public class GenericService<T> {
	
	@Setter
	private GenericDao<T> dao;

	public T create(T entity) {
		return dao.create(entity);
	}

	public int update(T entity) {
		return dao.update(entity);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public T findById(Long id) {
		return dao.findById(id);
	}

	public List<T> findAll() {
		return dao.findAll();
	}

}