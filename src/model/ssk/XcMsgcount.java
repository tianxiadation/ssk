package model.ssk;

import model.base.ssk.BaseXcMsgcount;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class XcMsgcount extends BaseXcMsgcount<XcMsgcount> {
	public static final XcMsgcount dao = new XcMsgcount().dao();
	public static void saveXc(int type,String pid,String cid,int num,String mian,String tmain){
		new XcMsgcount().setType(type).setPid(pid).setCid(cid)
				.setNum(num).setMian(mian).setTmian(tmain).save();
	}
}
