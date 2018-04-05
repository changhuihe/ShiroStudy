package realm.service;

import realm.dao.RoleDao;
import realm.entity.Role;

/*
 * ��ɫ�����ʵ����
 */
public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;

	// ����
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	// ɾ��
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	/*
	 * ��ӽ�ɫ-Ȩ��֮���ϵ
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	/*
	 * �Ƴ���ɫ-Ȩ��֮���ϵ
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}

}
