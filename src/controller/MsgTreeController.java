package controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import model.ssk.XcIndex;
import util.MsgUtil;
import util.node.InfiniteLevelTreeUtil;
import util.node.Node;

import java.util.ArrayList;
import java.util.List;
//信息树模块
public class MsgTreeController extends Controller {
    //信息树
    public void msgTree(){
            int type=getParaToInt("type");

            List<XcIndex> xc=XcIndex.getXcByType1(type);
            List<Node> nodeList=new ArrayList<>();
            for(XcIndex arr:xc){
               // Node node=Node.getNode(arr);
                nodeList.add(Node.getNode(arr));
            }
            List<Node> list=InfiniteLevelTreeUtil.getInfiniteLevelTree(nodeList);
            renderJson(MsgUtil.successMsg(list));

    }
    //信息云
    public void cloud(){
            renderJson(MsgUtil.successMsg(XcIndex.getCloud(getParaToInt("type"))));

    }
    //计算字段数
    public  static void calculation() {
        for (int i = 1; i < 3; i++) {
            List<XcIndex> arr=XcIndex.getXC(i);
            for (XcIndex xc:arr) {
                List<XcIndex> brr=XcIndex.getCxcBypid(xc.getCid());
                /*for (XcIndex xc1:brr) {
                    Db.update("update xc_index set num=(select count(*) from hz_xc_sssj."+xc.getEname()+" where `"+xc1.getEname()+"` is not null) where ename=?",xc1.getEname());
                    //renderJson(MsgUtil.successMsg("计算字段数值成功"));
                   // return;

                }*/
                List<Record> record= Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname());
                for(Record r:record){
                 long num=Db.findFirst("select count(*) num from hz_xc_sssj."+xc.getEname()+" where `"+r.getStr("column_name")+"` is not null").getLong("num");
                 XcIndex x=XcIndex.dao.findFirst("select * from xc_index where pid=? and ename=?",xc.getCid(),r.getStr("column_name"));
                  if(x==null){
                      XcIndex.saveIndex(xc.getType(),xc.getCid(),"",r.getStr("column_comment"),r.getStr("column_name"),4,false,r.getStr("column_comment"), num);
                      Db.update("update xc_index set cid=id");
                      Db.update("update xc_index set isCount=false where ename='id' or ename='update_time'");
                  }else{
                      x.setNum(num).update();
                     // Db.update("update xc_index set num=(select count(*) " +
                      //        "from hz_xc_sssj."+xc.getEname()+" " +
                         //     "where `"+r.getStr("column_name")+"` is not null) where ename=? and pid=?",r.getStr("column_name"),xc.getCid());
                  }
                }
            }

        }
        List<XcIndex> crr=XcIndex.getXcByLevel4Type3();
        for (XcIndex xc2:crr) {
            int i1=Db.findFirst("select count(*) num from hz_xc_sssj.fwxxbaseinfo where fwyt=?",xc2.getName()).getInt("num");
            int i2=Db.findFirst("select count(*) num from hz_xc_sssj.qydzxxinfo where fwyt=?",xc2.getName()).getInt("num");
            int i3=Db.findFirst("select count(*) num from hz_xc_sssj.rkdzxxinfo where fwyt=?",xc2.getName()).getInt("num");
            Db.update("update xc_index set num=? where id=?",i1+i2+i3,xc2.getId());
        }
       // renderJson(MsgUtil.successMsg("计算字段数值成功"));

    }
}
