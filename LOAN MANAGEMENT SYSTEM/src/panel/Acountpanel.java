
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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.util.DB;

public class Acountpanel extends JPanel implements ActionListener{

//public void actionPerformed(ActionEvent arg0) {
//	// TODO Auto-generated method stub
//	
//}
 
	JTextField accidTxt=new JTextField();
	JTextField customeridTxt=new JTextField();
	JTextField titleTxt=new JTextField();
	JTextField dateTxt=new JTextField();
	JTextField statusTxt=new JTextField();
	JTextField valueTxt=new JTextField();
	JTextField notesTxt=new JTextField();
	
	
	JButton addBtn=new JButton("Add");
	JButton updateBtn=new JButton("Update");
	JButton deleteBtn=new JButton("Delete");
	JButton loadBtn=new JButton("Load");

	JTable table;
	DefaultTableModel model;
	
	//constructor
	
	public Acountpanel(){
		setLayout(null);
		String[] labels={"Account id","Customerid","Title","Date","Status","Value","Notes"};
		String[] cols={"accid","customerid","title","date","status","value","notes"};
		
		model= new DefaultTableModel(labels,0);
		table =new JTable(model);
		
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(20,250,800,300);
		int y=20;
		addField("ID",accidTxt,y);
		y+=30;
		addField("Customerid",customeridTxt,y);
		y+=30;
		addField("Title",titleTxt,y);
		y+=30;
		addField("Date",dateTxt,y);
		y+=30;
		addField("Status",statusTxt,y);
		y+=30;
		addField("Value",valueTxt,y);
		y+=30;
		addField("Notes",notesTxt,y);
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
				sql="INSERT INTO account (customerid,title,date,status,value,notes) VALUES (?,?,?,?,?,?)";
				java.sql.PreparedStatement ps=con.prepareStatement(sql);
				//ps.setString(1, accidTxt.getText());
				ps.setString(1, customeridTxt.getText());
				ps.setString(2, titleTxt.getText());
				ps.setString(3, dateTxt.getText());
				ps.setString(4, statusTxt.getText());
				ps.setString(5, valueTxt.getText());
				ps.setString(6, notesTxt.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "account inserted successfully!");
			}else  if(e.getSource()==updateBtn){
					 sql="UPDATE  account SET title=?,status=?,notes=? WHERE accid=?";
					java.sql.PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, loanidTxt.getText());
					//ps.setString(1, customeridTxt.getText());
					ps.setString(1, titleTxt.getText());
					//ps.setString(2, dateTxt.getText());
					ps.setString(2, statusTxt.getText());
					//ps.setInt(3, valueTxt.getInt());
					ps.setString(3, notesTxt.getText());
					ps.setInt(4, Integer.parseInt(accidTxt.getText()));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "account updated successfully!");
				}else  if(e.getSource()==deleteBtn){
					 sql="DELETE FROM  account WHERE accid=?";
					PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, nameTxt.getText());
					ps.setInt(1, Integer.parseInt(accidTxt.getText()));
					 //ps.setInt(1, teacheridTxt); 
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "account deleted successfully!");
				}else  if(e.getSource()==loadBtn){
				 model.setRowCount(0);
				 sql="SELECT * FROM account";
				 ResultSet rs=con.createStatement().executeQuery(sql);
				while(rs.next()){
					model.addRow(new Object[]{rs.getInt("accid"),rs.getString("customerid"),rs.getString("title"),rs.getString("date"),rs.getString("status"),rs.getString("value"),rs.getString("notes")});
					
				}
				}
			}catch(Exception ex){ex.printStackTrace();}
}}


