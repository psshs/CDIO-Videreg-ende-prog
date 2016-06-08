package edu.example.client.models;

import java.io.Serializable;
import java.util.Objects;

import edu.example.client.exceptions.DALException;

/**
 * Raavare Data Objekt
 * 
 * @author mn/sh/tb
 * @version 1.2
 */

public final class RaavareDTO implements Serializable
{
	private static final long serialVersionUID = -5377060714932261571L;
	private int raavareID; 		//i omraadet 1-99999999 vaelges af brugerne
    private String raavareNavn; //min. 2 max. 20 karakterer
    private String leverandoer; //min. 2 max. 20 karakterer
	
    public RaavareDTO() {
    	
    }
    
    public RaavareDTO(int raavareId, String raavareNavn, String leverandoer) {
		this.raavareID = raavareId;
		this.raavareNavn = raavareNavn;
		this.leverandoer = leverandoer;
	}
	
    public int getRaavareID() { 
    	return raavareID; 
    }
    
    public void setRaavareId(int raavareId) throws DALException { 
    	if(raavareId < 1 || raavareId > 99999999)
    		throw new DALException("R�vare ID skal v�re mellem 1 og 99999999, den var " + raavareId);
    	else
    		this.raavareID = raavareId; 
    }
    
    public String getRaavareNavn() { 
    	return raavareNavn; 
    }
    
    public void setRaavareNavn(String raavareNavn) throws DALException {
    	int strLength = raavareNavn.length();
    	
    	if(strLength < 2 || strLength > 20)
    		throw new DALException("R�vare navnet skal v�re mellem 1 og 99999999 karakterer, den var " + strLength);
    	else
    		this.raavareNavn = raavareNavn; 
    }
    
    public String getLeverandoer() { 
    	return leverandoer; 
    }
    
    public void setLeverandoer(String leverandoer) throws DALException {
    	int strLength = leverandoer.length();
    	
    	if(strLength < 2 || strLength > 20)
    		throw new DALException("Leverand�rens navn skal v�re mellem 1 og 99999999 karakterer, den var " + strLength);
    	else
    		this.leverandoer = leverandoer; 
    }
    
    @Override
    public String toString() { 
		return raavareID + "\t" + raavareNavn +"\t" + leverandoer; 
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) 
            return false;
        final RaavareDTO other = (RaavareDTO) obj;
        
        return this.raavareID == other.raavareID &&
               Objects.equals(this.raavareNavn, other.raavareNavn) &&
               Objects.equals(this.leverandoer, other.leverandoer);
		
//		// TODO Auto-generated method stub
//		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
    
    
}