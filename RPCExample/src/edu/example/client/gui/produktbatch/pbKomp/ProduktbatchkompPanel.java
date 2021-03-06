package edu.example.client.gui.produktbatch.pbKomp;

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

import edu.example.client.exceptions.DALException;
import edu.example.client.gui.MenuWidget;
import edu.example.client.gui.produktbatch.ProduktbatchPanel;
import edu.example.client.models.ProduktbatchDTO;
import edu.example.client.models.ProduktbatchkompDTO;
import edu.example.client.service.RPCServiceClientImpl;

public class ProduktbatchkompPanel extends Composite 
{
	public ProduktbatchPanel parent;
	private RPCServiceClientImpl serverComm;
	private VerticalPanel mainPanel = new VerticalPanel();
	
	private final String searchBoxDefaultText = "Soeg efter produktbatch komponenter";
	private final TextBox searchBox = new TextBox();
	
	private final int tableColumns = 6;
	private final Grid tableList;
	private List<ProduktbatchkompDTO> pbkompList = null;
	private List<ProduktbatchkompDTO> dispPbkompList = null;
	
	private int currPBID;
		
	public ProduktbatchkompPanel(ProduktbatchPanel parent, RPCServiceClientImpl serverComm, int pbID) {
		this.parent = parent;
		this.serverComm = serverComm;
		this.currPBID = pbID;
		
		mainPanel.setSize("100%", "100%");
		initWidget(mainPanel);
		tableList = new Grid(1, tableColumns);
		init();
		
		this.serverComm.getPbkompListByPbID(currPBID);
	}
	
	private void init() {
		//Top panel
		HorizontalPanel topPanel = new HorizontalPanel();
		
		HTML pageTitle = new HTML("Produktbatch komponenter");
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
		tableList.setHTML(0, 0, "Produkt ID");
		tableList.setHTML(0, 1, "R&aring;varebatch ID");
		tableList.setHTML(0, 2, "Tara");
		tableList.setHTML(0, 3, "Netto");
		tableList.setHTML(0, 4, "Operat&oslash;r ID");
		tableList.setHTML(0, 5, "Handling");
		tableList.setBorderWidth(1);
		tableList.setCellPadding(10);
		tableList.setWidth("100%");
		
		//Buttom panel
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setWidth("100%");
		buttonPanel.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
		buttonPanel.addStyleName("paddedVerticalPanel");
		
		Button createButton = new Button();		
		createButton.setHTML("Opret Produktbatch Komponent");
		createButton.setStyleName("button");
		createButton.addClickHandler(new ClickHandlerCreate());
		
		Button backButton = new Button();		
		backButton.setHTML("Tilbage");
		backButton.setStyleName("button");
		backButton.addClickHandler(new ClickHandlerBack(this));
		
		//Add widgets
		topPanel.add(pageTitle);
		topPanel.add(searchBox);
		topPanel.add(buttonSearch);
		topPanel.add(buttonUpdate);
		
		buttonPanel.add(createButton);
		buttonPanel.add(backButton);
		
		mainPanel.add(topPanel);
		mainPanel.add(tableList);
		mainPanel.add(buttonPanel);
	}
	
	public void statusUpdate(String message) {
		Window.alert(message);
	}
	
	public void updateTable(List<ProduktbatchkompDTO> pbkomp) {
		pbkompList = pbkomp;
		update(pbkompList);
	}
	
	private void update(List<ProduktbatchkompDTO> pbkomps) {
		dispPbkompList = pbkomps;
		clearTable(pbkomps.size() + 1);
		
		for (int i = 0; i < pbkomps.size(); i++) {
			addPbkompToTable(i + 1, pbkomps.get(i));
		}
	}
	
