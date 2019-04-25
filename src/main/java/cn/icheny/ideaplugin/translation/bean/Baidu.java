package cn.icheny.ideaplugin.translation.bean;

import java.util.List;

public class Baidu {


    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"System","dst":"系统"}]
     */

    private String from;
    private String to;
    private List<TransResult> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResult> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResult> trans_result) {
        this.trans_result = trans_result;
    }

    public static class TransResult {
        /**
         * src : System
         * dst : 系统
         */

        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        @Override
        public String toString() {
            return src + "：" + dst;
        }
    }

    @Override
    public String toString() {
        final StringBuilder resultSB = new StringBuilder();
        if (trans_result != null) {
            for (TransResult r : trans_result) {
                resultSB.append(r.toString() + "\n");
            }
        }
        String result = resultSB.toString();
        return "来源于百度翻译：\n" + result;
    }
}
