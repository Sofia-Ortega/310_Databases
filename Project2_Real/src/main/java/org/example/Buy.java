package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Buy extends JDialog {
    private JPanel contentPane;
    private JLabel creditCardField;
    private JPanel productPanel;
    private JButton buttonOK;

    public Buy() {
        setSize(500, 500);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        creditCardField.setText(Login.dataAccess.getCreditCard(Home.userName));
    }

    private void createUIComponents() {
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));


        List<Map<String, String>> products = Login.dataAccess.getProducts();
        for (Map<String, String> product : products) {
            String productName = product.get("productName");
            String userName = product.get("userName");
            String price = product.get("price");
            String quantity = product.get("quantity");

            // System.out.println("Compare: "  + userName + ", " + Home.userName);
            if (Objects.equals(userName, Home.userName)) {
                JPanel rowPanel = new JPanel();

                JLabel quantityLabel = new JLabel("Quantity: " + quantity);
                rowPanel.add(new JLabel("Name: " + productName));
                rowPanel.add(new JLabel("Price: " + price));
                rowPanel.add(quantityLabel);

                JButton btn = new JButton("Buy");
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Integer newQuantity = Login.dataAccess.buyProduct(productName);
                        quantityLabel.setText("Quantity: " + newQuantity);


                        System.out.println("bought");

                    }
                });


                rowPanel.add(btn);


                productPanel.add(rowPanel);

            }

        }
    }
}
