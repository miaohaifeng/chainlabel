package com.happy.video.pojo;

public class TSignLibrary {
    private Integer fId;

    private String fSignId;

    private String fSignCreatedAt;

    private String fSignTextSignature;

    private String fSignHexSignature;

    private String fSignBytesSignature;

    private String action;

    private String fType;

    private String fCreateAt;

    private String fUpdateAt;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfSignId() {
        return fSignId;
    }

    public void setfSignId(String fSignId) {
        this.fSignId = fSignId == null ? null : fSignId.trim();
    }

    public String getfSignCreatedAt() {
        return fSignCreatedAt;
    }

    public void setfSignCreatedAt(String fSignCreatedAt) {
        this.fSignCreatedAt = fSignCreatedAt == null ? null : fSignCreatedAt.trim();
    }

    public String getfSignTextSignature() {
        return fSignTextSignature;
    }

    public void setfSignTextSignature(String fSignTextSignature) {
        this.fSignTextSignature = fSignTextSignature == null ? null : fSignTextSignature.trim();
    }

    public String getfSignHexSignature() {
        return fSignHexSignature;
    }

    public void setfSignHexSignature(String fSignHexSignature) {
        this.fSignHexSignature = fSignHexSignature == null ? null : fSignHexSignature.trim();
    }

    public String getfSignBytesSignature() {
        return fSignBytesSignature;
    }

    public void setfSignBytesSignature(String fSignBytesSignature) {
        this.fSignBytesSignature = fSignBytesSignature == null ? null : fSignBytesSignature.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public String getfCreateAt() {
        return fCreateAt;
    }

    public void setfCreateAt(String fCreateAt) {
        this.fCreateAt = fCreateAt == null ? null : fCreateAt.trim();
    }

    public String getfUpdateAt() {
        return fUpdateAt;
    }

    public void setfUpdateAt(String fUpdateAt) {
        this.fUpdateAt = fUpdateAt == null ? null : fUpdateAt.trim();
    }
}