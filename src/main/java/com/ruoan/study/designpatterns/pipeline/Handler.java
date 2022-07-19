package com.ruoan.study.designpatterns.pipeline;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月15日 15:28:00
 */
public abstract class Handler {
    // 处理者姓名
    protected String name;
    // 下一个处理者                                           
    Handler nextHandler;

    Handler(String name) {
        this.name = name;
    }

    /**
     * 处理请假
     */
    public abstract boolean process(LeaveRequest leaveRequest);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
