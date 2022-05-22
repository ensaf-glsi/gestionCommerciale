package ma.ensaf.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ExempleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	void params(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Request Parameters Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Request Parameters Example</h3>");
		out.println("Parameters in this request:<br>");

		request.getParameterMap().forEach((p, v) -> {
			System.out.println(p + " : " + v[0]);
		});
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");

		if (firstName != null || lastName != null) {
			out.println("First Name:");
			out.println(" = " + firstName + "<br>");
			out.println("Last Name:");
			out.println(" = " + lastName);
		} else {
			out.println("No Parameters, Please enter some");
		}
		out.println("<P>");
		out.print("""
					<form action="" method="POST">
					First Name:
					<input type="text" size="20" name="firstname">
				""");
		out.println("<br>");
		out.println("Last Name:");
		out.println("<input type=text size=20 name=lastname>");
		out.println("<br>");
		out.println("<input type=submit>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	void printCookie(PrintWriter out, Cookie c) {
		String name = c.getName();
		String value = c.getValue();
		out.println(name + " = " + value);
		out.println("<br />");
	}

	void printCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		PrintWriter out = response.getWriter();

		Stream.of(cookies).forEach(c -> this.printCookie(out, c));
	}

	void cookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		// print out cookies
		printCookies(request, response);

		// set a cookie
//
		String name = request.getParameter("cookieName");
		if (name != null && name.length() > 0) {
			String value = request.getParameter("cookieValue");
			Cookie c = new Cookie(name, value);
			c.setMaxAge(3600);
			response.addCookie(c);
		}
	}

	void session(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(true);

		// print session info

		Date created = new Date(session.getCreationTime());
		Date accessed = new Date(session.getLastAccessedTime());
		out.println("<br> ID " + session.getId());
		out.println("<br> Created: " + created);
		out.println("<br> Last Accessed: " + accessed);

		// set session info if needed
		String dataName = request.getParameter("dataName");
		if (dataName != null && dataName.length() > 0) {
			String dataValue = request.getParameter("dataValue");
			session.setAttribute(dataName, dataValue);
		}

		// print session contents
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			String value = session.getAttribute(name).toString();
			out.println("<p>" + name + " = " + value + "</p>");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("do get");
		PrintWriter out = response.getWriter();
		out.println("DO GET");
//		params(request, response);
//		cookies(request, response);
		session(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("do post");
		PrintWriter out = response.getWriter();
		out.println("DO POST");
		cookies(request, response);
//		params(request, response);
	}

}
