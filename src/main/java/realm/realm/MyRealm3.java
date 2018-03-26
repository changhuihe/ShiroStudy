package realm.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import realm.entity.User;

public class MyRealm3 implements Realm {

	/*
	 * 返回一个唯一的Realm名字
	 */
	public String getName() {
		return "c";// realm的名字为“c”
	}

	/*
	 * 判断此Realm是否支持此Token
	 */
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	/*
	 * 根据Token获认证信息
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		User user = new User("zhang", "123");
		return new SimpleAuthenticationInfo(user, "123", getName());
	}

}
