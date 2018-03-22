package realm.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/*
 * ���ݿ⹤����
 */
public class JdbcTemplateUtils {
	/*
	 * JdbcTemplate��Spring��jdbc_api�϶����һ������㣬�Դ˽���һ��jdbc��ȡ��� JdbcTemplate��Ҫ�ṩ���෽����
	 * execute():��������ִ���κ�sql��һ������ִ��DDL��䡣
	 * update()��batchUpdate():�����������޸ģ�ɾ������䡣
	 * query()��queryForXXX():����ִ����ز�ѯ��
	 * call():����ִ�д洢���̡�����������
	 */
	private static JdbcTemplate jdbcTemplate;

	public static JdbcTemplate jdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = createJdbcTemplate();
		}
		return jdbcTemplate;
	}

	private static JdbcTemplate createJdbcTemplate() {

		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");
		ds.setUsername("hechanghui");
		ds.setPassword("100640");

		return new JdbcTemplate(ds);
	}

}
