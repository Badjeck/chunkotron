package com.chuncking.json;

import java.util.List;

public class Country {
    public String filePath;
    public String Key;
    public List<String[]> copy1;
    public List<String[]> copy2;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public List<String[]> getCopy1() {
        return copy1;
    }

    public void setCopy1(List<String[]> copy1) {
        this.copy1 = copy1;
    }

    public List<String[]> getCopy2() {
        return copy2;
    }

    public void setCopy2(List<String[]> copy2) {
        this.copy2 = copy2;
    }
}
