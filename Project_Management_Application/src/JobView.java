import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobView extends JFrame {
    private JTextField idInput;
    private JTextField titleInput;
    private JTextField wageInput;
    private JButton submitButton;
    private JPanel jobClassPanel;

    public JobView() {
        setContentPane(jobClassPanel);
        setTitle("My awesome GUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JobModel j = new JobModel();
                j.id = Integer.parseInt(idInput.getText());
                j.title = titleInput.getText();
                j.wage = Double.parseDouble(wageInput.getText());

                System.out.println(j.id + ", " + j.title + ", " + j.wage);

                JobModel r = MyMain.dataAccess.readJobClass(j.id);
                if(r.id == -1) {
                    MyMain.dataAccess.createJobClass(j);
                } else {
                    MyMain.dataAccess.updateJobClass(j);
                }
            }
        });
    }
}
