package edu.example.client.models;

import java.io.Serializable;
import java.util.ArrayList;

import edu.example.client.exceptions.DALException;

public class ProduktbatchDTO implements Serializable
{
	private static final long serialVersionUID = 8533525616049826629L;
	
	public static final int STATUS_NOT_STARTET = 0;
	public static final int STATUS_STARTET = 1;
	public static final int STATUS_COMPLETE = 2;	
	public static final String STATUS_NOT_STARTET_STR = "Ikke startet";
	public static final String STATUS_STARTET_STR = "Under produktion";
	public static final String STATUS_COMPLETE_STR = "Afsluttet";	
	
	private int pbID;		// i omraadet 1-99999999
	private int status;		// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	private int receptID;
	private ArrayList<ProduktbatchkompDTO> produktbatchKompenenter = new ArrayList<>();
	
	public ProduktbatchDTO() {
		
	}
	
	public ProduktbatchDTO(int pbID, int status, int receptID) {
		this.pbID = pbID;
		this.status = status;
		this.receptID = receptID;
	}
	
	public int getPbID() { 
		return pbID;
	}
	
	public void setPbID(int pbID) throws DALException { 		
		if(pbID < 1 || pbID > 99999999)
    		throw new DALException("produktbatch ID skal v�re mellem 1 og 99999999, den var " + pbID);
    	else
    		this.pbID = pbID; 
	}
	
	public int getStatus() { 
		return status; 
	}
	
	public void setStatus(int status) throws DALException { 
		if(status < 0 || status > 2)
    		throw new DALException("Status skal v�re mellem 0 og 2, den var " + status);
    	else
    		this.status = status;
	}
	
	public int getReceptID() { 
		return receptID;
	}
	
	public void setReceptID(int receptID) throws DALException { 
		if(receptID < 1 || receptID > 99999999)
    		throw new DALException("Recept ID skal v�re mellem 1 og 99999999, den var " + receptID);
    	else
    		this.receptID = receptID;
	}
	
	public ArrayList<ProduktbatchkompDTO> getProduktbatchKompenenter () {
		return produktbatchKompenenter;
	}
	
	public void setProduktbatchKompenenter (ArrayList<ProduktbatchkompDTO> produktbatchKompenenter) {
		this.produktbatchKompenenter = produktbatchKompenenter;
	}
	
	public void addProduktbatchKompenenter (ProduktbatchkompDTO produktbatchKomp) {
		produktbatchKompenenter.add(produktbatchKomp);
	}
	
	public void clearProduktbatchKompenenter () {
		produktbatchKompenenter.clear();
	}
	
	public static String statusToString(int status) {
		switch (status) {
			case STATUS_NOT_STARTET:
				return STATUS_NOT_STARTET_STR;
			case STATUS_STARTET:
				return STATUS_STARTET_STR;
			case STATUS_COMPLETE:
				return STATUS_COMPLETE_STR;
			default:
				return "Ikke defineret";
		}
	}
	
	public static int statusToInt(String status) {
		switch (status) {
			case STATUS_NOT_STARTET_STR:
				return STATUS_NOT_STARTET;
			case STATUS_STARTET_STR:
				return STATUS_STARTET;
			case STATUS_COMPLETE_STR:
				return STATUS_COMPLETE;
			default:
				return -1;
		}
	}

	@Override
	public String toString() {
		return "{ID=" + pbID + ", ReceptID=" + receptID + ", Status=" + status + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) 
            return false;
        final ProduktbatchDTO other = (ProduktbatchDTO) obj;
        
        return this.pbID == other.pbID &&
        		this.receptID == other.receptID &&
        		this.status == other.status;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}