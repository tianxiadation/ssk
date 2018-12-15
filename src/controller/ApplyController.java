package controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import model.ssk.XcApply;
import model.ssk.XcIndex;
import model.ssk.XcLog;
import util.MsgUtil;

import java.util.List;

public class ApplyController extends Controller {
    public  void selectOneApply(){
        renderJson(MsgUtil.successMsg(XcApply.dao.findFirst("select resources,reason from xc_apply where id=?",getParaToInt("id"))));
    }
    public void selectApply(){
        renderJson(MsgUtil.successMsg(Db.paginate(getParaToInt("pageNumber"),
                getParaToInt("pageSize"),"select id," +
                        "deptName,trueName,resources,typeName,reason,crateTime ","from xc_apply order by id desc")));

    }
    public void saveApply(){
        List<XcIndex> list=XcIndex.getXC1();
        String[] str= {"申请中","已通过","被驳回"};
        for(XcIndex xc:list){
            //String userid,String deptName,String trueName,String resources,String typeName,int type,String reason
            int i= (int) (Math.random()*3);
            XcApply.saveApply("PQV8oo3jeeiDkLbY",
                    "技术组","方升群",xc.getRemarks(),str[i],i,"工作需要");

        }
        renderJson(MsgUtil.successMsg("测试数据添加成功"));


    }


}
