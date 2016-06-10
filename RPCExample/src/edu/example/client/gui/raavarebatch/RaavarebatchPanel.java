package edu.example.client.gui.raavarebatch;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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
import edu.example.client.models.RaavarebatchDTO;
import edu.example.client.models.RaavareDTO;
import edu.example.client.service.ExampleServiceClientImpl;

public class RaavarebatchPanel extends Composite 
{
	public MenuWidget parent;
	private ExampleServiceClientImpl serverComm;
	private VerticalPanel mainPanel = new VerticalPanel();
	
	private final String searchBoxDefaultText = "Soeg efter raavarebatch";
	private final TextBox searchBox = new TextBox();
	
	private final int tableColumns = 6;
	private final Grid tableList;
	private List<RaavarebatchDTO> raavarebatchList = null;
	private List<RaavarebatchDTO> dispRaavarebatchList = null;
	
	public RaavarebatchPanel(MenuWidget parent, ExampleServiceClientImpl serverComm) {
		this.parent = parent;
		this.serverComm = serverComm;
		
		mainPanel.setSize("100%", "100%");
		initWidget(mainPanel);
		tableList = new Grid(1, tableColumns);
		init();
		
		this.serverComm.getRaavareList();
	}
	
	private void init() {
		//Top panel
		HorizontalPanel topPanel = new HorizontalPanel();
		
		HTML pageTitle = new HTML("R&aring;varebatchs");
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
		tableList.setHTML(0, 1, "R&aring;vare");
		tableList.setHTML(0, 2, "M&aelig;ngde (kg)");
		tableList.setHTML(0, 3, "Leveran&oslash;r");
		tableList.setHTML(0, 4, "R&aring;vare id");
		tableList.setHTML(0, 5, "Handling");
		tableList.setBorderWidth(1);
		tableList.setCellPadding(10);
		tableList.setWidth("100%");
		
		//Buttom panel
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setWidth("100%");
		buttonPanel.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
		buttonPanel.addStyleName("paddedVerticalPanel");
		
		Button createButtom = new Button();		
		createButtom.setHTML("Opret R&aring;varebatch");
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

//	public void statusUpdate(String result) {
//		String reply = "something went wrong!";
//		
//		Window.alert(result);
//	}
	
//	public void updateTable(List<RaavareDTO> raavarer) {
//		raavareList = raavarer;
//		update(raavareList);
//	}
	
	private void update(List<RaavarebatchDTO> raavarebatchs) {
		dispRaavarebatchList = raavarebatchs;
		clearTable(raavarebatchs.size() + 1);
		
		for (int i = 0; i < raavarebatchs.size(); i++) {
			addRaavareToTable(i + 1, raavarebatchs.get(i));
		}
	}
	
	private void addRaavareToTable(int rowIndex, RaavarebatchDTO raavarebatch) {
		RaavareDTO raavare = raavarebatch.getRaavare();
		
		tableList.setText(rowIndex, 0, "" + raavarebatch.getRbID());
		tableList.setText(rowIndex, 1, raavare.getRaavareNavn());
		tableList.setText(rowIndex, 2, "" + raavarebatch.getMaengde());
		tableList.setText(rowIndex, 3, raavare.getLeverandoer());
		tableList.setText(rowIndex, 4, "" + raavare.getRaavareID());
		
		HorizontalPanel ButtonPanel = new HorizontalPanel();
		
		PushButton editButton = new PushButton(new Image("Billeder/edit-icon.png"));
		editButton.addClickHandler(new ClickHandlerEdit());
		editButton.setTitle("Rediger");
		ButtonPanel.add(editButton);
		
		PushButton deleteButton = new PushButton(new Image("Billeder/trash-icon.png"));
		deleteButton.addClickHandler(new ClickHandlerDelete());
		deleteButton.setTitle("Slet");
		ButtonPanel.add(deleteButton);
		
		tableList.setWidget(rowIndex, 5, ButtonPanel);
	}
	
	private void clearTable(int size) {
		tableList.resize(size, tableColumns);
	}
	
	private void search(String searchText) {		
		if(raavarebatchList != null) {
			ArrayList<RaavarebatchDTO> result = new ArrayList<>();
			
			try {
				int searhInt = Integer.parseInt(searchText);
				
				for (RaavarebatchDTO raavarebatch: raavarebatchList) {
					if(raavarebatch.getRbID() == searhInt || raavarebatch.getRaavare().getRaavareID() == searhInt || raavarebatch.getMaengde() == searhInt) {
						result.add(raavarebatch);
					}
				}
			}
			catch(NumberFormatException e) {
				searchText = searchText.toUpperCase();
				
				RaavareDTO raavare = null;
				for (RaavarebatchDTO raavarebatch : raavarebatchList) {
					raavare = raavarebatch.getRaavare();
					if(raavare.getRaavareNavn().toUpperCase().contains(searchText) || raavare.getLeverandoer().toUpperCase().contains(searchText)) {
						result.add(raavarebatch);
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
//			search(searchBox.getText());			
		}
	}
	
	private class ClickHandlerUpdate implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
//			serverComm.getRaavareList();
		}
	}
	
	private class ClickHandlerEdit implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
//			RaavareDTO raavare = dispRaavareList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
//			
//			RaavareInfoPopup editPopup = new RaavareInfoPopup(raavare);
//			editPopup.setExecuteClickHandler(new PopupHandlerExecute(editPopup));
//			editPopup.setCancelClickHandler(new PopupHandlerCancel(editPopup));
//			editPopup.show();
		}
	}
	
	private class ClickHandlerCreate implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
//			RaavareInfoPopup createPopup = new RaavareInfoPopup(null);
//			createPopup.setExecuteClickHandler(new PopupHandlerExecute(createPopup));
//			createPopup.setCancelClickHandler(new PopupHandlerCancel(createPopup));
//			createPopup.show();
		}
	}
	
	private class ClickHandlerDelete implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
//			RaavareDTO raavare = dispRaavareList.get(tableList.getCellForEvent(event).getRowIndex() - 1);
//			
//			if(Window.confirm("Er du sikker paa at du vil slette raavare " + raavare.getRaavareID() + ", " + raavare.getRaavareNavn() + " af " + raavare.getLeverandoer()))
//				serverComm.deleteRaavare(raavare.getRaavareID());
		}
	}
	
//	private class PopupHandlerExecute implements ClickHandler 
//	{
//		private final RaavareInfoPopup popup;
//		
//		protected PopupHandlerExecute(RaavareInfoPopup popup) {
//			this.popup = popup;
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			int raavareID = popup.getRaavareID();
//			String raavareName = popup.getRaavareName();
//			String leverandoer = popup.getRaavareLeverandoer();
//			
//			RaavareDTO raavare = new RaavareDTO(raavareID, raavareName, leverandoer);
//			
//			popup.hide();
//			if(popup.isCreate()) 
//				serverComm.createRaavare(raavare);
//			else 
//				serverComm.updateRaavare(raavare);
//		}
//	}
	
//	private class PopupHandlerCancel implements ClickHandler 
//	{
//		private RaavareInfoPopup popup;
//		
//		protected PopupHandlerCancel(RaavareInfoPopup popup) {
//			this.popup = popup;
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			popup.hide();
//		}
//	}
}