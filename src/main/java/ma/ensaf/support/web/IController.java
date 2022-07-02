package ma.ensaf.support.web;

import jakarta.servlet.http.HttpServletRequest;

public interface IController {

	String list(Model model, HttpServletRequest request);

	String edit(Model model, HttpServletRequest request);

	String view(Model model, HttpServletRequest request);

	String create(Model model, HttpServletRequest request);

	String update(Model model, HttpServletRequest request);

	String delete(Model model, HttpServletRequest request);

}