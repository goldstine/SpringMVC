package org.goldstine.bean;

public class Employee {
    private Integer id;
    private String empName;
    private Integer gender;
    private String email;
    private String loginAcc;

    public Employee() {
    }

    public Employee(String empName, Integer gender, String email, String loginAcc) {
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.loginAcc = loginAcc;
    }

    public Employee(Integer id, String empName, Integer gender, String email, String loginAcc) {
        this.id = id;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.loginAcc = loginAcc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginAcc() {
        return loginAcc;
    }

    public void setLoginAcc(String loginAcc) {
        this.loginAcc = loginAcc;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", loginAcc='" + loginAcc + '\'' +
                '}';
    }
}
