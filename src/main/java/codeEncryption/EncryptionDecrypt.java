package codeEncryption;

import java.security.Key;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.junit.Test;

/*
 * ���ܽ���
 */
public class EncryptionDecrypt {
	/*
	 * AES�㷨
	 */
	@Test
	public void test() {
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128);// ����key����
		Key key = aesCipherService.generateNewKey();// ����key
		String str = "hello";
		String encrptStr = aesCipherService.encrypt(str.getBytes(), key.getEncoded()).toHex();// ����
		System.out.println(encrptStr);
		String decryptStr = new String(aesCipherService.decrypt(Hex.decode(encrptStr), key.getEncoded()).getBytes());// ����
		System.out.println(decryptStr);
	}

}
