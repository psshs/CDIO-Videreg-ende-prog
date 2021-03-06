package edu.example.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.example.server.database.connector.Connector;
import edu.example.client.exceptions.DALException;
import edu.example.client.exceptions.OpIdException;
import edu.example.client.exceptions.OpNameException;
import edu.example.client.exceptions.OpPasswordException;
import edu.example.client.models.OperatorDTO;

public class MySQLOperatoerDAO implements OperatoerDAO 
{
	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("CALL `get_operator`(?)", oprId);
	    try {			
	    	if (!rs.first()) 
	    		throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	
	    	return new OperatorDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("rank"), rs.getString("hash"));
	    }
	    catch (SQLException | OpNameException | OpIdException | OpPasswordException e) {
	    	throw new DALException(e);
	    }
	}
	
	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		Connector.doUpdate ("CALL `create_operator`(?, ?, ?, ?, ?, ?, ?)",
				opr.getOprID(), opr.getName(), opr.getIni(), opr.getCpr(), opr.getPassword(), opr.getRank(), opr.getHash());
	}
	
	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {		
		Connector.doUpdate("CALL `update_operator`(?, ?, ?, ?, ?, ?, ?)",
				opr.getOprID(), opr.getName(), opr.getIni(), opr.getCpr(), opr.getPassword(), opr.getRank(), opr.getHash());
	}
	
	@Override
	public List<OperatorDTO> getOperatorList() throws DALException {
		List<OperatorDTO> list;
		try	{
			list = new ArrayList<OperatorDTO>();
			ResultSet rs = Connector.doQuery("CALL get_operators()");
			while (rs.next()) {
				list.add(new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("rank"), rs.getString("hash")));
			}
		}
		catch (SQLException | OpNameException | OpIdException | OpPasswordException e) { 
			throw new DALException(e); 
		}
		return list;
	}
	
	@Override
	public void deleteOperator(int oprId) throws DALException {
		Connector.doUpdate ("CALL `delete_operator`(?)",
				oprId);
	}
}