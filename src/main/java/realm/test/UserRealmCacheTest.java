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
		login(u1.getUsername(), password);// ��¼�ɹ��������֤�ᱣ���ڻ���
		userService.changePassword(u1.getId(), password + "1");// �޸�����
		login(u1.getUsername(), password);// ��¼�ɹ��� ��ʱʹ�þ����뻹�ܵ�¼����Ϊ��ʱ�ӻ����л�������֤��Ϣ
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());//// ���ָ���ʻ���AuthenticationInfo������Ŀ
		login(u1.getUsername(), password);// �������
		login(u1.getUsername(), password + "1");// ��¼�ɹ�
	}

	// @Test
	public void testClearCachedAuthorizationInfo() {
		login(u1.getUsername(), password);
		System.out.println(subject().hasRole(r1.getRole()));// true
		userService.correlationRoles(u1.getId(), r2.getId());// �����û���ɫ��ϵ
		System.out.println(subject().hasRole(r2.getRole()));// false�����ڴ�ʱ�ӻ����л����Ȩ��Ϣ
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthorizationInfo(subject().getPrincipals());// ���ָ���ʻ���AuthorizationInfo������Ŀ
		System.out.println(subject().hasRole(r2.getRole()));// true
	}

	@Test
	public void testClearCache() {
		login(u1.getUsername(), password);// ��¼�ɹ�
		System.out.println(subject().hasRole(r1.getRole()));// true
		userService.changePassword(u1.getId(), password + "1");// �޸�����
		userService.correlationRoles(u1.getId(), r2.getId());// �����û���ɫ��ϵ
		System.out.println(subject().hasRole(r2.getRole()));// false
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		// �����ָ�����ʻ����/��ݹ��������л�������,�̳���CachingRealm
		userRealm.clearCache(subject().getPrincipals());
		login(u1.getUsername(), password + "1");// ��¼�ɹ�
		System.out.println(subject().hasRole(r2.getRole()));// true
	}

}