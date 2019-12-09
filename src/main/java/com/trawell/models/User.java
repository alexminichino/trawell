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
     * @param username the username to set
     */
    public void setText(String username) {
        this.username = username;
    }

    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(String transientVar) {
        this.transientVar = transientVar;
    }
}