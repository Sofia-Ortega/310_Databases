import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProjectView2 extends JFrame {

    private List<ProjectModel> projectList;
    private List<EmployeeModel> employeeList;

    private void loadData() {

        projectList = MyMain.dataAccess.readProjects();
        employeeList = MyMain.dataAccess.readEmployees();

        MyMain.dataAccess.loadProjectsWithAssignment(projectList);
    }

    public ProjectView2() {

        loadData();

        setTitle("Project View");
        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(10, 1));

        for(ProjectModel project : projectList) {
            JLabel label = new JLabel("Project Name: " + project.projectName);
            mainPanel.add(label);


            String[] columnNames = {"Employee", "Hours", "Total Wage"};


            JTable table = new JTable(project.getTableData(), columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane);

        }

        JPanel addEmployeePanel = new JPanel(new FlowLayout());

        JComboBox<String> projectDropDown = new JComboBox<>();
        JComboBox<String> employeeNameDropDown = new JComboBox<>();
        JTextField hoursTextField = new JTextField(5);
        JButton addEmployeeButton = new JButton("Add");

        addEmployeePanel.add(new JLabel("ADD EMPLOYEE:    "));
        addEmployeePanel.add(new JLabel("Project"));
        addEmployeePanel.add(projectDropDown);
        addEmployeePanel.add(new JLabel("Employee Name: "));
        addEmployeePanel.add(employeeNameDropDown);
        addEmployeePanel.add(new JLabel("Number of Hours: "));
        addEmployeePanel.add(hoursTextField);
        addEmployeePanel.add(addEmployeeButton);


        JPanel addProjectPanel = new JPanel(new FlowLayout());

        JTextField newProjectLabel = new JTextField(20);
        JComboBox<String> employeeNameDropDown2 = new JComboBox<>();
        JButton addProjectButton = new JButton("Add");

        addProjectPanel.add(new JLabel("ADD PROJECT:    "));
        addProjectPanel.add(new JLabel("Project"));
        addProjectPanel.add(newProjectLabel);
        addProjectPanel.add(new JLabel("Project Leader:"));
        addProjectPanel.add(employeeNameDropDown2);
        addProjectPanel.add(addProjectButton);


        // *********** SQL ************************
        for(EmployeeModel employee : employeeList) {
            String employeeLabel = employee.id + ":" + employee.name;
            employeeNameDropDown.addItem(employeeLabel);
            employeeNameDropDown2.addItem(employeeLabel);
        }

        for(ProjectModel project : projectList) {
            String projectLabel = project.id + ":" + project.projectName;
            projectDropDown.addItem(projectLabel);
        }

        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Project Button:");
                String projectStr = newProjectLabel.getText();
                String employeeStr = (String) employeeNameDropDown2.getSelectedItem();
                int employeeID = Integer.parseInt(employeeStr.split(":")[0]);


                System.out.println("Selected Project: " + projectStr);
                System.out.println("Selected Employee: " + employeeStr + " -- " + employeeID);

                ProjectModel project = new ProjectModel();
                Random random = new Random();
                project.id = random.nextInt(100000000);
                project.projectName = projectStr;
                project.projectLeaderID = employeeID;

                System.out.println(project.id + ", " + project.projectName + ", " + project.projectLeaderID);


                MyMain.dataAccess.createProject(project);
            }
        });

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Add employee Button:");

                String projectStr = (String) projectDropDown.getSelectedItem();
                String employeeStr = (String) employeeNameDropDown.getSelectedItem();

                // Print selected values from projectDropDown and employeeNameDropDown
                System.out.println("Selected Project: " + projectStr);
                System.out.println("Selected Employee: " + employeeStr);
                System.out.println("Hours: " + hoursTextField.getText());




                // get employeeID
                int projectID = Integer.parseInt(projectStr.split(":")[0]);
                int employeeID = Integer.parseInt(employeeStr.split(":")[0]);
                double hours = Integer.parseInt(hoursTextField.getText());

                System.out.println("projectID: " + projectID );
                System.out.println("employeeID: " + employeeID );

                MyMain.dataAccess.updateAssignment(projectID, employeeID, hours);

                loadData();
            }
        });



        mainPanel.add(addEmployeePanel);
        mainPanel.add(addProjectPanel);



        add(mainPanel);
    }
}


