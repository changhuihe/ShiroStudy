package authorizer;

import org.apache.shiro.authz.Permission;

/*
 * �Զ���PermissionResolver���ڽ���Ȩ���ַ�����Permissionʵ��
 */
public class BitAndWildPermissionResolver implements Permission {

	private String resourceIdentify;
	private int permissionBit;
	private String instanceId;

	public boolean implies(Permission arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
