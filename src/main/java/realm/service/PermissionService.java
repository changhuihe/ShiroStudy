package realm.service;

import realm.entity.Permission;

/*
 * 权限服务层接口
 */
public interface PermissionService {
	// 新增
	public Permission createPermission(Permission permission);

	// 删除
	public void deletePermission(Long permissionId);

}
