package codeEncryption;

import org.apache.shiro.codec.Base64;
import org.junit.Test;

/*
 * base64�������
 */
public class Base64CodeEncryption {
	@Test
	public void test() {
		String str = "123";
		String base64Encoded = Base64.encodeToString(str.getBytes());// ����
		System.out.println(base64Encoded);
		String str2 = Base64.decodeToString(base64Encoded);// ����
		System.out.println(str2);

	}

}
