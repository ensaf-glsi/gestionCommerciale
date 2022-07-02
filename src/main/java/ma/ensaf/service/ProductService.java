package ma.ensaf.service;

import lombok.Getter;
import ma.ensaf.entity.Product;
import ma.ensaf.support.service.GenericService;

public class ProductService extends GenericService<Product> {
	@Getter
	private static final ProductService instance = new ProductService();
	private ProductService() {
		super();
	}

}
