package ma.ensaf.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ma.ensaf.dao.ProductDao;
import ma.ensaf.entity.Product;
import static org.mockito.Mockito.*;

class ProductServiceTest {
	
	private static ProductService productService;
	private static ProductDao productDao;
	
	@BeforeAll
	static void setup() {
		productService = ProductService.getInstance();
		productDao = mock(ProductDao.class);
		productService.setProductDao(productDao);
	}

	@Test
	void testCreate_should_create_entity() {
        // given
		Long id = 10L;
		Product input = getProduct(null);
		Product attendu = getProduct(id);
		doReturn(attendu).when(productDao).create(input);
        // when
        Product result = productService.create(input);
        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result).isEqualTo(attendu);
	}

	@Test
	@Disabled
	void testUpdate() {
	}

	@Test
	@Disabled
	void testDelete() {
	}

	@Test
	@Disabled
	void testFindById() {
	}

	@Test
	@Disabled
	void testFindAll() {
	}
	
	static Product getProduct(Long id) {
		return Product.builder()
				.name("Ecran LG HD")
				.price(new BigDecimal(799))
				.unit("U")
				.id(id)
				.build();
	}

}
