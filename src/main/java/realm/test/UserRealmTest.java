package realm.test;

import org.junit.Test;

public class UserRealmTest extends BaseTest {
	// @Test
	public void testLoginSuccess() {
		login("classpath:realm_shiro.ini", u1.getUsername(), password);
	}

	@Test
	public void testLoginFailWithUnknownUsername() {
		login("classpath:realm_shiro.ini", u1.getUsername() + "1", password);
	}

	// @Test
	public void testLoginFailWithErrorPassowrd() {
		login("classpath:shiro.ini", u1.getUsername(), password + "1");
	}

	// @Test
	public void testLoginFailWithLocked() {
		login("classpath:shiro.ini", u4.getUsername(), password + "1");
	}

	// @Test
	public void testLoginFailWithLimitRetryCount() {
		for (int i = 1; i <= 5; i++) {
			try {
				login("classpath:shiro.ini", u3.getUsername(), password + "1");
			} catch (Exception e) {
				/* ignore */}
		}
		login("classpath:shiro.ini", u3.getUsername(), password + "1");

		// 需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
	}

	// @Test
	public void testHasRole() {
		login("classpath:shiro.ini", u1.getUsername(), password);
	}

	// @Test
	public void testNoRole() {
		login("classpath:shiro.ini", u2.getUsername(), password);
	}

	// @Test
	public void testHasPermission() {
		login("classpath:shiro.ini", u1.getUsername(), password);
	}

	// @Test
	public void testNoPermission() {
		login("classpath:shiro.ini", u2.getUsername(), password);
	}

}
