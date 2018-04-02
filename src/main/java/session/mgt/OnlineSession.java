package session.mgt;

import org.apache.shiro.session.mgt.SimpleSession;

/*
 * 自定义session
 */
public class OnlineSession extends SimpleSession {
	private static final long serialVersionUID = -7125642695178165650L;

	public static enum OnlineStatus {
		on_line("在线"), hidden("隐身"), force_logout("强制退出");
		private final String info;

		private OnlineStatus(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	/**
	 * 用户浏览器类型
	 */
	private String userAgent;

	/**
	 * 在线状态
	 */
	private OnlineStatus status = OnlineStatus.on_line;

	/**
	 * 用户登录时系统IP
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