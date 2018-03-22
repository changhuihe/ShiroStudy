package realm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import realm.entity.Permission;

/*
 * Ȩ��dao��ʵ����
 */
public class PermissionDaoImpl implements PermissionDao {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	// ����
	public Permission createPermission(final Permission permission) {
		final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";
		// ����GeneratedKeyHolder����½���������ֵ
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		// PreparedStatement��ʾԤ�����SQL������SQL��䱻Ԥ���벢�洢��PreparedStatement�����У�Ȼ�����ʹ�ô˶����θ�Ч��ִ�и����
		// Connection.perpareStatement(String sql , String[]
		// columnNames)����һ���ܷ����ɸ�������ָ�����Զ����ɼ���Ĭ��PreparedStatement����
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				psst.setString(1, permission.getPermission());
				psst.setString(2, permission.getDescription());
				psst.setBoolean(3, permission.getAvailable());
				return psst;
			}
		}, keyHolder);
		permission.setId(keyHolder.getKey().longValue());

		return permission;
	}

	// ɾ��
	public void deletePermission(Long permissionId) {
		// ���Ȱ���permission��������ر������ɾ��
		String sql = "delete from sys_roles_permissions where permission_id=?";
		jdbcTemplate.update(sql, permissionId);

		sql = "delete from sys_permissions where id=?";
		jdbcTemplate.update(sql, permissionId);
	}

}