	private void addPbkompToTable(int rowIndex, ProduktbatchkompDTO pbKomp) {		
		tableList.setText(rowIndex, 0, "" + pbKomp.getPbID());
		tableList.setText(rowIndex, 1, "" + pbKomp.getRbID());
		tableList.setText(rowIndex, 2, "" + pbKomp.getTara());
		tableList.setText(rowIndex, 3, "" + pbKomp.getNetto());
		tableList.setText(rowIndex, 4, "" + pbKomp.getOprID());
		
		HorizontalPanel ButtonPanel = new HorizontalPanel();
		
		PushButton editButton = new PushButton(new Image("Billeder/edit-icon.png"));
		editButton.addClickHandler(new ClickHandlerEdit());
		editButton.setTitle("Rediger");
		ButtonPanel.add(editButton);
		
		PushButton deleteButton = new PushButton(new Image("Billeder/trash-icon.png"));
		deleteButton.addClickHandler(new ClickHandlerDelete());
		deleteButton.setTitle("Slet");
		ButtonPanel.add(deleteButton);
		
		tableList.setWidget(rowIndex, tableColumns - 1, ButtonPanel);
	}
	
	private void clearTable(int size) {
		tableList.resize(size, tableColumns);
	}
	
	private void search(String searchText) {
		if(pbkompList != null) {
			ArrayList<ProduktbatchkompDTO> result = new ArrayList<>();
			if(searchText.length() > 0) {
				try {
					int searhInt = Integer.parseInt(searchText);
					
					for (ProduktbatchkompDTO pbkomp: pbkompList) {
						if(pbkomp.getPbID() == searhInt || pbkomp.getRbID() == searhInt || pbkomp.getOprID() == searhInt) {
							result.add(pbkomp);
						}
					}
				}
				catch(NumberFormatException e) { }
				finally {
					if(result != null)
						update(result);
				}
			}
			else
				update(pbkompList);
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
			serverComm.getPbkompListByPbID(currPBID);
		}
	}
	
	private class ClickHandlerEdit implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			ProduktbatchkompDTO pbkomp = dispPbkompList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
			
			ProduktbatchkompPopup editPopup = new ProduktbatchkompPopup(pbkomp);
			editPopup.setExecuteClickHandler(new PopupHandlerExecute(editPopup));
			editPopup.setCancelClickHandler(new PopupHandlerCancel(editPopup));
			editPopup.show();
		}
	}
	
	private class ClickHandlerDelete implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			ProduktbatchkompDTO pbkomp = dispPbkompList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
			
			if(Window.confirm("Er du sikker paa at du vil slette produktbatch komponenten " + pbkomp.getPbID() + " + " + pbkomp.getRbID() + '?'))
				serverComm.deletePbkomp(pbkomp.getPbID(), pbkomp.getRbID());
		}
	}
	
	private class ClickHandlerCreate implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			ProduktbatchkompPopup createPopup = new ProduktbatchkompPopup(null);
			createPopup.setExecuteClickHandler(new PopupHandlerExecute(createPopup));
			createPopup.setCancelClickHandler(new PopupHandlerCancel(createPopup));
			createPopup.show();
		}
	}
	
	private class ClickHandlerBack implements ClickHandler
	{
		ProduktbatchkompPanel parent;
		
		protected ClickHandlerBack(ProduktbatchkompPanel parent) {
			this.parent = parent;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			parent.parent.parent.gotoPanel(parent.parent);
		}
	}
	
	private class PopupHandlerExecute implements ClickHandler 
	{
		private final ProduktbatchkompPopup popup;
		
		protected PopupHandlerExecute(ProduktbatchkompPopup popup) {
			this.popup = popup;
		}

		@Override
		public void onClick(ClickEvent event) {
			int produktbatchID = popup.getProduktbatchID();
			int raavarebatchID = popup.getRaavarebatchID();
			double tara = popup.getTara();
			double netto = popup.getNetto();
			int operatoerID = popup.getOperatoerID();
			
			try {
				ProduktbatchkompDTO produktbatch = new ProduktbatchkompDTO(produktbatchID, raavarebatchID, tara, netto, operatoerID);

				if(popup.isCreate())
					serverComm.createPbkomp(produktbatch);
				else 
					serverComm.updatePbkomp(produktbatch);
			} 
			catch (DALException e) {
				statusUpdate(e.getMessage());
			}
			finally {
				popup.hide();
			}
		}
	}
	
	private class PopupHandlerCancel implements ClickHandler 
	{
		private ProduktbatchkompPopup popup;
		
		protected PopupHandlerCancel(ProduktbatchkompPopup popup) {
			this.popup = popup;
		}

		@Override
		public void onClick(ClickEvent event) {
			popup.hide();
		}
	}
}