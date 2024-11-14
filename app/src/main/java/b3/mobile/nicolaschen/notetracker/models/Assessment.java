package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Assessment {
    private UUID mId;
    private String mNoteName;
    private String mUuidBacYear;
    private String mParentUuid;
    private Double mNoteMaxValue;
    private Boolean mIsSubAssessment;

    public Assessment(String noteName, String uuidBacYear, String parentUuid, Double noteMaxValue, Boolean isSubAssessment) {
        this(UUID.randomUUID());
        mNoteName = noteName;
        mUuidBacYear = uuidBacYear;
        mParentUuid = parentUuid;
        mNoteMaxValue = noteMaxValue;
        mIsSubAssessment = isSubAssessment;
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
    public String getParentUuid() {
        return mParentUuid;
    }
    public Double getNoteMaxValue() {
        return mNoteMaxValue;
    }
    public Boolean getIsSubAssessment() {
        return mIsSubAssessment;
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
    public void setParentUuid(String parentUuid) {
        this.mParentUuid = parentUuid;
    }
    public void setNoteMaxValue(Double noteMaxValue) {
        this.mNoteMaxValue = noteMaxValue;
    }
    public void setIsSubAssessment(Boolean isSubAssessment) {
        this.mIsSubAssessment = isSubAssessment;
    }
}
