package com.Bean;

/**
 * Created by 340144 on 2018/2/6.
 */

public class Addfunction {
    private String aName;
    private String aSpeak;
    private int aIcon;

    public  Addfunction(String aName, String aSpeak, int aIcon){
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;

    }
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }

    public int getaIcon() {
        return aIcon;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }
}
