package com.neusoft.imagemanager8082.image.entity;


public class Image {
    int img_id;
    String name;
    int width;
    int height;
    String uri;
    String type_cd;
    int entity_id;
    String entity_cd;
    int seq_no;
    String created_by;
    String creation_date;
    String last_update_by;
    String last_update_date;
    int call_cnt;
    String remark;
    char sts_cd;

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType_cd() {
        return type_cd;
    }

    public void setType_cd(String type_cd) {
        this.type_cd = type_cd;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_cd() {
        return entity_cd;
    }

    public void setEntity_cd(String entity_cd) {
        this.entity_cd = entity_cd;
    }

    public int getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getLast_update_by() {
        return last_update_by;
    }

    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getCall_cnt() {
        return call_cnt;
    }

    public void setCall_cnt(int call_cnt) {
        this.call_cnt = call_cnt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public char getSts_cd() {
        return sts_cd;
    }

    public void setSts_cd(char sts_cd) {
        this.sts_cd = sts_cd;
    }

    @Override
    public String toString() {
        return "Image{" +
                "img_id=" + img_id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", uri='" + uri + '\'' +
                ", type_cd='" + type_cd + '\'' +
                ", entity_id=" + entity_id +
                ", entity_cd='" + entity_cd + '\'' +
                ", seq_no=" + seq_no +
                ", created_by='" + created_by + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", last_update_by='" + last_update_by + '\'' +
                ", last_update_date='" + last_update_date + '\'' +
                ", call_cnt=" + call_cnt +
                ", remark='" + remark + '\'' +
                ", sts_cd=" + sts_cd +
                '}';
    }
}
