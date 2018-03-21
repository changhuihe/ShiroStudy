package authentication;

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
import org.junit.Test;

/*
 * 第一个身份认证测试
 */
public class FirstAuthenticationTest {
	@Test
	public void testHch() {
		/*
		 * 1、获取securityManager工厂，使用Ini配置文件初始化SecurityManager [users] 部分指定用户名 / 密码及其角色
		 * [roles] 部分指定角色即权限信息
		 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:firstAuththentication_shiro.ini");
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
}
