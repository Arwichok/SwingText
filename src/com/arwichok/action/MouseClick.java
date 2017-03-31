package com.arwichok.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.JMenuItem;


public class MouseClick extends MouseAdapter{
	JPopupMenu popupMenu;
	JTextPane textPane;
	JMenuItem[] menuItem;
	boolean isSelect;

	public MouseClick(JPopupMenu popupMenu, JTextPane textPane, JMenuItem ... menuItem){
		
		this.popupMenu = popupMenu;
		this.textPane = textPane;
		this.menuItem = menuItem;

	}
	public MouseClick(JTextPane textPane){
		this.textPane = textPane;
	}

	@Override
	public void mousePressed(MouseEvent me){
		setEnabledItem();

		if(!me.isPopupTrigger()) return;
		
		popupMenu.show(me.getComponent(), me.getX(), me.getY());
	}

	@Override
	public void mouseReleased(MouseEvent me){
		setEnabledItem();

		if(!me.isPopupTrigger()) return;
		
		popupMenu.show(me.getComponent(), me.getX(), me.getY());
	}


	void setEnabledItem(){
		if(textPane.getSelectedText() == null){
			isSelect = false;
		}else{
			isSelect = true;
		}

		for(int i = 0; i < menuItem.length; i++){
			menuItem[i].setEnabled(isSelect);
		}
	}
}