package session.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcTemplateUtils {
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
		ds.setUsername("root");
		ds.setPassword("");

		return new JdbcTemplate(ds);
	}

}
