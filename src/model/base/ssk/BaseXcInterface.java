package model.base.ssk;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseXcInterface<M extends BaseXcInterface<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public M setPageUrl(java.lang.String pageUrl) {
		set("pageUrl", pageUrl);
		return (M)this;
	}
	
	public java.lang.String getPageUrl() {
		return getStr("pageUrl");
	}

	public M setGmtCreate(java.lang.Long gmtCreate) {
		set("gmtCreate", gmtCreate);
		return (M)this;
	}
	
	public java.lang.Long getGmtCreate() {
		return getLong("gmtCreate");
	}

	public M setGmtModified(java.lang.Long gmtModified) {
		set("gmtModified", gmtModified);
		return (M)this;
	}
	
	public java.lang.Long getGmtModified() {
		return getLong("gmtModified");
	}

	public M setIsDeleted(java.lang.Boolean isDeleted) {
		set("isDeleted", isDeleted);
		return (M)this;
	}
	
	public java.lang.Boolean getIsDeleted() {
		return get("isDeleted");
	}

	public M setCrateTime(java.util.Date crateTime) {
		set("crateTime", crateTime);
		return (M)this;
	}
	
	public java.util.Date getCrateTime() {
		return get("crateTime");
	}

	public M setModifyTime(java.util.Date modifyTime) {
		set("modifyTime", modifyTime);
		return (M)this;
	}
	
	public java.util.Date getModifyTime() {
		return get("modifyTime");
	}

}