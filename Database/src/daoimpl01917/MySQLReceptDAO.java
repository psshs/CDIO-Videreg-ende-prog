package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ProduktBatchDTO;
import dto01917.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO 
{
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
	    try {
	    	if (!rs.first()) throw new DALException("recepten " + receptId + " findes ikke");
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
	
	/*@Override
	public void createRecept(ReceptDTO recept) throws DALException {		
			Connector.doUpdate(
				"INSERT INTO recept(recept_id, recept_navn) VALUES " +
				"(" + recept.getReceptId() + ", '" + recept.getReceptNavn() + "',)"
			);
	}
	*/
	
	 @Override
	    public void createRecept(ReceptDTO recept) throws DALException {
	        Connector.doUpdate("INSERT INTO recept(recept_id, recept_navn) VALUES(?, ?)",
	                recept.getReceptId(),recept.getReceptNavn());

	    }

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate("UPDATE recept SET  recept_id = '" + recept.getReceptId() + "', recept_navn =  '"
				+ recept.getReceptNavn() + "' WHERE recept_id = " + recept.getReceptId());
	}
	public void deleteRecept(int ReceptId) throws DALException {		
		Connector.doUpdate(
			"DELETE FROM recept where recept_id="+ReceptId
		);
}

}
