package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc {
	
	public static boolean checkDriver(String driver) {
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance();
			return true;
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			return false;
		}
	}
	
	public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		try {
			conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
					connectionProps);
		} catch (SQLException e) {
			System.out.println("Bі№d poі№czenia z baz№ danych! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(2);
		}
		System.out.println("Poі№czenie z baz№ danych: ... OK");
		return conn;
	}
	
	public static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Bі№d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(3);
		}
		return null;
	}
	
	public static void closeConnection(Connection connection, Statement s) {
		System.out.print("\nZamykanie polaczenia z baz№:");
		try {
			s.close();
			connection.close();
		} catch (SQLException e) {
			System.out
					.println("Bl№d przy zamykaniu pol№czenia z baz№! " + e.getMessage() + ": " + e.getErrorCode());;
			System.exit(4);
		}
		System.out.print(" zamkniкcie OK");
	}
	
	public static int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return -1;
	}
	
	public void chooseDatabase(Statement st) {
		if (executeUpdate(st, "USE ankieta;") == 0)
			System.out.println("Baza wybrana");
		else {
			System.out.println("Baza nie istnieje! Tworzymy bazк: ");
			if (executeUpdate(st, "create Database ankieta;") == 1)
				System.out.println("Baza utworzona");
			else
				System.out.println("Baza nieutworzona!");
			if (executeUpdate(st, "USE ankieta;") == 0)
				System.out.println("Baza wybrana");
			else
				System.out.println("Baza niewybrana!");
		}
	}
	
	public void chooseTableUsers(Statement st) {
		if (executeUpdate(st,
				"CREATE TABLE users ( niu VARCHAR(50) NOT NULL, imie VARCHAR(50) NOT NULL, nazwisko VARCHAR(50) NOT NULL, PRIMARY KEY (niu) );") == 0)
			System.out.println("Tabela utworzona");
		else
			System.out.println("Tabela nie utworzona!");
	}
	
	public void chooseTableQuestions(Statement st) {
		if (executeUpdate(st,
				"CREATE TABLE questions ( id INT NOT NULL AUTO_INCREMENT, question VARCHAR(50) NOT NULL, answer1 VARCHAR(50) NOT NULL,"
				+ " answer2 VARCHAR(50) NOT NULL, answer3 VARCHAR(50) NOT NULL, answer4 VARCHAR(50) NOT NULL, PRIMARY KEY (id) );") == 0) {
			System.out.println("Tabela utworzona");
			
			String question = "Ile masz lat?";
			String answer1 = "A)10-14";
			String answer2 = "B)15-19";
			String answer3 = "C)20-24";
			String answer4 = "D)25-29";
			insertIntoQuestions(st, 1,  question, answer1, answer2, answer3, answer4);
			
			question = "Skad jestes?";
			answer1 = "A)Polska";
			answer2 = "B)Bialorus";
			answer3 = "C)Uganda";
			answer4 = "D)Mars";
			insertIntoQuestions(st, 2, question, answer1, answer2, answer3, answer4);
			
			question = "Kim jestes?";
			answer1 = "A)Student";
			answer2 = "B)Inzynier";
			answer3 = "C)Spok";
			answer4 = "D)Piekarz";
			insertIntoQuestions(st, 3, question, answer1, answer2, answer3, answer4);
			
			question = "Jak oceniasz ankiete?";
			answer1 = "A)Super";
			answer2 = "B)Srednio";
			answer3 = "C)Slabo";
		    answer4 = "D)Sprzedam opla";
			insertIntoQuestions(st, 4, question, answer1, answer2, answer3, answer4);
		}
		else
			System.out.println("Tabela nie utworzona!");
	}
	
	public void chooseTableAnswers(Statement st) {
		if (executeUpdate(st,
				"CREATE TABLE answers ( id INT NOT NULL AUTO_INCREMENT, user_niu VARCHAR(50) NOT NULL, answer1 VARCHAR(50) NOT NULL,"
				+ " answer2 VARCHAR(50) NOT NULL, answer3 VARCHAR(50) NOT NULL, answer4 VARCHAR(50) NOT NULL, "
				+ " PRIMARY KEY (id), FOREIGN KEY (user_niu) REFERENCES ankieta.users(niu) );") == 0)
			System.out.println("Tabela utworzona");
		else
			System.out.println("Tabela nie utworzona!");
	}
	
	private void insertIntoQuestions( Statement st, int id, String question, String answer1, String answer2, String answer3, String answer4) {
		String str = "INSERT INTO questions VALUES ('" + id + "','" + question + "','" + answer1 + "','"
						+ answer2 + "','" + answer3 + "','"+ answer4 + "');";
		executeUpdate(st, str);
	}

}
