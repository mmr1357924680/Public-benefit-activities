package com.mengxuegu.springboot.entities;

public class Statistics {
    private double passRatio;
    private double actAvg;
    private double actAvgComment;

    public Statistics(double passRatio, double actAvg, double actAvgComment) {
        this.passRatio = passRatio;
        this.actAvg = actAvg;
        this.actAvgComment = actAvgComment;
    }

    public Statistics() {
    }

    public double getPassRatio() {
        return passRatio;
    }

    public void setPassRatio(double passRatio) {
        this.passRatio = passRatio;
    }

    public double getActAvg() {
        return actAvg;
    }

    public void setActAvg(double actAvg) {
        this.actAvg = actAvg;
    }

    public double getActAvgComment() {
        return actAvgComment;
    }

    public void setActAvgComment(double actAvgComment) {
        this.actAvgComment = actAvgComment;
    }
}
