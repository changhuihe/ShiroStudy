package realm.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

	/*
	 * ����һ��Ψһ��Realm����
	 */
	public String getName() {
		return "b";// realm������Ϊ��b��
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
		return new SimpleAuthenticationInfo("zhang", "123", getName());
	}

}
