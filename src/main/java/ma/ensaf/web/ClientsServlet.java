package ma.ensaf.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ensaf.entity.Client;
import ma.ensaf.service.ClientService;

@WebServlet(name = "Clients", urlPatterns = { "/clients/*" })
public class ClientsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ClientService clientService = new ClientService();

	public void requestInfo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
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
		out.println("<br/>PathInfo: " + pathInfo);
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		requestInfo(req, resp);
		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
		switch (pathInfo) {
		case "/new": {
			request.getRequestDispatcher("/views/clients/edit.jsp").forward(request, response);
		}
		case "/list": {
			request.setAttribute("list", clientService.getList());
			request.getRequestDispatcher("/views/clients/list.jsp").forward(request, response);
		}
		case "/edit": {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Optional<Client> client = clientService.findById(id);
			if (client.isEmpty()) {
				//TODO traitement des erreurs
				response.getWriter().print("client introuvable !");
			} else {
				request.setAttribute("entity", client.get());
				request.getRequestDispatcher("/views/clients/edit.jsp").forward(request, response);
			}
		}
		default:
			requestInfo(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String id = request.getParameter("id");
		Client client = Client.builder().nom(request.getParameter("nom"))
				.email(request.getParameter("email"))
				.tel(request.getParameter("tel"))
				.adresse(request.getParameter("adresse")).build();
		if (id == null || id.length() == 0) {
			// un ajout
			clientService.create(client);
		} else {
			// une modification
			client.setId(Integer.parseInt(id));
			clientService.update(client);
		}
		//TODO enregistrer en bd
		response.sendRedirect(contextPath + "/clients/list");
	}

}
