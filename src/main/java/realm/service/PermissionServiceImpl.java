package realm.service;

import realm.dao.PermissionDao;
import realm.entity.Permission;

/*
 * 权限服务层实现类
 */
public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao;

	/*
	 * 新增权限
	 */
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	/*
	 * 删除权限
	 */
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
