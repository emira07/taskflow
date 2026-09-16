public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void assignTask(Task task, User user) {
        // Intentionally missing the project-membership check.
        task.setAssigneeId(user.getId());
    }

    public void changeStatus(Task task, Task.Status newStatus) {

        if (task.getStatus() == Task.Status.DONE &&
                newStatus == Task.Status.TODO) {
            throw new IllegalStateException("DONE tasks cannot return to TODO");
        }

        if (task.getStatus() == Task.Status.CANCELLED) {
            throw new IllegalStateException("Cancelled tasks cannot change status");
        }

        if (newStatus == Task.Status.IN_PROGRESS &&
                task.getPriority() == Task.Priority.CRITICAL &&
                task.getAssigneeId() == null) {
            throw new IllegalStateException(
                    "Critical tasks require an assignee before starting");
        }

        task.setStatus(newStatus);
    }

    public void createTask(String title, String description,
                           Task.Priority priority, Long projectId) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }

        Task task = new Task(
                title,
                description,
                Task.Status.TODO,
                priority,
                projectId,
                null
        );

        taskRepository.save(task);
    }
}
