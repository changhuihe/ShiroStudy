package codeEncryption;

import org.apache.shiro.codec.Base64;
import org.junit.Test;

/*
 * base64±àÂë½âÂë
 */
public class Base64CodeEncryption {
	@Test
	public void test() {
		String str = "123";
		String base64Encoded = Base64.encodeToString(str.getBytes());// ¼ÓÃÜ
		System.out.println(base64Encoded);
		String str2 = Base64.decodeToString(base64Encoded);// ½âÃÜ
		System.out.println(str2);

	}

}
