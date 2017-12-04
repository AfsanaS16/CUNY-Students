//Afsana Siraj 
//CS 211

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public abstract class MenuFrame implements ActionListener, KeyListener, MouseListener{
	protected JFrame frame;
	protected FileDialog fd;
	public MenuFrame()
	{
		//Basic Setup for JFrame
		frame = new JFrame();
		frame.setSize(700, 550);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setTitle("Final Project");	
		
		//menu bar 
		JMenuBar menuBar = new JMenuBar();
		
		//options under file
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		JMenuItem jmiExport = new JMenuItem("Export");
		JMenuItem jmiQuit = new JMenuItem("Quit");
		
		//actions for file options
		jmFile.setMnemonic(KeyEvent.VK_F);
		jmiOpen.setMnemonic(KeyEvent.VK_O);
		jmiOpen.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_O,
						Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
						)
				);
		jmiExport.setMnemonic(KeyEvent.VK_E);
		jmiExport.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_E,
						Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
						)
				);
		jmiQuit.setMnemonic(KeyEvent.VK_Q);
		jmiQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		jmiOpen.addActionListener(this);
		jmiQuit.addActionListener(this);
		jmiExport.addActionListener(this);
		jmFile.add(jmiOpen);
		jmFile.add(jmiExport);
		jmFile.addSeparator();
		jmFile.add(jmiQuit);
		menuBar.add(jmFile);
		
		//Help tab and actions
		JMenu jmHelp = new JMenu("Help");
		jmHelp.setMnemonic(KeyEvent.VK_H);
		JMenuItem jmiAbout = new JMenuItem("About");
		jmiAbout.setMnemonic(KeyEvent.VK_A);
		jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
				));
		jmiAbout.addActionListener(this);
		jmHelp.add(jmiAbout);
		
		menuBar.add(jmHelp);
		frame.setJMenuBar(menuBar);
	
		FileDialog fd = new FileDialog(frame,"Select a File",FileDialog.LOAD);
		fd.setVisible(false);
		
		frame.setVisible(true);
	}
	

	protected void HandleAboutEvent()
	{
		JOptionPane.showMessageDialog(this.frame, "This is an application developed by me, Afsana Siraj, to demonstrate how to implement a desktop application for a student management system.");
	}
	
	public static boolean CheckInteger(String value)
	{
		boolean isValidInteger = false;
		try
		{
			Integer.parseInt(value);
			isValidInteger = true;
		}
		catch(Exception ex)
		{
		}
		return isValidInteger;
	}
	
	public static boolean CheckDouble(String value)
	{
		boolean isValidDouble = false;
		try
		{
			Double.parseDouble(value);
			isValidDouble = true;
		}
		catch(Exception ex)
		{
		}
		return isValidDouble;
	}
	
	
	
	abstract protected void HandleFileOpenEvent();
	
	abstract public void keyTyped(KeyEvent e); 
	
	abstract public void keyPressed(KeyEvent e);
	
	abstract public void keyReleased(KeyEvent e);

	abstract public void mouseReleased(MouseEvent e); 
	
	abstract public void mouseEntered(MouseEvent e);

	abstract public void mouseExited(MouseEvent e);

	abstract public void actionPerformed(ActionEvent e);
	
}
