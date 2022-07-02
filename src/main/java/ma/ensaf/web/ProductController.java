package ma.ensaf.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ensaf.entity.Product;
import ma.ensaf.service.ProductService;
import ma.ensaf.support.web.IController;
import ma.ensaf.support.web.Model;
import ma.ensaf.support.web.RequestUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductController implements IController {
	private static final String LIST_VIEW = "products/list";
	private static final String EDIT_VIEW = "products/edit";
	private static final String DETAIL_VIEW = "products/view";

	public static final String PATH = "products"; 
	
	@Getter
	private static final ProductController instance = new ProductController();
	
	@Setter
	private ProductService service;
	
	@Override
	public String list(Model model, HttpServletRequest request) {
		model.put("list", service.findAll());
		return LIST_VIEW;
	}

	@Override
	public String edit(Model model, HttpServletRequest request) {
		Long id = RequestUtils.getLong(request, "id");
		if (id != null) {
			// un formulaire de modification
			model.put("entity", service.findById(id));
		}
		return EDIT_VIEW;
	}
	
	@Override
	public String view(Model model, HttpServletRequest request) {
		Long id = RequestUtils.getLong(request, "id");
		model.put("entity", service.findById(id));
		return DETAIL_VIEW;
	}
	
	Product getEntity(HttpServletRequest request) {
		return Product.builder()
				.id(RequestUtils.getLong(request, "id"))
				.name(request.getParameter("name"))
				.price(RequestUtils.getBigDecimal(request, "price"))
				.unit(request.getParameter("unit"))
				.build();
	}
	
	@Override
	public String create(Model model, HttpServletRequest request) {
		service.create(getEntity(request));
		return RequestUtils.redirect(PATH);
	}
	
	@Override
	public String update(Model model, HttpServletRequest request) {
		service.update(getEntity(request));
		return RequestUtils.redirect(PATH);
	}

	@Override
	public String delete(Model model, HttpServletRequest request) {
		service.delete(RequestUtils.getLong(request, "id"));
		return RequestUtils.redirect(PATH);
	}
	
}
