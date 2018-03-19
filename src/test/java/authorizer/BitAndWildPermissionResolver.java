package authorizer;

import org.apache.shiro.authz.Permission;

/*
 * 自定义PermissionResolver用于解析权限字符串到Permission实例
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
