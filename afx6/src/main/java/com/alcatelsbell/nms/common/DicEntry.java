package com.alcatelsbell.nms.common;

/**
 * User: Ronnie
 * Date: 12-5-9
 * Time: 下午9:58
 */
public class DicEntry {
    public String desc;
    public int value;
    public String code;
    public String color;
    public DicEntry(String desc, int value, String code) {
        this.desc = desc;
        this.value = value;
        this.code = code;
    }
    

    
    public DicEntry(String desc, int value, String code, String color) {
        this.desc = desc;
        this.value = value;
        this.code = code;
        this.color = color;
    }

    public DicEntry(String desc, int value) {
        this(desc,value,"");
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
