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
	 * 多Realm测试 securityManager会按照realms指定的顺序进行身份验证，此处使用显示的指定顺序的方式指定了Realm的顺序，如果删除'
	 * securityManager.realms=$singleRealm,$singleRealm2'，
	 * 那么securityManager会按照realm声明的顺序进行使用，当使用显示指定Realm后，其他没有指定的Realm将被忽略
	 */
	@Test
	public void testMultipelRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:userDefinedMultipleRealm_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		/*
		 * 进行多个认证
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
