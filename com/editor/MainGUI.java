package com.editor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
	private JMenuItem clearBtn;
	private JMenu searchBtn;
	private DefaultListModel fileInfoList;
	private JMenuItem searchNameItem;
	private JMenuItem searchUserItem;
	private JMenu userMenu;
	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private FileManager fileManager;
	private List<File> files;
	private JMenuItem idItem;
	
	private void redirectEditGUI(int fileId){
		EditGUI edit = new EditGUI(fileId);
        edit.setVisible(true);
        dispose();
	};
	
	private void redirectViewGUI(int fileId){
		ViewGUI view = new ViewGUI(fileId);
		view.setVisible(true);
        dispose();
	};

	private void loadFileInfoList(){
		fileInfoList.clear();
		int counter = 0;
		for(File file: files){
			fileInfoList.add(counter, file.printFile());
			counter++;
		}
		fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (e.getClickCount() == 2) {
                    int index = fileList.locationToIndex(e.getPoint());
                    if (index != -1) {
                    	redirectViewGUI(files.get(index).getId());
                    }
            	}
            }
        });
	};
	
	private void sortNameAsc(){
		Collections.sort(files, Comparator.comparing(File::getName));
		loadFileInfoList();
	};
	
	private void sortNameDsc(){
		Collections.sort(files, Comparator.comparing(File::getName).reversed());
		loadFileInfoList();
	};
	
	private void sortLatest(){
		Collections.sort(files, Comparator.comparing(File::getModifyTime).reversed());
		loadFileInfoList();
	};
	
	private void sortOldest(){
		Collections.sort(files, Comparator.comparing(File::getModifyTime));
		loadFileInfoList();
	};
	
	private void sortId(){
		Collections.sort(files, Comparator.comparing(File::getId));
		loadFileInfoList();
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
		fileManager = new FileManager();
		files = fileManager.getFiles();
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
		
		idItem = new JMenuItem("Id");
		sortMenu.add(idItem);
		idItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	sortId();
            }
		});
		
		nameAscItem = new JMenuItem("Name (A to Z)");
		sortMenu.add(nameAscItem);
		
		nameAscItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	sortNameAsc();
            }
		});
		
		nameDscItem = new JMenuItem("Name (Z to A)");
		sortMenu.add(nameDscItem);
		
		nameDscItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	sortNameDsc();
            }
		});
		
		latestItem = new JMenuItem("Latest Modified");
		sortMenu.add(latestItem);
		
		latestItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	sortLatest();
            }
		});
		
		oldestItem = new JMenuItem("Oldest Modified");
		sortMenu.add(oldestItem);
		
		oldestItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	sortOldest();
            }
		});
		
		searchBar = new JTextField();
		searchBar.setToolTipText("");
		searchBar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mainMenu.add(searchBar);
		searchBar.setColumns(10);
		
		clearBtn = new JMenuItem("×");
		mainMenu.add(clearBtn);
		clearBtn.setOpaque(true);
		clearBtn.setBackground(Color.WHITE);
		clearBtn.setBorder(BorderFactory.createEmptyBorder());
		
		clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	searchBar.setText("");
            	sortId();
            }
		});
		
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
		fileInfoList = new DefaultListModel();
		loadFileInfoList();
		fileList.setFixedCellHeight(30);
		fileList.setBorder(new EmptyBorder(5, 5, 5, 5));
		fileList.setModel(fileInfoList);
		scrollPanel.setViewportView(fileList);
	}
}
