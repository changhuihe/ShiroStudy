package realm.dao;

import java.util.Set;

import realm.entity.User;

/*
 * 用户dao层接口
 */
public interface UserDao {
	/*
	 * 创建用户
	 */
	public User createUser(User user);

	/*
	 * 修改密码
	 */
	public void updateUser(User user);

	public void deleteUser(Long userId);

	public void correlationRoles(Long userId, Long... roleIds);

	public void uncorrelationRoles(Long userId, Long... roleIds);

	User findOne(Long userId);

	User findByUsername(String username);

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);

}
