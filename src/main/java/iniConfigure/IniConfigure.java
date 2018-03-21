package iniConfigure;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/*
 * ʹ�ô�Java����ʵ�ֵȼ���iniConfigure_shiro.ini�����ļ�
 */
public class IniConfigure {
	@Test
	public void testIniConfigure() {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		// ����authenticator(�����֤��)
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());// ������֤����
		securityManager.setAuthenticator(authenticator);
		// ����authorizer(��Ȩ��)
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());// ����Ȩ�޽���
		securityManager.setAuthorizer(authorizer);
		// ����Realm
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");
		ds.setUsername("hechanghui");
		ds.setPassword("100640");
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);// ����Ȩ�޲�ѯ
		securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));
		// ��SecurityManager���õ�SecurityUtils����ȫ��ʹ��
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
	}

}
