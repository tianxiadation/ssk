package util.node;

import model.ssk.XcIndex;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String cid;
    private String name;
    private String ename;
   // private String remarks;
    private String pid;
    private Long id;
    private int level;
    private int type;
    private boolean islast;
    private List<Node> children = new ArrayList<>();
    public static Node getNode(XcIndex arr){
        return  new Node(arr.getCid(),arr.getName(),arr.getEname(),arr.getPid(),arr.getId(),arr.getLevel(),arr.getType(),arr.getIslast());

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Node(String cid, String name, String ename, String pid, Long id, int level, int type, boolean islast) {
        this.cid = cid;
        this.name = name;
        this.ename = ename;
        this.pid = pid;
        this.id = id;
        this.level = level;
        this.type = type;
        this.islast = islast;
    }



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isIslast() {
        return islast;
    }

    public void setIslast(boolean islast) {
        this.islast = islast;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

   /* public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }*/

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
