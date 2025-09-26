package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.util.DB;

public class Paymentpanel extends JPanel implements ActionListener {

	 
		JTextField paymentidTxt=new JTextField();
		JTextField referencenoTxt=new JTextField();
		JTextField amountTxt=new JTextField();
		JTextField dateTxt=new JTextField();
		JTextField methodsTxt=new JTextField();
		JTextField statusTxt=new JTextField();
		
		
		
		JButton addBtn=new JButton("Add");
		JButton updateBtn=new JButton("Update");
		JButton deleteBtn=new JButton("Delete");
		JButton loadBtn=new JButton("Load");

		JTable table;
		DefaultTableModel model;
		
		//constructor
		
		public Paymentpanel(){
			setLayout(null);
			String[] labels={"Payment id","Reference no","Amount","Date","Method","Status"};
			String[] cols={"paymentid","referenceno","amount","date","methods","status"};
			
			model= new DefaultTableModel(labels,0);
			table =new JTable(model);
			
			JScrollPane sp=new JScrollPane(table);
			sp.setBounds(20,200,800,300);
			int y=20;
			addField("ID",paymentidTxt,y);
			y+=30;
			addField("Refernce",referencenoTxt,y);
			y+=30;
			addField("Amount",amountTxt,y);
			y+=30;
			addField("Date",dateTxt,y);
			y+=30;
			addField("Method",methodsTxt,y);
			y+=30;
			addField("Status",statusTxt,y);
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
					sql="INSERT INTO payment (referenceno,amount,date,methods,status) VALUES (?,?,?,?,?)";
					java.sql.PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, accidTxt.getText());
					//ps.setString(1, customeridTxt.getText());
					ps.setString(1, referencenoTxt.getText());
					ps.setString(2, amountTxt.getText());
					ps.setString(3, dateTxt.getText());
					ps.setString(4, methodsTxt.getText());
					ps.setString(5, statusTxt.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "payment inserted successfully!");
				}else  if(e.getSource()==updateBtn){
						 sql="UPDATE  payment SET amount=?,methods=?,status=? WHERE paymentid=?";
						java.sql.PreparedStatement ps=con.prepareStatement(sql);
						//ps.setString(1, loanidTxt.getText());
						//ps.setString(1, customeridTxt.getText());
						ps.setString(1, amountTxt.getText());
						//ps.setString(2, dateTxt.getText());
						ps.setString(2, methodsTxt.getText());
						//ps.setInt(3, valueTxt.getInt());
						ps.setString(3, statusTxt.getText());
						ps.setInt(4, Integer.parseInt(paymentidTxt.getText()));
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "payment updated successfully!");
					}else  if(e.getSource()==deleteBtn){
						 sql="DELETE FROM  payment WHERE paymentid=?";
						PreparedStatement ps=con.prepareStatement(sql);
						//ps.setString(1, nameTxt.getText());
						ps.setInt(1, Integer.parseInt(paymentidTxt.getText()));
						 //ps.setInt(1, teacheridTxt); 
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "payment deleted successfully!");
					}else  if(e.getSource()==loadBtn){
					 model.setRowCount(0);
					 sql="SELECT * FROM payment";
					 ResultSet rs=con.createStatement().executeQuery(sql);
					while(rs.next()){
						model.addRow(new Object[]{rs.getInt("paymentid"),rs.getString("referenceno"),rs.getString("amount"),rs.getString("date"),rs.getString("methods"),rs.getString("status")});
						
					}
					}
				}catch(Exception ex){ex.printStackTrace();}
	}}



