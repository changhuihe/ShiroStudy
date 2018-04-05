package realm.service;

import java.util.Set;

import realm.dao.UserDao;
import realm.entity.User;
import realm.utils.PasswordHelper;

/*
 * 用户服务层实现类
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private PasswordHelper passwordHelper;

	/*
	 * 创建用户
	 */
	public User createUser(User user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	/*
	 * 修改密码
	 */
	public void changePassword(Long userId, String newPassword) {
		User user = userDao.findOne(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	/*
	 * 添加用户-角色关系
	 */
	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);
	}

	/*
	 * 移除用户-角色关系
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);
	}

	/*
	 * 根据用户名查找用户
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/*
	 * 根据用户名查找其角色
	 */
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	/*
	 * 根据用户名查找其权限
	 */
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}

}
