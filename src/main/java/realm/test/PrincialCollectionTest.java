package realm.test;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import realm.entity.User;

public class PrincialCollectionTest extends BaseTest {
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		// ��ΪRealm��û�н�����֤�������൱��ÿ��Realm�������֤�ɹ���
		login("classpath:multiplerealm_shiro.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		// ��ȡPrimary Principal������һ����
		Object primaryPrincipal1 = subject.getPrincipal();// ���ش�Subject��Ӧ�ó���Χ��Ψһ��ʶ����
		PrincipalCollection princialCollection = subject.getPrincipals();// ��PrincipalCollection���� null�����Subject��������
		Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();// ����������Ӧ�ó�����ʹ�õ���Ҫ����
		// ������Ϊ���Realm��������Principal�����Դ˴��������ĸ��ǲ�ȷ����
		System.out.println(primaryPrincipal1.equals(primaryPrincipal2));

		// ���� a b c
		Set<String> realmNames = princialCollection.getRealmNames();// ��ȡ���������֤ͨ����Realm����
		System.out.println(realmNames);

		// ��ΪMyRealm1��MyRealm2���ص�ƾ�ݶ���zhang������������
		Set<Object> principals = princialCollection.asSet(); // תΪasList��תΪasSet�Ľ��һ��
		System.out.println(principals);

		// ����Realm���ֻ�ȡ
		Collection<User> users = princialCollection.fromRealm("c");/// ����Realm���ֻ�ȡ
		System.out.println(users);
	}

}
