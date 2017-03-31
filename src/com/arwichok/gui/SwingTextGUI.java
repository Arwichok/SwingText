package com.arwichok.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;
import javax.swing.KeyStroke;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

import java.awt.event.*;
import java.awt.*;

import com.arwichok.Main;
import com.arwichok.action.OpenFile;
import com.arwichok.action.SaveFile;
import com.arwichok.action.ExitFile;
import com.arwichok.action.AboutHelp;
import com.arwichok.action.MouseClick;
import com.arwichok.action.SearchEdit;
import com.arwichok.gui.TextLineNumber;

import java.net.URL;


public class SwingTextGUI extends JFrame{
	
	Main rootMain = new Main();

	ImageIcon icon = new ImageIcon(rootMain.getClass().getResource("etc/icon.png"));
	ImageIcon searchIcon = new ImageIcon(rootMain.getClass().getResource("etc/searchIcon.png"));


	JMenuBar menuBar;
	JTextPane textPane;
	JPopupMenu popupMenu;
	JTextField fieldSearch;
	JToolBar toolBar;
	JButton buttonSearch;
	int stateWindow = NORMAL;
	String encodingText = "UTF-8";
	OpenFile classOpen = null;
	SaveFile classSave = null;


	public SwingTextGUI(String caption){
		super(caption);
		
		setSize(500, 400);

		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
    	//setUndecorated(true);
    	//setDefaultLookAndFeelDecorated(true);
    	//addNotify();
    	//setOpacity(0.5f);

		
    	

		
		menuBar = new JMenuBar();

		JMenu menuFile = new JMenu("File");

		JMenuItem openFile = new JMenuItem("Open", KeyEvent.VK_O);
		openFile.setActionCommand("Open");
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		JMenuItem saveFile = new JMenuItem("Save                  ", KeyEvent.VK_S);
		saveFile.setActionCommand("Save");
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		JMenuItem saveAsFile = new JMenuItem("Save as...");
		saveAsFile.setActionCommand("Save as");
		JMenuItem hideFile = new JMenuItem("Hide", KeyEvent.VK_L);
		hideFile.setActionCommand("Hide");
		hideFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		JMenuItem exitFile = new JMenuItem("Exit", KeyEvent.VK_F4);
		exitFile.setActionCommand("Exit");
		exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

		//saveFile.setEnabled(false);

		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(saveAsFile);
		menuFile.addSeparator();
		menuFile.add(hideFile);
		menuFile.add(exitFile);
		
		menuBar.add(menuFile);


		JMenu menuEdit = new JMenu("Edit");

		//JTextField searchField = new JTextField(15);

		JMenuItem searchEdit = new JMenuItem("Search");
		searchEdit.setActionCommand("Search");
		searchEdit.setAccelerator(
			KeyStroke.getKeyStroke(
				KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));

		//menuEdit.add(searchField);
		menuEdit.add(searchEdit);

		
		JMenu encodingEdit = new JMenu("Encoding");

		JRadioButtonMenuItem utf8Encoding = 
			new JRadioButtonMenuItem("UTF-8");
		JRadioButtonMenuItem ansiEncoding = 
			new JRadioButtonMenuItem("US-ASCII");

		encodingEdit.add(utf8Encoding);
		encodingEdit.add(ansiEncoding);
		menuEdit.add(encodingEdit);

		ButtonGroup encodingGroup = new ButtonGroup();
		encodingGroup.add(utf8Encoding);
		encodingGroup.add(ansiEncoding);

		utf8Encoding.setSelected(true);


		menuBar.add(menuEdit);

		JMenu menuHelp = new JMenu("Help");

		JMenuItem aboutHelp = new JMenuItem("About");
		JMenuItem authorHelp = new JMenuItem("Author");
		
		menuHelp.add(aboutHelp);
		menuHelp.add(authorHelp);

		menuBar.add(menuHelp);

		setJMenuBar(menuBar);


		// toolBar = new JToolBar();
		// fieldSearch = new JTextField(15);
		// fieldSearch.setActionCommand("fieldSearch");
		// buttonSearch = new JButton(searchIcon);
		// buttonSearch.setActionCommand("buttonSearch");

		// toolBar.add(fieldSearch);
		// toolBar.add(buttonSearch);

		// toolBar.setFloatable(false);
		// add(toolBar, BorderLayout.NORTH);



		textPane = new JTextPane();
		textPane.setFont(new Font("Sans", Font.BOLD, 20));
		JScrollPane scrollPane = new JScrollPane(textPane);
		TextLineNumber textLineNumber = new TextLineNumber(textPane, 2);
		scrollPane.setRowHeaderView(textLineNumber);
		add(scrollPane);


		
		popupMenu = new JPopupMenu();

		JMenuItem popupCut = new JMenuItem(new DefaultEditorKit.CutAction());
		JMenuItem popupCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		JMenuItem popupPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		JMenuItem popupSelectAll = new JMenuItem("Select All", KeyEvent.VK_A);
		popupSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

		popupCut.setText("Cut");
		popupCopy.setText("Copy");
		popupPaste.setText("Paste");
		popupSelectAll.setText("Select All");

		popupCut.setActionCommand("Cut");
		popupCopy.setActionCommand("Copy");
		popupPaste.setActionCommand("Paste");
		popupSelectAll.setActionCommand("Select All");


		popupMenu.add(popupCut);
		popupMenu.add(popupCopy);
		popupMenu.add(popupPaste);
		popupMenu.add(popupSelectAll);

		setVisible(true);	


		textPane.addMouseListener(new MouseClick(popupMenu, textPane, popupCut, popupCopy));



		classOpen = new OpenFile(textPane, this, encodingText);
		classSave = new SaveFile(textPane, this, encodingText);

		openFile.addActionListener(classOpen);
		saveFile.addActionListener(classSave);

		saveAsFile.addActionListener(classSave);
		exitFile.addActionListener(new ExitFile());

		popupSelectAll.addActionListener((ae) -> textPane.selectAll());

		//fieldSearch.addActionListener(new SearchEdit(fieldSearch, textPane));
		// buttonSearch.addActionListener(new ActionListener(){
		// 	@Override
		// 	public void actionPerformed(ActionEvent ae){

		// 	}
		// });

		searchEdit.addActionListener(new SearchEdit(textPane, searchIcon));

		aboutHelp.addActionListener(new AboutHelp(this));

		authorHelp.addActionListener((ae) -> {
			try {
			    Desktop.getDesktop().browse(new URL("https://vk.com/arwichokgroup").toURI());
			} catch (Exception e) {
			    e.printStackTrace();
			}	
		});

		hideFile.addActionListener((ae) -> setState(ICONIFIED));


		utf8Encoding.addActionListener((ae) -> {
			encodingText = "UTF-8";
			classOpen.openInPane(encodingText);
		});
		
		ansiEncoding.addActionListener((ae) -> {
			encodingText = "US-ASCII";
			classOpen.openInPane(encodingText);
		});



	}
}