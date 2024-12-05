package com.editor;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class EditGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField fileName;
	private JScrollPane scrollPanel;
	private JMenuBar editMenu;
	private JMenu fileMenu;
	private JMenuItem saveItem;
	private JMenuItem deleteItem;
	private JMenuItem quitItem;
	private JLabel spacer;
	private JLabel fileLabel;
	private JTextArea file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditGUI frame = new EditGUI();
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
	public EditGUI() {
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
		
		deleteItem = new JMenuItem("Delete");
		fileMenu.add(deleteItem);
		
		quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		spacer = new JLabel("     ");  // Spacer for decorating the menu bar
        spacer.setPreferredSize(new Dimension(100, 0));  // Set width (100px) and height (0px)
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
	}
}
