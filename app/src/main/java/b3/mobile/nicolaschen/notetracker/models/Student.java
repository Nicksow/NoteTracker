package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Student {
    private UUID mId;
    private String mFistname;
    private String mLastname;
    private String mUuidBacYear;
    private float mGlobalNote;

    public Student() {
        this(UUID.randomUUID());
    }
    public Student(UUID id) {
        this.mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getFirstname() {
        return mFistname;
    }
    public String getLastname() {
        return mLastname;
    }
    public String getUuidBacYear() {
        return mUuidBacYear;
    }
    public float getGlobalNote() {
        return mGlobalNote;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public void setFirstname(String firstname) {
        this.mFistname = firstname;
    }
    public void setLastname(String lastname) {
        this.mLastname = lastname;
    }
    public void setUuidBacYear(String uuidBacYear) {
        this.mUuidBacYear = uuidBacYear;
    }
    public void setGlobalNote(float GlobalNote) {
        this.mGlobalNote = GlobalNote;
    }

}
