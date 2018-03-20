package userDefinedAuthorization;

import org.apache.shiro.authz.Permission;

/*
 * 自定义权限字符串解析
 * 权限字符串：+资源字符串+权限位+实例ID;以+开头中间通过+分割；
 * 权限：0表示所有权限，1新增，2修改，4删除、8查看
 */
public class BitPermission implements Permission {
	private String resourceIdentify;
	private int permissionBit;
	private String instanceId;

	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");
		if (array.length > 1) {
			resourceIdentify = array[1];
		}
		if (!"".equals(resourceIdentify) && resourceIdentify != null) {
			resourceIdentify = "*";
		}
		if (array.length > 2) {
			permissionBit = Integer.valueOf(array[2]);
		}
		if (array.length > 3) {
			instanceId = array[3];
		}
		if (!"".equals(instanceId) && instanceId != null) {
			instanceId = "*";
		}
	}

	public boolean implies(Permission p) {
		if (!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;
		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
			return false;
		}
		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}
		if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}
		return true;
	}
}
