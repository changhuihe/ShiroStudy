package session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/*
 * �Ự�����������ڼ����Ự�Ĵ��������ڼ�ֹͣ�¼�
 */
public class MySessionListener1 implements SessionListener {

	// �Ự����ʱ����
	public void onExpiration(Session session) {
		System.out.println("�Ự����" + session.getId());
	}

	// �Ự����ʱ����
	public void onStart(Session session) {
		System.out.println("�Ự����" + session.getId());
	}

	// �˳�/�Ự����ʱ����
	public void onStop(Session session) {
		System.out.println("�Ựֹͣ" + session.getId());
	}

}
