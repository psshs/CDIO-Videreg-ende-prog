package edu.example.client.service;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import edu.example.client.gui.Banner;
import edu.example.client.gui.Lists.OperatoerList;
import edu.example.client.gui.login.Login;
import edu.example.client.gui.produktbatch.ProduktbatchPanel;
import edu.example.client.gui.produktbatch.pbKomp.ProduktbatchkompPanel;
import edu.example.client.gui.profile.ViewProfile;
import edu.example.client.gui.raavare.RaavarePanel;
import edu.example.client.gui.raavarebatch.RaavarebatchPanel;
import edu.example.client.gui.recept.ReceptPanel;
import edu.example.client.gui.recept.receptkomp.ReceptkompPanel;
import edu.example.client.models.OperatorDTO;
import edu.example.client.models.ProduktbatchDTO;
import edu.example.client.models.ProduktbatchkompDTO;
import edu.example.client.models.RaavareDTO;
import edu.example.client.models.RaavarebatchDTO;
import edu.example.client.models.ReceptDTO;
import edu.example.client.models.ReceptkompDTO;
import edu.example.client.weightPage.WeightPage;

public class RPCServiceClientImpl implements RPCServiceIClient
{
	private RPCServiceAsync service;
	private Banner mainGui;
	
	public RPCServiceClientImpl(String url) {
		System.out.println("URL: " + url);
		
		this.service = GWT.create(RPCService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget) this.service;
		endPoint.setServiceEntryPoint(url);
		
		this.mainGui = new Banner(this);
	}
	
	public Banner getMainGUI() {
		return mainGui;
	}
	
	//Operators
	@Override
	public void getOpList()	{
		this.service.getOpList(new DefaultCallback());
	}
	
	@Override
	public void getOperator(int oprID) {
		this.service.getOperator(oprID, new DefaultCallback());
	}
	
	@Override
	public void createOperator(OperatorDTO opr) {
		this.service.createOperator(opr, new DefaultCallback());
	}
	
	@Override
	public void updateOperator(OperatorDTO opr) {
		this.service.updateOperator(opr, new DefaultCallback());
	}
	
	@Override
	public void getPassword(int oprID) {
		this.service.getPassword(oprID, new DefaultCallback());
	}

	@Override
	public void deleteOperator(int oprID) {
		this.service.deleteOperator(oprID, new DefaultCallback());
	}
	
	//Raavarer
	@Override
	public void getRaavareList() {
		this.service.getRaavareList(new DefaultCallback());
	}

	@Override
	public void createRaavare(RaavareDTO raavare) {
		this.service.createRaavare(raavare, new DefaultCallback());
	}

	@Override
	public void updateRaavare(RaavareDTO raavare) {
		this.service.updateRaavare(raavare, new DefaultCallback());
	}

	@Override
	public void deleteRaavare(int raavareID) {
		this.service.deleteRaavare(raavareID, new DefaultCallback());
	}
	
	//Raavarebatch
	@Override
	public void getRaavarebatch(int rbID) {
		this.service.getRaavarebatch(rbID, new DefaultCallback());
	}
	
	@Override
	public void getRaavarebatchList() {
		this.service.getRaavarebatchList(new RaavarebatchCallback());
	}

	@Override
	public void createRaavarebatch(RaavarebatchDTO raavarebatch) {
		this.service.createRaavarebatch(raavarebatch, new RaavarebatchCallback());
	}

	@Override
	public void updateRaavarebatch(RaavarebatchDTO raavarebatch) {
		this.service.updateRaavarebatch(raavarebatch, new RaavarebatchCallback());
	}

	@Override
	public void deleteRaavarebatch(int rbID) {
		this.service.deleteRaavarebatch(rbID, new RaavarebatchCallback());
	}
	
	//Produktbatch Komponent
	@Override
	public void getPbkompListByPbID(int pbID) {
		this.service.getPbkompListByPbID(pbID, new ProduktbatchKompCallback());
	}

	@Override
	public void createPbkomp(ProduktbatchkompDTO pbkomp) {
		this.service.createPbkomp(pbkomp, new ProduktbatchKompCallback());
	}

	@Override
	public void updatePbkomp(ProduktbatchkompDTO pbkomp) {
		this.service.updatePbkomp(pbkomp, new ProduktbatchKompCallback());
	}

