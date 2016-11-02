package com.lionsquare.registros.Beans;

/**
 * Created by archivaldo on 04/10/16.
 */

public class Person {
    public String id;
    public String name;
    public String tel;
    public String address;
    public String curp;
    public String rfc;
    public String nds;
    public String afore;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAfore() {
        return afore;
    }

    public void setAfore(String afore) {
        this.afore = afore;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNds() {
        return nds;
    }

    public void setNds(String nds) {
        this.nds = nds;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person(String id, String name, String tel, String address, String crup, String rfc, String nds, String afore) {
        this.id=id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.curp = crup;
        this.rfc = rfc;
        this.nds = nds;
        this.afore = afore;
    }


}
