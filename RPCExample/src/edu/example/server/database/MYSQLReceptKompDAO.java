/**
 * 
 */
package edu.example.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.example.client.exceptions.DALException;
import edu.example.client.models.ReceptkompDTO;
import edu.example.server.database.connector.Connector;

/**
 * @author Asger
 *
 * 28/04/2016
 */
public class MYSQLReceptKompDAO implements ReceptKompDAO
{
	/**
	 * 
	 */
	public MYSQLReceptKompDAO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#getReceptKomp(int, int)
	 */
	@Override
	public ReceptkompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id= "+receptId+" AND raavare_id="+raavareId);
		try
		{
			if(!rs.first()) throw new DALException("Recept komponent"+receptId+"Findes ikke");
			return new ReceptkompDTO (rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance"));
		}
		catch(SQLException e){throw new DALException(e);}
	}
		

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#getReceptKompList(int)
	 */
	@Override
	public List<ReceptkompDTO> getReceptKompList(int receptId) throws DALException {
			List<ReceptkompDTO> list = new ArrayList<ReceptkompDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE recept_id="+receptId);
			try
			{
				while (rs.next()) 
				{
					list.add(new ReceptkompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance")));
				}
			}
			catch (SQLException e) { throw new DALException(e); }
			return list;
		}

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#getReceptKompList()
	 */
	@Override
	public List<ReceptkompDTO> getReceptKompList() throws DALException {
		List<ReceptkompDTO> list = new ArrayList<ReceptkompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptkompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#createReceptKomp(dto01917.ReceptKompDTO)
	 */
	@Override
	public void createReceptKomp(ReceptkompDTO receptkomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(recept_id,raavare_id,nom_netto,tolerance) VALUES" +"("+receptkomponent.getReceptID()+", '"+receptkomponent.getRaavareID()+"', '"+receptkomponent.getNomNetto()+"', '"+receptkomponent.getTolerance()+"'"
				);
		}

	

	/* (non-Javadoc)
	 * @see daointerfaces01917.ReceptKompDAO#updateReceptKomp(dto01917.ReceptKompDTO)
	 */
	@Override
	public void updateReceptKomp(ReceptkompDTO receptkomponent) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatch SET  recept_id = '" + receptkomponent.getReceptID() + "', raavare_id =  '" + receptkomponent.getRaavareID()+ 
				"', nom_netto = '" + receptkomponent.getNomNetto() + "', tolerance='"+receptkomponent.getTolerance()+"' WHERE recept_id = " +
				receptkomponent.getReceptID()
		);
	}

}
