package edu.example.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import edu.example.client.gui.profile.ViewProfile;
import edu.example.client.service.ExampleServiceClientImpl;

public class MenuWidget extends Composite
{
	private MenuWidget me = this;
	private HorizontalPanel hPanel= new HorizontalPanel();
	private Banner parent;
	private Object currentPanel = null;
	private ExampleServiceClientImpl serverComm;

	public MenuWidget(Banner banner, ExampleServiceClientImpl serverComm) {
		initWidget(this.hPanel);
		parent = banner;
		this.serverComm = serverComm;
		
		MenuBar menu = new MenuBar(true);
		menu.addItem(new MenuItem("Ny afvejning", cmd_NyAfvejning));
		menu.addItem(new MenuItem("Min side", cmd_MinSide));
		menu.addItem(new MenuItem("Soeg", cmd_Soeg));
		menu.addItem(new MenuItem("Hjem", cmd_Hjem));
		menu.addItem(new MenuItem("Afslut", cmd_Afslut));

		hPanel.add(menu);
		menu.setHeight("100%");
	}
	
	public Object getCurrentPanel() {
		return currentPanel;
	}
	
	public void gotoPanel(Composite panel) {
		currentPanel = panel;
		
		parent.setContentPanel(panel);
	}

	Command cmd_NyAfvejning = new Command() {
		public void execute() {
//			xx.openNyAfvejning();
		}
	};

	Command cmd_MinSide = new Command() {
		public void execute() {
			ViewProfile viewPanel = new ViewProfile("Se Profil", 25, me, serverComm);
			gotoPanel(viewPanel);
//			currentPanel = viewPanel;
//			
//			parent.setContentPanel(viewPanel);
		}
	};
	
	Command cmd_Soeg = new Command() {
		public void execute() {
			
		}
	};
	
	Command cmd_Hjem = new Command() {
		public void execute() {
//			xx.openHjemSide();
		}
	};
	
	Command cmd_Afslut = new Command() {
		public void execute() {
//			xx.openAfslutSide();
		}
	};
}