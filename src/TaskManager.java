import java.util.*;

public class TaskManager {
    int idCounter = 1;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Task createTask(String title, String description) {
        Task task = new Task(idCounter++, title, description);
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(String title, String description) {
        Epic epic = new Epic(idCounter++, title, description);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask createSubtask(String title, String description, int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }

        Subtask subtask = new Subtask(idCounter++, title, description, epicId);
        subtasks.put(subtask.getId(), subtask);
        epic.getSubtaskIds().add(subtask.getId());
        updateEpicStatus(epicId);
        return subtask;
    }

    // Получение задач
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<Epic>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<Subtask>(subtasks.values());
    }

    public ArrayList<Subtask> getSubtasksByEpicId(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null; // Если эпик не найден, возвращаем пустой список
        }

        ArrayList<Subtask> result = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask != null) {
                result.add(subtask);
            }
        }
        return result;
    }

    // Обновление задач
    public void updateTask(Task task) {
        if (task instanceof Epic) {
            epics.put(task.getId(), (Epic) task);
        } else if (task instanceof Subtask) {
            Subtask subtask = (Subtask) task;
            subtasks.put(subtask.getId(), subtask);
            updateEpicStatus(subtask.getEpicId());
        } else {
            tasks.put(task.getId(), task);
        }
    }

    // Удаление задач
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtaskIds().remove((Integer) id);
                updateEpicStatus(epic.getId());
            }
        }
    }

    // Обновление статуса эпика
    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            ArrayList<Subtask> subtasks = getSubtasksByEpicId(epicId);
            epic.updateStatus(subtasks);
        }
    }
}
