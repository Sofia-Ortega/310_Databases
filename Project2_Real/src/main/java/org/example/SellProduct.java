package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellProduct extends JDialog {
    private JPanel contentPane;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton updateButton;
    private JTextField nameField;

    public SellProduct() {
        setContentPane(contentPane);
        setSize(200, 200);
        setModal(true);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = nameField.getText();
                String price = priceField.getText();
                String quantity = quantityField.getText();

                Login.dataAccess.addProduct(productName, price, quantity, Home.userName);

            }
        });
    }
}
