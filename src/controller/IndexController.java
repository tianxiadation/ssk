package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import model.ssk.XcApply;
import model.ssk.XcDatasource;
import model.ssk.XcIndex;
import model.ssk.XcInterface;
import util.JsonUtil;
import util.MsgUtil;
import util.node.InfiniteLevelTreeUtil;
import util.node.Node;

import java.util.ArrayList;
import java.util.List;

//指标配置
public class IndexController extends Controller {
    public void selectDataItem(){

        XcIndex xc= XcIndex.dao.findFirst("select * from xc_index where name=?",getPara("name"));
        if(xc!=null){
            renderJson(MsgUtil.successMsg(Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname())));
        }else{

            renderJson(MsgUtil.successMsg(Db.find( " SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name in("+

                   " Select table_name   from INFORMATION_SCHEMA.TABLES Where table_schema = 'hz_xc_sssj' AND TABLE_COMMENT=?"+

           " ) AND table_schema='hz_xc_sssj'",getPara("name"))));

        }

    }
    public void isExist(){
        renderJson(MsgUtil.successMsg(XcIndex.dao.findFirst("select * from xc_index where name=?" ,getPara("name"))==null?false:true));
    }
    public static void getArr(JSONArray arr,String pid){
       // new Thread(new Runnable() {
         //   @Override
        //    public void run() {
                if(arr.size()==0){
                    System.out.println(1);
                }else{
                    for(int i=0;i<arr.size();i++) {
                        XcIndex xc1 = JSON.toJavaObject(arr.getJSONObject(i), XcIndex.class);
                        if(xc1.getId()==null){
                            if(xc1.getIslast()||xc1.getType()!=3){
                              Record rr=Db.findFirst("Select table_name  from INFORMATION_SCHEMA.TABLES Where " +
                                        "table_schema = 'hz_xc_sssj' AND TABLE_COMMENT=?",xc1.getName());
                              if(rr!=null){
                                  String str=rr.getStr("table_name");
                                  xc1.setPid(pid).setRemarks(xc1.getName()).setEname(str).save();

                                  List<Record> recordList=Db.find( " SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name in("+
                                          " Select table_name   from INFORMATION_SCHEMA.TABLES Where table_schema = 'hz_xc_sssj' AND TABLE_COMMENT=?"+
                                          " ) AND table_schema='hz_xc_sssj'",xc1.getName());
                                  for(Record r1:recordList){
                                      XcIndex.saveIndex(xc1.getType(),xc1.getId()+"","",r1.getStr("column_comment"),r1.getStr("column_name"),4,false,r1.getStr("column_comment"), 0);
                                      Db.update("update xc_index set cid=id");
                                      Db.update("update xc_index set isCount=false where ename='id' or ename='update_time'");
                                  }
                              }else{
                                  xc1.setPid(pid).setRemarks(xc1.getName()).save();
                              }
                            }else{
                                xc1.setPid(pid).setRemarks(xc1.getName()).save();
                            }

                        }else{
                            xc1.update();
                        }
                        if (!xc1.getIslast()) {
                            JSONArray arr1 = arr.getJSONObject(i).getJSONArray("children");
                            getArr(arr1,xc1.getId()+"");
                        }
                    }
                }

          //  }});


    }
    public void editIndex(){
        JSONArray arr=JsonUtil.getJSONArray(getRequest());
        getArr(arr,"0");
        Db.update("update xc_index set cid=id");
        renderJson(MsgUtil.successMsg("编辑成功"));
    }

    /*public static JSONArray add(JSONArray arr){
            if(json.getBooleanValue("islast")){
                return json.getJSONArray("children");
            }else{
                for(int i=0;i<arr.size();i++){
                    XcIndex xc= JSON.toJavaObject(arr.getJSONObject(i),XcIndex.class);
                    JSONArray arr1=arr.getJSONObject(i).getJSONArray("children");
                    arr=add(arr1);
                }
                return arr;
            }
    }*/


    public void updateOrSaveDatasource(){
        JSONObject json=JsonUtil.getJSONObject(getRequest());
        XcDatasource xc=json.toJavaObject(XcDatasource.class);

        if(json.getString("method").equals("update")){
           renderJson(MsgUtil.successMsg(xc.update()));
        }else {
            renderJson(MsgUtil.successMsg(xc.save()));
        }

    }
    public void selectDatasourceById(){
        renderJson(MsgUtil.successMsg(XcDatasource.dao.
                findFirst("select id,type,name,oldName,ename,busnum," +
                        "remarks,DATE_FORMAT(accessTime,\"%Y-%m-%d\") accessTime," +
                        "typenum,colnum from xc_datasource where id=?",getPara("id"))));

    }

    public void selectIndexOrDatasource(){
            int type=getParaToInt("type");
            String name=getPara("name");

            if(name.equals("index")){
                List<XcIndex> xc=XcIndex.getXcByType1(type);
                List<Node> nodeList=new ArrayList<>();
                for(XcIndex arr:xc){
                    nodeList.add(Node.getNode(arr));
                }
                List<Node> list=InfiniteLevelTreeUtil.getInfiniteLevelTree(nodeList);
                renderJson(MsgUtil.successMsg(list));
               /* Page<Record> page=Db.paginate(pageNumber,pageSize,"SELECT a.id,a.name,b.name " +
                        "oneName"," FROM xc_index a,xc_index b where a.type=? and a.level=2 and a.pid=b.cid",type);
                renderJson(MsgUtil.successMsg(page));*/


            }else{
                int pageNumber=getParaToInt("pageNumber");
                int pageSize=getParaToInt("pageSize");
                Page<Record> page=Db.paginate(pageNumber,pageSize,"select id,type,name,oldName," +
                        "ename,busnum,remarks,DATE_FORMAT(accessTime,\"%Y-%m-%d\") accessTime,typenum,colnum"," from xc_datasource where type=?",type);
                renderJson(MsgUtil.successMsg(page));
            }


    }
    public void selectDatasource(){
            int type=getParaToInt("type");
            int pageNumber=getParaToInt("pageNumber");
            int pageSize=getParaToInt("pageSize");
            Page<Record> page=Db.paginate(pageNumber,pageSize,"select id,type,name,oldName," +
                    "ename,busnum,remarks,accessTime,typenum,colnum"," from xc_datasource where type=?",type);
           renderJson(MsgUtil.successMsg(page));

    }


    public void table() {
        List<XcIndex> list=XcIndex.getXC(2);
        for(XcIndex xc:list){
           Record record= Db.findFirst("SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE " +
                    "table_schema='hz_xc_sssj' and TABLE_COMMENT " +
                    "like '%企业%' and TABLE_COMMENT like '%"+xc.getName()+"%'");
           if(record!=null){
               xc.setEname(record.getStr("TABLE_NAME")).setRemarks(record.getStr("TABLE_COMMENT")).update();

           }

        }
        renderJson(MsgUtil.successMsg("同步企业表英文名成功"));
    }
    public void column() {
        List<XcIndex> list=XcIndex.getXC(2);
        for(XcIndex xc:list){

           List<Record> record= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                   "WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname());
           if(record!=null){
               //xc.setEname(record.getStr("TABLE_NAME")).setRemarks(record.getStr("TABLE_COMMENT")).update();
               for (int i = 0; i <record.size() ; i++) {
                   XcIndex.saveXc(xc.getCid(),record.get(i).getStr("column_comment"),record.get(i).getStr("COLUMN_NAME"));
               }
           }

        }
        renderJson(MsgUtil.successMsg("同步企业表字段成功"));
    }
}
