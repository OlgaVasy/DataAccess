package com.example.DataAccess.daoClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DataAccess.POGO.Person;
import com.example.DataAccess.connection.ConnectionFactory;

public class PersonDao {

	boolean personInDb = true;

	public Person getById(Long id) throws ClassNotFoundException {
		Person person = new Person();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM person WHERE person_id=" + id);

			while (result.next()) {
				person.setId(result.getLong("person_id"));
				person.setFirstName(result.getString("first_name"));
				person.setLastName(result.getString("last_name"));
				person.setAge(result.getInt("age"));
			}
		} catch (SQLException ex) {
			ex.getStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException ex) {
					ex.getStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.getStackTrace();
				}
			}
		}
		if (person != null)
			personInDb = true;
		else
			personInDb = false;

		return person;

	}

	public void savePerson(Person person) throws ClassNotFoundException {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			getById(person.getId());

			if (person.getId() == null)
				statement.executeQuery("INSERT INTO PERSON" + "(first_name,last_name,age)" + "VALUES" + "("
						+ person.getFirstName() + "," + person.getLastName() + "," + person.getAge() + ")");
			else if (person.getId() != null && personInDb == true)
				statement.executeQuery("UPDATE PERSON" + "SET first_name = " + person.getFirstName()
						+ "SET last_name = " + person.getLastName() + "SET age = " + person.getAge()
						+ "WHERE person_id=" + person.getId());
		} catch (SQLException ex) {
			ex.getStackTrace();
		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.getStackTrace();
				}
			}
		}

	}
}