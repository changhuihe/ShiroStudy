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
 * ��Realm���ã�����Realm�ӿڣ�ʵ������������
 */
public class UserDefinedRealm1 implements Realm {

	/*
	 * ����Token����֤��Ϣ
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();// �õ��û���
		String password = new String((char[]) token.getCredentials());// �õ�����
		if (!"hch".equals(username)) {
			System.out.println("�û�������");
			throw new UnknownAccountException();// �û�������
		}
		if (!"123".equals(password)) {
			System.out.println("�������");
			throw new IncorrectCredentialsException();// �������
		}
		System.out.println("��֤�ɹ�");
		// ��������֤�ɹ�������һ��AuthenticationInfoʵ��
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	/*
	 * ����һ��Ψһ��Realm����
	 */
	public String getName() {
		return "singleRealm";
	}

	/*
	 * �жϴ�Realm�Ƿ�֧�ִ�Token
	 */
	public boolean supports(AuthenticationToken token) {
		// ��֧��UsernamePasswordToken���͵�Token
		return token instanceof UsernamePasswordToken;
	}

}
