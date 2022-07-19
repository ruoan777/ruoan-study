package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月25日 11:05:00
 */
public abstract class IntGenerator {
   private volatile boolean canceled =false;
   public abstract  int next();
   public void cancel(){
       canceled=true;
   }

    public boolean isCanceled() {
        return canceled;
    }
}
