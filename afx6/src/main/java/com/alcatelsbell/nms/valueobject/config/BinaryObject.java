package com.alcatelsbell.nms.valueobject.config;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

@Table(name = "BINARYOBJECT")
@Entity
public class BinaryObject extends BObject{
	
	private String name;
	private int type;
	private int category;
	private int subcategory;
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private byte[] content;
	private String fileName;

    @Column(name="key1")
    private String key;
	private long userId;
	private String comments;
    private String correlationValue;
    private String correlationType;


    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(int subcategory) {
		this.subcategory = subcategory;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

    public String getCorrelationValue() {
        return correlationValue;
    }

    public void setCorrelationValue(String correlationValue) {
        this.correlationValue = correlationValue;
    }

    public String getCorrelationType() {
        return correlationType;
    }

    public void setCorrelationType(String correlationType) {
        this.correlationType = correlationType;
    }
}
