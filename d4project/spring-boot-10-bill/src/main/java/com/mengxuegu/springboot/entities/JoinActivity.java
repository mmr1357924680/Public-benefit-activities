package com.mengxuegu.springboot.entities;

public class JoinActivity {
    private Integer id;
    private Integer uid;
    private Integer aid;
    private String comments;
    private String userName;
    private String activityName;
    private Integer state;

    public JoinActivity() {
    }

    public JoinActivity(Integer id, Integer uid, Integer aid, String comments, String userName, String activityName, Integer state) {
        this.id = id;
        this.uid = uid;
        this.aid = aid;
        this.comments = comments;
        this.userName = userName;
        this.activityName = activityName;
        this.state = state;
    }

    @Override
    public String toString() {
        return "JoinActivity{" +
                "id=" + id +
                ", uid=" + uid +
                ", aid=" + aid +
                ", comments='" + comments + '\'' +
                ", userName='" + userName + '\'' +
                ", activityName='" + activityName + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
