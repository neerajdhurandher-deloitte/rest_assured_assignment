package api_responses;

public class Task {

    boolean complected;
    String id;
    String description;
    String owner;
    String createdAt;
    String updatedAt;
    int v;

    public Task() {
    }

    public Task(boolean complected, String id, String description, String owner, String createdAt, String updatedAt, int v) {
        this.complected = complected;
        this.id = id;
        this.description = description;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public boolean isComplected() {
        return complected;
    }

    public void setComplected(boolean complected) {
        this.complected = complected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
