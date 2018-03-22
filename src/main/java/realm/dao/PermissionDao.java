package realm.dao;

import realm.entity.Permission;

/*
 * 权限dao层接口
 */
public interface PermissionDao {

	// 新增
	public Permission createPermission(Permission permission);

	// 删除
	public void deletePermission(Long permissionId);

}
