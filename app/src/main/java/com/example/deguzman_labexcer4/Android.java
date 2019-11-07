package com.example.deguzman_labexcer4;

public class Android {
    int logo;
    String android, level, rdate, version, dbmsg;

    public Android(int logo, String android, String level, String rdate, String version, String dbmsg) {
        this.logo = logo;
        this.android = android;
        this.level = level;
        this.version = version;
        this.rdate = rdate;
        this.dbmsg = dbmsg;

    }

    public int getLogo(){
        return logo;
    }

    public String getAndroid() {
        return android;
    }

    public String getLevel(){
        return level;
    }

    public String getVersion() {return version;}

    public String getRdate() {return rdate;}

    public String getDbmsg() {return dbmsg;}

}
