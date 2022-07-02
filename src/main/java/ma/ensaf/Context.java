package ma.ensaf;

import java.util.HashMap;
import java.util.Map;

import ma.ensaf.dao.FamilleDao;
import ma.ensaf.dao.ProductDao;
import ma.ensaf.service.FamilleService;
import ma.ensaf.service.ProductService;
import ma.ensaf.support.web.IController;
import ma.ensaf.web.FamilleController;
import ma.ensaf.web.ProductController;

public class Context {

	Map<String, IController> controllers = new HashMap<>();
	public Context() {
		ProductService.getInstance().setDao(ProductDao.getInstance());
		ProductController.getInstance().setService(ProductService.getInstance());
		
		FamilleService.getInstance().setDao(FamilleDao.getInstance());
		FamilleController.getInstance().setService(FamilleService.getInstance());
		
		controllers.put(ProductController.PATH, ProductController.getInstance());
		controllers.put(FamilleController.PATH, FamilleController.getInstance());
	}
	
	public IController getController(String name) {
		return controllers.get(name);
	}
}
