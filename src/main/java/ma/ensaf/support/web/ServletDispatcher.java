package ma.ensaf.support.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Objects;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ensaf.Context;

@WebServlet(name = "ServletDispatcher", description = "ServletDispatcher", urlPatterns = { ServletDispatcher.APP_PATTERN })
public class ServletDispatcher extends HttpServlet {

	static final String APP_PATTERN = "/app/*";

	private static final String METHOD = "_method";

	private static final String VIEW_SUFFIX = ".jsp";

	private static final String VIEW_PREFIX = "/WEB-INF/views/";


	private static final long serialVersionUID = 1L;
	private transient Context context;
	
	public ServletDispatcher() {
		this.context = new Context(); // injection des dependences
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		String pathInfo = request.getPathInfo().substring(1);
		String[] pathParts = pathInfo.split("/");
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String controllerUri = pathParts[0];
		String fullPath = contextPath + servletPath + "/";
		request.setAttribute("fullPath", fullPath);
		request.setAttribute("controllerPath", fullPath + controllerUri);
		IController controller = this.context.getController(controllerUri);
		Model model = new Model();
		String view = null;
		String alternateMethod = request.getParameter(METHOD);
		if (alternateMethod != null) {
			method = alternateMethod;
		}
		System.out.println("method : " + method);
		System.out.println("controller : " + pathParts[0]);
		System.out.println("serv path : " + request.getServletPath());
		switch (method) {
		case Constants.GET: {
			// GET products => liste products
			// GET products/view?id= => detail client
			// GET products/edit?id= => afficher le formulaire d'ajout ou de modification d'un client
			if (pathParts.length == 1) {
				view = controller.list(model, request);
			} else if (Objects.equals(Constants.VIEW, pathParts[1])) {
				// products/view?id=&nom=fwe&
				view = controller.view(model, request);
			} else if (Objects.equals(Constants.EDIT, pathParts[1])) {
				// products/edit?id=1
				view = controller.edit(model, request);
			}
			for (Entry<String, Object> entry : model.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}			
			break;
		}
		case Constants.POST: {
			// POST products body {data} => créer un client
			view = controller.create(model, request);
			break;
		}
		case Constants.PUT: {
			// PUT products body {data} => modifier un client
			view = controller.update(model, request);
			break;
		}
		case Constants.DELETE: {
			// DELETE products body : {id: 1} => supprimer un client
			view = controller.delete(model, request);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}
		if (view == null) {
			throw new RuntimeException("View cannot be null");
		}
		if (view.startsWith(Constants.REDIRECT)) {
			String uri = fullPath + view.replace(Constants.REDIRECT, "");
			response.sendRedirect(uri);
		} else {
			request.getRequestDispatcher(VIEW_PREFIX + view + VIEW_SUFFIX).forward(request, response);
		}
	}

	// PATCH
	// HEAD
	// OPTIONS
	// TRACE

	public void requestInfo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo().substring(1);
		String contextPath = request.getContextPath();
		switch (pathInfo) {
		case "home": {
			request.setAttribute("nom", "test Nom");
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
			break;
		}
		case "comp": {
			request.setAttribute("nom", "test Nom");
			response.sendRedirect(contextPath + "/comparisons.jsp");
			break;
		}
		default:
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title>Request Information Example</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>Request Information Example</h3>");
			out.println("<br/>Method: " + request.getMethod());
			out.println("<br/>Context path: " + contextPath);
			out.println("<br/>Request URI: " + request.getRequestURI());
			out.println("<br/>Protocol: " + request.getProtocol());
			out.println("<br/>PathInfo: " + pathInfo);
			out.println("<br/>Remote Address: " + request.getRemoteAddr());
			out.println("</body>");
			out.println("</html>");
		}
	}
}
