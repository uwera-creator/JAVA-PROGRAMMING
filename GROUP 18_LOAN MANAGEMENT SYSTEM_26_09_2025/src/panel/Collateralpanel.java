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

public class Collateralpanel extends JPanel implements ActionListener {


	 
		JTextField collateralidTxt=new JTextField();
		JTextField categoryTxt=new JTextField();
		JTextField detailTxt=new JTextField();
		JTextField ownerTxt=new JTextField();
		JTextField locationTxt=new JTextField();
		JTextField createdatTxt=new JTextField();
		
		
		JButton addBtn=new JButton("Add");
		JButton updateBtn=new JButton("Update");
		JButton deleteBtn=new JButton("Delete");
		JButton loadBtn=new JButton("Load");

		JTable table;
		DefaultTableModel model;
		
		//constructor
		
		public Collateralpanel(){
			setLayout(null);
			String[] labels={"Collateral id","Category","Detail","Owner","Location","Created At"};
			String[] cols={"collateralid","category","detail","owner","location","createdat"};
			
			model= new DefaultTableModel(labels,0);
			table =new JTable(model);
			
			JScrollPane sp=new JScrollPane(table);
			sp.setBounds(20,200,800,300);
			int y=20;
			addField("ID",collateralidTxt,y);
			y+=30;
			addField("Category",categoryTxt,y);
			y+=30;
			addField("Detail",detailTxt,y);
			y+=30;
			addField("Owner",ownerTxt,y);
			y+=30;
			addField("Location",locationTxt,y);
			y+=30;
			addField("Created At",createdatTxt,y);
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
					sql="INSERT INTO collateral (Category,detail,owner,location,createdat) VALUES (?,?,?,?,?)";
					java.sql.PreparedStatement ps=con.prepareStatement(sql);
					//ps.setString(1, accidTxt.getText());
					//ps.setString(1, collateralidTxt.getText());
					ps.setString(1, categoryTxt.getText());
					ps.setString(2, detailTxt.getText());
					ps.setString(3, ownerTxt.getText());
					ps.setString(4, locationTxt.getText());
					ps.setString(5, createdatTxt.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "collateral inserted successfully!");
				}else  if(e.getSource()==updateBtn){
						 sql="UPDATE  collateral SET category=?,detail=?,owner=?,location=? WHERE collateralid=?";
						java.sql.PreparedStatement ps=con.prepareStatement(sql);
						//ps.setString(1, loanidTxt.getText());
						//ps.setString(1, customeridTxt.getText());
						ps.setString(1, categoryTxt.getText());
						//ps.setString(2, dateTxt.getText());
						ps.setString(2, detailTxt.getText());
						//ps.setInt(3, valueTxt.getInt());
						ps.setString(3, ownerTxt.getText());
						ps.setString(4, locationTxt.getText());
						ps.setInt(5, Integer.parseInt(collateralidTxt.getText()));
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "collateral updated successfully!");
					}else  if(e.getSource()==deleteBtn){
						 sql="DELETE FROM  collateral WHERE collateralid=?";
						PreparedStatement ps=con.prepareStatement(sql);
						//ps.setString(1, nameTxt.getText());
						ps.setInt(1, Integer.parseInt(collateralidTxt.getText()));
						 //ps.setInt(1, teacheridTxt); 
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "collateral deleted successfully!");
					}else  if(e.getSource()==loadBtn){
					 model.setRowCount(0);
					 sql="SELECT * FROM collateral";
					 ResultSet rs=con.createStatement().executeQuery(sql);
					while(rs.next()){
						model.addRow(new Object[]{rs.getInt("collateralid"),rs.getString("category"),rs.getString("detail"),rs.getString("owner"),rs.getString("location"),rs.getString("createdat")});
						
					}
					}
				}catch(Exception ex){ex.printStackTrace();}
	}}




