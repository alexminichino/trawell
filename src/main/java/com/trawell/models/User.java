package com.trawell.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
/**
 * @author Milione Vincent 
 * class models a User that interacts with our platform
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

    public User(Long id, String mail, String username, String password, String name, String surname, java.sql.Date birth, boolean banned, String bio, int profilePhoto, String phone, boolean isAdmin, boolean isBanned, String transientVar) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.banned = banned;
        this.bio = bio;
        this.profilePhoto = profilePhoto;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.transientVar = transientVar;
    }

    public boolean isBanned() {
        return this.banned;
    }

    public boolean isIsAdmin() {
        return this.isAdmin;
    }

    public boolean isIsBanned() {
        return this.isBanned;
    }

    public String getTransientVar() {
        return this.transientVar;
    }


    public User id(Long id) {
        this.id = id;
        return this;
    }

    public User mail(String mail) {
        this.mail = mail;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public User surname(String surname) {
        this.surname = surname;
        return this;
    }

    public User birth(java.sql.Date birth) {
        this.birth = birth;
        return this;
    }

    public User banned(boolean banned) {
        this.banned = banned;
        return this;
    }

    public User bio(String bio) {
        this.bio = bio;
        return this;
    }

    public User profilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
        return this;
    }

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    public User isAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public User isBanned(boolean isBanned) {
        this.isBanned = isBanned;
        return this;
    }

    public User transientVar(String transientVar) {
        this.transientVar = transientVar;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(mail, user.mail) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(birth, user.birth) && banned == user.banned && Objects.equals(bio, user.bio) && profilePhoto == user.profilePhoto && Objects.equals(phone, user.phone) && isAdmin == user.isAdmin && isBanned == user.isBanned && Objects.equals(transientVar, user.transientVar);
    }

    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", mail='" + getMail() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birth='" + getBirth() + "'" +
            ", banned='" + isBanned() + "'" +
            ", bio='" + getBio() + "'" +
            ", profilePhoto='" + getProfilePhoto() + "'" +
            ", phone='" + getPhone() + "'" +
            ", isAdmin='" + isIsAdmin() + "'" +
            ", isBanned='" + isIsBanned() + "'" +
            ", transientVar='" + getTransientVar() + "'" +
            "}";
    }


    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(final String transientVar) {
        this.transientVar = transientVar;
    }
}