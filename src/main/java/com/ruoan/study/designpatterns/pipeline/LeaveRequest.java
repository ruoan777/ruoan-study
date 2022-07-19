package com.ruoan.study.designpatterns.pipeline;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月15日 15:29:00
 */
public class LeaveRequest {

    // 请假人姓名
    private String name;

    // 请假天数
    private int numOfDays;

    public LeaveRequest(String name, int numOfDays) {
        this.name = name;
        this.numOfDays = numOfDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "name='" + name + '\'' +
                ", numOfDays=" + numOfDays +
                '}';
    }
}
