package realm.service;

import realm.entity.Permission;

/*
 * Ȩ�޷����ӿ�
 */
public interface PermissionService {
	// ����
	public Permission createPermission(Permission permission);

	// ɾ��
	public void deletePermission(Long permissionId);

}
