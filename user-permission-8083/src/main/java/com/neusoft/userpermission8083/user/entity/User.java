package com.neusoft.userpermission8083.user.entity;



import com.neusoft.userpermission8083.common.base.BaseEntity;

import javax.validation.constraints.NotNull;

public class User implements BaseEntity<String> {
//    @NotNull(groups={InsertGroup.class, UpdateGroup.class, SelectGroup.class, DeleteGroup.class}, message = "userid should not be null")
    String user_id;
    String username;
    String password;
    String name;
    String rights;
    int role_id;
    String last_login;
    String ip;
    String status;
    String bz;
    String skin;
    String email;
    String number;
    String phone;
    int man_buyer_id;
    Role role;

    public String getId() { return user_id; }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }


    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMan_buyer_id() {
        return man_buyer_id;
    }

    public void setMan_buyer_id(int man_buyer_id) {
        this.man_buyer_id = man_buyer_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", rights='" + rights + '\'' +
                ", role_id=" + role_id +
                ", last_login='" + last_login + '\'' +
                ", ip='" + ip + '\'' +
                ", status='" + status + '\'' +
                ", bz='" + bz + '\'' +
                ", skin='" + skin + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", man_buyer_id=" + man_buyer_id +
                ", role=" + role +
                '}';
    }
}
