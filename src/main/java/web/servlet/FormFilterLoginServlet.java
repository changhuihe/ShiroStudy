package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

@WebServlet(name = "formFilterLoginServlet", urlPatterns = "/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String errorClassName = (String) req.getAttribute("shiroLoginFailure");

		if (UnknownAccountException.class.getName().equals(errorClassName)) {
			req.setAttribute("error", "�û���/�������");
		} else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
			req.setAttribute("error", "�û���/�������");
		} else if (errorClassName != null) {
			req.setAttribute("error", "δ֪����" + errorClassName);
		}

		req.getRequestDispatcher("/WEB-INF/jsp/formfilterlogin.jsp").forward(req, resp);
	}
}