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
		// 因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
		login("classpath:multiplerealm_shiro.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		// 获取Primary Principal（即第一个）
		Object primaryPrincipal1 = subject.getPrincipal();// 返回此Subject的应用程序范围内唯一标识主体
		PrincipalCollection princialCollection = subject.getPrincipals();// 以PrincipalCollection或者 null如果此Subject是匿名的
		Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();// 返回在整个应用程序中使用的主要主体
		// 但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
		System.out.println(primaryPrincipal1.equals(primaryPrincipal2));

		// 返回 a b c
		Set<String> realmNames = princialCollection.getRealmNames();// 获取所有身份验证通过的Realm名字
		System.out.println(realmNames);

		// 因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
		Set<Object> principals = princialCollection.asSet(); // 转为asList和转为asSet的结果一样
		System.out.println(principals);

		// 根据Realm名字获取
		Collection<User> users = princialCollection.fromRealm("c");/// 根据Realm名字获取
		System.out.println(users);
	}

}
