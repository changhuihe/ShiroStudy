package userDefinedRealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class UserDefinedMultipleRealmTest {
	/*
	 * ��Realm���� securityManager�ᰴ��realmsָ����˳����������֤���˴�ʹ����ʾ��ָ��˳��ķ�ʽָ����Realm��˳�����ɾ��'
	 * securityManager.realms=$singleRealm,$singleRealm2'��
	 * ��ôsecurityManager�ᰴ��realm������˳�����ʹ�ã���ʹ����ʾָ��Realm������û��ָ����Realm��������
	 */
	@Test
	public void testMultipelRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:userDefinedMultipleRealm_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		/*
		 * ���ж����֤
		 */
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		subject.logout();
	}
}
