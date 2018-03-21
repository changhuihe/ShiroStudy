package authenticationStrategy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/*
 * �Զ�����֤���Բ���
 */
public class UserDefinedOnlyOneStrategyTest {
	@Test
	public void testOnlyOneStrategy() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:userDefinedOnlyOneStrategy_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
		// �õ�һ����ݼ��ϣ��������Realm��֤�ɹ��������Ϣ
		PrincipalCollection principalCollection = subject.getPrincipals();
		System.out.println(principalCollection);
	}
}