	@Override
	public void deletePbkomp(int pbID, int rbID) {
		this.service.deletePbkomp(pbID, rbID, new ProduktbatchKompCallback());
	}
	
	//Produktbatch
	@Override
	public void getProduktbatch(int pbID) {
		this.service.getProduktbatch(pbID, new DefaultCallback());
	}
	
	@Override
	public void getProduktbatchList() {
		this.service.getProduktbatchList(new ProduktbatchCallback());
	}

	@Override
	public void createProduktbatch(ProduktbatchDTO produktbatch) {
		this.service.createProduktbatch(produktbatch, new ProduktbatchCallback());
	}

	@Override
	public void updateProduktbatch(ProduktbatchDTO produktbatch) {
		this.service.updateProduktbatch(produktbatch, new ProduktbatchCallback());
	}

	@Override
	public void deleteProduktbatch(int pbID) {
		this.service.deleteProduktbatch(pbID, new ProduktbatchCallback());
	}
	
	//Recepter
	@Override
	public void getRecept(int receptID) {
		this.service.getRecept(receptID, new DefaultCallback());
	}
	
	@Override
	public void getReceptList() {
		this.service.getReceptList(new DefaultCallback());
	}

	@Override
	public void createRecept(ReceptDTO recept) {
		this.service.createRecept(recept, new DefaultCallback());
	}

	@Override
	public void updateRecept(ReceptDTO raavare) {
		this.service.updateRecept(raavare, new DefaultCallback());
	}

	@Override
	public void deleteRecept(int receptID) {
		this.service.deleteRecept(receptID, new DefaultCallback());
	}
	
	//Recept Komponent	
	@Override
	public void getReceptkompListByReceptID(int receptID) {
		this.service.getReceptkompListByReceptID(receptID, new ReceptKompCallback());
	}

	@Override
	public void createReceptkomp(ReceptkompDTO receptkomp) {
		this.service.createReceptkomp(receptkomp, new ReceptKompCallback());
	}

	@Override
	public void updateReceptkomp(ReceptkompDTO receptkomp) {
		this.service.updateReceptkomp(receptkomp, new ReceptKompCallback());
	}

	@Override
	public void deleteReceptkomp(int receptID, int raavareID) {
		this.service.deleteReceptkomp(receptID, raavareID, new ReceptKompCallback());
	}
	
	//Telnet Client
	public void getDataList(String command, int expectedReplies, List<String> params){
		this.service.getDataList(command,expectedReplies,params, new DefaultCallback());
	}
	
	public void getData(String command, List<String> params){
		this.service.getData(command, params ,new DefaultCallback());
	}
	
