package session.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import session.constants.ShiroConstants;
import session.mgt.OnlineSession;

public class OnlineSessionFilter extends AccessControlFilter {

	/**
	 * ǿ���˳����ض���ĵ�ַ
	 */
	private String forceLogoutUrl;

	private SessionDAO sessionDAO;

	public String getForceLogoutUrl() {
		return forceLogoutUrl;
	}

	public void setForceLogoutUrl(String forceLogoutUrl) {
		this.forceLogoutUrl = forceLogoutUrl;
	}

	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		if (subject == null || subject.getSession(false) == null) {
			return true;
		}
		Session session = sessionDAO.readSession(subject.getSession().getId());
		if (session != null && session instanceof OnlineSession) {
			OnlineSession onlineSession = (OnlineSession) session;
			request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);

			if (onlineSession.getStatus() == OnlineSession.OnlineStatus.force_logout) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject != null) {
			subject.logout();
		}
		saveRequestAndRedirectToLogin(request, response);
		return true;
	}

	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		WebUtils.issueRedirect(request, response, getForceLogoutUrl());
	}

}