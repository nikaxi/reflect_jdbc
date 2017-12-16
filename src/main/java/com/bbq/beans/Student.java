package com.bbq.beans;

import java.math.BigInteger;
import java.sql.Date;

public class Student {
    private Long id;
    private String name;
    private String QQ;
    private Long careerType;
    private Date   entryDate;  //入学时间
    private String school;
    private String idOnSite;
    private String dailyReportUrl;
    private String brotherName;
    private long   createAt;
    private long   updateAt;
    private String desire;

    public Student() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public Long getCareerType() {
        return careerType;
    }

    public void setCareerType(Long careerType) {
        this.careerType = careerType;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIdOnSite() {
        return idOnSite;
    }

    public void setIdOnSite(String idOnSite) {
        this.idOnSite = idOnSite;
    }

    public String getDailyReportUrl() {
        return dailyReportUrl;
    }

    public void setDailyReportUrl(String dailyReportUrl) {
        this.dailyReportUrl = dailyReportUrl;
    }

    public String getBrotherName() {
        return brotherName;
    }

    public void setBrotherName(String brotherName) {
        this.brotherName = brotherName;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public String getDesire() {
        return desire;
    }

    public void setDesire(String desire) {
        this.desire = desire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " +
                name + " " +
                QQ   + " " +
                careerType + " " +
                school + " " +
                entryDate + " " +
                idOnSite + " " +
                dailyReportUrl + " " +
                brotherName + " " +
                createAt + " " +
                updateAt + " " +
                desire;
    }
}
