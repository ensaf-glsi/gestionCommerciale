package ma.ensaf.support.web;

import java.math.BigDecimal;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	public static Long getLong(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static BigDecimal getBigDecimal(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		try {
			return new BigDecimal(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String value = "12ads";
		try {
			System.out.println(Long.parseLong(value));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public static String redirect(String uri) {
		return Constants.REDIRECT + uri;
	}
}
