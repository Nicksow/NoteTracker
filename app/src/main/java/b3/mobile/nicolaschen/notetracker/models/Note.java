package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class Note {
    private String mAssessmentUuid;
    private String mStudentUuid;
    private Double mNoteValue;


    public String getAssessmentUuid() {
        return mAssessmentUuid;
    }
    public String getStudentUuid() {
        return mStudentUuid;
    }
    public Double getNoteValue() {
        return mNoteValue;
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
