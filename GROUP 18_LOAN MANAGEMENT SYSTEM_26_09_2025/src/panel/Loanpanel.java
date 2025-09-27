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

public class Loanpanel extends JPanel implements ActionListener {

 
	JTextField loanidTxt=new JTextField();
	JTextField nameTxt=new JTextField();
	JTextField typeTxt=new JTextField();
	JTextField startdateTxt=new JTextField();
	JTextField enddateTxt=new JTextField();
	JTextField statusTxt=new JTextField();
	
	
	JButton addBtn=new JButton("Add");
	JButton updateBtn=new JButton("Update");
	JButton deleteBtn=new JButton("Delete");
	JButton loadBtn=new JButton("Load");

	JTable table;
	DefaultTableModel model;
	
	//constructor
	
	public Loanpanel(){
		setLayout(null);
		String[] labels={"Account id","Customerid","Title","Date","Status","Value","Notes"};
		String[] cols={"accid","customerid","title","date","status","value","notes"};
		
		model= new DefaultTableModel(labels,0);
		table =new JTable(model);
		
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(20,200,800,300);
		int y=20;
		addField("ID",loanidTxt,y);
		y+=30;
		addField("Name",nameTxt,y);
		y+=30;
		addField("Type",typeTxt,y);
		y+=30;
		addField("Start Date",startdateTxt,y);
		y+=30;
		addField("End Date",enddateTxt,y);
		y+=30;
		addField("Status",statusTxt,y);
		y+=30;
		addField("Notes",statusTxt,y);
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
				sql="INSERT INTO loan (name,type,startdate,enddate,status) VALUES (?,?,?,?,?)";
				java.sql.PreparedStatement ps=con.prepareStatement(sql);
				//ps.setString(1, accidTxt.getText());
				//ps.setString(1,loanidTxt.getText());
				ps.setString(1, nameTxt.getText());
				ps.setString(2, typeTxt.getText());
				ps.setString(3, startdateTxt.getText());
				ps.setString(4, enddateTxt.getText());
				ps.setString(5, statusTxt.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "loan inserted successfully!");
			}else  if(e.getSource()==updateBtn){
					 sql="UPDATE  loan SET name=?,status=? WHERE loanid=?";
					java.sql.PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, loanidTxt.getText());
					//ps.setString(1, customeridTxt.getText());
					ps.setString(1, nameTxt.getText());
					//ps.setString(2, dateTxt.getText());
					ps.setString(2, statusTxt.getText());
					//ps.setInt(3, valueTxt.getInt());
					//ps.setString(3, notesTxt.getText());
					ps.setInt(3, Integer.parseInt(loanidTxt.getText()));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "loan updated successfully!");
				}else  if(e.getSource()==deleteBtn){
					 sql="DELETE FROM  loan WHERE loanid=?";
					PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, nameTxt.getText());
					ps.setInt(1, Integer.parseInt(loanidTxt.getText()));
					 //ps.setInt(1, teacheridTxt); 
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "loan deleted successfully!");
				}else  if(e.getSource()==loadBtn){
				 model.setRowCount(0);
				 sql="SELECT * FROM loan";
				 ResultSet rs=con.createStatement().executeQuery(sql);
				while(rs.next()){
					model.addRow(new Object[]{rs.getInt("loanid"),rs.getString("name"),rs.getString("type"),rs.getString("startdate"),rs.getString("enddate"),rs.getString("status")});
					
				}
				}
			}catch(Exception ex){ex.printStackTrace();}
}}


