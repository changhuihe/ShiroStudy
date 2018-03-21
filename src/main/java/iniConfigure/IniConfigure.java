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
 * 使用纯Java代码实现等价于iniConfigure_shiro.ini配置文件
 */
public class IniConfigure {
	@Test
	public void testIniConfigure() {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		// 设置authenticator(身份验证器)
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());// 设置认证策略
		securityManager.setAuthenticator(authenticator);
		// 设置authorizer(授权器)
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());// 设置权限解析
		securityManager.setAuthorizer(authorizer);
		// 设置Realm
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");
		ds.setUsername("hechanghui");
		ds.setPassword("100640");
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);// 开启权限查询
		securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));
		// 将SecurityManager设置到SecurityUtils方便全局使用
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
	}

}
