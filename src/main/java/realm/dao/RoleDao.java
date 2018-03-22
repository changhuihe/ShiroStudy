package realm.dao;

import realm.entity.Role;

/*
 * ��ɫdao��
 */
public interface RoleDao {
	// ����
	public Role createRole(Role role);

	// ɾ��
	public void deleteRole(Long roleId);

	/*
	 * ��ӽ�ɫ-Ȩ��֮���ϵ
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/*
	 * �Ƴ���ɫ-Ȩ��֮���ϵ
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
