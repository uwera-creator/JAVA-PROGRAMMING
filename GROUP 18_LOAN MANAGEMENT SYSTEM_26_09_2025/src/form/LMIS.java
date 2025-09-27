package form;

import java.awt.BorderLayout;
import javax.swing.*;

import panel.Accountpanel;
import panel.Collateralpanel;
import panel.Customerpanel;
import panel.Loanpanel;
import panel.Paymentpanel;
import panel.Userpanel;

public class LMIS extends JFrame {
    public LMIS(String role, int userid) {
        setTitle("Loan Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("User", new Userpanel());
        tabs.add("Account", new Accountpanel());
       // tabs.add("Collateral", new Collateralpanel());
        //tabs.add("Account", new Customerpanel());
        tabs.add("Loan", new Loanpanel());
        tabs.add("Payment", new Paymentpanel());
        tabs.add("Collateral", new Collateralpanel());

        add(tabs);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LMIS("admin", 1);
    }
}


//import javax.swing.*;
//
//import panel.*;
//
//public class LMIS extends JFrame {
//	JTabbedPane tabs=new JTabbedPane();
//	//constructor
//	public LMIS (String role,int userid){
//		setTitle("Loan Management System");
//		setSize(900,600);
//		setLayout(new BorderLayout ());
//		if(role.equalsIgnoreCase("admin")){
//			tabs.add("users", new Userpanel());
//			tabs.add("Collateral", new Collateralpanel());
//			tabs.add("Account",new Accountpanel());
//			tabs.add("Customer", new Customerpanel());
//			tabs.add("Loan",new Loanpanel());
//			tabs.add("Payment", new Paymentpanel());
//		}else if(role.equalsIgnoreCase("account")){
//			tabs.add("Account",new Accountpanel());
//			
//		}
//		else if(role.equalsIgnoreCase("loan")){
//			tabs.add("my loan", new Loanpanel());
//			
//		}
//		else if(role.equalsIgnoreCase("payment")){
//			tabs.add("my payment", new Paymentpanel());
//			
//		}
//		else if(role.equalsIgnoreCase("collateral")){
//			tabs.add("my collateral", new Collateralpanel());
//			
//		}
//		add(tabs,BorderLayout.CENTER);
//		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//	}
//
//}
//
//
//
