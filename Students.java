//Afsana Siraj 
//CS 211

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Students extends JDialog implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	public final static int ADD_MODE = 0;
	public final static int MODIFY_MODE = 1;
	public final static int DELETE_MODE = 2;
	protected int count = 0;
	
	private JTable myReferencedTable;
	JTextField Row = new JTextField(15);
	JTextField FName = new JTextField(15);
	JTextField LName = new JTextField(15);
	JTextField CUNYID = new JTextField(15);
	JTextField GPA = new JTextField(15);
	JTextField VenusLogIn = new JTextField(15);
	
	public Students(JTable newTable)
	{
		//Appears when student is clicked
		this.myReferencedTable = newTable;
		this.setSize(320,250);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Modify Student Information");
		
		JLabel lblRow = new JLabel("Row: ");
		JLabel lblFName = new JLabel("First Name: ");
		JLabel lblLName = new JLabel("Last Name: ");
		JLabel lblCUNYID = new JLabel("CUNY ID: ");
		JLabel lblGPA = new JLabel("GPA: ");
		JLabel lblVLOGIN = new JLabel("Venus Login: ");
		lblRow.setPreferredSize(new Dimension(100,11));
		lblFName.setPreferredSize(new Dimension(100,11));
		lblLName.setPreferredSize(new Dimension(100,11));
		lblCUNYID.setPreferredSize(new Dimension(100,11));
		lblGPA.setPreferredSize(new Dimension(100,11));
		lblVLOGIN.setPreferredSize(new Dimension(100,11));
		
		//Cannot change row # or student login information
		VenusLogIn.setEditable(false);
		Row.setEditable(false);
		
		JButton btnOperation = new JButton("Modify");
		btnOperation.setActionCommand("Operation");
		btnOperation.addActionListener(this);
		JButton btnCancel = new JButton ("Cancel");
		btnCancel.addActionListener(this);
		this.add(lblRow);
		this.add(Row);
		this.add(lblFName);
		this.add(FName);
		this.add(lblLName);
		this.add(LName);
		this.add(lblCUNYID);
		this.add(CUNYID);
		this.add(lblGPA);
		this.add(GPA);
		this.add(lblVLOGIN);
		this.add(VenusLogIn);
		this.add(btnOperation);
		this.add(btnCancel);
		
	}
	public Students(JTable newTable, int add)
	{
		this.count = add;
		this.myReferencedTable = newTable;
		this.setSize(350,200);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Add New Student");
		
		//frame that appears to add a new student 
		JLabel lblFName = new JLabel("First Name: ");
		JLabel lblLName = new JLabel("Last Name: ");
		JLabel lblCUNYID = new JLabel("CUNY ID: ");
		JLabel lblGPA = new JLabel("GPA: ");
	
		lblFName.setPreferredSize(new Dimension(100,11));
		lblLName.setPreferredSize(new Dimension(100,11));
		lblCUNYID.setPreferredSize(new Dimension(100,11));
		lblGPA.setPreferredSize(new Dimension(100,11));
		JButton btnOperation = new JButton("Add");
		btnOperation.setActionCommand("Add");
		btnOperation.addActionListener(this);
		JButton btnCancel = new JButton ("Cancel");
		btnCancel.addActionListener(this);
		this.add(lblFName);
		this.add(FName);
		this.add(lblLName);
		this.add(LName);
		this.add(lblCUNYID);
		this.add(CUNYID);
		this.add(lblGPA);
		this.add(GPA);
		this.add(btnOperation);
		this.add(btnCancel);
	
		
		this.setModal(true);
		this.getRootPane().setDefaultButton(btnOperation); 

	}
	
	public void PopulateDataFromSelectedRowInTable()
	{
	
		int currentRowInGUI = this.myReferencedTable.getSelectedRow();
		if(currentRowInGUI >= 0)
		{
			
			this.Row.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 0).toString());
			this.FName.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 1).toString());
			this.LName.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 2).toString());
			this.CUNYID.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 3).toString());
			this.GPA.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 4).toString());
			this.VenusLogIn.setText(this.myReferencedTable.getValueAt
					(currentRowInGUI, 5).toString());
			
		}
		else
		{
			System.out.println("No row selected in JTable");
		}
	}
	
	//checks to make sure the user inputs to add student are valid, to create a venus login and proper Cuny ID
	public boolean Validate(){
		
		boolean valid = false;
		if(FName.getText().length()<2)
		{
			JOptionPane.showMessageDialog(null, "First name must have at least 2 characters.");
		}
		else if(LName.getText().length()<2)
		{
			JOptionPane.showMessageDialog(null, "Last name must have at least 2 characters.");
		}
		else if(Integer.parseInt(CUNYID.getText())<10000000 || Integer.parseInt(CUNYID.getText())>99999999)
		{
			JOptionPane.showMessageDialog(null, "Cuny ID must have 8 digits");
		}
		else if(
				Double.parseDouble(GPA.getText())<0.0 || Double.parseDouble(GPA.getText())>4.0)
		{
			JOptionPane.showMessageDialog(null, "GPA is not valid");
		}
		else
		{
			valid = true;
			return valid;
		}
		return valid;
	}

	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		case "Operation":
			int currentRowInGUI = this.myReferencedTable.getSelectedRow();
			StringBuilder vlogin = new StringBuilder();
			String fname,lname;
			int cunyid;
			double gpa;			
			int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to modify the student's data?");
			if(result == JOptionPane.OK_OPTION)
			{	
				if(currentRowInGUI >= 0)
				{
					if(Validate())
					{
						fname = FName.getText();
						lname = LName.getText();
						cunyid = Integer.parseInt(CUNYID.getText());
						gpa = Double.parseDouble(GPA.getText());
						vlogin.append(lname, 0, 2).append(fname,0,2).append(CUNYID.getText(),4,8);
						this.myReferencedTable.setValueAt(fname, currentRowInGUI, 1);
						this.myReferencedTable.setValueAt(lname, currentRowInGUI, 2);
						this.myReferencedTable.setValueAt(cunyid, currentRowInGUI, 3);
						this.myReferencedTable.setValueAt(gpa, currentRowInGUI, 4);
						this.myReferencedTable.setValueAt(vlogin, currentRowInGUI, 5);
					}
				}
				
			}
			this.setVisible(false);
			break;
			
		case "Add":
			StringBuilder vlogin1 = new StringBuilder();
			String fname1,lname1;
			int cunyid1;
			double gpa1;
			int result1 = JOptionPane.showConfirmDialog(null,"Are you sure you want to add this student?");
			if(result1 == JOptionPane.OK_OPTION)
			{
				
				if(Validate())
				{
					fname1 = FName.getText();
					lname1 = LName.getText();
					cunyid1 = Integer.parseInt(CUNYID.getText());
					gpa1 = Double.parseDouble(GPA.getText());
					vlogin1.append(lname1, 0, 2).append(fname1,0,2).append(CUNYID.getText(),4,8);
					DefaultTableModel tblModel = (DefaultTableModel)this.myReferencedTable.getModel();
					tblModel.addRow(new Object[]{tblModel.getRowCount()+1,fname1,lname1,cunyid1,gpa1,vlogin1});
				}
			}
			this.setVisible(false);
			break;
		case "Cancel":
			this.setVisible(false);
			break;
		}
		
	}
}
