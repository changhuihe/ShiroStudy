package defaultJdbcRealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class DefaultJdbcRealm {
	/*
	 * 测试默认的JdbcRealm
	 */
	@Test
	public void testJDBCRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:defaultJdbcRealm_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		try {
			subject.login(token);
			System.out.println("验证成功");
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		subject.logout();
	}
}
