package org.example;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Sell extends JDialog {
    private JPanel contentPane;
    private JButton sellButton;
    private JPanel productPanel;

    public Sell() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setSize(500, 500);
        setModal(true);



        /*
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

         */
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellProduct sellProduct = new SellProduct();
                sellProduct.setVisible(true);
            }
        });
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));


        List<Map<String, String>> products = Login.dataAccess.getProducts();
        for(Map<String, String> product : products) {
            String productName = product.get("productName");
            String userName = product.get("userName");
            String price = product.get("price");
            String quantity = product.get("quantity");

            // System.out.println("Compare: "  + userName + ", " + Home.userName);
            if(Objects.equals(userName, Home.userName)) {
                JPanel rowPanel = new JPanel();
                rowPanel.add(new JLabel("Name: " + productName));
                rowPanel.add(new JLabel("Price: " + price));
                rowPanel.add(new JLabel("Quantity: " + quantity));

                JButton deleteBtn = new JButton("Delete");
                deleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Login.dataAccess.deleteProduct(productName);
                        System.out.println("deleted");

                    }
                });


                rowPanel.add(deleteBtn);


                productPanel.add(rowPanel);

            }

        }

    }
}
