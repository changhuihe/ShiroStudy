package realm.dao;

import realm.entity.Permission;

/*
 * Ȩ��dao��ӿ�
 */
public interface PermissionDao {

	// ����
	public Permission createPermission(Permission permission);

	// ɾ��
	public void deletePermission(Long permissionId);

}
