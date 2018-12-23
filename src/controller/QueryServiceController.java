package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import model.ssk.*;
import org.apache.poi.hslf.record.Sound;
import util.FullCharConverter;
import util.HttpUtil;
import util.JsonUtil;
import util.MsgUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QueryServiceController extends Controller {
    /*   //1do审批回调接口
     public void approval(){
         JSONObject json=JsonUtil.getJSONObject(getRequest());
         XcApply xc=json.toJavaObject(XcApply.class);
         xc.setTypeName(xc.getType()==2?"已通过":"已驳回");
         renderJson(MsgUtil.successMsg(xc.update()));
     }
  //申请全信息
     public void apply(){
         String cid=getPara("cid");
         XcUser user=getSessionAttr("user");
         int status=getParaToInt("status");
         String result1="";
         //指标信息
         if(status==1){
             result1=XcIndex.dao.findById(getParaToInt("cid")).getRemarks();
         //来源信息
         }else if(status==2){
             XcDatasource xc= XcDatasource.dao.findById(getParaToInt("cid"));
             result1=xc.getName()+"-"+xc.getTableName();
         //相关信息
         }else{
             result1=XcAssociation.dao.findById(getParaToInt("cid")).getName();
         }
         JSONObject data= HttpUtil.loginIm(user.getAppName());
         JSONObject json=new JSONObject();
         long id=  XcApply.saveApply(user.getId(),user.getDept(),user.getUserName(),result1,"申请中",1,getPara("reason"),cid,status);
          json.put("O_DESCRIBE","申请查看"+result1+"全信息，请审批");
          json.put("O_CUSTOMER",data.getString("userId"));
          json.put("O_CUSTOMER_NAME",user.getUserName());
          json.put("O_EXECUTOR","WeKJDWROnaHQn6Yq");
          json.put("O_EXECUTOR_NAME","王帅帅");
          json.put("SOURCE","3");
          json.put("APARAMETER",id);
          json.put("CPARAMETER",1);
          JSONObject json1=new JSONObject();
          json1.put("BASE",json);
         System.out.println(json1.toString());
          JSONObject result=HttpUtil.doPost3("https://tyhy.hzxc.gov.cn:28443/1do/do/createIdo",json1.toString());
          if(result.getInteger("code")==200) {
              Db.update("update xc_apply set isDeleted=true where status=? and cid=? and userid=? and id!=?",status,cid,user.getId(),id);
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
          int status=getParaToInt("status");
          JSONObject json=new JSONObject();
          XcUser user=getSessionAttr("user");
          //指标配置信息
          if(status==1) {
              XcIndex xc=XcIndex.dao.findById(getParaToInt("cid"));
              if (XcApply.getApplyIsExist(cid, user.getId(),1)) {
                  if (xc.getType() == 1||xc.getType() == 3) {
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                      List<Record> records = Db.find("select * from hz_xc_sssj." + xc.getEname() + " where cardNum=?", value);
                      List<Record> header=getTableMsg(record,records);
                      json.put("Header", header);
                      json.put("table", records);
                  } else if (xc.getType() == 2) {
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                      List<Record> records = Db.find("select * from hz_xc_sssj." + xc.getEname() + " where  QYMC=? or UNISCID=?", value, value);
                      List<Record> header=getTableMsg(record,records);
                      json.put("Header", header);
                      json.put("table", records);
                  }
              } else {
                  if (xc.getType() == 1) {
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                      List<Record> records = Db.find("select id from hz_xc_sssj." + xc.getEname() + " where cardNum=?", value);

                      List<Record> records1=new ArrayList<Record>();
                      for(Record r:records){
                          Record re = Db.use("tmk").findFirst("select * from hz_xc_sssj." + xc.getEname() + " where id=?",r.getLong("id"));
                          records1.add(re);
                      }
                      List<Record> header=getTableMsg(record,records1);
                      json.put("Header", header);
                      json.put("table", records1);
                  } else if (xc.getType() == 2) {
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                      List<Record> records = Db.find("select id from hz_xc_sssj." + xc.getEname() + " where  QYMC=? or UNISCID=?", value, value);
                      List<Record> records1=new ArrayList<Record>();
                      for(Record r:records){
                          Record re = Db.use("tmk").findFirst("select * from hz_xc_sssj." + xc.getEname() + " where id=?",r.getLong("id"));
                          records1.add(re);
                      }
                      List<Record> header=getTableMsg(record,records1);
                      json.put("Header", header);
                     // json.put("Header", record);
                      json.put("table", records1);
                  } else{
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'", xc.getEname());
                      List<Record> records=Db.find("SELECT *  FROM hz_xc_sssj."+xc.getEname()+" where addressInfo=?",value);
                      List<Record> records1=new ArrayList<Record>();
                      for(Record r:records){
                          Record re = Db.use("tmk").findFirst("select * from hz_xc_sssj." + xc.getEname() + " where id=?",r.getLong("id"));
                          records1.add(re);
                      }
                      List<Record> header=getTableMsg(record,records1);
                      json.put("Header", header);
                      //json.put("Header", record);
                      json.put("table", records);
                  }

              }
              XcLog.saveLog(user.getId(), xc.getRemarks(), user.getUserName());
              renderJson(MsgUtil.successMsg(json));
          //数据源信息
          }else if(status==2){
              XcDatasource xc1=XcDatasource.dao.findById(cid);
              XcLog.saveLog(user.getId(), xc1.getName()+"-"+xc1.getTableName(), user.getUserName());
              //查全信息
              //if (XcApply.getApplyIsExist(cid, user.getId(),2)){
                 //人口
                  if(xc1.getType()==1){
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema=?", xc1.getTableEname(),xc1.getEname());
                       List<Record> records =xc1.getIsOtherLibraries()==1?Db.find("select *  from  "+xc1.getEname()+"."+xc1.getTableEname()+" where "+xc1.getColumn()+"=?",value)
                                  :Db.use("temp").find("select *  from  "+xc1.getEname()+"."+xc1.getTableEname()+" where "+xc1.getColumn()+"=?",value);
                      List<Record> header=getTableMsg(record,records);
                      json.put("Header", header);
                       json.put("table", records);
                     // json.put("Header", record);
                      renderJson(MsgUtil.successMsg(json));

                  }else{
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema=?", xc1.getTableEname(),xc1.getEname());
                      List<Record> records =xc1.getIsOtherLibraries()==1?Db.find("select *  from  "+xc1.getEname()+"."+xc1.getTableEname()+" where "+xc1.getColumn(),value,value)
                              :Db.use("temp").find("select *  from  "+xc1.getEname()+"."+xc1.getTableEname()+" where "+xc1.getColumn(),value,value);
                      List<Record> header=getTableMsg(record,records);
                      json.put("table", records);
                      json.put("Header", header);
                      renderJson(MsgUtil.successMsg(json));

                  }

              //}
          //相关信息
          }else{
              XcAssociation xc2=XcAssociation.dao.findById(cid);
              XcLog.saveLog(user.getId(), xc2.getName(), user.getUserName());

              //全信息
              //if(XcApply.getApplyIsExist(cid, user.getId(),3)){
                  if(xc2.getType()==1){
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name='rkbaseinfo' AND table_schema='hz_xc_sssj'");
                      List<Record> records = Db.find("SELECT a.* FROM hz_xc_sssj.qybaseinfo a,hz_xc_sssj.qydjinfo b where (a.QYMC=b.QYMC or a.UNISCID=b.UNISCID) and b.SFZJHM=?",value);
                      json.put("Header", record);
                      json.put("table", records);
                      renderJson(MsgUtil.successMsg(json));
                  }else if(xc2.getType()==2){
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name='rkbaseinfo' AND table_schema='hz_xc_sssj'");
                      List<Record> records = Db.find(xc2.getAsql(),value,value);
                      json.put("Header", record);
                      json.put("table", records);
                      renderJson(MsgUtil.successMsg(json));
                  }else{
                      List<Record> record = Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema='hz_xc_sssj'",xc2.getEname());
                      List<Record> records = Db.find(xc2.getAsql(),value);
                      json.put("Header", record);
                      json.put("table", records);
                      renderJson(MsgUtil.successMsg(json));
                  }
             // }


          }
      }
      //查询服务
      public void selectQS(){
          int type=getParaToInt("type");
          String value=getPara("value");
          JSONObject json=new JSONObject();
         // String[] datebase={"rkbaseinfo","qybaseinfo","fwxxbaseinfo","qydzxxinfo","rkdzxxinfo"};
        //  String[] datebase1={"cardNum","QYMC","UNISCID","addressInfo"};
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

                      String str = " select e.*,IFNULL(f.type,0) type from (select e.id,e.cid,e.name,e.ename from xc_index e,(SELECT " +
                              "c.cid FROM ( SELECT a.cid,IF (" +
                              "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                              "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                              "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                              ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                              "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                              "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := "+xc.getCid()+") b" +
                              ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true and isSelect=true ) e left join (select  type ,cid from xc_apply where isDeleted=false and status=1 and userid="+user.getId()+")f " +
                              " on e.cid=f.cid order by e.id";
                      List<Record> records=Db.find(str);
                      List<Record> records1=new ArrayList<Record>();
                      for(Record r:records){
                          if(StrKit.notBlank(r.getStr("ename"))){
                              List<Record> record11= getTableMsg(r.getStr("ename"),"hz_xc_sssj");
                              String str1=getSql(record11,1);
                             int ii= Db.findFirst("select count(*) num from hz_xc_sssj."+r.getStr("ename")+" where cardNum=? AND "+str1,value).getInt("num");
                              if(ii>0){
                                  r.set("num",ii);
                                  records1.add(r);

                              }

                          }
                      }
                      json1.put("values",records1);
                  }else{
                      List<XcIndex> records=new ArrayList<XcIndex>();
                      if(StrKit.notBlank(xc.getEname())) {
                          List<Record> record11=getTableMsg(xc.getEname(),"hz_xc_sssj");
                          String str1=getSql(record11,1);
                         long ii= Db.findFirst("select count(*) num from hz_xc_sssj." + xc.getEname() + " where cardNum=? and "+str1, value).getLong("num");
                          if(ii>0){
                              xc.setNum(ii);
                              records.add(xc);
                          }
                      }
                      json1.put("values",records);
                  }
                  arr.add(json1);

              }
              json.put("result",arr);
              //相关信息
              XcAssociation xcAssociations=XcAssociation.getXcBytypes(1,user.getId(),2).get(0);
             // List<XcAssociation> xcAssociations1=new ArrayList<XcAssociation>();
             // for(XcAssociation xc:xcAssociations){
                  int i=Db.findFirst("SELECT count(*) num FROM hz_xc_sssj.qybaseinfo a,hz_xc_sssj.qydjinfo b where (a.QYMC=b.QYMC or a.UNISCID=b.UNISCID) and b.SFZJHM=?",value).getInt("num");
                 if(i>0){
                     xcAssociations.setNum(i);
                     json.put("qybaseinfo",xcAssociations);
                    // xcAssociations1.add(xc);
                 }else{
                     json.put("qybaseinfo",null);
                 }

              //}
             // json.put("qybaseinfo",xcAssociations1);
              //数据来源
              List<XcDatasource> xcDatasources=XcDatasource.getDs2(1,user.getId());
              List<XcDatasource> xcDatasources1=new ArrayList<XcDatasource>();
              for(XcDatasource xc:xcDatasources){
                  if(xc.getIsOtherLibraries()==1){
                     int i1= Db.findFirst("select count(*) num from  "+xc.getEname()+"."+xc.getTableEname()+" where "+xc.getColumn()+"=?",value).getInt("num");
                    if(i1>0){
                        xc.setNum(i1);
                        xcDatasources1.add(xc);
                    }
                  }else{
                      int i2= Db.use("temp").findFirst("select count(*) num from  "+xc.getEname()+"."+xc.getTableEname()+" where "+xc.getColumn()+"=?",value).getInt("num");
                      if(i2>0){
                          xc.setNum(i2);
                          xcDatasources1.add(xc);
                      }
                  }
              }
              json.put("datasource",xcDatasources1);
              renderJson(MsgUtil.successMsg(json));
          }else if(type==2){
              List<XcIndex> list=XcIndex.getOneXcIndex1(type);
              Record record=Db.findFirst("SELECT * FROM hz_xc_sssj.qybaseinfo where QYMC=? or UNISCID=?",value,value);

              json.put("jcxx",record);
              json.put("dh",list);
              JSONArray arr=new JSONArray();
              for(XcIndex xc:list){
                  JSONObject json1=new JSONObject();
                  json1.put("name",xc.getName());
                  if(!xc.getIslast())
                  {
                      String str = "select e.*,IFNULL(f.type,0) type from (select e.id,e.cid,e.name,e.ename from xc_index e,(SELECT " +
                              "c.cid FROM ( SELECT a.cid,IF (" +
                              "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                              "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                              "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                              ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                              "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                              "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := "+xc.getCid()+") b" +
                              ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true and isSelect=true ) e left join (select  type ,cid from xc_apply where isDeleted=false and  status=1 and userid="+user.getId()+")f" +
                              " on e.cid=f.cid order by e.id";

                      List<Record> records=Db.find(str);
                      List<Record> records1=new ArrayList<Record>();
                      for(Record r:records){
                          if(StrKit.notBlank(r.getStr("ename"))){
                              List<Record> record11=getTableMsg(r.getStr("ename"),"hz_xc_sssj");
                              String str1=getSql(record11,2);
                              int ii=Db.findFirst("select count(*) num from hz_xc_sssj."+r.getStr("ename")+" where QYMC=? or UNISCID=? and"+str1,value,value).getInt("num");
                              if(ii>0){
                                  r.set("num",ii);
                                  records1.add(r);
                              }
                          }
                      }
                      json1.put("values",records1);
                  }else{
                      List<XcIndex> records=new ArrayList<XcIndex>();
                      if(StrKit.notBlank(xc.getEname())) {
                          List<Record> record11=getTableMsg(xc.getEname(),"hz_xc_sssj");

                          String str1=getSql(record11,2);
                         Long i= Db.findFirst("select count(*) num from hz_xc_sssj."+ xc.getEname() + " where QYMC=? or UNISCID=?",value,value).getLong("num");
                         if(i>0){
                             xc.setNum(i);
                             records.add(xc);
                         }
                      }
                      json1.put("values",records);
                  }
                  arr.add(json1);

              }
              json.put("result",arr);
              //新

              //相关信息
              List<XcAssociation> xcAssociations=XcAssociation.getXcBytypes(2,user.getId(),1);
              List<XcAssociation> xcAssociations1=new ArrayList<XcAssociation>();

              for(XcAssociation xca:xcAssociations){
                  int i=Db.findFirst(xca.getSql(),value,value).getInt("num");
                    if(i>0){
                        xca.setNum(i);
                        xcAssociations1.add(xca);
                    }
              }
              json.put("rkbaseinfo",xcAssociations1);

              List<XcDatasource> xcDatasources=XcDatasource.getDs2(2,user.getId());
              List<XcDatasource> xcDatasources1=new ArrayList<XcDatasource>();
              for(XcDatasource xc:xcDatasources){
                  if(xc.getIsOtherLibraries()==1){
                      int i= Db.findFirst("select count(*) num from  "+xc.getEname()+"."+xc.getTableEname()+" where "+xc.getColumn(),value,value).getInt("num");
                      if(i>0){
                          xc.setNum(i);
                          xcDatasources1.add(xc);
                      }
                  }else{
                      int i= Db.use("temp").findFirst("select count(*) num from  "+xc.getEname()+"."+xc.getTableEname()+" where "+xc.getColumn(),value,value).getInt("num");
                      if(i>0){
                          xc.setNum(i);
                          xcDatasources1.add(xc);
                      }
                  }
              }
              json.put("datasource",xcDatasources1);

              renderJson(MsgUtil.successMsg(json));
          }else{
              Record rs= Db.findFirst("SELECT * FROM hz_xc_sssj.fwxxbaseinfo where addressInfo=?",value);
              json.put("jcxx",rs);

              List<XcIndex> list=XcIndex.getOneXcIndex2(3,user.getId());
              for(XcIndex xc:list){
                  Long i=Db.findFirst("SELECT count(*) num FROM hz_xc_sssj."+xc.getEname()+" where addressInfo=?",value).getLong("num");
                  if(i>0){
                      xc.setNum(i);
                      json.put(xc.getEname(),xc);
                  }else{
                      json.put(xc.getEname(),null);
                  }

              }
              //相关信息人口信息
              List<XcAssociation> xcAssociations=XcAssociation.getXcBytypes(3,user.getId(),1);
              List<XcAssociation> xcAssociations1=new ArrayList<XcAssociation>();

              for(XcAssociation xca:xcAssociations){
                  if(xca.getStatus()){//如果是全角
                      try {
                          int i=Db.findFirst(xca.getSql(),FullCharConverter.half2Fullchange(getPara("value"))).getInt("num");
                          if(i>0){
                              xca.setNum(i);
                              xcAssociations1.add(xca);
                          }
                      }catch (UnsupportedEncodingException u){
                          u.printStackTrace();
                      }

                  }else{
                      int i=Db.findFirst(xca.getSql(),value).getInt("num");
                      if(i>0){
                          xca.setNum(i);
                          xcAssociations1.add(xca);
                      }
                  }

              }
              json.put("rkbaseinfo",xcAssociations1);

              //相关信息企业信息
              List<XcAssociation> xgqx=XcAssociation.getXcBytypes(3,user.getId(),2);
              List<XcAssociation> xgqx1=new ArrayList<XcAssociation>();
              for(XcAssociation xca:xgqx){
                  int i=Db.findFirst(xca.getSql(),value).getInt("num");
                  if(i>0){
                      xca.setNum(i);
                      xgqx1.add(xca);
                  }
              }
              json.put("qybaseinfo",xgqx1);

              List<XcDatasource> xcDatasources=XcDatasource.getDs2(3,user.getId());
              List<XcDatasource> xcDatasources1=new ArrayList<XcDatasource>();
              for(XcDatasource xc:xcDatasources){
                  if(xc.getIsOtherLibraries()==1){
                      int i= Db.findFirst("select count(*) num from  "+xc.getEname()+"."+xc.getTableEname()+" where "+xc.getColumn(),value,value).getInt("num");
                      if(i>0){
                          xc.setNum(i);
                          xcDatasources1.add(xc);
                      }
                  }else{
                      int i= Db.use("temp").findFirst("select count(*) num from  "+xc.getEname()+"."+xc.getTableEname()+" where "+xc.getColumn(),value,value).getInt("num");
                      if(i>0){
                          xc.setNum(i);
                          xcDatasources1.add(xc);
                      }
                  }
              }
              json.put("datasource",xcDatasources1);

              renderJson(MsgUtil.successMsg(json));

          }


      }


  //计算指标所对应的数据库是否有数据
  public void jszb(){
         List<XcIndex> xc=XcIndex.dao.find("select * from xc_index where islast=true and type!=3 ");
         for(XcIndex xc1:xc){
             if(StrKit.notBlank(xc1.getEname())) {
                 int i3 = Db.findFirst("select count(*) num from hz_xc_sssj." + xc1.getEname()).getInt("num");
                 if(i3==0){
                     xc1.setIsSelect(false).update();
                 }
             }
         }
      renderJson(MsgUtil.successMsg("计算查询服务结果成功"));
  }
     //计算
  public void JSQS() {
          for(int i=1;i<3;i++){
              List<XcIndex> list = XcIndex.getOneXcIndex1(i);
              if(i==1) {
                  List<Record> record = Db.find("SELECT * FROM hz_xc_sssj.rkbaseinfo");
                  for(Record r1:record){
                      for (XcIndex xc : list) {
                          if (!xc.getIslast()) {
                              String str = "select e.id,e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                      "c.cid FROM ( SELECT a.cid,IF (" +
                                      "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                      "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                      "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                      ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                      "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                      "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := " + xc.getCid() + ") b" +
                                      ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true ";
                              List<Record> records = Db.find(str);
                              for (Record r : records) {
                                  if (StrKit.notBlank(r.getStr("ename"))) {
                                       List<Record> record11= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                                             "WHERE table_name=? AND table_schema='hz_xc_sssj'",r.getStr("ename"));
                                       String str1=getSql(record11,1);
                                     int i3=Db.findFirst("select count(*) num from hz_xc_sssj." + r.getStr("ename") + " where cardNum=? and "+ str1, r1.getStr("cardNum")).getInt("num");
                                      if(i3>0){
                                          XcMsgcount.saveXc(1,xc.getCid(),r.getStr("cid"),i3,r1.getStr("cardNum"),"");
                                      }
                                  }
                              }

                          } else {
                              if (StrKit.notBlank(xc.getEname())) {
                                  List<Record> record11= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                                          "WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname());
                                  String str1=getSql(record11,1);
                                  int i3=Db.findFirst("select count(*) num from hz_xc_sssj." + xc.getEname() + " where cardNum=? and "+str1, r1.getStr("cardNum")).getInt("num");
                                  if(i3>0){
                                      XcMsgcount.saveXc(1,xc.getCid(),xc.getCid(),i3,r1.getStr("cardNum"),"");
                                  }
                              }

                          }
                          //arr.add(json1);

                      }
                  }

              }else{
                 // Record record=Db.findFirst("SELECT * FROM hz_xc_sssj.qybaseinfo where QYMC=? or UNISCID=?",value,value);
                  List<Record> record = Db.find("SELECT * FROM hz_xc_sssj.qybaseinfo where QYMC is not null");
                  for(Record r1:record){
                      for (XcIndex xc : list) {
                          if (!xc.getIslast()) {
                              String str = "select e.id,e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                      "c.cid FROM ( SELECT a.cid,IF (" +
                                      "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                      "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                      "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                      ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                      "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                      "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := " + xc.getCid() + ") b" +
                                      ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true ";
                              List<Record> records = Db.find(str);
                              for (Record r : records) {
                                  if (StrKit.notBlank(r.getStr("ename"))) {
                                      List<Record> record11= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                                              "WHERE table_name=? AND table_schema='hz_xc_sssj'",r.getStr("ename"));
                                      String str1=getSql(record11,2);
                                      int i3=Db.findFirst("select count(*) num from hz_xc_sssj."+r.getStr("ename")+" where QYMC=? and "+str1,r1.getStr("QYMC")).getInt("num");

                                      if(i3>0){
                                          XcMsgcount.saveXc(1,xc.getCid(),r.getStr("cid"),i3,r1.getStr("QYMC"),"");
                                      }
                                  }
                              }

                          } else {
                              if (StrKit.notBlank(xc.getEname())) {
                                  List<Record> record11= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                                          "WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname());
                                  String str1=getSql(record11,2);
                                  int i3=Db.findFirst("select count(*) num from hz_xc_sssj."+xc.getEname()+" where QYMC=? and "+str1,r1.getStr("QYMC")).getInt("num");
                                  if(i3>0){
                                      XcMsgcount.saveXc(1,xc.getCid(),xc.getCid(),i3,r1.getStr("QYMC"),"");
                                  }
                              }

                          }
                          //arr.add(json1);

                      }
                  }
                  List<Record> record2 = Db.find("SELECT * FROM hz_xc_sssj.qybaseinfo where UNISCID is not null");
                  for(Record r1:record2){
                      for (XcIndex xc : list) {
                          if (!xc.getIslast()) {
                              String str = "select e.id,e.cid,e.name,e.ename from xc_index e,(SELECT " +
                                      "c.cid FROM ( SELECT a.cid,IF (" +
                                      "FIND_IN_SET(a.pid ,@pids) > 0,IF (length(@pids) - length(" +
                                      "REPLACE (@pids, a.pid, '')) > 1,IF (" +
                                      "length(@pids) - length(REPLACE(@pids, a.cid, '')) > 1 ,@pids ,@pids := concat(@pids, ',', a.cid)" +
                                      ") ,@pids := concat(@pids, ',', a.cid)),0) AS 'plist'," +
                                      "IF (FIND_IN_SET(a.pid ,@pids) > 0,@pids,0) AS ischild FROM " +
                                      "(SELECT r.cid,r.pid FROM xc_index r) a,(SELECT @pids := " + xc.getCid() + ") b" +
                                      ") c WHERE c.ischild !=0) f where e.cid=f.cid and e.islast=true ";
                              List<Record> records = Db.find(str);
                              for (Record r : records) {
                                  if (StrKit.notBlank(r.getStr("ename"))) {
                                      List<Record> record11= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                                              "WHERE table_name=? AND table_schema='hz_xc_sssj'",r.getStr("ename"));
                                      String str1=getSql(record11,2);
                                      int i3=Db.findFirst("select count(*) num from hz_xc_sssj."+r.getStr("ename")+" where UNISCID=? and "+str1 ,r1.getStr("UNISCID")).getInt("num");

                                      if(i3>0){
                                          XcMsgcount.saveXc(1,xc.getCid(),r.getStr("cid"),i3,r1.getStr("UNISCID"),"");
                                      }
                                  }
                              }

                          } else {
                              if (StrKit.notBlank(xc.getEname())) {
                                  List<Record> record11= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
                                          "WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname());
                                  String str1=getSql(record11,2);
                                  int i3=Db.findFirst("select count(*) num from hz_xc_sssj."+xc.getEname()+" where  UNISCID=? and"+str1,r1.getStr("UNISCID")).getInt("num");
                                  if(i3>0){
                                      XcMsgcount.saveXc(1,xc.getCid(),xc.getCid(),i3,r1.getStr("UNISCID"),"");
                                  }
                              }

                          }
                          //arr.add(json1);

                      }
                  }
              }
          }




          renderJson(MsgUtil.successMsg("计算查询服务结果成功"));

  }
  public static String getSql(List<Record> r,int i){
     // List<Record> record= Db.find("SELECT COLUMN_NAME,column_comment FROM INFORMATION_SCHEMA.Columns " +
       //       "WHERE table_name=? AND table_schema='hz_xc_sssj'",xc.getEname());
      if(i==1) {
          String str = "(";
          for (Record record : r) {
             // System.out.println(!record.getStr("COLUMN_NAME").equals("id"));
              if (!record.getStr("COLUMN_NAME").equals("id") && !record.getStr("COLUMN_NAME").equals("update_time") && !record.getStr("COLUMN_NAME").equals("cardNum")
                      && !record.getStr("COLUMN_NAME").equals("name") && !record.getStr("COLUMN_NAME").equals("CZRKCSRQ") && !record.getStr("COLUMN_NAME").equals("CZRKZZ")
                      && !record.getStr("COLUMN_NAME").equals("CZRKMZ") && !record.getStr("COLUMN_NAME").equals("CZRKXB")
                      && !record.getStr("COLUMN_NAME").equals("sex")&& !record.getStr("COLUMN_NAME").equals("race")) {
                  str += record.getStr("COLUMN_NAME") + " is not null or ";
              }

          }
          str = str.substring(0, str.length() - 3) + ")";
          return str;
      }else{
          String str = "(";
          for (Record record : r) {
              if (!record.getStr("COLUMN_NAME").equals("id") && !record.getStr("COLUMN_NAME").equals("update_time") && !record.getStr("COLUMN_NAME").equals("QYMC")
                      && !record.getStr("COLUMN_NAME").equals("ZCH") && !record.getStr("COLUMN_NAME").equals("UNISCID")) {
                  str += record.getStr("COLUMN_NAME") + " is not null or ";
              }

          }
          str = str.substring(0, str.length() - 3) + ")";
          return str;
      }

  }
      public void test()throws UnsupportedEncodingException {
         String str= FullCharConverter.half2Fullchange(getPara("value"));
         renderJson(MsgUtil.successMsg(Db.find("select * from hz_xc_sssj.rkbaseinfo where czrkzz=?",str)));
      }
 public static  List<Record>  getTableMsg(String tableName,String tableSchema){
      return Db.find("SELECT column_name,column_comment FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema=? and column_comment!='' ", tableName,tableSchema);//and column_name!='id' and column_name='update_time'
  }
  public static List<Record> getTableMsg(List<Record> tableMsg,List<Record> value){
      HashSet<String> hashSet=new HashSet<String>();
      for(Record v:value) {
          for (Record r : tableMsg) {
              if(StrKit.notBlank(v.getStr(r.getStr("column_name")))){
                  hashSet.add(r.getStr("column_name"));
              }
          }
      }
      List<Record> list=new ArrayList<Record>();
      for(String str:hashSet){
          for (Record r : tableMsg) {
              if(str.equals(r.getStr("column_name"))){
                  list.add(r);
              }
          }
      }
      return  list;
  }*/
    public static void main(String[] args) {
        //System.out.println("COLUMN_NAME sssss and".substring(0,"COLUMN_NAME sssss and".length()-3));
        HashSet<String> hashSet=new HashSet<String>();
        hashSet.add("id");
        hashSet.add("id");
        hashSet.add("id");
        System.out.println(hashSet.size());
        for(String str:hashSet){
            System.out.println(str);
        }

    }

}
