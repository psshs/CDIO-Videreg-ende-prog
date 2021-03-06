/**
 * 
 */
package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptKompDAO;
import dto01917.ReceptKompDTO;

/**
 * @author Asger
 *
 * 28/04/2016
 */
public class MySQLReceptKompDAO implements ReceptKompDAO
{
	/**
	 * 
	 */
	public MySQLReceptKompDAO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#getReceptKomp(int, int)
	 */
	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id= "+receptId+" AND raavare_id="+raavareId);
		try
		{
			if(!rs.first()) throw new DALException("Recept komponent"+receptId+"Findes ikke");
			return new ReceptKompDTO (rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance"));
		}
		catch(SQLException e){throw new DALException(e);}
	}
		

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#getReceptKompList(int)
	 */
	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
			List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id="+receptId);
			try
			{
				while (rs.next()) 
				{
					list.add(new ReceptKompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance")));
				}
			}
			catch (SQLException e) { throw new DALException(e); }
			return list;
		}

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#getReceptKompList()
	 */
	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#createReceptKomp(dto01917.ReceptKompDTO)
	 */
	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
				Connector.doUpdate("INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES(?, ?, ?, ?)",
		                receptkomponent.getReceptId(), receptkomponent.getRaavareId(), receptkomponent.getNomNetto(), receptkomponent.getTolerance());
				
	}

	

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#updateReceptKomp(dto01917.ReceptKompDTO)
	 */
	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {

				 Connector.doUpdate("UPDATE receptkomponent SET nom_netto=?, tolerance=? WHERE recept_id=? AND raavare_id=?",
			                receptkomponent.getNomNetto(), receptkomponent.getTolerance(), receptkomponent.getReceptId(), receptkomponent.getRaavareId());
				
		
	}

	public void deleteReceptKomp(int receptId, int raavareId) throws DALException {		
		Connector.doUpdate(
			"DELETE FROM receptkomponent WHERE recept_id="+receptId+" AND raavare_id="+raavareId
		);
}
}
