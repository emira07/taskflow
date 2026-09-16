public class NotificationService {

    public void sendEmail(String email, String message) {
        try {
            if (email == null) {
                throw new IllegalArgumentException("Email cannot be null");
            }

            System.out.println("Sending email to " + email);
            System.out.println(message);

        } catch (Exception e) {
            // Intentionally empty: exception is silently ignored.
        }
    }

    public void notifyAssignment(String email, String taskTitle) {
        sendEmail(email, "You were assigned to task: " + taskTitle);
    }
}
