package codeEncryption;

import org.apache.shiro.codec.Hex;
import org.junit.Test;

/*
 * 16½øÖÆ±àÂë½âÂë
 */
public class HexCodeEncryption {
	@Test
	public void test() {
		String str = "123";
		String hexEncode = Hex.encodeToString(str.getBytes());
		System.out.println(hexEncode);
		String str2 = new String(Hex.decode(hexEncode));
		System.out.println(str2);
	}
}
