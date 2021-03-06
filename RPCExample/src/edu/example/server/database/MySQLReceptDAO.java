package edu.example.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.example.client.exceptions.DALException;
import edu.example.client.models.ReceptDTO;
import edu.example.server.database.connector.Connector;

public class MySQLReceptDAO implements ReceptDAO 
{
	@Override
	public ReceptDTO getRecept(int receptID) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptID);
	    try {
	    	if (!rs.first()) 
	    		throw new DALException("recepten " + receptID + " findes ikke");
	    	return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM recept");
		try {
			while (rs.next()) {
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public void createRecept(ReceptDTO recept) throws DALException {		
			Connector.doUpdate("INSERT INTO recept(recept_id, recept_navn) VALUES " +
				"(" + recept.getReceptID() + ", '" + recept.getReceptNavn() + "')"
			);
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate("UPDATE recept SET  recept_id = '" + recept.getReceptID() + "', recept_navn =  '"
				+ recept.getReceptNavn() + "' WHERE recept_id = " + recept.getReceptID());
	}
@Override
	public void deleteRecept(int receptID) throws DALException {
		Connector.doUpdate("CALL `delete_recept`(?)", 
				receptID);
	}
}
