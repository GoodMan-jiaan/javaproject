package com.example.demo.entity;

/**
 * 用户个人信息中，
 * 返回用户所包含的视频集信息，作为下拉点击
 */
public class VideoesgatherResult {
    private String value;
    private String label;

    @Override
    public String toString() {
        return "VideoesgatherResult{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
