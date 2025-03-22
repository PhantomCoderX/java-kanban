import manager.TaskManager;
import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // 1. Создаем 2 задачи, один эпик с 2 подзадачами, а другой эпик с 1 подзадачей
        Task task1 = manager.createTask(new Task("Задача 1", "Описание задачи 1"));
        Task task2 = manager.createTask(new Task("Задача 2", "Описание задачи 2"));

        Epic epic1 = manager.createEpic(new Epic("Эпик 1", "Описание эпика 1"));
        Subtask subtask1 = manager.createSubtask(new Subtask("Подзадача 1", "Описание подзадачи 1", epic1.getId()));
        Subtask subtask2 = manager.createSubtask(new Subtask("Подзадача 2", "Описание подзадачи 2", epic1.getId()));

        Epic epic2 = manager.createEpic(new Epic("Эпик 2", "Описание эпика 2"));
        Subtask subtask3 = manager.createSubtask(new Subtask("Подзадача 3", "Описание подзадачи 3", epic2.getId()));

        // 2. Распечатываем списки эпиков, задач и подзадач
        System.out.println("Все задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nВсе эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nВсе подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        // 3. Изменяем статусы созданных объектов
        task1.setStatus(Status.IN_PROGRESS);
        manager.updateTask(task1);

        subtask1.setStatus(Status.DONE);
        manager.updateTask(subtask1);

        subtask2.setStatus(Status.IN_PROGRESS);
        manager.updateTask(subtask2);

        subtask3.setStatus(Status.DONE);
        manager.updateTask(subtask3);

        // 4. Распечатываем обновленные статусы
        System.out.println("\nОбновленные задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nОбновленные эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nОбновленные подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        // 5. Удаляем одну из задач и один из эпиков
        manager.deleteTask(task1.getId());
        manager.deleteEpic(epic1.getId());

        // 6. Распечатываем итоговые списки
        System.out.println("\nЗадачи после удаления:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nЭпики после удаления:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nПодзадачи после удаления:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }
}