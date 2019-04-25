package cn.icheny.ideaplugin.translation.bean;

import cn.icheny.ideaplugin.translation.util.TextUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Youdao {


    /**
     * translation : ["的名字"]
     * basic : {"us-phonetic":"nem","phonetic":"neɪm","uk-phonetic":"neɪm","explains":["n. 名称，名字；姓名；名誉","adj. 姓名的；据以取名的","vt. 命名，任命；指定；称呼；提名；叫出","n. (Name)人名；(日)滑(姓)；(英)内姆"]}
     * query : name
     * errorCode : 0
     * web : [{"value":["代号","开发代号","产品代号"],"key":"code name"},{"value":["主机名","主机名称","主机的名称"],"key":"Host name"},{"value":["零件名称","名称","阀体"],"key":"Part Name"}]
     */

    private Basic basic;
    private String query;
    private int errorCode;
    private List<String> translation;
    private List<Web> web;

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<Web> getWeb() {
        return web;
    }

    public void setWeb(List<Web> web) {
        this.web = web;
    }

    public static class Basic {
        /**
         * us-phonetic : nem
         * phonetic : neɪm
         * uk-phonetic : neɪm
         * explains : ["n. 名称，名字；姓名；名誉","adj. 姓名的；据以取名的","vt. 命名，任命；指定；称呼；提名；叫出","n. (Name)人名；(日)滑(姓)；(英)内姆"]
         */

        @SerializedName("us-phonetic")
        private String usphonetic;
        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        private List<String> explains;

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }

        @Override
        public String toString() {
            final StringBuilder explainsSB = new StringBuilder();
            if (explains != null) {
                for (String e :explains) {
                    explainsSB.append(e + "\n");
                }
            }
            return explainsSB.toString();
        }
    }

    public static class Web {
        /**
         * value : ["代号","开发代号","产品代号"]
         * key : code name
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        @Override
        public String toString() {
            String text = key + "：" + value.toString();
            return text;
        }
    }

    @Override
    public String toString() {
        final StringBuilder transSB = new StringBuilder();
        if (translation != null) {
            for (String s : translation) {
                transSB.append(s + " ");
            }
        }
        String trans = transSB.toString();

        String explains = basic==null?null:basic.toString();

        final StringBuilder websSB = new StringBuilder();
        if (web != null) {
            for (Web w : web) {
                websSB.append(w.toString() + "\n");
            }
        }
        String webs = websSB.toString();
        String text = "来源于有道词典：\n" + query + "：" + (TextUtils.isEmpty(trans) ? "" : (trans + "\n"))
                + (TextUtils.isEmpty(explains) ? "" : ("基本释义：\n" + explains))
                + (TextUtils.isEmpty(webs) ? "" : ("网络释义：\n" + webs));
        return text;
    }
}
