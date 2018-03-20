package userDefinedAuthorization;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/*
 * RolePermissionResolver���ڸ��ݽ�ɫ�ַ����������õ�Ȩ�޼���
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if ("role1".equals(roleString)) {
			return Arrays.asList((Permission) new WildcardPermission("menu:*"));
		}
		return null;
	}
}
