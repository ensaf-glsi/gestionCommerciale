package ma.ensaf.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ensaf.entity.Famille;
import ma.ensaf.service.FamilleService;
import ma.ensaf.support.web.IController;
import ma.ensaf.support.web.Model;
import ma.ensaf.support.web.RequestUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FamilleController implements IController {
	private static final String LIST_VIEW = "familles/list";
	private static final String EDIT_VIEW = "familles/edit";
	private static final String DETAIL_VIEW = "familles/view";

	public static final String PATH = "familles"; 
	
	@Getter
	private static final FamilleController instance = new FamilleController();
	
	@Setter
	private FamilleService service;
	
	public String list(Model model, HttpServletRequest request) {
		model.put("list", service.findAll());
		return LIST_VIEW;
	}

	public String edit(Model model, HttpServletRequest request) {
		Long id = RequestUtils.getLong(request, "id");
		if (id != null) {
			// un formulaire de modification
			model.put("entity", service.findById(id));
		}
		return EDIT_VIEW;
	}

	public String view(Model model, HttpServletRequest request) {
		Long id = RequestUtils.getLong(request, "id");
		model.put("entity", service.findById(id));
		return DETAIL_VIEW;
	}

	Famille getEntity(HttpServletRequest request) {
		return Famille.builder()
				.id(RequestUtils.getLong(request, "id"))
				.name(request.getParameter("name"))
				.build();
	}

	public String create(Model model, HttpServletRequest request) {
		service.create(getEntity(request));
		return RequestUtils.redirect(PATH);
	}

	public String update(Model model, HttpServletRequest request) {
		service.update(getEntity(request));
		return RequestUtils.redirect(PATH);
	}

	public String delete(Model model, HttpServletRequest request) {
		service.delete(RequestUtils.getLong(request, "id"));
		return RequestUtils.redirect(PATH);
	}
	
}
