package com.neusoft.wallet8086.wallet.entity;


import com.neusoft.wallet8086.common.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class WalletTransactionAudit implements BaseEntity<Integer> {

    int transaction_audit_id;
    int buyer_id;
    int transaction_id;
    BigDecimal available_money_before;
    BigDecimal depositing_money_before;
    BigDecimal withdrawing_money_before;
    BigDecimal operate_money;
    int operate_type;
    BigDecimal available_money_after;
    BigDecimal depositing_money_after;
    BigDecimal withdrawing_money_after;
    String operate_by;
    int status;
    String create_by;
    Date create_time;
    String last_update_by;
    Date last_update_time;
    int img_id;
    String img_url;

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public Integer getId() {
        return transaction_audit_id;
    }

    public int getTransaction_audit_id() {
        return transaction_audit_id;
    }

    public void setTransaction_audit_id(int transaction_audit_id) {
        this.transaction_audit_id = transaction_audit_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public BigDecimal getAvailable_money_before() {
        return available_money_before;
    }

    public void setAvailable_money_before(BigDecimal available_money_before) {
        this.available_money_before = available_money_before;
    }

    public BigDecimal getDepositing_money_before() {
        return depositing_money_before;
    }

    public void setDepositing_money_before(BigDecimal depositing_money_before) {
        this.depositing_money_before = depositing_money_before;
    }

    public BigDecimal getWithdrawing_money_before() {
        return withdrawing_money_before;
    }

    public void setWithdrawing_money_before(BigDecimal withdrawing_money_before) {
        this.withdrawing_money_before = withdrawing_money_before;
    }

    public BigDecimal getOperate_money() {
        return operate_money;
    }

    public void setOperate_money(BigDecimal operate_money) {
        this.operate_money = operate_money;
    }

    public int getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(int operate_type) {
        this.operate_type = operate_type;
    }

    public BigDecimal getAvailable_money_after() {
        return available_money_after;
    }

    public void setAvailable_money_after(BigDecimal available_money_after) {
        this.available_money_after = available_money_after;
    }

    public BigDecimal getDepositing_money_after() {
        return depositing_money_after;
    }

    public void setDepositing_money_after(BigDecimal depositing_money_after) {
        this.depositing_money_after = depositing_money_after;
    }

    public BigDecimal getWithdrawing_money_after() {
        return withdrawing_money_after;
    }

    public void setWithdrawing_money_after(BigDecimal withdrawing_money_after) {
        this.withdrawing_money_after = withdrawing_money_after;
    }

    public String getOperate_by() {
        return operate_by;
    }

    public void setOperate_by(String operate_by) {
        this.operate_by = operate_by;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getLast_update_by() {
        return last_update_by;
    }

    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    public Date getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    @Override
    public String toString() {
        return "WalletTransactionAudit{" +
                "transaction_audit_id=" + transaction_audit_id +
                ", buyer_id=" + buyer_id +
                ", transaction_id=" + transaction_id +
                ", available_money_before=" + available_money_before +
                ", depositing_money_before=" + depositing_money_before +
                ", withdrawing_money_before=" + withdrawing_money_before +
                ", operate_money=" + operate_money +
                ", operate_type=" + operate_type +
                ", available_money_after=" + available_money_after +
                ", depositing_money_after=" + depositing_money_after +
                ", withdrawing_money_after=" + withdrawing_money_after +
                ", operate_by='" + operate_by + '\'' +
                ", status=" + status +
                ", create_by='" + create_by + '\'' +
                ", create_time=" + create_time +
                ", last_update_by='" + last_update_by + '\'' +
                ", last_update_time=" + last_update_time +
                ", img_id=" + img_id +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}

