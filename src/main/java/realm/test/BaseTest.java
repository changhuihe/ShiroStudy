package realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

import realm.entity.Permission;
import realm.entity.Role;
import realm.entity.User;
import realm.service.PermissionService;
import realm.service.PermissionServiceImpl;
import realm.service.RoleService;
import realm.service.RoleServiceImpl;
import realm.service.UserService;
import realm.service.UserServiceImpl;
import realm.utils.JdbcTemplateUtils;

public abstract class BaseTest {
	protected PermissionService permissionService = new PermissionServiceImpl();
	protected RoleService roleService = new RoleServiceImpl();
	protected UserService userService = new UserServiceImpl();

	protected String password = "123";

	protected Permission p1;
	protected Permission p2;
	protected Permission p3;

	protected Role r1;
	protected Role r2;

	protected User u1;
	protected User u2;
	protected User u3;
	protected User u4;

	@Before
	public void setUp() {
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users");
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles");
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_permissions");
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users_roles");
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles_permissions");

		// 1、新增权限
		p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
		p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
		p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);
		// 2、新增角色
		r1 = new Role("admin", "管理员", Boolean.TRUE);
		r2 = new Role("user", "用户管理员", Boolean.TRUE);
		roleService.createRole(r1);
		roleService.createRole(r2);
		// 3、关联角色-权限
		roleService.correlationPermissions(r1.getId(), p1.getId());
		roleService.correlationPermissions(r1.getId(), p2.getId());
		roleService.correlationPermissions(r1.getId(), p3.getId());

		roleService.correlationPermissions(r2.getId(), p1.getId());
		roleService.correlationPermissions(r2.getId(), p2.getId());

		// 4、新增用户
		u1 = new User("zhang", password);
		u2 = new User("li", password);
		u3 = new User("wu", password);
		u4 = new User("wang", password);
		u4.setLocked(Boolean.TRUE);
		userService.createUser(u1);
		userService.createUser(u2);
		userService.createUser(u3);
		userService.createUser(u4);
		// 5、关联用户-角色
		userService.correlationRoles(u1.getId(), r1.getId());
	}

	@After
	public void tearDown() {
		ThreadContext.unbindSubject();// 退出时清解除绑定Subject到线程，否则对下次测试造成影响
	}

	protected void login(String configFile, String username, String password) {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			System.out.println("身份验证成功");
		} catch (ConcurrentAccessException e) {
			System.out.println("此用户正在登录!");
		} catch (LockedAccountException e) {
			System.out.println("此用户已锁定!");
		} catch (ExcessiveAttemptsException e) {
			System.out.println("登录失败次数过多!");
		} catch (UnknownAccountException e) {
			System.out.println("此用户不存在!");
		} catch (ExpiredCredentialsException e) {
			System.out.println("过期的凭证!");
		} catch (IncorrectCredentialsException e) {
			System.out.println("错误的凭证!");
		} catch (AuthenticationException e) {
			System.out.println("登录异常");
		}
	}

	public Subject subject() {
		return SecurityUtils.getSubject();
	}

}
