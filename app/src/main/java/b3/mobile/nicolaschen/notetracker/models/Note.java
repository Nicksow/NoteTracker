package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Note {
    private UUID mId;
    private String mAssessmentUuid;
    private String mStudentUuid;
    private Double mNoteValue;


    public Note() {
        this(UUID.randomUUID());
    }
    public Note(UUID id) {
        this.mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getAssessmentUuid() {
        return mAssessmentUuid;
    }
    public String getStudentUuid() {
        return mStudentUuid;
    }
    public Double getNoteValue() {
        return mNoteValue;
    }
    public void setId(UUID id) {
        mId = id;
    }
    public void setAssessmentUuid(String assessmentUuid) {
        mAssessmentUuid = assessmentUuid;
    }
    public void setStudentUuid(String studentUuid) {
        mStudentUuid = studentUuid;
    }
    public void setNoteValue(Double noteValue) {
        mNoteValue = noteValue;
    }

}
