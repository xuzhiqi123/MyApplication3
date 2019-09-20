package com.example.myapplication.bean;

public class CodeBean {
    /**
     * data : {"code":97407,"key":"d51b5bd4-daaf-11e9-8999-acde48001122","phone":"19965772170","purpose":"找回密码","time":{"$date":1568907234000}}
     * msg : 发送成功
     * page : 0
     * pageSize : 0
     * success : true
     * total : 0
     */

    private DataBean data;
    private String msg;
    private String page;
    private int pageSize;
    private boolean success;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * code : 97407
         * key : d51b5bd4-daaf-11e9-8999-acde48001122
         * phone : 19965772170
         * purpose : 找回密码
         * time : {"$date":1568907234000}
         */

        private int code;
        private String key;
        private String phone;
        private String purpose;
        private TimeBean time;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * $date : 1568907234000
             */

            private long $date;

            public long get$date() {
                return $date;
            }

            public void set$date(long $date) {
                this.$date = $date;
            }
        }
    }


    /**
     * phone 获取手机号
     * purpose 注册或找回密码
     * sign2 MD加密后（手机号+请求时间+Android密钥 ）jkguyewiurewbkjrejgrwegrkhsdfoiew
     */


}
