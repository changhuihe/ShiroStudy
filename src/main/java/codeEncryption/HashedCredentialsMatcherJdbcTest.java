package codeEncryption;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class HashedCredentialsMatcherJdbcTest {
	@Test
	public void test() {
		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:hashedCredentialsMatcher_jdbc_shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("liu", "23");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		subject.logout();
	}

	private class EnumConverter extends AbstractConverter {
		@Override
		protected String convertToString(final Object value) throws Throwable {
			return ((Enum) value).name();
		}

		@Override
		protected Object convertToType(final Class type, final Object value) throws Throwable {
			return Enum.valueOf(type, value.toString());
		}

		@Override
		protected Class getDefaultType() {
			return null;
		}

	}
}
