
public class Main 
{
    public static void main(String[] args) throws DALException {
       
    	OperatorDAO ops =new OperatorDAO();
    	OperatorDTO op=new OperatorDTO(11);
    	ops.createOperatoer(op);
    	
        IView view = new View(new Functionality());
        view.dialog();
    	
    	
    	System.out.println(ops.getOperator(11)); 
    }
}	