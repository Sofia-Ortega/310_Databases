package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class Login extends JDialog {
    public static DataAccess dataAccess = null;

    private JPanel contentPane;
    private JPanel userPanel;


    public Login() {


        setContentPane(contentPane);
        setModal(true);
        // setSize(700, 700);
        setLocationRelativeTo(null);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        if(dataAccess == null)
            dataAccess = new DataAccess();

    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    public static void main(String[] args) {

        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        if(dataAccess == null) {
            dataAccess = new DataAccess();
        }


        Map<String, String> users = dataAccess.getUsers();
        for(Map.Entry<String, String> entry: users.entrySet()) {
            String userName = entry.getKey();

            JPanel rowPanel = new JPanel();

            JButton btn = new JButton("Login");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("userName: " + userName);

                    Home dialog = new Home();
                    dialog.pack();
                    dialog.setVisible(true);
                    System.exit(0);

                }
            });


            rowPanel.add(new JLabel(userName));
            rowPanel.add(btn);

            userPanel.add(rowPanel);


        }

    }
}
