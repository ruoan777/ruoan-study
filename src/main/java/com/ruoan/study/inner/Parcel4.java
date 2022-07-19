package com.ruoan.study.inner;

/**
 * @author xh.gao
 * @Description 内部类与向上转型
 * @createTime 2020年06月27日 11:30:00
 */
public class Parcel4 {

    private class PContents implements Contents {

        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {

        private String label;

        public PDestination(String label) {
            this.label = label;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }
}
