package com.balala.myapplication.bean;

public class Identifying extends AllBean {


    private String phone;

    private String purpose;

    private String sign2;


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

    public String getSign2() {
        return sign2;
    }

    public void setSign2(String sign2) {
        this.sign2 = sign2;
    }

    public Identifying(String user_id, String version, String current_device, int unique_identifier, String user_defined_name, String download_channel, String phone_version, int phone_model, String wx_unionid, String request_start_time) {
        super(user_id, version, current_device, unique_identifier, user_defined_name, download_channel, phone_version, phone_model, wx_unionid, request_start_time);
    }

    public Identifying() {

    }
}
