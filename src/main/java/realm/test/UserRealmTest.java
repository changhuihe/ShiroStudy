package realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class UserRealmTest extends BaseTest {
	// @Test
	public void testLoginSuccess() {
		login("classpath:realm_shiro.ini", u1.getUsername(), password);
	}

	// @Test
	public void testLoginFailWithUnknownUsername() {
		login("classpath:realm_shiro.ini", u1.getUsername() + "1", password);
	}

	// @Test
	public void testLoginFailWithErrorPassowrd() {
		login("classpath:realm_shiro.ini", u1.getUsername(), password + "1");
	}

	// @Test
	public void testLoginFailWithLocked() {
		login("classpath:realm_shiro.ini", u4.getUsername(), password + "1");
	}

	// @Test
	public void testLoginFailWithLimitRetryCount() {
		for (int i = 1; i <= 5; i++) {
			try {
				login("classpath:realm_shiro.ini", u3.getUsername(), password + "1");
			} catch (Exception e) {
				/* ignore */}
		}
		login("classpath:realm_shiro.ini", u3.getUsername(), password + "1");

		// 需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
	}

	// @Test
	public void testHasRole() {
		login("classpath:realm_shiro.ini", u1.getUsername(), password);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.hasRole("admin"));
	}

	// @Test
	public void testNoRole() {
		login("classpath:realm_shiro.ini", u2.getUsername(), password);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.hasRole("admin"));
	}

	// @Test
	public void testHasPermission() {
		login("classpath:realm_shiro.ini", u1.getUsername(), password);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isPermittedAll("user:create", "user:update"));
		System.out.println(subject.isPermittedAll("user:create", "user:update", "menu:create"));

	}

	@Test
	public void testNoPermission() {
		login("classpath:realm_shiro.ini", u2.getUsername(), password);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isPermittedAll("user:create", "user:update"));
		System.out.println(subject.isPermittedAll("user:create", "user:update", "menu:create"));
	}

}
