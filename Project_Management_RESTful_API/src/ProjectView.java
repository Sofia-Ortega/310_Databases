import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProjectView extends JFrame {
    private JPanel projectPanel;
    private JScrollPane tableScroll;
    private JComboBox comboProject;
    private JComboBox comboEmployeeName;
    private JSpinner spinnerHours;
    private JButton addEmployeeButton;
    private JTextField newProjectName;
    private JComboBox comboEmployeeLeader;
    private JButton addProjectButton;
    private JPanel tablePanel;



    public ProjectView() {
        setContentPane(projectPanel);
        setTitle("Project View");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 1000);
        setLocationRelativeTo(null);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        // setting table view
        String projectName = "Kingdom";
        String[] columnNames = {"Employee", "Hours", "Total Wage"};
        String[][] employeeInfo = {{"Sam", "5", "12"}, {"Orange", "4", "12"}};

        JTable table = new JTable(employeeInfo, columnNames);
        /*
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

         */



        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked that button");
            }
        });
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adding Project");
            }
        });
    }
}
