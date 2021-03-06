package edu.example.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.example.client.exceptions.DALException;
import edu.example.client.models.OperatorDTO;
import edu.example.client.models.ProduktbatchDTO;
import edu.example.client.models.ProduktbatchkompDTO;
import edu.example.client.models.RaavareDTO;
import edu.example.client.models.RaavarebatchDTO;
import edu.example.client.models.ReceptDTO;
import edu.example.client.models.ReceptkompDTO;
import edu.example.client.service.RPCService;
import edu.example.server.database.MYSQLProduktbatchDAO;
import edu.example.server.database.MYSQLReceptkompDAO;
import edu.example.server.database.MySQLOperatoerDAO;
import edu.example.server.database.MySQLProduktbatchKompDAO;
import edu.example.server.database.MySQLRaavareDAO;
import edu.example.server.database.MySQLRaavarebatchDAO;
import edu.example.server.database.MySQLReceptDAO;
import edu.example.server.database.connector.Connector;

public class RPCServiceServerImpl extends RemoteServiceServlet implements RPCService
{
	private static final long serialVersionUID = 5550980922485907926L;
	
	//Operators
	private final MySQLOperatoerDAO opDAO = new MySQLOperatoerDAO();
	
	@Override
	public ArrayList<OperatorDTO> getOpList() {
		Connector con = null;
		ArrayList<OperatorDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<OperatorDTO>(opDAO.getOperatorList());
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public OperatorDTO getOperator(int oprID) {
		OperatorDTO result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			result = opDAO.getOperator(oprID);
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	@Override
	public boolean createOperator(OperatorDTO opr) {
		boolean result = false;
		Connector con = null;
		
		try {
			con = new Connector();
			opDAO.createOperator(opr);
			result = true;
		}
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		return result;
	}
	
	@Override
	public boolean updateOperator(OperatorDTO opr) {
		boolean result = false;
		Connector con = null;
		
		try {
			con = new Connector();
			opDAO.updateOperator(opr);
			result = true;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	@Override
	public String getPassword(int oprID) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			result = opDAO.getOperator(oprID).getPassword();
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		return result;
	}

	public void deleteOperator(int oprId) {
		Connector con = null;
		
		try {
			con = new Connector();
			opDAO.deleteOperator(oprId);
			
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		
	}
	
	
	//Raavarer
	private final MySQLRaavareDAO raDAO = new MySQLRaavareDAO();
	
	@Override
	public ArrayList<RaavareDTO> getRaavareList() {
		Connector con = null;
		ArrayList<RaavareDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<RaavareDTO>(raDAO.getRaavareList());
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String createRaavare(RaavareDTO raavare) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			raDAO.createRaavare(raavare);
			result = "Råvare " + raavare.toString() + " blev oprettet";
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "Råvare " + raavare.toString() + " kunne ikke oprettes: " + e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String updateRaavare(RaavareDTO raavare) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			raDAO.updateRaavare(raavare);
			result = "Råvare " + raavare.getRaavareID() + " blev opateret";
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "Råvare " + raavare.getRaavareID() + " kunne ikke opdateres: " + e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String deleteRaavare(int raavareID) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			raDAO.deleteRaavare(raavareID);
			result = "Råvaren " + raavareID + " blev slettet";
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
			result = "Råvaren " + raavareID + " kunne ikke slettes: " + e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	//Råvarebatch
	private final MySQLRaavarebatchDAO rbDAO = new MySQLRaavarebatchDAO();

	@Override
	public RaavarebatchDTO getRaavarebatch(int rbID) {
		Connector con = null;
		RaavarebatchDTO result = null;
		
		try {
			con = new Connector();
			result = rbDAO.getRaavarebatch(rbID);
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	@Override
	public ArrayList<RaavarebatchDTO> getRaavarebatchList() {
		Connector con = null;
		ArrayList<RaavarebatchDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<RaavarebatchDTO>(rbDAO.getRaavarebatchlist());
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String createRaavarebatch(RaavarebatchDTO raavarebatch) {
		String result = "Råvarebatchen " + raavarebatch.getRbID() + " kunne ikke oprettes: ";
		Connector con = null;
		
		try {
			con = new Connector();

			int reply = rbDAO.createRaavarebatch(raavarebatch);
			if(reply > 0)
				result = "Råvarebatchen " + raavarebatch.getRbID() + " blev oprettet";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String updateRaavarebatch(RaavarebatchDTO raavarebatch) {
		String result = "Råvarebatchen " + raavarebatch.getRbID() + " kunne ikke opdateres: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = rbDAO.updateRaavarebatch(raavarebatch);
			if(reply > 0)
				result = "Råvarebatchen " + raavarebatch.getRbID() + " blev opdateret";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String deleteRaavarebatch(int rbID) {
		String result = "Råvarebatchen " + rbID + " kunne ikke slettes: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = rbDAO.deleteRaavarebatch(rbID);
			if(reply > 0)
				result = "Råvarebatchen " + rbID + " blev slettet";
			else
				result += "reply = " + reply;
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	//Produktbatch komponent
	private final MySQLProduktbatchKompDAO pbkompDAO = new MySQLProduktbatchKompDAO();
	
	@Override
	public ArrayList<ProduktbatchkompDTO> getPbkompListByPbID(int pbID) {
		Connector con = null;
		ArrayList<ProduktbatchkompDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<ProduktbatchkompDTO>(pbkompDAO.getProduktbatchKompListByRBID(pbID));
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try { con.closeConnection(); } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String createPbkomp(ProduktbatchkompDTO pbkomp) {
		String result = "Produktbatch komponentet " + pbkomp.getPbID() + " + " + pbkomp.getRbID() + " kunne ikke oprettes: ";
		Connector con = null;
		
		try {
			con = new Connector();

			int reply = pbkompDAO.createProduktbatchKomp(pbkomp);
			if(reply > 0)
				result = "Produktbatch komponentet " + pbkomp.getPbID() + " + " + pbkomp.getRbID() + " blev oprettet";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String updatePbkomp(ProduktbatchkompDTO pbkomp) {
		String result = "Produktbatch komponentet " + pbkomp.getPbID() + " + " + pbkomp.getRbID() + " kunne ikke opdateres: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = pbkompDAO.updateProduktbatchKomp(pbkomp);
			if(reply > 0)
				result = "Produktbatch komponentet " + pbkomp.getPbID() + " + " + pbkomp.getRbID() + " blev opdateret";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String deletePbkomp(int pbID, int rbID) {
		String result = "Produktbatch komponentet " + pbID + " + " + rbID + " kunne ikke slettes: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = pbkompDAO.deleteProduktbatchKomp(pbID, rbID);
			if(reply > 0)
				result = "Produktbatch komponentet " + pbID + " + " + rbID + " blev slettet";
			else
				result += "reply = " + reply;
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	//Produktbatch
	private final MYSQLProduktbatchDAO pbDAO = new MYSQLProduktbatchDAO();

	@Override
	public ProduktbatchDTO getProduktbatch(int pbID) {
		Connector con = null;
		ProduktbatchDTO result = null;
		
		try {
			con = new Connector();
			result = pbDAO.getProduktbatch(pbID);
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public ArrayList<ProduktbatchDTO> getProduktbatchList() {
		Connector con = null;
		ArrayList<ProduktbatchDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<ProduktbatchDTO>(pbDAO.getProduktbatchList());
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String createProduktbatch(ProduktbatchDTO produktbatch) {
		String result = "Produktbatchen " + produktbatch.getPbID() + " kunne ikke oprettes: ";
		Connector con = null;
		
		try {
			con = new Connector();

			int reply = pbDAO.createProduktbatch(produktbatch);
			if(reply > 0)
				result = "Produktbatchen " + produktbatch.getPbID() + " blev oprettet";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String updateProduktbatch(ProduktbatchDTO produktbatch) {
		String result = "Produktbatchen " + produktbatch.getPbID() + " kunne ikke opdateres: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = pbDAO.updateProduktbatch(produktbatch);
			if(reply > 0)
				result = "Produktbatchen " + produktbatch.getPbID() + " blev opdateret";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String deleteProduktbatch(int pbID) {
		String result = "Produktbatchen " + pbID + " kunne ikke slettes: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = pbDAO.deleteProduktbatch(pbID);
			if(reply > 0)
				result = "Produktbatchen " + pbID + " blev slettet";
			else
				result += "reply = " + reply;
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	
	//Recepter
	private final MySQLReceptDAO receptDAO = new MySQLReceptDAO();

	@Override
	public ReceptDTO getRecept(int receptID) {
		Connector con = null;
		ReceptDTO result = null;
		
		try {
			con = new Connector();
			result = receptDAO.getRecept(receptID);
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	@Override
	public ArrayList<ReceptDTO> getReceptList() {
		Connector con = null;
		ArrayList<ReceptDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<ReceptDTO>(receptDAO.getReceptList());
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String createRecept(ReceptDTO recept) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			receptDAO.createRecept(recept);
			result = "Recept " + recept.toString() + " blev oprettet";
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "Recept " + recept.toString() + " kunne ikke oprettes: " + e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String updateRecept(ReceptDTO recept) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			receptDAO.updateRecept(recept);
			result = "Recepten " + recept.getReceptID() + " blev opateret";
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "Recepten " + recept.getReceptID() + " kunne ikke opdateres: " + e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String deleteRecept(int receptID) {
		String result = null;
		Connector con = null;
		
		try {
			con = new Connector();
			receptDAO.deleteRecept(receptID);
			result = "Recepten " + receptID + " blev slettet";
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
			result = "Recepten " + receptID + " kunne ikke slettes: " + e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}
	
	//Recept komponent
	private final MYSQLReceptkompDAO receptkompDAO = new MYSQLReceptkompDAO();

	@Override
	public ArrayList<ReceptkompDTO> getReceptkompListByReceptID(int receptID) {
		Connector con = null;
		ArrayList<ReceptkompDTO> result = null;
		
		try {
			con = new Connector();
			result = new ArrayList<ReceptkompDTO>(receptkompDAO.getReceptkompListByReceptID(receptID));
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
		}
        finally {
            if(con != null)
                try { con.closeConnection(); } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String createReceptkomp(ReceptkompDTO receptkomp) {
		String result = "Recept komponentet " + receptkomp.getReceptID() + " + " + receptkomp.getRaavareID() + " kunne ikke oprettes: ";
		Connector con = null;
		
		try {
			con = new Connector();

			int reply = receptkompDAO.createReceptkomp(receptkomp);
			if(reply > 0)
				result = "Recept komponentet " + receptkomp.getReceptID() + " + " + receptkomp.getRaavareID() + " blev oprettet";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String updateReceptkomp(ReceptkompDTO receptkomp) {
		String result = "Recept komponentet " + receptkomp.getReceptID() + " + " + receptkomp.getRaavareID() + " kunne ikke opdateres: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = receptkompDAO.updateReceptkomp(receptkomp);
			if(reply > 0)
				result = "Recept komponentet " + receptkomp.getReceptID() + " + " + receptkomp.getRaavareID() + " blev opdateret";
			else
				result += "reply = " + reply;
		} 
		catch (DALException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	@Override
	public String deleteReceptkomp(int receptID, int raavareID) {
		String result = "Recept komponentet " + receptID + " + " + raavareID + " kunne ikke slettes: ";
		Connector con = null;
		
		try {
			con = new Connector();
			int reply = receptkompDAO.deleteReceptkomp(receptID, raavareID);
			if(reply > 0)
				result = "Recept komponentet " + receptID + " + " + raavareID + " blev slettet";
			else
				result += "reply = " + reply;
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | DALException e) {
			e.printStackTrace();
			result += e.getMessage();
		}
        finally {
            if(con != null)
                try {
                    con.closeConnection();
                } catch (SQLException e) { }
        }
		
		return result;
	}

	/* (non-Javadoc)
	 * @see edu.example.client.service.RPCService#getDataList(java.lang.String, int, java.util.List)
	 */
	@Override
	public ArrayList<String> getDataList(String command, int expectedReplies, List<String> params) {
		TelnetClient con=null;
		ArrayList<String> result = null;
		
		try {
			con=new TelnetClient("localhost",8000);
			result=con.getData(command, expectedReplies, params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see edu.example.client.service.RPCService#getData(java.lang.String, java.util.List)
	 */
	@Override
	public String getData(String command, List<String> params) {
		TelnetClient con=null;
		String result = null;
		
		try {
			con=new TelnetClient("localhost",8000);
			con.connect();
			result=con.getData(command, params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}