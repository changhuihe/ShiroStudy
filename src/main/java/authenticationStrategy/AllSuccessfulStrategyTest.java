package authenticationStrategy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AllSuccessfulStrategyTest {
	/*
	 * 3��AllSuccessfulStrategy������ Realm ��֤�ɹ�����ɹ����ҷ������� Realm ������֤�ɹ�����֤��Ϣ�������һ��ʧ�ܾ�ʧ����
	 */
	@Test
	public void testAllSuccessfulStrategy() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:allSuccessfulStrategy_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
		// �õ�һ�����ݼ��ϣ��������Realm��֤�ɹ���������Ϣ
		PrincipalCollection principalCollection = subject.getPrincipals();
		System.out.println(principalCollection);
	}
}