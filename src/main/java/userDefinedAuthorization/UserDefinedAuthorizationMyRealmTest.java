package userDefinedAuthorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class UserDefinedAuthorizationMyRealmTest {
	@Test
	public void testUserDefinedAuthorizationMyRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:userDefinedAuthorization_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
		// �ж�ӵ��Ȩ�ޣ�user:create
		System.out.println(subject.isPermitted("user1:update"));
		System.out.println(subject.isPermitted("user2:update"));
		System.out.println(subject.isPermitted("+user1+2"));// ����Ȩ��
		System.out.println(subject.isPermitted("+user1+8"));// �鿴
		System.out.println(subject.isPermitted("+user2+10"));// �������鿴
		System.out.println(subject.isPermitted("+user1+4"));// û��ɾ��
		System.out.println(subject.isPermitted("menu:view"));// ͨ��MyRolePermissionResolver�����õ���Ȩ��
	}

}
