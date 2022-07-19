package com.ruoan.study.designpatterns.pipeline;

import java.util.Random;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月15日 15:30:00
 */
public class Director extends Handler {

    public Director(String name) {
        super(name);
    }

    @Override
    public boolean process(LeaveRequest leaveRequest) {
        boolean result = (new Random().nextInt(10)) > 3;
        String log = "主管<%s> 审批 <%s> 的请假申请，请假天数： <%d> ，审批结果：<%s> ";
        System.out.println(String.format(log,
                this.name,
                leaveRequest.getName(),
                leaveRequest.getNumOfDays(),
                result ? "批准" : "不批准"));
        if (!result) {
            return false;
        } else if (leaveRequest.getNumOfDays() < 3) {
            return true;
        }
        return nextHandler.process(leaveRequest);
    }
}
