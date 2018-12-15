package model.base.ssk;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRegUserDept<M extends BaseRegUserDept<M>> extends Model<M> implements IBean {

	public M setID(java.lang.Integer ID) {
		set("ID", ID);
		return (M)this;
	}
	
	public java.lang.Integer getID() {
		return getInt("ID");
	}

	public M setUName(java.lang.String uName) {
		set("U_NAME", uName);
		return (M)this;
	}
	
	public java.lang.String getUName() {
		return getStr("U_NAME");
	}

	public M setUDeptId(java.lang.String uDeptId) {
		set("U_DEPT_ID", uDeptId);
		return (M)this;
	}
	
	public java.lang.String getUDeptId() {
		return getStr("U_DEPT_ID");
	}

	public M setCShowId(java.lang.String cShowId) {
		set("C_SHOW_ID", cShowId);
		return (M)this;
	}
	
	public java.lang.String getCShowId() {
		return getStr("C_SHOW_ID");
	}

	public M setIsMain(java.lang.Integer isMain) {
		set("IS_MAIN", isMain);
		return (M)this;
	}
	
	public java.lang.Integer getIsMain() {
		return getInt("IS_MAIN");
	}

	public M setCreateUser(java.lang.String createUser) {
		set("CREATE_USER", createUser);
		return (M)this;
	}
	
	public java.lang.String getCreateUser() {
		return getStr("CREATE_USER");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("CREATE_TIME", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("CREATE_TIME");
	}

	public M setCreateUserName(java.lang.String createUserName) {
		set("CREATE_USER_NAME", createUserName);
		return (M)this;
	}
	
	public java.lang.String getCreateUserName() {
		return getStr("CREATE_USER_NAME");
	}

	public M setUDeptSort(java.lang.Integer uDeptSort) {
		set("U_DEPT_SORT", uDeptSort);
		return (M)this;
	}
	
	public java.lang.Integer getUDeptSort() {
		return getInt("U_DEPT_SORT");
	}

}