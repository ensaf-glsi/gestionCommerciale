package ma.ensaf.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletDispatcher", description = "ServletDispatcher", urlPatterns = { "/app/*" })
public class ServletDispatcher extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		}
		case "comp": {
			request.setAttribute("nom", "test Nom");
			response.sendRedirect(contextPath + "/comparisons.jsp");
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestInfo(req, resp);
	}

}
