package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class NoteStudent {
    private UUID mId;
    private String mUuidStudent;
    private String mUuidAssessment;
    private float mNoteValue;

    public NoteStudent() {
        this(UUID.randomUUID());
    }
    public NoteStudent(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getUuidStudent() {
        return mUuidStudent;
    }
    public String getUuidAssessment() {
        return mUuidAssessment;
    }
    public float getNoteValue() {
        return mNoteValue;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public void setUuidStudent(String uuidStudent) {
        this.mUuidStudent = uuidStudent;
    }
    public void setUuidAssessment(String uuidAssessment) {
        this.mUuidAssessment = uuidAssessment;
    }
    public void setNoteValue(float noteValue) {
        this.mNoteValue = noteValue;
    }

}
