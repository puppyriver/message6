package com.alcatelsbell.nms.common;

/**
 * Author: Ronnie.Chen
 * Date: 14-1-26
 * Time: 下午3:06
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class Dic {
    private String name;
    private String type;
    private String desc;

    public Dic(String name, String type, String desc) {
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Dic{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
