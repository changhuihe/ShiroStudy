package session.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.junit.Test;

import junit.framework.Assert;

public class SessionTest extends BaseTest {

	@Test
	public void testGetSession() throws Exception {
		login("classpath:shiro.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();// ��ȡ�Ự

		System.out.println(session.getId());// ��ȡ�ỰID
		System.out.println(session.getHost());// ��ȡ��ǰ��¼�û�������ַ
		System.out.println(session.getTimeout());// ��ȡ��ʱʱ��
		System.out.println(session.getStartTimestamp()); // ��ȡ�Ự����ʱ��
		System.out.println(session.getLastAccessTime()); // ��ȡ������ʱ��
		Thread.sleep(1000L);
		session.touch();// ���»Ự������ʱ��
		System.out.println(session.getLastAccessTime());

		// �Ự���Բ���
		session.setAttribute("key", "123");
		Assert.assertEquals("123", session.getAttribute("key"));
		session.removeAttribute("key");
	}

}
