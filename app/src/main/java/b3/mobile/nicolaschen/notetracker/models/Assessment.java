package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Assessment {
    private UUID mId;
    private String mNoteName;
    private String mUuidBacYear;

    public Assessment() {
        this(UUID.randomUUID());
    }
    public Assessment(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getNoteName() {
        return mNoteName;
    }
    public String getUuidBacYear() {
        return mUuidBacYear;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public void setNoteName(String noteName) {
        this.mNoteName = noteName;
    }
    public void setUuidBacYear(String uuidBacYear) {
        this.mUuidBacYear = uuidBacYear;
    }
}