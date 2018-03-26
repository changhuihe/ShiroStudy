package realm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import realm.entity.Role;
import realm.utils.JdbcTemplateUtils;

/*
 * ��ɫdao����ʵ����
 */
public class RoleDaoImpl implements RoleDao {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	// ����
	public Role createRole(final Role Role) {
		final String sql = "insert into sys_roles(role, description, available) values(?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				psst.setString(1, Role.getRole());
				psst.setString(2, Role.getDescription());
				psst.setBoolean(3, Role.getAvailable());
				return psst;
			}
		}, keyHolder);
		Role.setId(keyHolder.getKey().longValue());

		return Role;
	}

	// ɾ��
	public void deleteRole(Long roleId) {
		// ���ȰѺ�role��������ر�����ɾ��
		String sql = "delete from sys_users_roles where role_id=?";
		jdbcTemplate.update(sql, roleId);

		sql = "delete from sys_roles where id=?";
		jdbcTemplate.update(sql, roleId);
	}

	/*
	 * ��ӽ�ɫ-Ȩ��֮���ϵ
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
		for (Long permissionId : permissionIds) {
			if (!exists(roleId, permissionId)) {
				jdbcTemplate.update(sql, roleId, permissionId);
			}
		}
	}

	/*
	 * �Ƴ���ɫ-Ȩ��֮���ϵ
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
		for (Long permissionId : permissionIds) {
			if (exists(roleId, permissionId)) {
				jdbcTemplate.update(sql, roleId, permissionId);
			}
		}
	}

	private boolean exists(Long roleId, Long permissionId) {
		String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, roleId, permissionId) != 0;
	}

}
