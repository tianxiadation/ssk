package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import model.ssk.*;
import util.JsonUtil;
import util.MsgUtil;
import util.node.InfiniteLevelTreeUtil;
import util.node.Node;

import java.util.ArrayList;
import java.util.List;

//指标配置
public class BaseCountController extends Controller {
    //统计1.实有人口数2.实有企业数3.实有房屋数4.平台信息总量
    public static void changeXcCount(){
        String[] str={"hz_xc_sssj.rkbaseinfo","hz_xc_sssj.qybaseinfo","hz_xc_sssj.fwxxbaseinfo"};
        for (int i = 0; i < str.length; i++) {
            Db.update("update xc_count set num=(select count(*) from "+str[i]+") where type=?",i+1);
        }
        Db.update("update xc_count set num=(select * from (select sum(num) from xc_count where id<4) a) where id=4");
       // renderJson(MsgUtil.successMsg("计算成功"));
    }
    public  void changeXcCount1(){
        String[] str={"hz_xc_sssj.rkbaseinfo","hz_xc_sssj.qybaseinfo","hz_xc_sssj.fwxxbaseinfo"};
        for (int i = 0; i < str.length; i++) {
            Db.update("update xc_count set num=(select count(*) from "+str[i]+") where type=?",i+1);
        }
        Db.update("update xc_count set num=(select * from (select sum(num) from xc_count where id<4) a) where id=4");
        renderJson(MsgUtil.successMsg("计算成功"));
    }
    //1.实有人口数2.实有企业数3.实有房屋数4.平台信息总量
    public void selectXcCount(){
        renderJson(MsgUtil.successMsg(XcCount.seleteXcCOunts()));
    }
    //首页数据来源
    public void selectDatasource(){
        renderJson(MsgUtil.successMsg((XcDatasource.getDs(getParaToInt("type")))));
    }
    public void selectLog(){
       renderJson(MsgUtil.successMsg(Db.paginate(getParaToInt("pageNumber"),getParaToInt("pageSize"),"select trueName,log,crateTime","from xc_log order by id desc")));

    }

    public void saveLog() {

            List<XcIndex> list=XcIndex.getXC1();
            for(XcIndex xc:list){
                XcLog.saveLog(11,xc.getRemarks(),"方升群");

            }


        renderJson(MsgUtil.successMsg("测试数据添加成功"));
    }
}
