package authenticationStrategy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class FirstSuccessfulStrategyTest {
	/*
	 * Authenticator��AuthenticationStrategy, Authenticator
	 * ��ְ������֤�û��ʺţ������֤�ɹ���������AuthenticationInfo��֤��Ϣ������Ϣ�а�������ݼ�ƾ֤��
	 * �����֤ʧ�ܽ��׳���Ӧ��AuthenticationExceptionʵ�֡�
	 * SecurityManager�ӿڼ̳���Authenticator,���⻹��һ��ModularRealmAuthenticatorʵ�֣���ί�и����
	 * Realm ������֤����֤����ͨ�� AuthenticationStrategy
	 * �ӿ�ָ����Ĭ���ṩ��ʵ�֣�1��FirstSuccessfulStrategy��ֻҪ��һ�� Realm ��֤�ɹ����ɣ�ֻ���ص�һ�� Realm
	 * �����֤�ɹ�����֤��Ϣ�������ĺ��ԣ� 2��AtLeastOneSuccessfulStrategy��ֻҪ��һ�� Realm ��֤�ɹ����ɣ���
	 * FirstSuccessfulStrategy ��ͬ���������� Realm �����֤�ɹ�����֤��Ϣ�� 3��AllSuccessfulStrategy������
	 * Realm ��֤�ɹ�����ɹ����ҷ������� Realm �����֤�ɹ�����֤��Ϣ�������һ��ʧ�ܾ�ʧ���ˡ� ModularRealmAuthenticator
	 * Ĭ��ʹ�� AtLeastOneSuccessfulStrategy���ԡ�
	 */
	@Test
	public void testFirstSuccessfulStrategy() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:firstSuccessfulStrategy_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zxh", "123");
		subject.login(token);
		// �õ�һ����ݼ��ϣ��������Realm��֤�ɹ��������Ϣ
		PrincipalCollection principalCollection = subject.getPrincipals();
		System.out.println(principalCollection);
	}
}
