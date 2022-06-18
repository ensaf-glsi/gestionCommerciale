package ma.ensaf.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ma.ensaf.dao.ProductDao;
import ma.ensaf.entity.Product;

public class ProductService {
	@Getter
	private static final ProductService instance = new ProductService();
	private ProductService() {}

	@Setter
	private ProductDao productDao;
	
	public Product create(Product entity) {
		// regles de gestion, controles des champs
		Product p = productDao.create(entity);
		// regles ou des traitements apres la creation
		return p;
	}

	public int update(Product entity) {
		return productDao.update(entity);
	}

	public void delete(Long id) {
		productDao.delete(id);
	}

	public Product findById(Long id) {
		return productDao.findById(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

}
