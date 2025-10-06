//GROUP 18
//UWERA Josianne 223002615
//UWASE Monique 223005990
//NYIRAMBARUSHIMANA Assoumpta 223016189

package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import com.mysql.jdbc.PreparedStatement;
import com.util.DB;

public class Customerpanel extends JPanel implements ActionListener{
	 
		JTextField customeridTxt=new JTextField();
		JTextField nameTxt=new JTextField();
		JTextField addressTxt=new JTextField();
		JTextField phoneTxt=new JTextField();
		JTextField emailTxt=new JTextField();
		JTextField dobTxt=new JTextField();
		JTextField national_idTxt=new JTextField();
		
		
		JButton addBtn=new JButton("Add");
		JButton updateBtn=new JButton("Update");
		JButton deleteBtn=new JButton("Delete");
		JButton loadBtn=new JButton("Load");

		JTable table;
		DefaultTableModel model;
		
		//constructor
		
		public Customerpanel(){
			setLayout(null);
			String[] labels={"Customerid","Name","Address","Phone","Email","DOB",""};
			String[] cols={"customerid","name","address","phone","email","DOB","national_id"};
			
			model= new DefaultTableModel(labels,0);
			table =new JTable(model);
			
			JScrollPane sp=new JScrollPane(table);
			sp.setBounds(20,250,800,300);
			int y=20;
			addField("ID",customeridTxt,y);
			y+=30;
			addField("Name",nameTxt,y);
			y+=30;
			addField("Address",addressTxt,y);
			y+=30;
			addField("Phone",phoneTxt,y);
			y+=30;
			addField("Email",emailTxt,y);
			y+=30;
			addField("DOB",dobTxt,y);
			y+=30;
			addField("National ID",national_idTxt,y);
			y+=30;
			
			addButtons();
			add(sp);
			

		}

		private void addButtons() {
			addBtn.setBounds(300,20,100,30);
			updateBtn.setBounds(300,60,100,30);
			deleteBtn.setBounds(300,100,100,30);
			loadBtn.setBounds(300,140,100,30);
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
			JLabel l=new JLabel(lbl);
			l.setBounds(20,y,80,25);
			txt.setBounds(100,y,150,25);
			add(l);
			add(txt);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try(Connection con=DB.getConnection()){
				String sql=null;
				if(e.getSource()==addBtn){
					
					 //sql="INSERT INTO account(name,email)VALUES(?,?)";
					sql="INSERT INTO customer (name, address, phone, email, DOB, national_id) VALUES(?,?,?,?,?,?)";
					java.sql.PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, accidTxt.getText());
					//ps.setString(1, customeridTxt.getText());
					ps.setString(1, nameTxt.getText());
					ps.setString(2, addressTxt.getText());
					ps.setString(3, phoneTxt.getText());
					ps.setString(4, emailTxt.getText());
					ps.setString(5, dobTxt.getText());
					ps.setString(6, national_idTxt.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "customer inserted successfully!");
				}else  if(e.getSource()==updateBtn){
						 sql="UPDATE  customer SET name=?,address=?,phone=?,email=? WHERE customerid=?";
						java.sql.PreparedStatement ps=con.prepareStatement(sql);
						//ps.setString(1, loanidTxt.getText());
						//ps.setString(1, customeridTxt.getText());
						ps.setString(1, nameTxt.getText());
						ps.setString(2, addressTxt.getText());
						ps.setString(3, phoneTxt.getText());
						ps.setString(4, emailTxt.getText());
						//ps.setString(3, notesTxt.getText());
					ps.setInt(5, Integer.parseInt(customeridTxt.getText()));
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "customer updated successfully!");
					}else  if(e.getSource()==deleteBtn){
						 sql="DELETE FROM  customer WHERE customerid=?";
						java.sql.PreparedStatement ps=con.prepareStatement(sql);
						//ps.setString(1, nameTxt.getText());
						ps.setInt(1, Integer.parseInt(customeridTxt.getText()));
						 //ps.setInt(1, teacheridTxt); 
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "customer deleted successfully!");
					}else  if(e.getSource()==loadBtn){
					 model.setRowCount(0);
					 sql="SELECT * FROM customer";
					 ResultSet rs=con.createStatement().executeQuery(sql);
					while(rs.next()){
						model.addRow(new Object[]{rs.getInt("customerid"),rs.getString("name"),rs.getString("address"),rs.getString("phone"),rs.getString("email"),rs.getString("DOB"),rs.getString("national_id")});
						
					}
					}
				}catch(Exception ex){ex.printStackTrace();}
	}

}
