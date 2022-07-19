package com.ruoan.study.designpatterns.pipeline;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月15日 15:34:00
 */
public class Client {
    public static void main(String[] args) {
        Handler director = new Director("主管");
        Handler manager = new Manager("经理");
        Handler topManager = new TopManager("总经理");

        // 创建责任链
        director.setNextHandler(manager);
        manager.setNextHandler(topManager);

        // 发起请假申请
        boolean result1 = director.process(new LeaveRequest("小旋锋", 1));
        System.out.println("最终结果：" + result1 + "\n");

        boolean result2 = director.process(new LeaveRequest("小旋锋", 4));
        System.out.println("最终结果：" + result2 + "\n");

        boolean result3 = director.process(new LeaveRequest("小旋锋", 8));
        System.out.println("最终结果：" + result3 + "\n");
    }
}
