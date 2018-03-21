package authorization;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/*
 * ��Ȩ����
 */
public class AuthorizationTest {
	/*
	 * ���ڽ�ɫ�ķ��ʿ���
	 */
	// @Test
	public void testRole() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:authorization_role_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
		/*
		 * 1��subject.hasRole()�ж��Ƿ���ĳ����ɫ/Ȩ��2��subject.hasAllRoles()�ж��Ƿ��ж����ɫ/Ȩ��,���з���true
		 * 3��subject.hasRoles()�ж��Ƿ���ĳ����ɫ/Ȩ��
		 */
		System.out.println(subject.hasRole("role1"));// true
		System.out.println(subject.hasRole("role3"));// false
		System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2")));// true
		System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2", "role3")));// false
		boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
	}

	/*
	 * ������Դ�ķ��ʿ��ƣ���ʾ��ɫ��
	 */
	@Test
	public void testPermission() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:authorization_permission_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
		System.out.println(subject.isPermitted("user:create"));// true
		System.out.println(subject.isPermitted("user:view"));// false
		System.out.println(subject.isPermittedAll("user:create", "user:update"));// true
	}
}
