package cn.icheny.ideaplugin.translation.bean;

import java.util.List;

public class Ciba {


    /**
     * word_name : public
     * is_CRI : 1
     * exchange : {"word_pl":["publics"],"word_third":"","word_past":"","word_done":"","word_ing":"","word_er":"","word_est":""}
     * symbols : [{"ph_en":"ˈpʌblɪk","ph_am":"ˈpʌblɪk","ph_other":"","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/7d/6d/7d6d2833e97760352874e2b7cf716b9c.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/4c/91/4c9184f37cff01bcdc32dc486ec36961.mp3","ph_tts_mp3":"http://res-tts.iciba.com/4/c/9/4c9184f37cff01bcdc32dc486ec36961.mp3","parts":[{"part":"adj.","means":["公众的，公共的","公开的","政府的","人人知道的，知名的"]},{"part":"n.","means":["大众","社会","公共场所","（文学家等的）爱读者"]}]}]
     */

    private String word_name;
    private String is_CRI;
    private List<Symbols> symbols;

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public String getIs_CRI() {
        return is_CRI;
    }

    public void setIs_CRI(String is_CRI) {
        this.is_CRI = is_CRI;
    }


    public List<Symbols> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbols> symbols) {
        this.symbols = symbols;
    }


    public static class Symbols {
        /**
         * ph_en : ˈpʌblɪk
         * ph_am : ˈpʌblɪk
         * ph_other :
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/oxford/0/7d/6d/7d6d2833e97760352874e2b7cf716b9c.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/4c/91/4c9184f37cff01bcdc32dc486ec36961.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/4/c/9/4c9184f37cff01bcdc32dc486ec36961.mp3
         * parts : [{"part":"adj.","means":["公众的，公共的","公开的","政府的","人人知道的，知名的"]},{"part":"n.","means":["大众","社会","公共场所","（文学家等的）爱读者"]}]
         */

        private String ph_en;
        private String ph_am;
        private String ph_other;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private String word_symbol;
        private List<Parts> parts;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_other() {
            return ph_other;
        }

        public void setPh_other(String ph_other) {
            this.ph_other = ph_other;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<Parts> getParts() {
            return parts;
        }

        public void setParts(List<Parts> parts) {
            this.parts = parts;
        }

        public void setWord_symbol(String word_symbol) {
            this.word_symbol = word_symbol;
        }

        public static class Parts {
            /**
             * part : adj.
             * means : ["公众的，公共的","公开的","政府的","人人知道的，知名的"]
             */
            private String part;
            private List<String> means;

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public List<String> getMeans() {
                return means;
            }

            public void setMeans(List<String> means) {
                this.means = means;
            }

            @Override
            public String toString() {
                StringBuilder meansSB = new StringBuilder(part);
                meansSB.append(" ");
                for (String m : means) {
                    meansSB.append(m + "，");
                }
                return meansSB.toString();
            }
            public static class Mean{

                /**
                 * word_mean : hello
                 * has_mean : 1
                 * split : 1
                 */

                private String word_mean;
                private int has_mean;
                private int split;

                public String getWord_mean() {
                    return word_mean;
                }

                public void setWord_mean(String word_mean) {
                    this.word_mean = word_mean;
                }

                public int getHas_mean() {
                    return has_mean;
                }

                public void setHas_mean(int has_mean) {
                    this.has_mean = has_mean;
                }

                public int getSplit() {
                    return split;
                }

                public void setSplit(int split) {
                    this.split = split;
                }
            }

        }

        @Override
        public String toString() {
            String readVovice = ph_en == null ? "" : ("英式：" + ph_en + "  ") + (ph_am == null ? "" : ("美式：" + ph_am ))
                    + (word_symbol == null ? "" : ("拼音：" + word_symbol));
            StringBuilder partsSB = new StringBuilder();
            if (parts != null) {
                for (Parts p : parts) {
                    partsSB.append(p.toString()+"\n");
                }
            }
            String parts = partsSB.toString();
            return (readVovice == null ? "" : readVovice )+ (parts == null ? "" : ("\n" + parts));
        }
    }

    @Override
    public String toString() {
        final StringBuilder symbolsSB = new StringBuilder();
        if (symbols != null) {
            for (Symbols s : symbols) {
                symbolsSB.append(s.toString() + "\n");
            }
        }
        String symbols = symbolsSB.toString();
        return "来源于金山词霸翻译：\n" + word_name + "\n"+symbols;
    }
}
