package codeEncryption;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/*
 * 散列算法：一般用于生产数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，
 * 常见的散列算法如MD5、SHA等。一般进行散列时最好提供一个salt（盐），比如加密密码 “admin”，
 * 产生的散列值是 “21232f297a57a5a743894a0e4a801fc3”，可以到一些 md5 解密网站很容易的通过散
 * 列值得到密码 “admin”，即如果直接对密码进行散列相对来说破解更容易，此时我们可以加一些只有系统知道的干扰数据，
 * 如用户名和 ID（即盐）；这样散列的对象是 “密码 + 用户名 +ID”，这样生成的散列值相对来说更难破解。
 */
public class HashAlgorithm {
	// @Test
	public void test() {
		String str = "hello";
		String salt = "123";
		// md:三个参数：明文、盐、次数
		System.out.println("md2:" + new Md2Hash(str, salt).toString());// 还可以转换为toBase64(),toHex()
		System.out.println("md5:" + new Md5Hash(str, salt).toString());
		// sha:三个参数：明文、盐、次数
		System.out.println("sha1:" + new Sha1Hash(str, salt).toString());
		System.out.println("sha256:" + new Sha256Hash(str, salt).toString());
		System.out.println("sha384:" + new Sha384Hash(str, salt).toString());
		System.out.println("sha512:" + new Sha512Hash(str, salt).toString());
		// 通用散列支持：三个参数：明文、盐、次数
		System.out.println("md2:" + new SimpleHash("Md2", str, salt).toString());
		System.out.println("md5:" + new SimpleHash("Md5", str, salt).toString());
		System.out.println("SHA-1:" + new SimpleHash("SHA-1", str, salt).toString());
		System.out.println("SHA-256:" + new SimpleHash("SHA-256", str, salt).toString());
		System.out.println("SHA-384:" + new SimpleHash("SHA-384", str, salt).toString());
		System.out.println("SHA-512:" + new SimpleHash("SHA-512", str, salt).toString());
	}

	/*
	 * 用于在新增用户/修改密码时使用，将生成的密码及salt2存入数据库
	 */
	@Test
	public void test2() {
		String algorithmName = "md5";// 加密方式
		String username = "liu";
		String password = "123";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
	}

}
