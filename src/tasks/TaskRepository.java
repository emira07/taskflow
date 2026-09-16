import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        tasks.add(task);
    }

    public List<Task> findByProjectId(Long projectId) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getProjectId().equals(projectId)) {
                result.add(task);
            }
        }

        return result;
    }
}
