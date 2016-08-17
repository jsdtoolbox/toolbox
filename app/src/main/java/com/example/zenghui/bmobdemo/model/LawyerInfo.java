package com.example.zenghui.bmobdemo.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 16/8/13.
 */
public class LawyerInfo {

    String reason;
    List<LawyerInfoItem> result;
    int error_code;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public  List<LawyerInfoItem> getResult() {
        return result;
    }

    public void setResult( List<LawyerInfoItem> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
   public class LawyerInfoItem implements Serializable {
        /**
         * spec :
         * tel : 13328326991
         * img : http://yuli8.cn/kk/law4/photo/20061122104217.jpg
         * addr : 厦门国贸大厦29楼Ｃ、Ｄ座
         * corp : 福建厦门启和律师事务所
         * desp :
         * city : 厦门
         * area : 厦门
         * email :
         * name : 陈跃通律师
         * province : 福建
         * idno : 13042004110827
         * qq :
         * mobile : 13328326991
         */

        private static final long serialVersionUID = 1L;
        private String spec;
        private String tel;
        private String img;
        private String addr;
        private String corp;
        private String desp;
        private String city;
        private String area;
        private String email;
        private String name;
        private String province;
        private String idno;
        private String qq;
        private String mobile;

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getCorp() {
            return corp;
        }

        public void setCorp(String corp) {
            this.corp = corp;
        }

        public String getDesp() {
            return desp;
        }

        public void setDesp(String desp) {
            this.desp = desp;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getIdno() {
            return idno;
        }

        public void setIdno(String idno) {
            this.idno = idno;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
