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
	 * Authenticator、AuthenticationStrategy, Authenticator
	 * 的职责是验证用户帐号，如果验证成功，将返回AuthenticationInfo验证信息，此信息中包含了身份及凭证，
	 * 如果验证失败将抛出相应的AuthenticationException实现。
	 * SecurityManager接口继承了Authenticator,另外还有一个ModularRealmAuthenticator实现，其委托给多个
	 * Realm 进行验证，验证规则通过 AuthenticationStrategy
	 * 接口指定，默认提供的实现：1、FirstSuccessfulStrategy：只要有一个 Realm 验证成功即可，只返回第一个 Realm
	 * 身份验证成功的认证信息，其他的忽略； 2、AtLeastOneSuccessfulStrategy：只要有一个 Realm 验证成功即可，和
	 * FirstSuccessfulStrategy 不同，返回所有 Realm 身份验证成功的认证信息； 3、AllSuccessfulStrategy：所有
	 * Realm 验证成功才算成功，且返回所有 Realm 身份验证成功的认证信息，如果有一个失败就失败了。 ModularRealmAuthenticator
	 * 默认使用 AtLeastOneSuccessfulStrategy策略。
	 */
	@Test
	public void testFirstSuccessfulStrategy() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:firstSuccessfulStrategy_shiro.ini");
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
