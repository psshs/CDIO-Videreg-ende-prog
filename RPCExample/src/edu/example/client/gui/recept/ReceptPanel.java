/**
 * 
 */
package edu.example.client.gui.recept;

/**
 * @author Asger
 *
 * 10/06/2016
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.example.client.gui.MenuWidget;
import edu.example.client.gui.recept.receptkomp.ReceptkompPanel;
import edu.example.client.models.ReceptDTO;
import edu.example.client.service.RPCServiceClientImpl;

public class ReceptPanel extends Composite
{
	public MenuWidget parent;
	private RPCServiceClientImpl serverComm;
	private VerticalPanel mainPanel = new VerticalPanel();
	
	private final String searchBoxDefaultText = "Soeg efter recept";
	private final TextBox searchBox = new TextBox();
	
	private final Grid tableList = new Grid(1, 3);
	private List<ReceptDTO> receptList = null;
	private List<ReceptDTO> dispReceptList = null;
	
	public ReceptPanel(MenuWidget parent, RPCServiceClientImpl serverComm) {
		this.parent = parent;
		this.serverComm = serverComm;
		
		mainPanel.setSize("100%", "100%");
		initWidget(mainPanel);
		init();
		
		this.serverComm.getReceptList();
	}
	
	private void init() {
		//Top panel
		HorizontalPanel topPanel = new HorizontalPanel();
		
		HTML pageTitle = new HTML("Recepter");
		pageTitle.addStyleName("h1");
		
		searchBox.setText(searchBoxDefaultText);
		searchBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getUnicodeCharCode() == 13)
					search(searchBox.getText());
			}
		});
		searchBox.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				if(searchBox.getText().equals(searchBoxDefaultText))
					searchBox.setText("");
			}
		});
		
		Button buttonSearch = new Button();
		buttonSearch.setHTML("S&oslash;g");
		buttonSearch.addClickHandler(new ClickHandlerSearch());
		
		Button buttonUpdate = new Button("Opdater");
		buttonUpdate.addClickHandler(new ClickHandlerUpdate());
		
		//Table
		tableList.setHTML(0, 0, "ID");
		tableList.setHTML(0, 1, "Recept Navn");
		tableList.setHTML(0, 2, "Handling");
		tableList.setBorderWidth(1);
		tableList.setCellPadding(10);
		tableList.setWidth("100%");
		
		//Buttom panel
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setWidth("100%");
		buttonPanel.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
		buttonPanel.addStyleName("paddedVerticalPanel");
		
		Button createButtom = new Button();		
		createButtom.setHTML("Opret Recept");
		createButtom.setStyleName("button");
		createButtom.addClickHandler(new ClickHandlerCreate());
		
		//Add widgets
		topPanel.add(pageTitle);
		topPanel.add(searchBox);
		topPanel.add(buttonSearch);
		topPanel.add(buttonUpdate);
		
		buttonPanel.add(createButtom);
		
		mainPanel.add(topPanel);
		mainPanel.add(tableList);
		mainPanel.add(buttonPanel);
	}

	public void statusUpdate(String message) {
		Window.alert(message);
	}
	
	public void updateTable(List<ReceptDTO> recept) {
		receptList = recept;
		update(receptList);
	}
	
	private void update(List<ReceptDTO> recept) {
		dispReceptList = recept;
		clearTable(recept.size() + 1);
		
		for (int i = 0; i < recept.size(); i++) {
			addReceptToTable(i + 1, recept.get(i));
		}
	}
	
	private void addReceptToTable(int rowIndex, ReceptDTO recept) {
		tableList.setText(rowIndex, 0, "" + recept.getReceptID());
		tableList.setText(rowIndex, 1, recept.getReceptNavn());
		
		HorizontalPanel ButtonPanel = new HorizontalPanel();
		
		PushButton viewButton = new PushButton(new Image("Billeder/view-icon.png"));
		viewButton.addClickHandler(new ClickHandlerView(this));
		viewButton.setTitle("Se");
		ButtonPanel.add(viewButton);
		
		PushButton editButton = new PushButton(new Image("Billeder/edit-icon.png"));
		editButton.addClickHandler(new ClickHandlerEdit());
		editButton.setTitle("Rediger");
		ButtonPanel.add(editButton);
		
		PushButton deleteButton = new PushButton(new Image("Billeder/trash-icon.png"));
		deleteButton.addClickHandler(new ClickHandlerDelete());
		deleteButton.setTitle("Slet");
		ButtonPanel.add(deleteButton);
		
		tableList.setWidget(rowIndex, 2, ButtonPanel);
	}
	
	private void clearTable(int size) {
		tableList.resize(size, 3);
	}
	
	private void search(String searchText) {		
		if(receptList != null) {
			ArrayList<ReceptDTO> result = new ArrayList<>();
			
			try {
				int searhID = Integer.parseInt(searchText);
				
				for (ReceptDTO recept : receptList) {
					if(recept.getReceptID() == searhID) {
						result.add(recept);
						break;
					}
				}
			}
			catch(NumberFormatException e) {
				searchText = searchText.toUpperCase();
				
				for (ReceptDTO recept : receptList) {
					if(recept.getReceptNavn().toUpperCase().contains(searchText)) {
						result.add(recept);
					}
				}
			}
			finally {
				if(result != null)
					update(result);
			}
		}
	}
	
	private class ClickHandlerSearch implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			search(searchBox.getText());			
		}
	}
	
	private class ClickHandlerUpdate implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			serverComm.getReceptList();
		}
	}
	
	private class ClickHandlerEdit implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			ReceptDTO recept = dispReceptList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
			
			ReceptPopup editPopup = new ReceptPopup(recept);
			editPopup.setExecuteClickHandler(new PopupHandlerExecute(editPopup));
			editPopup.setCancelClickHandler(new PopupHandlerCancel(editPopup));
			editPopup.show();
		}
	}
	
	private class ClickHandlerCreate implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			ReceptPopup createPopup = new ReceptPopup(null);
			createPopup.setExecuteClickHandler(new PopupHandlerExecute(createPopup));
			createPopup.setCancelClickHandler(new PopupHandlerCancel(createPopup));
			createPopup.show();
		}
	}
	
	private class ClickHandlerDelete implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			ReceptDTO recept = dispReceptList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
			
			if(Window.confirm("Er du sikker paa at du vil slette recepten " + recept.getReceptID() + ", " + recept.getReceptNavn() +"?"))
				serverComm.deleteRecept(recept.getReceptID());
		}
	}
	
	private class ClickHandlerView implements ClickHandler
	{
		private ReceptPanel parent;
		
		protected ClickHandlerView(ReceptPanel parent) {
			this.parent = parent;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			ReceptDTO recept = dispReceptList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
			
			ReceptkompPanel receptkompPanel = new ReceptkompPanel(parent, serverComm, recept.getReceptID());
			parent.parent.gotoPanel(receptkompPanel);
		}
	}
	
	private class PopupHandlerExecute implements ClickHandler 
	{
		private final ReceptPopup popup;
		
		protected PopupHandlerExecute(ReceptPopup popup) {
			this.popup = popup;
		}

		@Override
		public void onClick(ClickEvent event) {
			int receptID = popup.getReceptID();
			String receptName = popup.getReceptName();
			
			
			ReceptDTO recept = new ReceptDTO(receptID, receptName);
			
			popup.hide();
			if(popup.isCreate()) 
				serverComm.createRecept(recept);
			else 
				serverComm.updateRecept(recept);
		}
	}
	
	private class PopupHandlerCancel implements ClickHandler 
	{
		private ReceptPopup popup;
		
		protected PopupHandlerCancel(ReceptPopup popup) {
			this.popup = popup;
		}

		@Override
		public void onClick(ClickEvent event) {
			popup.hide();
		}
	}
}