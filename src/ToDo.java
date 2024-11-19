public class ToDo {

    // Variables
    private final String name;
    private final String description;
    private boolean isDone;

    // Constructor
    public ToDo(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDone = false;                  // false by default
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = isDone ? "Done" : "Pending";
        // Green for done, red for pending
        String color = isDone ? "\033[0;32m" : "\033[0;31m";
        String resetColor = "\033[0m";
        return color + "Name: " + name + " Description: " + description + " | Status: " + status + resetColor;
    }
}