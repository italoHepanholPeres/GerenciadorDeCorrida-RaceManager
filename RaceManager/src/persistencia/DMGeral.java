package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DMGeral
{
	protected static Connection connection;

	public static Connection getConnection()
	{	return connection;	}

	//opera��o criada para conectar com o BD
	public void conectaDataBase(String dataBase, String userName, String password)
	{	
		//string de conex�o JDBC, indica o BD ao qual deseja conectar	
		String url = "jdbc:mysql://localhost/"+dataBase+"?serverTimezone=UTC";
		
		try
		{	//carrega o driver especificado (driver do mysql)
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//ap�s o carregamento do driver, deve-se abrir uma conex�o com o BD
			//usando o m�todo est�tico getConnection da classe DriverManager
		   	connection = DriverManager.getConnection(url,userName,password);
			System.out.println("Conexao ao banco de dados feita com sucesso!");
		} 
		catch (ClassNotFoundException cnfex)
		{	//driver n�o encontrado
			System.err.println("Falha ao abrir o driver JDBC/ODBC");
			cnfex.printStackTrace();
			System.exit(1);
		}
		catch (SQLException sqlex)
		{	System.err.println("Imposs�vel conectar");
			sqlex.printStackTrace();
		}
	}

	//opera��o para desconectar do BD	
	public void shutDown()
	{	try
		{	connection.close();	}
		catch (SQLException sqlex)
		{	System.err.println("Imposs�vel desconectar");
		  	sqlex.printStackTrace();
		}
	}
	
	//opera��es abstratas para inclus�o,consulta,exclus�o e altera��o no BD
	//devem ser implementadas nas subclasses de DMGeral
	public abstract void incluir(Object obj); 
	public abstract Object consultar(Object obj);
}