/*Mario Paone*/
package com.trawell.models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author Mario Paone
 * 
 */

@Entity
public class BanData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long idAdmin;
    private Date banUntil;
    private String motivation;

    public BanData() {
    }

    public BanData(Long id, String username, Long idAdmin, Date banUntil, String motivation) {
        this.id = id;
        this.username = username;
        this.idAdmin = idAdmin;
        this.banUntil = banUntil;
        this.motivation = motivation;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIdAdmin() {
        return this.idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Date getBanUntil() {
        return this.banUntil;
    }

    public void setBanUntil(Date banUntil) {
        this.banUntil = banUntil;
    }

    public String getMotivation() {
        return this.motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public BanData id(Long id) {
        this.id = id;
        return this;
    }

    public BanData username(String username) {
        this.username = username;
        return this;
    }

    public BanData idAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
        return this;
    }

    public BanData banUntil(Date banUntil) {
        this.banUntil = banUntil;
        return this;
    }

    public BanData motivation(String motivation) {
        this.motivation = motivation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BanData)) {
            return false;
        }
        BanData banData = (BanData) o;
        return Objects.equals(id, banData.id) && Objects.equals(username, banData.username) && Objects.equals(idAdmin, banData.idAdmin) && Objects.equals(banUntil, banData.banUntil) && Objects.equals(motivation, banData.motivation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, idAdmin, banUntil, motivation);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", idAdmin='" + getIdAdmin() + "'" +
            ", banUntil='" + getBanUntil() + "'" +
            ", motivation='" + getMotivation() + "'" +
            "}";
    }

    
    

    
}
