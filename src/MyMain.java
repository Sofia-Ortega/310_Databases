import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMain extends JFrame{
    public static DataAccess dataAccess = new DataAccess();
    private JPanel MainPanel;
    private JButton jobClassButton;
    private JButton employeeButton;
    private JButton projectButton;

    public MyMain() {
        setContentPane(MainPanel);
        setTitle("My awesome GUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);


        jobClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Job button");
                JobView jobView = new JobView();
                jobView.setVisible(true);
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Employee Button");
                EmployeeView employeeView = new EmployeeView();
                employeeView.setVisible(true);
            }
        });

        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Project Button");
                ProjectView2 projectView2 = new ProjectView2();
                projectView2.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        dataAccess.connect();
        new MyMain();
    }
}
