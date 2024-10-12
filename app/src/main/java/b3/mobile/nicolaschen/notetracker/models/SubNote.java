package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class SubNote {
    private UUID mId;
    private String mSubNoteName;
    private float mSubNoteValue;
    private String mUuidNoteStudent;

    public SubNote() {
        this(UUID.randomUUID());
    }
    public SubNote(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getSubNoteName() {
        return mSubNoteName;
    }
    public float getSubNoteValue() {
        return mSubNoteValue;
    }
    public String getUuidNoteStudent() {
        return mUuidNoteStudent;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public void setSubNoteName(String subNoteName) {
        this.mSubNoteName = subNoteName;
    }
    public void setSubNoteValue(float subNoteValue) {
        this.mSubNoteValue = subNoteValue;
    }
    public void setUuidNoteStudent(String uuidNoteStudent) {
        this.mUuidNoteStudent = uuidNoteStudent;
    }
}
