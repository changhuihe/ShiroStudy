package realm.service;

import realm.dao.RoleDao;
import realm.entity.Role;

/*
 * 角色服务层实现类
 */
public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;

	// 新增
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	// 删除
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	/*
	 * 添加角色-权限之间关系
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	/*
	 * 移除角色-权限之间关系
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}

}
