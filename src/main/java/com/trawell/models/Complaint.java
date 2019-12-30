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
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    private Long idUser;
    
    private String complaintObject;
    private String complaintDescription;
    private String complaintMail;
    private Long idAnswerer;
    private boolean complaintAnswered;
    private String complaintAnswere;

    
    

    @Transient //variable that doesn't have to be saved in the DB
    private String transientVar;

    public Complaint()
    {
        super();
    }


    public Complaint(Long id, Long idUser, String complaintObject, String complaintDescription, String complaintMail, Long idAnswerer, boolean complaintAnswered, String complaintAnswere) {
        this.id = id;
        this.idUser = idUser;
        this.complaintObject = complaintObject;
        this.complaintDescription = complaintDescription;
        this.complaintMail = complaintMail;
        this.idAnswerer = idAnswerer;
        this.complaintAnswered = complaintAnswered;
        this.complaintAnswere = complaintAnswere;
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

    public String getMail() {
        return this.complaintMail;
    }

    public void setMail(String mail) {
        this.complaintMail = mail;
    }
    
    public Long getIdAnswerer() {
        return this.idAnswerer;
    }

    public void setIdAnswerer(Long idAnswerer) {
        this.idAnswerer = idAnswerer;
    }

    public boolean isComplaintAnswered() {
        return this.complaintAnswered;
    }

    public boolean getComplaintAnswered() {
        return this.complaintAnswered;
    }

    public void setComplaintAnswered(boolean complaintAnswered) {
        this.complaintAnswered = complaintAnswered;
    }

    public String getComplaintAnswere() {
        return this.complaintAnswere;
    }

    public void setComplaintAnswere(String complaintAnswere) {
        this.complaintAnswere = complaintAnswere;
    }
    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idUser='" + getIdUser() + "'" +
            ", complaintObject='" + getComplaintObject() + "'" +
            ", complaintDescription='" + getComplaintDescription() + "'" +
            ", complaintMail='" + getMail() + "'" +
            ", idAnswerer='" + getIdAnswerer() + "'" +
            ", complaintAnswered='" + isComplaintAnswered() + "'" +
            ", complaintAnswere='" + getComplaintAnswere() + "'" +
            "}";
    }

}