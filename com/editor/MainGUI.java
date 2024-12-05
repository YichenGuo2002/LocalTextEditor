package com.editor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;

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
	private DefaultListModel demoList;
	private JMenuItem searchNameItem;
	private JMenuItem searchUserItem;
	private JMenu userMenu;
	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	
	private void redirectEditGUI(int fileId){
		ViewGUI edit = new ViewGUI(fileId);
        edit.setVisible(true);
	};

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
		
		newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	redirectEditGUI(-1);
            	dispose();
            }
		});
		
		quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		userMenu = new JMenu("User");
		mainMenu.add(userMenu);
		
		loginItem = new JMenuItem("Logged in as {User}");
		loginItem.setEnabled(false);
		userMenu.add(loginItem);
		
		logoutItem = new JMenuItem("Log out");
		userMenu.add(logoutItem);
		
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
		
		searchNameItem = new JMenuItem("By Name");
		searchBtn.add(searchNameItem);
		
		searchUserItem = new JMenuItem("By User");
		searchBtn.add(searchUserItem);
		scrollPanel.setBounds(0, 29, 490, 274);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		contentPanel.add(scrollPanel);
		
		fileList = new JList();
		demoList = new DefaultListModel();
		demoList.add(0, "file1");
		demoList.add(1, "file2");
		demoList.add(2, "file3");
		demoList.add(3, "file4");
		demoList.add(4, "file5");
		demoList.add(5, "file6");
		demoList.add(6, "file7");
		demoList.add(7, "file8");
		demoList.add(8, "file5");
		demoList.add(9, "file6");
		demoList.add(10, "file7");
		demoList.add(11, "file8");
		
		fileList.setFixedCellHeight(30);
		fileList.setBorder(new EmptyBorder(5, 5, 5, 5));
		fileList.setModel(demoList);
		scrollPanel.setViewportView(fileList);
	}
}
