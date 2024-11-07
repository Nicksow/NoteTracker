package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Student {
    private UUID mId;
    private String mMatricule;
    private String mFirstname;
    private String mLastname;
    private String mUuidBacYear;

    public Student(String matricule, String firstname, String lastname, String uuidBacYear) {
        this(UUID.randomUUID());
        this.mMatricule = matricule;
        this.mFirstname = firstname;
        this.mLastname = lastname;
        this.mUuidBacYear = uuidBacYear;
    }
    public Student(UUID id) {
        this.mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getMatricule() {
        return mMatricule;
    }
    public String getFirstname() {
        return mFirstname;
    }
    public String getLastname() {
        return mLastname;
    }
    public String getUuidBacYear() {
        return mUuidBacYear;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public void setMatricule(String matricule) {
        this.mMatricule = matricule;
    }
    public void setFirstname(String firstname) {
        this.mFirstname = firstname;
    }
    public void setLastname(String lastname) {
        this.mLastname = lastname;
    }
    public void setUuidBacYear(String uuidBacYear) {
        this.mUuidBacYear = uuidBacYear;
    }
}
