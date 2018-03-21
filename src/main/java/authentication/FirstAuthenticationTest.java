package authentication;

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
import org.junit.Test;

/*
 * ��һ�������֤����
 */
public class FirstAuthenticationTest {
	@Test
	public void testHch() {
		/*
		 * 1����ȡsecurityManager������ʹ��Ini�����ļ���ʼ��SecurityManager [users] ����ָ���û��� / ���뼰���ɫ
		 * [roles] ����ָ����ɫ��Ȩ����Ϣ
		 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:firstAuththentication_shiro.ini");
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
}
