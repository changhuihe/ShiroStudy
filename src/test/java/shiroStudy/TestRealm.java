package shiroStudy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class TestRealm {

	// @Test
	public void testHch() {
		/*
		 * 1����ȡsecurityManager������ʹ��Ini�����ļ���ʼ��SecurityManager [users] ����ָ���û��� / ���뼰���ɫ
		 * [roles] ����ָ����ɫ��Ȩ����Ϣ
		 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		/*
		 * 2�����������ļ����õ�SecurityManagerʵ��
		 */
		SecurityManager securityManager = factory.getInstance();
		/*
		 * 3������SecurityManager����̬�ڴ���������ģʽ
		 */
		SecurityUtils.setSecurityManager(securityManager);
		/*
		 * 4����ȡSubject�������û���/���������֤Token�����û����/ƾ֤��
		 */
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		try {
			/*
			 * 5����¼���������֤
			 */
			subject.login(token);
			System.out.println("�����֤�ɹ�");
			/*
			 * AuthenticationException�����֤�쳣�ĸ���
			 */
		} catch (ConcurrentAccessException e) {
			System.out.println("���û����ڵ�¼!");
		} catch (LockedAccountException e) {
			System.out.println("���û�������!");
		} catch (ExcessiveAttemptsException e) {
			System.out.println("��¼ʧ�ܴ�������!");
		} catch (UnknownAccountException e) {
			System.out.println("���û�������!");
		} catch (ExpiredCredentialsException e) {
			System.out.println("���ڵ�ƾ֤!");
		} catch (IncorrectCredentialsException e) {
			System.out.println("�����ƾ֤!");
		} catch (AuthenticationException e) {
			System.out.println("��¼�쳣");
		}
		/*
		 * 7���˳�
		 */
		subject.logout();
	}

	/*
	 * ��Realm����
	 */
	// @Test
	public void testSingleRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_singleRealm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("ch", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		subject.logout();
	}

	/*
	 * ��Realm���� securityManager�ᰴ��realmsָ����˳����������֤���˴�ʹ����ʾ��ָ��˳��ķ�ʽָ����Realm��˳�����ɾ��'
	 * securityManager.realms=$singleRealm,$singleRealm2'��
	 * ��ôsecurityManager�ᰴ��realm������˳�����ʹ�ã���ʹ����ʾָ��Realm������û��ָ����Realm��������
	 */
	// @Test
	public void testMultipelRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_multipleRealm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		/*
		 * ���ж����֤
		 */
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		subject.logout();
	}

	/*
	 * ����Ĭ�ϵ�JdbcRealm
	 */
	@Test
	public void testJDBCRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_jdbcRealm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("ch", "123");
		try {
			subject.login(token);
			System.out.println("��֤�ɹ�");
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		subject.logout();
	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// �˳�ʱ������Subject���߳� ������´β������Ӱ��
	}
}
