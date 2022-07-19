package com.ruoan.study.observer;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月22日 17:49:00
 */
public class Event {

    private Thief thief;

    public Event() {
    }

    public Event(Thief thief) {
        this.thief = thief;
    }

    public Thief getThief() {
        return thief;
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }
}
