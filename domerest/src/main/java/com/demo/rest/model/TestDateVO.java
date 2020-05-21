package com.demo.rest.model;

import java.io.Serializable;

/**
 * @author littlenew
 * @date 2020/5/18 4:39 PM
 **/
public class TestDateVO implements Serializable {
    private static final long serialVersionUID = -161668063453001497L;
    private String stuName;
    private Byte stuSex;
    private Integer stuAge;
    private String stuSchoolName;
    private String stuClassName;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Byte getStuSex() {
        return stuSex;
    }

    public void setStuSex(Byte stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuSchoolName() {
        return stuSchoolName;
    }

    public void setStuSchoolName(String stuSchoolName) {
        this.stuSchoolName = stuSchoolName;
    }

    public String getStuClassName() {
        return stuClassName;
    }

    public void setStuClassName(String stuClassName) {
        this.stuClassName = stuClassName;
    }

    @Override
    public String toString() {
        return "TestDateVO{" +
                "stuName='" + stuName + '\'' +
                ", stuSex=" + stuSex +
                ", stuAge=" + stuAge +
                ", stuSchoolName='" + stuSchoolName + '\'' +
                ", stuClassName='" + stuClassName + '\'' +
                '}';
    }
}
