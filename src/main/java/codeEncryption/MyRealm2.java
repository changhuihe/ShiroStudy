package codeEncryption;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm2 extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken)
			throws AuthenticationException {
		String username = "liu"; // �û�����salt1
		String salt2 = "0072273a5d87322163795118fdd7c45e";
		String password = "be320beca57748ab9632c4121ccac0db"; // ���ܺ������
		SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, password, getName());
		ai.setCredentialsSalt(ByteSource.Util.bytes(username + salt2)); // �����û���+�����
		return ai;
	}

}
