import java.util.Objects;

public class Task {
    private final int id;
    private String title;
    private String description;
    private Status status;

    Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return this.id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
