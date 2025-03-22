package task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
    }

    @Override
    public TaskType getType() { return TaskType.EPIC; }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void updateStatus(ArrayList<Subtask> subtasks) {
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() != Status.DONE) allDone = false;
            if (subtask.getStatus() != Status.NEW) allNew = false;
        }

        if (allDone) setStatus(Status.DONE);
        else if (allNew) setStatus(Status.NEW);
        else setStatus(Status.IN_PROGRESS);
    }
}
