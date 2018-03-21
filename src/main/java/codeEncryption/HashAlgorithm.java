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
 * ɢ���㷨��һ�������������ݵ�ժҪ��Ϣ����һ�ֲ�������㷨��һ���ʺϴ洢����֮������ݣ�
 * ������ɢ���㷨��MD5��SHA�ȡ�һ�����ɢ��ʱ����ṩһ��salt���Σ�������������� ��admin����
 * ������ɢ��ֵ�� ��21232f297a57a5a743894a0e4a801fc3�������Ե�һЩ md5 ������վ�����׵�ͨ��ɢ
 * ��ֵ�õ����� ��admin���������ֱ�Ӷ��������ɢ�������˵�ƽ�����ף���ʱ���ǿ��Լ�һЩֻ��ϵͳ֪���ĸ������ݣ�
 * ���û����� ID�����Σ�������ɢ�еĶ����� ������ + �û��� +ID�����������ɵ�ɢ��ֵ�����˵�����ƽ⡣
 */
public class HashAlgorithm {
	// @Test
	public void test() {
		String str = "hello";
		String salt = "123";
		// md:�������������ġ��Ρ�����
		System.out.println("md2:" + new Md2Hash(str, salt).toString());// ������ת��ΪtoBase64(),toHex()
		System.out.println("md5:" + new Md5Hash(str, salt).toString());
		// sha:�������������ġ��Ρ�����
		System.out.println("sha1:" + new Sha1Hash(str, salt).toString());
		System.out.println("sha256:" + new Sha256Hash(str, salt).toString());
		System.out.println("sha384:" + new Sha384Hash(str, salt).toString());
		System.out.println("sha512:" + new Sha512Hash(str, salt).toString());
		// ͨ��ɢ��֧�֣��������������ġ��Ρ�����
		System.out.println("md2:" + new SimpleHash("Md2", str, salt).toString());
		System.out.println("md5:" + new SimpleHash("Md5", str, salt).toString());
		System.out.println("SHA-1:" + new SimpleHash("SHA-1", str, salt).toString());
		System.out.println("SHA-256:" + new SimpleHash("SHA-256", str, salt).toString());
		System.out.println("SHA-384:" + new SimpleHash("SHA-384", str, salt).toString());
		System.out.println("SHA-512:" + new SimpleHash("SHA-512", str, salt).toString());
	}

	/*
	 * �����������û�/�޸�����ʱʹ�ã������ɵ����뼰salt2�������ݿ�
	 */
	@Test
	public void test2() {
		String algorithmName = "md5";// ���ܷ�ʽ
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
