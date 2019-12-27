package com.trawell.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
/**
 * User
 * Questa classe è solo un esempio, dovrete definire bene field e metodi
 */
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    private Long idUser;
    
    private String complaintObject;
    private String complaintDescription;
    private String complaintMail;

    @Transient //variable that doesn't have to be saved in the DB
    private String transientVar;

    public Complaint()
    {
        super();
    }

    public Complaint(String ComplaintObject, String ComplaintDescription, Long idUser, String mail) {
        super();
        this.complaintDescription = ComplaintDescription;
        this.complaintObject = ComplaintObject;
        this.idUser = idUser;
        this.complaintMail = mail;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getComplaintObject() {
        return this.complaintObject;
    }

    public void setComplaintObject(String ComplaintObject) {
        this.complaintObject = ComplaintObject;
    }

    public String getComplaintDescription() {
        return this.complaintDescription;
    }

    public void setComplaintDescription(String ComplaintDescription) {
        this.complaintDescription = ComplaintDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Long getId(){
        return id;
    }
   
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idUser='" + getIdUser() + "'" +
            ", complaintObject='" + getComplaintObject() + "'" +
            ", complaintDescription='" + getComplaintDescription() + "'" +
            ", mail='" + getMail() + "'" +
            "}";
    }


    public String getMail() {
        return this.complaintMail;
    }

    public void setMail(String mail) {
        this.complaintMail = mail;
    }
    
}