package com.mengxuegu.springboot.entities;


public class Activity {
    private Integer id;
    private String name;
    private String content;
    private Integer state;

    public Activity(Integer id, String name, String content,Integer state) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.state = state;
    }

    public Activity() {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }
}
