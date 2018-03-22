package realm.service;

import realm.dao.PermissionDao;
import realm.dao.PermissionDaoImpl;
import realm.entity.Permission;

/*
 * Ȩ�޷����ʵ����
 */
public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao = new PermissionDaoImpl();

	/*
	 * ����Ȩ��
	 */
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	/*
	 * ɾ��Ȩ��
	 */
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