	/**
	 * Async Callback
	 */
	private class DefaultCallback implements AsyncCallback 
	{
		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Object result) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ViewProfile) {
				ViewProfile viewProfile = (ViewProfile) currentPanel;
				viewProfile.updateUser((OperatorDTO) result);
			}
			else if(currentPanel instanceof OperatoerList) {
				OperatoerList oplist = (OperatoerList) currentPanel;
				if(result instanceof String) 
					oplist.statusUpdate((String) result);
				else
					oplist.updateTable((List<OperatorDTO>) result);
			}
			else if(currentPanel instanceof Login) {
				Login login = (Login) currentPanel;
				login.CompareLogin((OperatorDTO) result);
			}
			else if (currentPanel instanceof RaavarePanel) {
				RaavarePanel raavarePanel = (RaavarePanel) currentPanel;
				
				if(result instanceof String) 
					raavarePanel.statusUpdate((String) result);
				else
					raavarePanel.updateTable((List<RaavareDTO>) result);
			}
			else if(currentPanel instanceof ReceptPanel) {
				ReceptPanel recept = (ReceptPanel) currentPanel;
				
				if(result instanceof String) 
					recept.statusUpdate((String) result);
				else
					recept.updateTable((List<ReceptDTO>) result);
			}
			else if (currentPanel instanceof WeightPage){
				WeightPage weightPage = (WeightPage) currentPanel;
				if(result instanceof String) 
					weightPage.displayData((String) result);
				else if(result instanceof OperatorDTO) 
					weightPage.setCurOpr((OperatorDTO) result);
				else if(result instanceof ProduktbatchDTO) 
					weightPage.setCurPB((ProduktbatchDTO) result);
				else if(result instanceof ReceptDTO) 
					weightPage.setCurRecept((ReceptDTO) result);
				else if(result instanceof RaavarebatchDTO) 
					weightPage.setCurRB((RaavarebatchDTO) result);
			}
		}
	}
	
	/**
	 * Async Callback for råvarebatch
	 */
	private class RaavarebatchCallback implements AsyncCallback 
	{

		@Override
		public void onFailure(Throwable caught) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof RaavarebatchPanel) {
				RaavarebatchPanel raavarebatchPanel = (RaavarebatchPanel) currentPanel;
				raavarebatchPanel.statusUpdate("Error on server: " + caught.getMessage());
			}
		}

		@Override
		public void onSuccess(Object result) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof RaavarebatchPanel) {
				RaavarebatchPanel raavarebatchPanel = (RaavarebatchPanel) currentPanel;
				
				if(result instanceof String) 
					raavarebatchPanel.statusUpdate((String) result);
				else if(result instanceof List<?>)
					raavarebatchPanel.updateTable((List<RaavarebatchDTO>) result);
				else
					raavarebatchPanel.statusUpdate("Received unknown message from server " + result.getClass().getSimpleName() + "{" + result.toString() + "}");
			}
		}
	}
	
	/**
	 * Async Callback for produktbatch
	 */
	private class ProduktbatchCallback implements AsyncCallback 
	{

		@Override
		public void onFailure(Throwable caught) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ProduktbatchPanel) {
				ProduktbatchPanel produktbatchPanel = (ProduktbatchPanel) currentPanel;
				produktbatchPanel.statusUpdate("Error on server: " + caught.getMessage());
			}
		}

		@Override
		public void onSuccess(Object result) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ProduktbatchPanel) {
				ProduktbatchPanel produktbatchPanel = (ProduktbatchPanel) currentPanel;
				
				if(result instanceof String) 
					produktbatchPanel.statusUpdate((String) result);
				else if(result instanceof List<?>)
					produktbatchPanel.updateTable((List<ProduktbatchDTO>) result);
				else
					produktbatchPanel.statusUpdate("Received unknown message from server " + result.getClass().getSimpleName() + "{" + result.toString() + "}");
			}
		}
	}
	
	/**
	 * Async Callback for produktbatch komponent
	 */
	private class ProduktbatchKompCallback implements AsyncCallback 
	{

		@Override
		public void onFailure(Throwable caught) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ProduktbatchkompPanel) {
				ProduktbatchkompPanel pbkompPanel = (ProduktbatchkompPanel) currentPanel;
				pbkompPanel.statusUpdate("Error on server: " + caught.getMessage());
			}
		}

		@Override
		public void onSuccess(Object result) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ProduktbatchkompPanel) {
				ProduktbatchkompPanel pbkompPanel = (ProduktbatchkompPanel) currentPanel;
				
				if(result instanceof String) 
					pbkompPanel.statusUpdate((String) result);
				else if(result instanceof List<?>)
					pbkompPanel.updateTable((List<ProduktbatchkompDTO>) result);
				else
					pbkompPanel.statusUpdate("Received unknown message from server " + result.getClass().getSimpleName() + "{" + result.toString() + "}");
			}
		}
	}
	
	/**
	 * Async Callback for recept komponent
	 */
	private class ReceptKompCallback implements AsyncCallback 
	{
		@Override
		public void onFailure(Throwable caught) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ReceptkompPanel) {
				ReceptkompPanel receptkompPanel = (ReceptkompPanel) currentPanel;
				receptkompPanel.statusUpdate("Error on server: " + caught.getMessage());
			}
		}

		@Override
		public void onSuccess(Object result) {
			Object currentPanel = mainGui.getCurrentPanel();
			
			if(currentPanel instanceof ReceptkompPanel) {
				ReceptkompPanel receptkompPanel = (ReceptkompPanel) currentPanel;
				
				if(result instanceof String) 
					receptkompPanel.statusUpdate((String) result);
				else if(result instanceof List<?>)
					receptkompPanel.updateTable((List<ReceptkompDTO>) result);
				else
					receptkompPanel.statusUpdate("Received unknown message from server " + result.getClass().getSimpleName() + "{" + result.toString() + "}");
			}
		}
	}
}