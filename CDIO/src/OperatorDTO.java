import java.util.List;
import java.util.Scanner;

public class OperatorDTO {
	int oprId;
	String oprNavn;
	String ini;
	int cpr;
	String password;
public OperatorDTO(int opr)
{
oprId=opr;	
}
public void setoprId(int oprId)
{
	this.oprId=oprId;
	
}
public int getoprId()
{
	return oprId;
	
}
public void setName(String name)
{
	this.oprNavn=name;
	
}
public void setCpr(int cpr)
{
	this.cpr=cpr;
	
}

public void setPassword(String password)
{
	this.password=password;
	
}
public String getName()
{
	return this.oprNavn;
	
}
public int getCpr()
{
	return this.cpr;
	
}

public String getPassword()
{
	return this.password;
	
}



}