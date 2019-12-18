package com.trawell.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
/**
 * User
 * Questa classe è solo un esempio, dovrete definire bene field e metodi
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String mail;
    private String password;
    private String name;
    private String surname;
    private java.sql.Date birth;
    private int banned;
    private int profilePhoto;
    private String Phone;
    private String bio;

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getProfilePhoto() {
        return this.profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private boolean idAgency;
    private boolean isBanned;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getBirth() {
        return this.birth;
    }

    public void setBirth(java.sql.Date birth) {
        this.birth = birth;
    }

    public int getBanned() {
        return this.banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public boolean getIdAgency() {
        return this.idAgency;
    }

    public void setIdAgency(boolean idAgency) {
        this.idAgency = idAgency;
    }

    public boolean getIsBanned() {
        return this.isBanned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    @Transient
    private String transientVar;

    public Long getId(){
        return id;
    }

    public User() {
        super();
    }

    public User(String username) {
        super();
        this.username = username;
    }

    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(String transientVar) {
        this.transientVar = transientVar;
    }
}