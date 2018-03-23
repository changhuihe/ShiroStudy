package realm.test;

import org.junit.Test;

import realm.entity.Permission;
import realm.entity.Role;
import realm.entity.User;
import realm.service.PermissionServiceImpl;
import realm.service.RoleServiceImpl;
import realm.service.UserServiceImpl;

public class ServiceTest {
	@Test
	public void test() {
		Permission permission = new Permission("user:create", "新增", false);
		PermissionServiceImpl permissionService = new PermissionServiceImpl();
		permission = permissionService.createPermission(permission);
		System.out.println(permission);

		Role role = new Role("admin", "管理员", false);
		RoleServiceImpl roleService = new RoleServiceImpl();
		role = roleService.createRole(role);
		System.out.println(role);
		roleService.correlationPermissions(role.getId(), permission.getId());

		User user = new User();
		user.setUsername("hch");
		user.setPassword("123");
		user.setLocked(true);
		UserServiceImpl userService = new UserServiceImpl();
		user = userService.createUser(user);
		System.out.println(user);
		userService.correlationRoles(user.getId(), role.getId());
	}

}
