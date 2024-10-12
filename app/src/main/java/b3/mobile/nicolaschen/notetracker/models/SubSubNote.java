package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class SubSubNote {
    private UUID mId;
    private String mSubSubNoteName;
    private float mSubSubNoteValue;
    private String mUuidSubNote;

    public SubSubNote() {
        this(UUID.randomUUID());
    }
    public SubSubNote(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getSubSubNoteName() {
        return mSubSubNoteName;
    }
    public String getUuidSubNote() {
        return mUuidSubNote;
    }
    public void setSubSubNoteValue(float subSubNoteValue) {
        this.mSubSubNoteValue = subSubNoteValue;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public void setSubSubNoteName(String subSubNoteName) {
        this.mSubSubNoteName = subSubNoteName;
    }
    public float getSubSubNoteValue() {
        return mSubSubNoteValue;
    }
    public void setUuidSubNote(String uuidSubNote) {
        this.mUuidSubNote = uuidSubNote;
    }
}
