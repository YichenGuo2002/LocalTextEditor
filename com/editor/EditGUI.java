package com.editor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField fileName;
	private JScrollPane scrollPanel;
	private JMenuBar editMenu;
	private JMenu fileMenu;
	private JMenuItem saveItem;
	private JMenuItem menuItem;
	private JLabel spacer;
	private JLabel fileLabel;
	private JTextArea file;
	private JMenu userMenu;
	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private FileManager fileManager;
	
	//fileId is used to manage the file we are editing
	//if fileId is -1, it means we are creating a new file
	//else we are editing an existing file
	private int fileId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditGUI frame = new EditGUI(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditGUI(int fileId) {
		this.fileId = fileId;
		fileManager = new FileManager();
		
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
		
		saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
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
		
		loginItem = new JMenuItem("Logged in as {User}");
		loginItem.setEnabled(false);
		userMenu.add(loginItem);
		
		logoutItem = new JMenuItem("Log out");
		userMenu.add(logoutItem);
		
		spacer = new JLabel("     ");  // Spacer for decorating the menu bar
        spacer.setPreferredSize(new Dimension(100, 0));
        editMenu.add(spacer);
		
		fileLabel = new JLabel("File Name:  ");
		fileLabel.setForeground(Color.GRAY);
		editMenu.add(fileLabel);
		
		fileName = new JTextField();
		fileLabel.setLabelFor(fileName);
		fileName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		editMenu.add(fileName);
		fileName.setColumns(10);
		scrollPanel.setBounds(0, 30, 476, 272);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		contentPanel.add(scrollPanel);
		
		file = new JTextArea();
		file.setWrapStyleWord(true);
		scrollPanel.setViewportView(file);
		file.setForeground(Color.BLACK);
		file.setColumns(30);
		file.setRows(10);
		file.setLineWrap(true);
		
		this.addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        new MainGUI().setVisible(true);
		    }
		});
	}
}
