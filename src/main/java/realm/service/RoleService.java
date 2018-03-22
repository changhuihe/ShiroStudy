package realm.service;

import realm.entity.Role;

/*
 * 角色服务层接口
 */
public interface RoleService {
	// 新增
	public Role createRole(Role role);

	// 删除
	public void deleteRole(Long roleId);

	/*
	 * 添加角色-权限之间关系
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/*
	 * 移除角色-权限之间关系
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
