package org.example;

import javax.swing.*;
import java.awt.event.*;

public class Home extends JDialog {
    private JPanel contentPane;
    private JTextField creditCardField;
    private JButton updateButton;
    private JButton sellButton;
    private JButton buyButton;

    public static String userName = null;



    public Home(String userName) {
        Home.userName = userName;
        String creditCard = Login.dataAccess.getCreditCard(userName);
        System.out.println("Credit Card: " + creditCard);
        creditCardField.setText(creditCard);

        setContentPane(contentPane);
        setModal(true);

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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCard = creditCardField.getText();
                Login.dataAccess.updateCreditCard(userName, newCard);
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sell sell = new Sell();
                // sell.pack();
                sell.setVisible(true);
            }
        });
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buy buy = new Buy();
                buy.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
