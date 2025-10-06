//GROUP 18
//UWERA Josianne 223002615
//UWASE Monique 223005990
//NYIRAMBARUSHIMANA Assoumpta 223016189

package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.util.DB;

public class Userpanel extends JPanel implements ActionListener {
    // Components
    JTextField idTxt = new JTextField();
    JTextField nameTxt = new JTextField();
    JTextField roleTxt = new JTextField();
    JTextField emailTxt = new JTextField();
    JTextField createTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    // Constructor
    public Userpanel() {
        setLayout(null);

        String[] labels = {"ID", "Username", "Password", "Role", "Email", "Created"};
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 800, 300);

        int y = 20;
        addField("ID", idTxt, y); y += 30;
        addField("Username", nameTxt, y); y += 30;
        addField("Password", passTxt, y); y += 30;
        addField("Role", roleTxt, y); y += 30;
        addField("Email", emailTxt, y); y += 30;
        addField("Created At", createTxt, y); y += 30;

        addButtons();
        add(sp);
    }

    private void addButtons() {
        addBtn.setBounds(300, 20, 100, 30);
        updateBtn.setBounds(300, 60, 100, 30);
        deleteBtn.setBounds(300, 100, 100, 30);
        loadBtn.setBounds(300, 140, 100, 30);

        add(addBtn);
        add(updateBtn);
        add(deleteBtn);
        add(loadBtn);

        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    private void addField(String lbl, JComponent txt, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 80, 25);
        txt.setBounds(100, y, 150, 25);
        add(l);
        add(txt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (Connection con = DB.getConnection()) {
            String sql;

            if (e.getSource() == addBtn) {
                sql = "INSERT INTO user (userid, username, passwordhash, role, email, createdat) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idTxt.getText()));
                ps.setString(2, nameTxt.getText());
                ps.setString(3, new String(passTxt.getPassword()));
                ps.setString(4, roleTxt.getText());
                ps.setString(5, emailTxt.getText());
                ps.setString(6, createTxt.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User added successfully.");

            } else if (e.getSource() == updateBtn) {
                sql = "UPDATE user SET username=?, passwordhash=?, role=?, email=?, createdat=? WHERE userid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nameTxt.getText());
                ps.setString(2, new String(passTxt.getPassword()));
                ps.setString(3, roleTxt.getText());
                ps.setString(4, emailTxt.getText());
                ps.setString(5, createTxt.getText());
                ps.setInt(6, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User updated successfully.");

            } else if (e.getSource() == deleteBtn) {
                sql = "DELETE FROM user WHERE userid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User deleted successfully.");

            } else if (e.getSource() == loadBtn) {
                model.setRowCount(0); // Clear table
                sql = "SELECT * FROM user";
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("passwordhash"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("createdat")
                    });
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
