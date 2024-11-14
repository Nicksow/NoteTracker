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
            public static final String MATRICULE = "matricule";
            public static final String FIRST_NAME = "firstName";
            public static final String LAST_NAME = "lastName";
            public static final String UUID_BAC_YEAR = "idBacYear";
        }
    }

    public static final class AssessmentTable {
        public static final String NAME = "assessments";
        public static final class cols {
            public static final String UUID = "id";
            public static final String NOTE_NAME = "noteName";
            public static final String UUID_BAC_YEAR = "idBacYear";
            public static final String PARENT_ID = "parentId";
            public static final String MAX_NOTE = "maxNote";
            public static final String SUB_ASSESSMENT = "subAssessment";
        }
    }

    public static final class NoteTable {
        public static final String NAME = "notes";
        public static final class cols {
            public static final String UUID_ASSESSMENT = "idAssessment";
            public static final String UUID_STUDENT = "idStudent";
            public static final String NOTE = "note";
        }
    }

}