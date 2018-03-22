package realm.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/*
 * 数据库工具类
 */
public class JdbcTemplateUtils {
	/*
	 * JdbcTemplate是Spring在jdbc_api上定义的一个抽象层，以此建立一个jdbc存取框架 JdbcTemplate主要提供五类方法：
	 * execute():可以用于执行任何sql，一般用于执行DDL语句。
	 * update()、batchUpdate():用于新增，修改，删除等语句。
	 * query()、queryForXXX():用于执行相关查询。
	 * call():用于执行存储过程、函数相关语句
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
