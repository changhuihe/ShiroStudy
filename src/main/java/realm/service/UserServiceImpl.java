package realm.service;

import java.util.Set;

import realm.dao.UserDao;
import realm.entity.User;
import realm.utils.PasswordHelper;

/*
 * �û������ʵ����
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private PasswordHelper passwordHelper;

	/*
	 * �����û�
	 */
	public User createUser(User user) {
		// ��������
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	/*
	 * �޸�����
	 */
	public void changePassword(Long userId, String newPassword) {
		User user = userDao.findOne(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	/*
	 * ����û�-��ɫ��ϵ
	 */
	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);
	}

	/*
	 * �Ƴ��û�-��ɫ��ϵ
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);
	}

	/*
	 * �����û��������û�
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/*
	 * �����û����������ɫ
	 */
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	/*
	 * �����û���������Ȩ��
	 */
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}

}
