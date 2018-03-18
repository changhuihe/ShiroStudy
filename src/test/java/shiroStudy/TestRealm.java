package shiroStudy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class TestRealm {

	// @Test
	public void testHch() {
		/*
		 * 1、获取securityManager工厂，使用Ini配置文件初始化SecurityManager [users] 部分指定用户名 / 密码及其角色
		 * [roles] 部分指定角色即权限信息
		 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		/*
		 * 2、解析配置文件，得到SecurityManager实例
		 */
		SecurityManager securityManager = factory.getInstance();
		/*
		 * 3、设置SecurityManager到静态内存区，单例模式
		 */
		SecurityUtils.setSecurityManager(securityManager);
		/*
		 * 4、获取Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		 */
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		try {
			/*
			 * 5、登录，即身份验证
			 */
			subject.login(token);
			System.out.println("身份验证成功");
			/*
			 * AuthenticationException身份认证异常的父类
			 */
		} catch (ConcurrentAccessException e) {
			System.out.println("此用户正在登录!");
		} catch (LockedAccountException e) {
			System.out.println("此用户已锁定!");
		} catch (ExcessiveAttemptsException e) {
			System.out.println("登录失败次数过多!");
		} catch (UnknownAccountException e) {
			System.out.println("此用户不存在!");
		} catch (ExpiredCredentialsException e) {
			System.out.println("过期的凭证!");
		} catch (IncorrectCredentialsException e) {
			System.out.println("错误的凭证!");
		} catch (AuthenticationException e) {
			System.out.println("登录异常");
		}
		/*
		 * 7、退出
		 */
		subject.logout();
	}

	/*
	 * 单Realm测试
	 */
	// @Test
	public void testSingleRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_singleRealm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("ch", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		subject.logout();
	}

	/*
	 * 多Realm测试 securityManager会按照realms指定的顺序进行身份验证，此处使用显示的指定顺序的方式指定了Realm的顺序，如果删除'
	 * securityManager.realms=$singleRealm,$singleRealm2'，
	 * 那么securityManager会按照realm声明的顺序进行使用，当使用显示指定Realm后，其他没有指定的Realm将被忽略
	 */
	// @Test
	public void testMultipelRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_multipleRealm.ini");
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

	/*
	 * 测试默认的JdbcRealm
	 */
	@Test
	public void testJDBCRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_jdbcRealm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("ch", "123");
		try {
			subject.login(token);
			System.out.println("验证成功");
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		subject.logout();
	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}
}
