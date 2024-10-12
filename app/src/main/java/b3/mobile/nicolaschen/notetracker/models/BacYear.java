package b3.mobile.nicolaschen.notetracker.models;

import java.util.UUID;

public class BacYear {
    private UUID mId;
    private String mName;

    public BacYear() {
        this(UUID.randomUUID());
    }
    public BacYear(UUID id) {
        this.mId = id;
    }

    public UUID getId() {
        return mId;
    }
    public String getName() {
        return mName;
    }
    public void setId(UUID mId) {
        this.mId = mId;
    }
    public void setName(String mName) {
        this.mName = mName;
    }

}
