package userDefinedRealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/*
 * 单Realm配置，集成Realm接口，实现其三个方法
 */
public class UserDefinedRealm1 implements Realm {

	/*
	 * 根据Token获认证信息
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();// 得到用户名
		String password = new String((char[]) token.getCredentials());// 得到密码
		if (!"hch".equals(username)) {
			System.out.println("用户名错误");
			throw new UnknownAccountException();// 用户名错误
		}
		if (!"123".equals(password)) {
			System.out.println("密码错误");
			throw new IncorrectCredentialsException();// 密码错误
		}
		System.out.println("认证成功");
		// 如果身份认证成功，返回一个AuthenticationInfo实现
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	/*
	 * 返回一个唯一的Realm名字
	 */
	public String getName() {
		return "singleRealm";
	}

	/*
	 * 判断此Realm是否支持此Token
	 */
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

}
