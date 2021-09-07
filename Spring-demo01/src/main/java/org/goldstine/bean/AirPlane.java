package org.goldstine.bean;

public class AirPlane {
    private String fdj;
    private String fdjName;
    private String jzName;
    private Integer personNum;
    private String yc;

    public AirPlane() {
    }

    public AirPlane(String fdj, String fdjName, String jzName, Integer personNum, String yc) {
        this.fdj = fdj;
        this.fdjName = fdjName;
        this.jzName = jzName;
        this.personNum = personNum;
        this.yc = yc;
    }

    public String getFdj() {
        return fdj;
    }

    public void setFdj(String fdj) {
        this.fdj = fdj;
    }

    public String getFdjName() {
        return fdjName;
    }

    public void setFdjName(String fdjName) {
        this.fdjName = fdjName;
    }

    public String getJzName() {
        return jzName;
    }

    public void setJzName(String jzName) {
        this.jzName = jzName;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getYc() {
        return yc;
    }

    public void setYc(String yc) {
        this.yc = yc;
    }

    @Override
    public String toString() {
        return "AirPlane{" +
                "fdj='" + fdj + '\'' +
                ", fdjName='" + fdjName + '\'' +
                ", jzName='" + jzName + '\'' +
                ", personNum=" + personNum +
                ", yc='" + yc + '\'' +
                '}';
    }
}
