package com.editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JFormattedTextField;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField searchBar;
	private JList fileList;
	private JScrollPane scrollPanel;
	private JMenuBar mainMenu;
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem quitItem;
	private JMenu sortMenu;
	private JMenuItem nameAscItem;
	private JMenuItem nameDscItem;
	private JMenuItem latestItem;
	private JMenuItem oldestItem;
	private JMenu clearBtn;
	private JMenu searchBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 340);
		setResizable(false);
		contentPanel = new JPanel();
		scrollPanel = new JScrollPane();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		mainMenu = new JMenuBar();
		mainMenu.setBorderPainted(false);
		mainMenu.setToolTipText("");
		mainMenu.setBounds(0, 0, 480, 30);
		contentPanel.add(mainMenu);
		
		fileMenu = new JMenu("File");
		mainMenu.add(fileMenu);
		
		newItem = new JMenuItem("New");
		fileMenu.add(newItem);
		
		quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		sortMenu = new JMenu("Sort");
		mainMenu.add(sortMenu);
		
		nameAscItem = new JMenuItem("Name (A to Z)");
		sortMenu.add(nameAscItem);
		
		nameDscItem = new JMenuItem("Name (Z to A)");
		sortMenu.add(nameDscItem);
		
		latestItem = new JMenuItem("Latest Modified");
		sortMenu.add(latestItem);
		
		oldestItem = new JMenuItem("Oldest Modified");
		sortMenu.add(oldestItem);
		
		searchBar = new JTextField();
		searchBar.setToolTipText("");
		searchBar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mainMenu.add(searchBar);
		searchBar.setColumns(10);
		
		clearBtn = new JMenu("×");
		mainMenu.add(clearBtn);
		clearBtn.setOpaque(true);
		clearBtn.setBackground(Color.WHITE);
		clearBtn.setBorder(BorderFactory.createEmptyBorder());
		
		searchBtn = new JMenu("Search");
		mainMenu.add(searchBtn);
		scrollPanel.setBounds(0, 29, 490, 274);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		contentPanel.add(scrollPanel);
		
		fileList = new JList();
		scrollPanel.setViewportView(fileList);
	}
}
