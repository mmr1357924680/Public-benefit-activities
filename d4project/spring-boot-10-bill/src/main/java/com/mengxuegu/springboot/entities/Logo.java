package com.mengxuegu.springboot.entities;

public class Logo {
    private Integer id;
    private String pictureLink;
    private Integer showStatus;
    public Logo(Integer id, String pictureLink, Integer showStatus) {
        this.id = id;
        this.pictureLink = pictureLink;
        this.showStatus = showStatus;
    }
    public Logo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "Logo{" +
                "id=" + id +
                ", pictureLink='" + pictureLink + '\'' +
                ", showStatus=" + showStatus +
                '}';
    }
}
