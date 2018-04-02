package session.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
	@Before
	public void setUp() {

	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// �˳�ʱ������Subject���߳� ������´β������Ӱ��
	}

	protected void login(String configFile, String username, String password) {
		// 1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

		// 2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setHost("10.83.1.1");
		subject.login(token);
	}

	public Subject subject() {
		return SecurityUtils.getSubject();
	}
}
