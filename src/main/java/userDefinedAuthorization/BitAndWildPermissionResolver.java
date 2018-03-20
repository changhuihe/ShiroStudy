package userDefinedAuthorization;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/*
 * PermissionResolver��������Ȩ���ַ�����Permissionʵ��
 */
public class BitAndWildPermissionResolver implements PermissionResolver {
	public Permission resolvePermission(String permissionString) {
		if (permissionString.startsWith("+")) {
			return new BitPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}
}
