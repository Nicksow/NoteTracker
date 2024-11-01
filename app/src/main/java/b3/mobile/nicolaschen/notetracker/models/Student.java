package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Student {
    private UUID mId;
    private String mMatricule;
    private String mFirstname;
    private String mLastname;
    private String mUuidBacYear;

    public Student() {
        this(UUID.randomUUID());
    }
    public Student(UUID id) {
        this.mId = id;
        this.mMatricule = "la012345";
        this.mFirstname = "John";
        this.mLastname = "Doe";
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
