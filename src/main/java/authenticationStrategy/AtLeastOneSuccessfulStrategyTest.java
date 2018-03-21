package authenticationStrategy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AtLeastOneSuccessfulStrategyTest {

	/*
	 * 2、AtLeastOneSuccessfulStrategy：只要有一个 Realm 验证成功即可，和 FirstSuccessfulStrategy
	 * 不同，返回所有 Realm 身份验证成功的认证信息
	 */
	@Test
	public void testAtLeastOneSuccessfulStrategy() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:atLeastOneSuccessfulStrategy_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zxh", "123");
		subject.login(token);
		// 得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection = subject.getPrincipals();
		System.out.println(principalCollection);
	}
}
