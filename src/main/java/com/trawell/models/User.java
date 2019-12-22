package com.trawell.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
/**
 * User
 * Questa classe è solo un esempio, dovrete definire bene field e metodi
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mail;
    private String username;
    private String password;
    private String name;
    private String surname;
    private java.sql.Date birth;
    private boolean banned;
    private String bio;
    private int profilePhoto;
    private String phone;
    private boolean isAdmin;
    private boolean isBanned;

    public java.sql.Date getBirth() {
        return this.birth;
    }

    public void setBirth(final java.sql.Date birth) {
        this.birth = birth;
    }

    public boolean getBanned() {
        return this.banned;
    }

    public void setBanned(final boolean banned) {
        this.banned = banned;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public int getProfilePhoto() {
        return this.profilePhoto;
    }

    public void setProfilePhoto(final int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsBanned() {
        return this.isBanned;
    }

    public void setIsBanned(final boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Transient
    private String transientVar;

    public Long getId(){
        return id;
    }

    public void setId(final Long id){
        this.id = id;
    }

    public User() {
        super();
    }


    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(final String transientVar) {
        this.transientVar = transientVar;
    }
}