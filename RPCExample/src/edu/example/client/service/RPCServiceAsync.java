package edu.example.client.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.example.client.models.OperatorDTO;
import edu.example.client.models.ProduktbatchDTO;
import edu.example.client.models.ProduktbatchkompDTO;
import edu.example.client.models.RaavareDTO;
import edu.example.client.models.RaavarebatchDTO;
import edu.example.client.models.ReceptDTO;
import edu.example.client.models.ReceptkompDTO;

public interface RPCServiceAsync 
{
	//operators
	void getOpList(AsyncCallback callback);
	
	void getOperator(int oprID, AsyncCallback callback);
	
	void createOperator(OperatorDTO opr, AsyncCallback callback);
	
	void updateOperator(OperatorDTO opr, AsyncCallback callback);
	
	void getPassword(int oprID, AsyncCallback callback);
	
	void deleteOperator(int oprID,AsyncCallback callback);
	
	//Raavarer
	void getRaavarebatch(int rbID, AsyncCallback callback);
	
	void getRaavareList(AsyncCallback callback);
	
	void createRaavare(RaavareDTO raavare, AsyncCallback callback);
	
	void updateRaavare(RaavareDTO raavare, AsyncCallback callback);
	
	void deleteRaavare(int raavareID, AsyncCallback callback);
	
	//Råvarebatch
	void getRaavarebatchList(AsyncCallback callback);
	
	void createRaavarebatch(RaavarebatchDTO raavarebatch, AsyncCallback callback);
	
	void updateRaavarebatch(RaavarebatchDTO raavarebatch, AsyncCallback callback);
	
	void deleteRaavarebatch(int rbID, AsyncCallback callback);
	
	//Produktbatch komponent
	void getPbkompListByPbID(int pbID, AsyncCallback callback);
	
	void createPbkomp(ProduktbatchkompDTO pbkomp, AsyncCallback callback);
	
	void updatePbkomp(ProduktbatchkompDTO pbkomp, AsyncCallback callback);
	
	void deletePbkomp(int pbID, int rbID, AsyncCallback callback);
	
	//Produktbatch
	void getProduktbatch(int pbID, AsyncCallback callback); 
	
	void getProduktbatchList(AsyncCallback callback);
	
	void createProduktbatch(ProduktbatchDTO produktbatch, AsyncCallback callback);
	
	void updateProduktbatch(ProduktbatchDTO produktbatch, AsyncCallback callback);
	
	void deleteProduktbatch(int pbID, AsyncCallback callback);
	
	//Recept
	void getRecept(int receptID, AsyncCallback callback);
	
	void getReceptList(AsyncCallback callback);
	
	void createRecept(ReceptDTO recept, AsyncCallback callback);
	
	void updateRecept(ReceptDTO recept, AsyncCallback callback);
	
	void deleteRecept(int receptID, AsyncCallback callback);
	
	//Recept komponent
	void getReceptkompListByReceptID(int receptID, AsyncCallback callback);
	
	void createReceptkomp(ReceptkompDTO receptkomp, AsyncCallback callback);
	
	void updateReceptkomp(ReceptkompDTO receptkomp, AsyncCallback callback);
	
	void deleteReceptkomp(int receptID, int raavareID, AsyncCallback callback);
	
	//TelnetClient
	void getDataList(String command, int expectedReplies, List<String> params, AsyncCallback callback);
	
	void getData(String command, List<String> params, AsyncCallback callback);
	
}