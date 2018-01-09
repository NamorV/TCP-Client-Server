package main;

import java.sql.Connection;
import java.sql.Statement;

import jdbc.Jdbc;

public class Kolod {

	public static void main(String[] args) {
		Jdbc jdbc = new Jdbc();
		
		if (jdbc.checkDriver("com.mysql.jdbc.Driver"))
			System.out.println(" ... OK");
		else
			System.exit(1);
		
		Connection con = jdbc.getConnection("jdbc:mysql://", "localhost", 3306, "root", "root");
		Statement st = jdbc.createStatement(con);
		
		jdbc.chooseDatabase(st);
		jdbc.chooseTableUsers(st);
		jdbc.chooseTableQuestions(st);
		jdbc.chooseTableAnswers(st);
		
		jdbc.closeConnection(con, st);
	}

}
