package b3.mobile.nicolaschen.notetracker.database;

public class NoteDbSchema {
    public static final class BacYearTable {
        public static final String NAME = "bacYears";
        public static final class cols {
            public static final String UUID = "id";
            public static final String NAME = "name";
        }
    }

    public static final class StudentTable {
        public static final String NAME = "students";
        public static final class cols {
            public static final String UUID = "id";
            public static final String FIRST_NAME = "firstName";
            public static final String LAST_NAME = "lastName";
            public static final String UUID_BAC_YEAR = "idBacYear";
            public static final String GLOBAL_NOTE = "globalNote";
        }
    }

    public static final class AssessmentTable {
        public static final String NAME = "assessments";
        public static final class cols {
            public static final String UUID = "id";
            public static final String NOTE_NAME = "noteName";
            public static final String UUID_BAC_YEAR = "idBacYear";
        }
    }

    public static final class NoteStudentTable {
        public static final String NAME = "noteStudents";
        public static final class cols {
            public static final String UUID = "id";
            public static final String UUID_STUDENT = "idStudent";
            public static final String UUID_ASSESSMENT = "idAssessment";
            public static final String NOTE_VALUE = "noteValue";
        }
    }

    public static final class SubNoteTable {
        public static final String NAME = "subNotes";
        public static final class cols {
            public static final String UUID = "id";
            public static final String SUB_NOTE_NAME = "subNoteName";
            public static final String SUB_NOTE_VALUE = "SubNoteValue";
            public static final String UUID_NOTE_STUDENT = "idNoteStudent";
        }
    }

    public static final class SubSubNoteTable {
        public static final String NAME = "subSubNotes";
        public static final class cols {
            public static final String UUID = "id";
            public static final String SUB_SUB_NOTE_NAME = "subSubNoteName";
            public static final String SUB_SUB_NOTE_VALUE = "subSubNoteValue";
            public static final String UUID_SUB_NOTE = "idSubNote";
        }
    }
}