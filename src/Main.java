public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание эпика
        Epic epic = manager.createEpic("Переезд", "Организация переезда в новую квартиру");

        // Создание подзадач
        Subtask subtask1 = manager.createSubtask("Собрать коробки", "Купить картонные коробки", epic.getId());
        Subtask subtask2 = manager.createSubtask("Упаковать кухню", "Упаковать посуду и технику", epic.getId());
        Subtask subtask3 = manager.createSubtask("Упаковать кухню", "Упаковать посуду и технику", 3455);

        // Проверка статусов
        System.out.println("Статус эпика: " + epic.getStatus()); // NEW

        subtask1.setStatus(Status.IN_PROGRESS);
        manager.updateTask(subtask1);
        System.out.println("Статус эпика после изменения: " + epic.getStatus()); // IN_PROGRESS

        // Удаление эпика
        manager.deleteEpic(epic.getId());
        System.out.println("Эпиков после удаления: " + manager.getAllEpics().size()); // 0
    }
}