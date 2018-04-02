package realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.junit.Test;

import realm.realm.UserRealm;

public class UserRealmCacheTest extends BaseTest {

	@Override
	public void tearDown() {
		userService.changePassword(u1.getId(), password);
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());
		super.tearDown();
	}

	// @Test
	public void testClearCachedAuthenticationInfo() {
		login(u1.getUsername(), password);// 登录成功，身份认证会保存在缓存
		userService.changePassword(u1.getId(), password + "1");// 修改密码
		login(u1.getUsername(), password);// 登录成功， 此时使用旧密码还能登录，因为此时从缓存中获得身份验证信息
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());//// 清除指定帐户的AuthenticationInfo缓存条目
		login(u1.getUsername(), password);// 密码错误
		login(u1.getUsername(), password + "1");// 登录成功
	}

	// @Test
	public void testClearCachedAuthorizationInfo() {
		login(u1.getUsername(), password);
		System.out.println(subject().hasRole(r1.getRole()));// true
		userService.correlationRoles(u1.getId(), r2.getId());// 新增用户角色关系
		System.out.println(subject().hasRole(r2.getRole()));// false，由于此时从缓存中获得授权信息
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthorizationInfo(subject().getPrincipals());// 清除指定帐户的AuthorizationInfo缓存条目
		System.out.println(subject().hasRole(r2.getRole()));// true
	}

	@Test
	public void testClearCache() {
		login(u1.getUsername(), password);// 登录成功
		System.out.println(subject().hasRole(r1.getRole()));// true
		userService.changePassword(u1.getId(), password + "1");// 修改密码
		userService.correlationRoles(u1.getId(), r2.getId());// 新增用户角色关系
		System.out.println(subject().hasRole(r2.getRole()));// false
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		// 清除与指定的帐户身份/身份关联的所有缓存数据,继承于CachingRealm
		userRealm.clearCache(subject().getPrincipals());
		login(u1.getUsername(), password + "1");// 登录成功
		System.out.println(subject().hasRole(r2.getRole()));// true
	}

}