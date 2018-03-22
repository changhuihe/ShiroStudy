package realm.dao;

import java.util.Set;

import realm.entity.User;

/*
 * �û�dao��ӿ�
 */
public interface UserDao {
	/*
	 * �����û�
	 */
	public User createUser(User user);

	/*
	 * �޸�����
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
