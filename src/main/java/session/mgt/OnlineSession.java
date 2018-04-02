package session.mgt;

import org.apache.shiro.session.mgt.SimpleSession;

/*
 * �Զ���session
 */
public class OnlineSession extends SimpleSession {
	private static final long serialVersionUID = -7125642695178165650L;

	public static enum OnlineStatus {
		on_line("����"), hidden("����"), force_logout("ǿ���˳�");
		private final String info;

		private OnlineStatus(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	/**
	 * �û����������
	 */
	private String userAgent;

	/**
	 * ����״̬
	 */
	private OnlineStatus status = OnlineStatus.on_line;

	/**
	 * �û���¼ʱϵͳIP
	 */
	private String systemHost;

	public OnlineSession() {
		super();
	}

	public OnlineSession(String host) {
		super(host);
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}

	public String getSystemHost() {
		return systemHost;
	}

	public void setSystemHost(String systemHost) {
		this.systemHost = systemHost;
	}

}