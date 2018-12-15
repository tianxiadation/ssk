package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import model.ssk.XcIndex;
import model.ssk.XcLog;
import model.ssk.XcUser;
import util.MsgUtil;

import java.util.ArrayList;
import java.util.List;

public class QueryServiceController extends Controller {
       //查询单个资源信息
        public void selectOneQS(){
            String cid=getPara("cid");
           // String value=getPara("value");
            XcIndex xc=XcIndex.dao.findById(getParaToInt("cid"));
            JSONObject json=new JSONObject();
            XcUser user=getSessionAttr("user");
            if(StrKit.isBlank(xc.getEname())){
                List<Record> record=new ArrayList<Record>();
                List<Record> records=new ArrayList<Record>();
                json.put("Header",record);
                json.put("table",records);
            }else{
                // List<XcIndex> list=XcIndex.
                String[] str={"cardNum","QYMC","UNISCID","addressInfo"};
                if(xc.getType()==1) {
                    List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                    List<Record> records = Db.find("select * from hz_xc_sssj." + xc.getEname() + " where cardNum=?", getPara("value"));
                    json.put("Header", record);
                    json.put("table", records);
                }else if(xc.getType()==2){
                    List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                    List<Record> records = Db.find("select * from hz_xc_sssj." + xc.getEname() + " where  QYMC=? or UNISCID=?", getPara("value"),getPara("value"));
                    json.put("Header", record);
                    json.put("table", records);
                }else{

                }
            }
            XcLog.saveLog(user.getAppName(),xc.getRemarks(),user.getUserName());
            renderJson(MsgUtil.successMsg(json));
        }

        public void selectQS(){
            int type=getParaToInt("type");
            String value=getPara("value");
            JSONObject json=new JSONObject();
            String[] datebase={"rkbaseinfo","qybaseinfo","fwxxbaseinfo","qydzxxinfo","rkdzxxinfo"};
            String[] datebase1={"cardNum","QYMC","UNISCID","addressInfo"};
            if(type==1){
                List<XcIndex> list=XcIndex.getOneXcIndex1(type);

                Record record=Db.findFirst("SELECT * FROM hz_xc_sssj.rkbaseinfo where cardNum=?",value);
                json.put("jcxx",record);
                json.put("dh",list);
                JSONArray arr=new JSONArray();
                for(XcIndex xc:list){
                    JSONObject json1=new JSONObject();
                    json1.put("name",xc.getName());
                    if(!xc.getIslast())
                    {

                        String str = "select e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                "c.cid FROM ( SELECT a.cid,IF (" +
                                "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := "+xc.getCid()+") b" +
                                ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true";
                        List<Record> records=Db.find(str);
                        for(Record r:records){
                            if(StrKit.notBlank(r.getStr("ename"))){
                                r.set("num",Db.findFirst("select count(*) num from hz_xc_sssj."+r.getStr("ename")+" where cardNum=?",value).getInt("num"));
                            }else{
                                r.set("num",0);
                            }
                        }
                        json1.put("values",records);
                    }else{
                        List<XcIndex> records=new ArrayList<XcIndex>();
                        if(StrKit.notBlank(xc.getEname())) {
                            xc.setNum(Db.findFirst("select count(*) num from hz_xc_sssj." + xc.getEname() + " where cardNum=?", value).getLong("num"));
                        }else {
                            xc.setNum(0l);
                        }
                        records.add(xc);
                        json1.put("values",records);
                    }
                    arr.add(json1);

                }
                json.put("result",arr);
                renderJson(MsgUtil.successMsg(json));
            }else if(type==2){
                List<XcIndex> list=XcIndex.getOneXcIndex1(type);
                /*XcIndex xcIndex=new XcIndex().setName("数据来源");
                XcIndex xcIndex1=new XcIndex().setName("企业信息");
                XcIndex xcIndex2=new XcIndex().setName("房屋信息");*/

                Record record=Db.findFirst("SELECT * FROM hz_xc_sssj.qybaseinfo where QYMC=? or UNISCID=?",value,value);

                json.put("jcxx",record);
                json.put("dh",list);
                JSONArray arr=new JSONArray();
                for(XcIndex xc:list){
                    JSONObject json1=new JSONObject();
                    json1.put("name",xc.getName());
                    if(!xc.getIslast())
                    {
                        String str = "select e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                "c.cid FROM ( SELECT a.cid,IF (" +
                                "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := "+xc.getCid()+") b" +
                                ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true";

                        List<Record> records=Db.find(str);
                        for(Record r:records){
                            if(StrKit.notBlank(r.getStr("ename"))){
                                r.set("num",Db.findFirst("select count(*) num from hz_xc_sssj."+r.getStr("ename")+" where QYMC=? or UNISCID=?",value,value).getInt("num"));
                            }else{
                                r.set("num",0);
                            }
                        }
                        json1.put("values",records);
                    }else{
                        List<XcIndex> records=new ArrayList<XcIndex>();
                        if(StrKit.notBlank(xc.getEname())) {
                            xc.setNum(Db.findFirst("select count(*) num from hz_xc_sssj."+ xc.getEname() + " where QYMC=? or UNISCID=?",value,value).getLong("num"));
                        }else {
                            xc.setNum(0l);
                        }
                        records.add(xc);
                        json1.put("values",records);
                    }
                    arr.add(json1);

                }
                json.put("result",arr);
                renderJson(MsgUtil.successMsg(json));
            }else{
                List<Record> rs= Db.find("SELECT * FROM fwxxbaseinfo where addressInfo=?",value);
                List<Record> rs1= Db.find("SELECT * FROM qydzxxinfo where addressInfo=?",value);
                List<Record> rs2= Db.find("SELECT * FROM rkdzxxinfo where addressInfo=?",value);

            }


        }
}
