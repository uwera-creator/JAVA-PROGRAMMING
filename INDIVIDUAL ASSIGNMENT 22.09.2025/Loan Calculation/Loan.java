package loancalculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Loan extends JFrame implements ActionListener {
    // Labels
	
    JLabel lblAmount, lblDuration, lblTotal;
    JTextField txtAmount, txtDuration, txtTotal;
    JButton btnCalculate;

    public Loan() {
        // Frame settings
        setTitle("Loan Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // absolute positioning

        // Initialize components
        lblAmount = new JLabel("Amount requested");
        lblAmount.setBounds(30, 30, 150, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(200, 30, 150, 25);
        add(txtAmount);

        lblDuration = new JLabel("Duration (year)");
        lblDuration.setBounds(30, 70, 150, 25);
        add(lblDuration);

        txtDuration = new JTextField();
        txtDuration.setBounds(200, 70, 150, 25);
        add(txtDuration);

        lblTotal = new JLabel("Total to return");
        lblTotal.setBounds(30, 110, 150, 25);
        add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(200, 110, 150, 25);
        txtTotal.setEditable(false); // read-only
        add(txtTotal);

        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(120, 160, 150, 30);
        btnCalculate.addActionListener(this);
        add(btnCalculate);

        setVisible(true);
    }

   
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            int years = Integer.parseInt(txtDuration.getText());

            
            double interestRate = 0.006; 
            double total = amount + (amount * interestRate * years);
            
            txtTotal.setText(String.valueOf(total));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        new Loan();
    }


	}