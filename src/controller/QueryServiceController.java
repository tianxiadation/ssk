package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import model.ssk.XcApply;
import model.ssk.XcIndex;
import model.ssk.XcLog;
import model.ssk.XcUser;
import util.HttpUtil;
import util.MsgUtil;

import java.util.ArrayList;
import java.util.List;

public class QueryServiceController extends Controller {
       public void apply(){
           String cid=getPara("cid");
           XcUser user=getSessionAttr("user");
           XcIndex xc=XcIndex.dao.findById(getParaToInt("cid"));
           JSONObject data= HttpUtil.loginIm(user.getAppName());
           JSONObject json=new JSONObject();
           long id=  XcApply.saveApply(user.getId(),user.getDept(),user.getUserName(),xc.getRemarks(),"申请中",1,getPara("reason"),xc.getCid());
            json.put("O_DESCRIBE","申请查看"+xc.getRemarks()+"全信息，请审批");
            json.put("O_CUSTOMER",data.getString("userId"));
            json.put("O_CUSTOMER_NAME",user.getUserName());
            json.put("O_EXECUTOR","297NKDKkDzHZe2da");
            json.put("O_EXECUTOR_NAME","刘佳民");
            json.put("SOURCE","3");
            json.put("APARAMETER",id);
            json.put("CPARAMETER",1);
            JSONObject json1=new JSONObject();
            json1.put("BASE",json);
           System.out.println(json1.toString());
            JSONObject result=HttpUtil.doPost3("https://tyhy.hzxc.gov.cn:28443/1do/do/createIdo",json1.toString());
            if(result.getInteger("code")==200) {
                renderJson(MsgUtil.successMsg("申请成功"));
            }else{
                XcApply.dao.deleteById(id);
                renderJson(MsgUtil.successMsg("申请失败"));
            }
       }
       //查询单个资源信息
        public void selectOneQS(){
            String cid=getPara("cid");
            String value=getPara("value");
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
                    List<Record> records = Db.find("select * from hz_xc_sssj." + xc.getEname() + " where cardNum=?", value);
                    json.put("Header", record);
                    json.put("table", records);
                }else if(xc.getType()==2){
                    List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                    List<Record> records = Db.find("select * from hz_xc_sssj." + xc.getEname() + " where  QYMC=? or UNISCID=?", value,value);
                    json.put("Header", record);
                    json.put("table", records);
                }
            }
            XcLog.saveLog(user.getId(),xc.getRemarks(),user.getUserName());
            renderJson(MsgUtil.successMsg(json));
        }

        public void selectQS(){
            int type=getParaToInt("type");
            String value=getPara("value");
            JSONObject json=new JSONObject();
            String[] datebase={"rkbaseinfo","qybaseinfo","fwxxbaseinfo","qydzxxinfo","rkdzxxinfo"};
            String[] datebase1={"cardNum","QYMC","UNISCID","addressInfo"};
            XcUser user=getSessionAttr("user");

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

                        String str = " select e.*,IFNULL(f.type,0) type from (select e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                "c.cid FROM ( SELECT a.cid,IF (" +
                                "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := "+xc.getCid()+") b" +
                                ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true ) e left join (select  type ,cid from xc_apply where userid="+user.getId()+")f on e.cid=f.cid";
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
                //新
                json.put("qybaseinfo",Db.find("SELECT a.* FROM hz_xc_sssj.qybaseinfo a,hz_xc_sssj.qyjyglzjbinfo b where (a.QYMC=b.QYMC or a.UNISCID=b.UNISCID) and b.cardNum=?",value));
                List<Record> rs= Db.find("SELECT a.* FROM hz_xc_sssj.fwxxbaseinfo a, hz_xc_sssj.rkbaseinfo b where a.addressInfo=b.CZRKZZ and b.cardNum=?",value);
                List<Record> rs1= Db.find("SELECT a.* FROM hz_xc_sssj.qydzxxinfo a, hz_xc_sssj.rkbaseinfo b where a.addressInfo=b.CZRKZZ and b.cardNum=?",value);
                List<Record> rs2= Db.find("SELECT a.* FROM hz_xc_sssj.rkdzxxinfo a, hz_xc_sssj.rkbaseinfo b where a.addressInfo=b.CZRKZZ and b.cardNum=?",value);
                json.put("fwxxbaseinfo",rs);
                json.put("qydzxxinfo",rs1);
                json.put("rkdzxxinfo",rs2);
                //新

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
                        String str = "select e.*,IFNULL(f.type,0) type from (select e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                "c.cid FROM ( SELECT a.cid,IF (" +
                                "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := "+xc.getCid()+") b" +
                                ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true ) e left join (select  type ,cid from xc_apply where userid="+user.getId()+")f on e.cid=f.cid";

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
                //新
                json.put("rkbaseinfo",Db.find("SELECT c.* FROM hz_xc_sssj.rkbaseinfo c,hz_xc_sssj.qybaseinfo a,hz_xc_sssj.qyjyglzjbinfo b where" +
                        " (a.QYMC=b.QYMC or a.UNISCID=b.UNISCID) and b.cardNum=c.cardNum and (a.QYMC=? or a.UNISCID=?)",value,value));
                List<Record> rs= Db.find("SELECT a.* FROM hz_xc_sssj.fwxxbaseinfo a, hz_xc_sssj.qybaseinfo b,hz_xc_sssj.qydzinfo c " +
                        "where a.addressInfo=c.JYCS and (b.QYMC=c.QYMC or b.UNISCID=c.UNISCID) and (b.QYMC=? or b.UNISCID=?)",value,value);
                List<Record> rs1= Db.find("SELECT a.* FROM hz_xc_sssj.qydzxxinfo a, hz_xc_sssj.qybaseinfo b,hz_xc_sssj.qydzinfo c " +
                        "where a.addressInfo=c.JYCS and (b.QYMC=c.QYMC or b.UNISCID=c.UNISCID) and (b.QYMC=? or b.UNISCID=?)",value,value);
                List<Record> rs2= Db.find("SELECT a.* FROM hz_xc_sssj.rkdzxxinfo a, hz_xc_sssj.qybaseinfo b,hz_xc_sssj.qydzinfo c " +
                        "where a.addressInfo=c.JYCS and (b.QYMC=c.QYMC or b.UNISCID=c.UNISCID) and (b.QYMC=? or b.UNISCID=?)",value,value);
                json.put("fwxxbaseinfo",rs);
                json.put("qydzxxinfo",rs1);
                json.put("rkdzxxinfo",rs2);
                //新
                renderJson(MsgUtil.successMsg(json));
            }else{
                Record rs= Db.findFirst("SELECT * FROM hz_xc_sssj.fwxxbaseinfo where addressInfo=?",value);
                Record rs1= Db.findFirst("SELECT * FROM hz_xc_sssj.qydzxxinfo where addressInfo=?",value);
                Record rs2= Db.findFirst("SELECT * FROM hz_xc_sssj.rkdzxxinfo where addressInfo=?",value);
                List<Record> rkrs= Db.find("SELECT b.* FROM hz_xc_sssj.fwxxbaseinfo a, hz_xc_sssj.rkbaseinfo b where a.addressInfo=b.CZRKZZ and a.addressInfo=?",value);
                List<Record> rkrs1= Db.find("SELECT b.* FROM hz_xc_sssj.qydzxxinfo a, hz_xc_sssj.rkbaseinfo b where a.addressInfo=b.CZRKZZ and a.addressInfo=?",value);
                List<Record> rkrs2= Db.find("SELECT b.* FROM hz_xc_sssj.rkdzxxinfo a, hz_xc_sssj.rkbaseinfo b where a.addressInfo=b.CZRKZZ and a.addressInfo=?",value);
                List<Record> qyrs= Db.find("SELECT b.* FROM hz_xc_sssj.fwxxbaseinfo a, hz_xc_sssj.qybaseinfo b,hz_xc_sssj.qydzinfo c " +
                        "where a.addressInfo=c.JYCS and (b.QYMC=c.QYMC or b.UNISCID=c.UNISCID) and a.addressInfo=?",value);
                List<Record> qyrs1= Db.find("SELECT b.* FROM hz_xc_sssj.qydzxxinfo a, hz_xc_sssj.qybaseinfo b,hz_xc_sssj.qydzinfo c " +
                        "where a.addressInfo=c.JYCS and (b.QYMC=c.QYMC or b.UNISCID=c.UNISCID) and a.addressInfo=?",value);
                List<Record> qyrs2= Db.find("SELECT b.* FROM hz_xc_sssj.rkdzxxinfo a, hz_xc_sssj.qybaseinfo b,hz_xc_sssj.qydzinfo c " +
                        "where a.addressInfo=c.JYCS and (b.QYMC=c.QYMC or b.UNISCID=c.UNISCID) and a.addressInfo=?",value);
                json.put("fwxxbaseinfo",rs);
                json.put("qydzxxinfo",rs1);
                json.put("rkdzxxinfo",rs2);
                json.put("fwxxbaseinfo_rk",rkrs);
                json.put("qydzxxinfo_rk",rkrs1);
                json.put("rkdzxxinfo_rk",rkrs2);
                json.put("fwxxbaseinfo_qy",qyrs);
                json.put("qydzxxinfo_qy",qyrs1);
                json.put("rkdzxxinfo_qy",qyrs2);

                renderJson(MsgUtil.successMsg(json));

            }


        }
}
