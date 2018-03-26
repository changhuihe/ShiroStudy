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
	 * ����һ��Ψһ��Realm����
	 */
	public String getName() {
		return "c";// realm������Ϊ��c��
	}

	/*
	 * �жϴ�Realm�Ƿ�֧�ִ�Token
	 */
	public boolean supports(AuthenticationToken token) {
		// ��֧��UsernamePasswordToken���͵�Token
		return token instanceof UsernamePasswordToken;
	}

	/*
	 * ����Token����֤��Ϣ
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		User user = new User("zhang", "123");
		return new SimpleAuthenticationInfo(user, "123", getName());
	}

}
