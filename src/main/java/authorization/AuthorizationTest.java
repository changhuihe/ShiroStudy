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
 * 授权测试
 */
public class AuthorizationTest {
	/*
	 * 基于角色的访问控制
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
		 * 1、subject.hasRole()判断是否有某个角色/权限2、subject.hasAllRoles()判断是否有多个角色/权限,都有返回true
		 * 3、subject.hasRoles()判断是否有某个角色/权限
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
	 * 基于资源的访问控制（显示角色）
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
