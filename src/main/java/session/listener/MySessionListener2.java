package session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/*
 * ���ֻ�����ĳһ���¼������Լ̳� SessionListenerAdapter ʵ��
 */
public class MySessionListener2 extends SessionListenerAdapter {

	// �Ự����ʱ����
	public void onStart(Session session) {
		System.out.println("�Ự����" + session.getId());
	}

}
