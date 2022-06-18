package ma.ensaf.dao;

import lombok.Getter;
import ma.ensaf.entity.Product;
import ma.ensaf.support.dao.GenericDao;

public class ProductDao extends GenericDao<Product> {
	@Getter
	private final static ProductDao instance = new ProductDao();
	private ProductDao() {}

}
