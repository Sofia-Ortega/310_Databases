import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame{
    private JTextField idField;
    private JTextField nameField;
    private JTextField jobIdField;
    private JButton submitButton;
    private JPanel employeePanel;

    public EmployeeView() {
        setContentPane(employeePanel);
        setTitle("My awesome GUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeModel em = new EmployeeModel();

                em.id = Integer.parseInt(idField.getText());
                em.name = nameField.getText();
                em.jobId = Integer.parseInt(jobIdField.getText());

                System.out.println(em.id + ", " + em.name + ", " + em.jobId);

                EmployeeModel em2 = MyMain.dataAccess.readEmployee(em.id);
                if(em2.id == -1) {
                    System.out.println("Employee not found -- create");
                    MyMain.dataAccess.createEmployee(em);
                } else {
                    System.out.println("Employee found -- update");
                    MyMain.dataAccess.updateEmployee(em);
                }


            }
        });
    }
}
