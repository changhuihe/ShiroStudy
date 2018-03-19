package authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestPermission {

	@Test
	public void test() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_permission.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hch", "123");
		subject.login(token);
		// ������Դ����Ȩ��
		System.out.println("role1:" + subject.isPermitted("system:user:update"));
		// ������Դ���Ȩ��
		System.out.println("role2:" + subject.isPermittedAll("system:user:update", "system:user:delete"));
		System.out.println("role3:" + subject.isPermittedAll("system:user:update,delete"));
		// ������Դȫ��Ȩ��
		System.out.println("role4:" + subject.isPermittedAll("system:user:update,delete"));
		System.out.println("role4:" + subject.isPermittedAll("system:user:create,update,delete,view"));
		System.out.println("role5:" + subject.isPermitted("system:user:*"));
		// ������Դȫ��Ȩ��
		System.out.println("role6:" + subject.isPermitted("user:view"));
		System.out.println("role7:" + subject.isPermitted("system:user:view"));
		// ʵ�������Ȩ��
		System.out.println("role8:" + subject.isPermitted("user:view:1"));
		System.out.println("role9:" + subject.isPermitted("user:update,delete:1"));
		System.out.println("role9:" + subject.isPermittedAll("user:update:1", "user:delete:1"));
		System.out.println("role10:" + subject.isPermittedAll("user:update:1", "user:delete:1"));
		System.out.println("role11:" + subject.isPermittedAll("user:auth:1", "user:auth:2"));
		System.out.println("role12:" + subject.isPermittedAll("user:auth:1", "user:view:2"));
		// WildcardPermission,�������ַ�ʽ�ȼ�
		System.out.println("role12:" + subject.isPermitted("user:auth:1"));
		System.out.println("role12:" + subject.isPermitted(new WildcardPermission("user:auth:1")));
	}
}
