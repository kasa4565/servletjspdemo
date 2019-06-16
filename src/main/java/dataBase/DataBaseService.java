package dataBase;

import domain.Person;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseService {

    private String baseName = "personDB";

    public void createPersonTable()
    {
        Connection connection = connectWithDataBase();
        Statement stat = null;
        try {
            stat = connection.createStatement();

            String table = "CREATE TABLE Person"
                    + " (id INTEGER PRIMARY KEY NOT NULL,"
                    + " firstName CHAR(50) NOT NULL, "
                    + " lastName CHAR(50) NOT NULL, "
                    + " gender CHAR(50) NOT NULL, "
                    + " birthday CHAR(50) NOT NULL, "
                    + " email CHAR(50) NOT NULL, "
                    + " age INTEGER NOT NULL);";

            stat.executeUpdate(table);
            stat.close();
            connection.close();
            System.out.println("Table Person created.");
        } catch (SQLException e) {
            System.out.println("Creating table Person failed... \n" + e.getMessage());
        }
    }

    private Connection connectWithDataBase()
    {
        Connection connection = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + baseName + ".db");
            System.out.println("Connected with " + baseName + " data base.");
        }
        catch (Exception e)
        {
            System.err.println("Connection failed... \n" + e.getMessage());
            return null;
        }
        return connection;
    }

    private ArrayList<Person> getPersonsByQuery(String query)
    {
        Statement stat = null;
        ArrayList<Person> persons = new ArrayList<Person>();
        Connection connection = connectWithDataBase();
        try {
            stat = connection.createStatement();
            ResultSet result = stat.executeQuery(query);
            ArrayList<String> labels = Person.GetLabels();

            while(result.next())
            {
                Person person = new Person();
                for (String label: labels) {
                    person.SetParameter(label, result.getString(label));
                }
                persons.add(person);
            }

            result.close();
            stat.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Getting persons failed... \n" + e.getMessage());
        }

        return persons;
    }

    public void addPerson(Person person)
    {
        Connection connection = connectWithDataBase();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            String query = "INSERT INTO Person (firstName, lastName, gender, birthday, email, age) "
                    + "VALUES ("
                    + "'" + person.getFirstName() + "',"
                    + "'" + person.getLastName() + "',"
                    + "'" + person.getGender() + "',"
                    + "'" + person.getBirthday() + "',"
                    + "'" + person.getEmail() + "',"
                    + "'" + person.getAge() + "');";

            stat.executeUpdate(query);
            stat.close();
            connection.close();
            System.out.println("New Person added.");
        } catch (Exception e) {
            System.out.println("Adding new Person failed... \n" + e.getMessage());
        }
    }

    public void updatePerson(Person person)
    {
        Connection connection = connectWithDataBase();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            String query = "UPDATE Person SET " +
                    "firstName = '" + person.getFirstName() + "'," +
                    "lastName = '" + person.getLastName() + "'," +
                    "gender = '" + person.getGender() + "'," +
                    "birthday = '" + person.getBirthday() + "'," +
                    "email = '" + person.getEmail() + "'," +
                    "age = " + person.getAge() + " " +
                    "WHERE id == " + person.getId() + ";";
            stat.executeUpdate(query);
            stat.close();
            connection.close();
            System.out.println("The Person updated.");
        } catch (Exception e) {
            System.out.println("Updating the Person failed... \n" + e.getMessage());
        }
    }

    public void removePerson(Person person)
    {
        Connection connection = connectWithDataBase();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            String query = "DELETE FROM Person WHERE id = " + person.getId() + ";";

            stat.executeUpdate(query);
            stat.close();
            connection.close();
            System.out.println("The Person removed.");
        } catch (Exception e) {
            System.out.println("Removing the Person failed... \n" + e.getMessage());
        }
    }

    public Person getPersonById(int id)
    {
        String query = "SELECT * FROM Person WHERE id = " + id + ";";
        return getPersonsByQuery(query).get(0);
    }

    public ArrayList<Person> getAllPersons()
    {
        String query = "SELECT * FROM Person;";
        return getPersonsByQuery(query);
    }
}
