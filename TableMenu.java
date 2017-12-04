//Afsana Siraj 
//CS 211

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TableMenu extends MenuFrame{

	
	protected JLabel lblOutput;
	protected JTable tblData;
	protected DefaultTableModel tblModel;
	protected Students myDialog;
	public int count;
	JComboBox <Object> jcbSearch;
	private JTextField searchBox;
	public TableMenu()
	{
		//table setup for students in JFrame
		this.frame.setSize(600, 550); 
		JLabel lblSearch = new JLabel("Search By:");
		frame.add(lblSearch);
		jcbSearch = new JComboBox<Object>();
		jcbSearch.setActionCommand("Search");
		jcbSearch.addItem("Row ID");
		jcbSearch.addItem("First Name");
		jcbSearch.addItem("Last Name");
		jcbSearch.addItem("CUNY ID");
		jcbSearch.addItem("GPA");
		jcbSearch.addItem("Venus Login");
		jcbSearch.getSelectedIndex();
		frame.add(jcbSearch);
		
		//Search Box
		searchBox = new JTextField(10);
		searchBox.setActionCommand("Search");
		searchBox.addActionListener(this);
		searchBox.addKeyListener(this);
		frame.add(searchBox);
		
		//button for add
		JButton btnAdd = new JButton("Add");
		btnAdd.setActionCommand("Add");
		btnAdd.addActionListener(this);
		frame.add(btnAdd);
	
		//Button for delete
		JButton btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("Delete");
		btnDelete.addActionListener(this);
		frame.add(btnDelete);
		
		
		tblData = new JTable();
		JScrollPane dataTable = new JScrollPane(tblData);
		this.frame.add(dataTable);
		
		tblData.setModel(new DefaultTableModel() 
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//cells in the table cannot be changed
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
			
            @Override
            public Class<?> getColumnClass(int column) 
            {
                switch (column) 
                {
                    case 0:
                        	return Integer.class; 
                    case 1:
                    		return String.class; 
                    case 2:
                    		return String.class;
                    case 3:
                			return Integer.class;
                    case 4:
                			return Integer.class;
                    case 5:
                			return String.class;

                    default:
                    		return String.class;
                }
            }
        });
		
		
		tblModel = (DefaultTableModel)tblData.getModel();
		tblModel.addColumn("Row");
		tblModel.addColumn("First Name");
		tblModel.addColumn("Last Name");
		tblModel.addColumn("CUNY ID");
		tblModel.addColumn("GPA");
		tblModel.addColumn("Venus Login");
		tblData.setAutoCreateRowSorter(true);
		tblData.addMouseListener(this);
		JButton btnExport = new JButton("Export");
		btnExport.setActionCommand("Export");
		btnExport.addActionListener(this);
		
		frame.add(btnExport);
		
		this.frame.setVisible(true);
	}
	private int GetCurrentComboSearchIndex() 
	{ 
		return this.jcbSearch.getSelectedIndex(); 	
	}
	private String GetCurrentTextSearch() 
	{ 
		return this.searchBox.getText();
	}
	private void FilterRowBasedOnSearch()
	{
		RowFilter<Object,Object> rowFilter = new RowFilter<Object,Object>()
		{
			@Override
			public boolean include(javax.swing.RowFilter.Entry<? extends Object, ? extends Object> entry) {
				boolean shouldInclude = false;
				
				switch(GetCurrentComboSearchIndex())
			     {
			     	case 0: 
			     		if(MenuFrame.CheckInteger(GetCurrentTextSearch()) 
			     				&& Integer.parseInt(entry.getStringValue(GetCurrentComboSearchIndex()).trim()) 
			     				== Integer.parseInt(GetCurrentTextSearch()))
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 1:
			     		if(
			     				entry.getStringValue( GetCurrentComboSearchIndex() ).toLowerCase().indexOf
			     				(
			     					GetCurrentTextSearch().toLowerCase()
			     				) >= 0
			     		)
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 2: 
			     		if(
			     				entry.getStringValue( GetCurrentComboSearchIndex() ).toLowerCase().indexOf
			     				(
			     					GetCurrentTextSearch().toLowerCase()
			     				) >= 0
			     		)
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 3: 
			     		if(MenuFrame.CheckInteger(GetCurrentTextSearch()) 
			     				&& Integer.parseInt(entry.getStringValue(GetCurrentComboSearchIndex()).trim()) 
			     				== Integer.parseInt(GetCurrentTextSearch()))
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 4:
			     		if(MenuFrame.CheckDouble(GetCurrentTextSearch()) 
			     				&& Double.parseDouble(entry.getStringValue(GetCurrentComboSearchIndex()).trim()) 
			     				>= Double.parseDouble(GetCurrentTextSearch()))
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     	case 5: 
			     		if(
			     				entry.getStringValue( GetCurrentComboSearchIndex() ).toLowerCase().indexOf
			     				(
			     					GetCurrentTextSearch().toLowerCase()
			     				) >= 0
			     		)
			     		{
			     			shouldInclude = true;
			     		}
			     	break;
			     }
				
				
				return shouldInclude;
			}
		};
		@SuppressWarnings("unchecked")
		TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tblData.getRowSorter();
		sorter.setRowFilter(rowFilter);
	}
	
	@Override
	protected void HandleAboutEvent()
	{
		JOptionPane.showMessageDialog(this.frame, "This is an application developed by me, Afsana Siraj, to implement a desktop application, for student data. ");
	}
	
	@Override
	protected void HandleFileOpenEvent() 
	{
		FileDialog fd = new FileDialog(frame);
		fd.setVisible(true);
		String InputFile = fd.getFile();
        if (InputFile != null) {
        File selectedFile = new File(fd.getDirectory()+InputFile);
        try
        {
        	@SuppressWarnings("resource")
			Scanner fsc = new Scanner(selectedFile).useDelimiter(", |,|\\n");
        	while(fsc.hasNextLine())
        	{
        		String FName = fsc.next().trim();
        		String LName = fsc.next().trim();
        		int CID = fsc.nextInt();
        		Double Grade = fsc.nextDouble();
        		String Login = fsc.next().trim();
        		tblModel.addRow(new Object[]{tblModel.getRowCount()+1,FName,LName,CID,Grade,Login});
        	}
        	fsc.close();
        }
        catch (IOException e){}
        System.out.println(selectedFile.getName());
         
        }
	}
	protected void HandleFileExportEvent()
	{
		
		FileDialog fd = new FileDialog(frame,"Save Info",FileDialog.SAVE);
		fd.setVisible(true);
		String InputFile = fd.getFile();
        if (InputFile != null) {
        	File selectedFile = new File(fd.getDirectory()+InputFile);
        	try
        	{
        		FileWriter fw = new FileWriter(selectedFile);
        		for(int i = 0;i<count;i++)
        		{
        			String fname, lname, vlogin;
        			int cunyid;
        			double gpa;
        			fname = (String) tblModel.getValueAt(i, 1);
        			lname = (String) tblModel.getValueAt(i, 2);
        			cunyid = (Integer) tblModel.getValueAt(i, 3);
        			gpa = (Double) tblModel.getValueAt(i, 4);
        			vlogin = (String) tblModel.getValueAt(i, 5);
        			fw.write(fname+", "+lname+", "+cunyid+", "+gpa+", "+vlogin+"\r\n");
        		}
        		fw.close();
        		JOptionPane.showMessageDialog(null, "File Exported to "+fd.getDirectory()+InputFile);
        	}
        	catch(IOException e){}
        }
	}
	protected void HandleAddPersonEvent()
	{
		myDialog = new Students(this.tblData,count);
		myDialog.setVisible(true);
	}
	protected void HandleDeletePersonEvent()
	{
		if(tblData.getSelectedRow() >= 0 )
		{
			StringBuilder sb = new StringBuilder();
			int rowID = tblData.convertRowIndexToModel(tblData.getSelectedRow());
			tblModel.getColumnCount();
			for(int i = 0; i < tblModel.getColumnCount(); i++)
			{
				
				sb.append("\r\n" + tblModel.getColumnName(i) + " : " +  tblModel.getValueAt(rowID, i) + " ");
			}
			int result = JOptionPane.showConfirmDialog(null, 
					"Are you sure you want to delete this person's data?" + sb.toString()
					);
			if(result == JOptionPane.OK_OPTION)
			{
				tblModel.removeRow(tblData.convertRowIndexToModel(tblData.getSelectedRow()));
			}

		}
	}
	protected void HandleModifyPersonEvent()
	{
		
		myDialog = new Students(this.tblData);

		if(tblData.getSelectedRow() >= 0)
		{
			myDialog.PopulateDataFromSelectedRowInTable();
			myDialog.setVisible(true);
		}
		else
		{
			JOptionPane.showMessageDialog(this.frame, 
					"No row was selected, please select a row.",
					"No Row Selected",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
			case "Search":
				FilterRowBasedOnSearch();
			break;
			case "Delete" :
				HandleDeletePersonEvent();
			break;
			case "Add" :
				HandleAddPersonEvent();
			break;
			case "Modify":
				HandleModifyPersonEvent();
			break;
			case "Open":
				HandleFileOpenEvent();
			break;
			case "Export":
				HandleFileExportEvent();
			break;
			case "Quit":
				System.exit(0);
			break;
			case "About":
				HandleAboutEvent();
			break;
		}
	}
	public void mouseClicked(MouseEvent e) 
	{
		//when mouse is clicked twice
		if(e.getClickCount() == 2)
		{
			HandleModifyPersonEvent();	
		}
		
		
	}
	public void mousePressed(MouseEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
}
