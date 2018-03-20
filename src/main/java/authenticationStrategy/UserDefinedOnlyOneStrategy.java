package authenticationStrategy;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

/*
 * �Զ���AuthenticationStrategyʵ��
 */
public class UserDefinedOnlyOneStrategy extends AbstractAuthenticationStrategy {

	// ������Realm��֤֮ǰ����
	@Override
	public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		return new SimpleAuthenticationInfo();// ����һ��Ȩ�޵���֤��Ϣ
	}

	// ��ÿ��Realm֮ǰ����
	@Override
	public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		return aggregate;
	}

	// ��ÿ��Realm֮�����
	@Override
	public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
		AuthenticationInfo info;
		if (singleRealmInfo == null) {
			info = aggregateInfo;
		} else {
			if (aggregateInfo == null) {
				info = singleRealmInfo;
			} else {
				info = merge(singleRealmInfo, aggregateInfo);
				if (info.getPrincipals().getRealmNames().size() > 1) {
					System.out.println(info.getPrincipals().getRealmNames());
					throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] "
							+ "could not be authenticated by any configured realms.  Please ensure that only one realm can "
							+ "authenticate these tokens.");
				}
			}
		}
		return info;
	}

	// ������Realm֮�����
	@Override
	public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		return aggregate;
	}

}
