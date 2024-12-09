package com.editor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField fileName;
	private JScrollPane scrollPanel;
	private JMenuBar editMenu;
	private JMenu fileMenu;
	private JMenuItem editItem;
	private JMenuItem deleteItem;
	private JMenuItem menuItem;
	private JLabel spacer;
	private JLabel fileLabel;
	private JTextArea fileContent;
	private JMenu userMenu;
	private JMenuItem loggedInItem;
	private JMenuItem logoutItem;
	private FileManager fileManager;
	private File file;
	
	//fileId is used to manage the file we are editing
	//if fileId is -1, it means we are creating a new file
	//else we are editing an existing file
	private int fileId;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewGUI frame = new ViewGUI(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ViewGUI(int fileId) {
		this.fileId = fileId;
		this.fileManager = new FileManager();
		file = fileManager.getFileById(fileId);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 340);
		setResizable(false);
		contentPanel = new JPanel();
		scrollPanel = new JScrollPane();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		editMenu = new JMenuBar();
		editMenu.setBorderPainted(false);
		editMenu.setBounds(0, 0, 480, 30);
		contentPanel.add(editMenu);
		
		fileMenu = new JMenu("File");
		editMenu.add(fileMenu);
		
		editItem = new JMenuItem("Edit");
		fileMenu.add(editItem);
		editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new EditGUI(fileId).setVisible(true);
            	dispose();
            }
		});
		
		deleteItem = new JMenuItem("Delete");
		fileMenu.add(deleteItem);
		deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	fileManager.deleteFileById(fileId);
            	fileManager.save();
            	new MainGUI().setVisible(true);
            	dispose();
            }
		});
		
		menuItem = new JMenuItem("Menu");
		fileMenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new MainGUI().setVisible(true);
            	dispose();
            }
		});
		
		userMenu = new JMenu("User");
		editMenu.add(userMenu);
		
		loggedInItem = new JMenuItem("Logged in as: " + LoginGUI.getLoggedInUser());
		loggedInItem.setEnabled(false);
		userMenu.add(loggedInItem);
		logoutItem = new JMenuItem("Logout");
		userMenu.add(logoutItem);

		logoutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI.loggedInUser = null;
				new LoginGUI().setVisible(true);
				dispose();
			}
		});
		
		spacer = new JLabel("     ");  // Spacer for decorating the menu bar
        spacer.setPreferredSize(new Dimension(100, 0));
        editMenu.add(spacer);
		
		fileLabel = new JLabel("File Name:  ");
		fileLabel.setForeground(Color.GRAY);
		editMenu.add(fileLabel);
		
		fileName = new JTextField();
		fileName.setEnabled(false);
		fileName.setEditable(false);
		fileLabel.setLabelFor(fileName);
		fileName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fileName.setText(file.getName());
		fileName.setDisabledTextColor(Color.BLACK);
		editMenu.add(fileName);
		fileName.setColumns(10);
		scrollPanel.setBounds(0, 30, 476, 272);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		contentPanel.add(scrollPanel);
		
		fileContent = new JTextArea();
		fileContent.setEnabled(false);
		fileContent.setEditable(false);
		fileContent.setWrapStyleWord(true);
		scrollPanel.setViewportView(fileContent);
		
		fileContent.setForeground(Color.BLACK);
		fileContent.setDisabledTextColor(Color.BLACK);
		fileContent.setColumns(30);
		fileContent.setRows(10);
		fileContent.setLineWrap(true);
		fileContent.setText(file.getContent());
		
		SwingUtilities.invokeLater(new Runnable()
		{
		    public void run()
		    {
		        scrollPanel.getViewport().setViewPosition( new Point(0, 0) );
		    }
		});
	}
}
