import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoApp {

    // Variables
    private final JFrame frame;
    private final DefaultListModel<ToDo> todoListModel;
    private final JList<ToDo> todoList;
    private final JTextField nameField;
    private final JTextArea descriptionArea;
    private final ArrayList<ToDo> todos;

    public ToDoApp() {
        todos = new ArrayList<>();
        todoListModel = new DefaultListModel<>();

        // Initialise Frame
        frame = new JFrame("ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);

        // Panel for input fields (name and description)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Name
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("ToDo Name"));
        inputPanel.add(nameField);

        // Description
        descriptionArea = new JTextArea();
        descriptionArea.setBorder(BorderFactory.createTitledBorder("Description"));
        descriptionArea.setRows(4);
        inputPanel.add(new JScrollPane(descriptionArea));

        // Button for adding new ToDos
        JButton addButton = new JButton("Add new ToDo");
        addButton.addActionListener(e -> addTodo());
        inputPanel.add(addButton);

        // Button for deleting ToDos
        JButton deleteButton = new JButton("Delete ToDo");
        deleteButton.addActionListener(e -> deleteTodo());
        inputPanel.add(deleteButton);

        // Button to mark ToDos as done
        JButton doneButton = new JButton("Mark ToDo as done");
        deleteButton.addActionListener(e -> doneTodo());
        inputPanel.add(doneButton);

        // Panel for ToDo-List
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.setBackground(Color.LIGHT_GRAY);

        todoList = new JList<>(todoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setCellRenderer(new ToDoListCellRenderer());

        JScrollPane listScrollPane = new JScrollPane(todoList);         // add a scrollbar
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(listPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Method for adding new ToDos
    private void addTodo() {
        String name = nameField.getText().trim();
        String description = descriptionArea.getText().trim();

        // Ensure that the name and description have been entered
        if (name.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Name and Description!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ToDo newTodo = new ToDo(name, description);
        todos.add(newTodo);
        todoListModel.addElement(newTodo);

        // Reset input fields
        nameField.setText("");
        descriptionArea.setText("");
    }

    // Method for deleting ToDos
    private void deleteTodo() {
        ToDo selectedTodo = todoList.getSelectedValue();
        if (selectedTodo != null) {
            todos.remove(selectedTodo);
            todoListModel.removeElement(selectedTodo);
        } else {
            JOptionPane.showMessageDialog(frame, "Please choose a ToDo from List!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doneTodo() {
        ToDo selectedToDo = todoList.getSelectedValue();
        if (selectedToDo != null) {

            todoList.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Please choose a ToDo from List!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Display ToDos in a List - below the input fields
    private static class ToDoListCellRenderer implements ListCellRenderer<ToDo> {
        @Override
        public Component getListCellRendererComponent(JList<? extends ToDo> list, ToDo value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);

            if (value.isDone()) {
                panel.setBackground(Color.GREEN);
            }

            // Label for ToDos
            JLabel label = new JLabel(value.getName());
            if (value.isDone()) {
                label.setText(value.getName() + "(isDone");
                panel.repaint();
            }
            panel.add(label);
            return panel;
        }
    }

    public static void main(String[] args) {
        new ToDoApp();
    }
}