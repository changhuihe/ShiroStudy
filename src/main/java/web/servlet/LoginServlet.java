package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	/*
	 * ���ص�¼����
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}

	/*
	 * ��¼
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String error = null;
		String username = req.getParameter("username");
		String passowrd = req.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, passowrd);
		token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "�û���/�������";
		} catch (IncorrectCredentialsException e) {
			error = "�û���/�������";
		} catch (AuthenticationException e) {
			// �������󣬱�������������뵥�������뵥��catch����
			error = "��������" + e.getMessage();
		}
		if (error != null) {// �����ˣ����ص�¼ҳ��
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		} else {// ��¼�ɹ�
			req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp);
		}
	}

}
