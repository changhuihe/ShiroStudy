package userDefinedAuthorization;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

	/*
	 * �����û���ݻ�ȡ��Ȩ��Ϣ
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("role1");
		authorizationInfo.addRole("role2");
		authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
		authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
		authorizationInfo.addStringPermission("+user2+10");
		authorizationInfo.addStringPermission("user2:*");
		return authorizationInfo;
	}

	/*
	 * ��ȡ�����֤��Ϣ
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
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

}
