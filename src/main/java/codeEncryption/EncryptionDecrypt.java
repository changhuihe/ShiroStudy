package codeEncryption;

import java.security.Key;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.junit.Test;

/*
 * 加密解密
 */
public class EncryptionDecrypt {
	/*
	 * AES算法
	 */
	@Test
	public void test() {
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128);// 设置key长度
		Key key = aesCipherService.generateNewKey();// 生成key
		String str = "hello";
		String encrptStr = aesCipherService.encrypt(str.getBytes(), key.getEncoded()).toHex();// 加密
		System.out.println(encrptStr);
		String decryptStr = new String(aesCipherService.decrypt(Hex.decode(encrptStr), key.getEncoded()).getBytes());// 解密
		System.out.println(decryptStr);
	}

}
