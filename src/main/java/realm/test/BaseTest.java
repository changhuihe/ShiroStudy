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

		// 1������Ȩ��
		p1 = new Permission("user:create", "�û�ģ������", Boolean.TRUE);
		p2 = new Permission("user:update", "�û�ģ���޸�", Boolean.TRUE);
		p3 = new Permission("menu:create", "�˵�ģ������", Boolean.TRUE);
		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);
		// 2��������ɫ
		r1 = new Role("admin", "����Ա", Boolean.TRUE);
		r2 = new Role("user", "�û�����Ա", Boolean.TRUE);
		roleService.createRole(r1);
		roleService.createRole(r2);
		// 3��������ɫ-Ȩ��
		roleService.correlationPermissions(r1.getId(), p1.getId());
		roleService.correlationPermissions(r1.getId(), p2.getId());
		roleService.correlationPermissions(r1.getId(), p3.getId());

		roleService.correlationPermissions(r2.getId(), p1.getId());
		roleService.correlationPermissions(r2.getId(), p2.getId());

		// 4�������û�
		u1 = new User("zhang", password);
		u2 = new User("li", password);
		u3 = new User("wu", password);
		u4 = new User("wang", password);
		u4.setLocked(Boolean.TRUE);
		userService.createUser(u1);
		userService.createUser(u2);
		userService.createUser(u3);
		userService.createUser(u4);
		// 5�������û�-��ɫ
		userService.correlationRoles(u1.getId(), r1.getId());
	}

	@After
	public void tearDown() {
		ThreadContext.unbindSubject();// �˳�ʱ������Subject���̣߳�������´β������Ӱ��
	}

	protected void login(String configFile, String username, String password) {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			System.out.println("�����֤�ɹ�");
		} catch (ConcurrentAccessException e) {
			System.out.println("���û����ڵ�¼!");
		} catch (LockedAccountException e) {
			System.out.println("���û�������!");
		} catch (ExcessiveAttemptsException e) {
			System.out.println("��¼ʧ�ܴ�������!");
		} catch (UnknownAccountException e) {
			System.out.println("���û�������!");
		} catch (ExpiredCredentialsException e) {
			System.out.println("���ڵ�ƾ֤!");
		} catch (IncorrectCredentialsException e) {
			System.out.println("�����ƾ֤!");
		} catch (AuthenticationException e) {
			System.out.println("��¼�쳣");
		}
	}

	public Subject subject() {
		return SecurityUtils.getSubject();
	}

}
