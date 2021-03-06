package edu.example.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import edu.example.client.service.RPCServiceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RPCExample implements EntryPoint 
{
	/**hej
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RPCServiceClientImpl clientImpl = new RPCServiceClientImpl(GWT.getModuleBaseURL() + "exampleservice");
		RootPanel.get().add(clientImpl.getMainGUI());
	}
}