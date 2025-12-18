import javax.swing.*;   // Import Swing components for GUI
import java.awt.*;      // Import AWT classes for layout and color
import java.awt.event.ActionEvent;   // Import ActionEvent for button actions
import java.awt.event.ActionListener; // Import ActionListener interface
import java.awt.event.KeyEvent;       // Import KeyEvent for keyboard input
import java.awt.event.KeyAdapter;     // Import KeyAdapter for handling key events

// Main class extends JFrame to create a windowed GUI application
public class ToDoApp extends JFrame {
    // Model to store tasks in the list
    private DefaultListModel<String> toDoListModel;
    // JList to display tasks
    private JList<String> toDoList;
    // Text field for entering new tasks
    private JTextField taskField;
    // Buttons for adding and removing tasks
    private JButton addButton;
    private JButton removeButton;

    // Constructor sets up the GUI
    public ToDoApp() {
        setTitle("To-Do List App");                 // Window title
        setSize(400, 300);                          // Window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program when window closes

        // Initialize list model and list
        toDoListModel = new DefaultListModel<>();
        toDoList = new JList<>(toDoListModel);

        // Initialize text field for task input
        taskField = new JTextField();
        taskField.setPreferredSize(new Dimension(200, 30)); // Set size of text field

        // Initialize buttons
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        // Set button colors
        addButton.setBackground(Color.PINK);
        removeButton.setBackground(Color.CYAN);

        // Add action listener to "Add" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();   // Call method to add task
            }
        });

        // Add action listener to "Remove" button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();   // Call method to remove selected task
            }
        });

        // Add key listener to text field (press Enter to add task)
        taskField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addTask();   // Add task when Enter is pressed
                }
            }
        });

        // Panel to hold input field and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        // Set layout and add components to frame
        setLayout(new BorderLayout());
        add(new JScrollPane(toDoList), BorderLayout.CENTER); // Scrollable list in center
        add(inputPanel, BorderLayout.SOUTH);                 // Input panel at bottom

        setLocationRelativeTo(null); // Center window on screen
        setVisible(true);            // Make window visible
    }

    // Method to add a task to the list
    private void addTask() {
        String task = taskField.getText().trim(); // Get text and trim spaces
        if (!task.isEmpty()) {                    // Only add if not empty
            toDoListModel.addElement(task);       // Add to list model
            taskField.setText("");                // Clear text field
        }
    }

    // Method to remove selected task from the list
    private void removeTask() {
        int selectedIndex = toDoList.getSelectedIndex(); // Get selected index
        if (selectedIndex != -1) {                       // If a task is selected
            toDoListModel.remove(selectedIndex);         // Remove it
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Run GUI creation on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoApp();   // Create and show the app
            }
        });
    }
}