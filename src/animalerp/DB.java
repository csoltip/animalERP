
package animalerp;

import pojo.Assignment;
import pojo.Animal;
import pojo.Person;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Creating a local Derby database and defining its tables
 * @author Bailo Dávid
 */
public class DB {

    final String URL = "jdbc:derby:aerpDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    public Connection connection() {
        
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            System.out.println("Error 0: " + ex);
        }
        
        return conn;
    }
    
    public DB() {

        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection estabilished.");
        } catch (SQLException ex) {
            System.out.println("Error 1: " + ex);
        }

        if (conn != null) {
            try {
                createStatement = conn.createStatement();
                System.out.println("CreateStatement created.");
            } catch (SQLException ex) {
                System.out.println("Error 2: " + ex);
            }
        }

        try {
            dbmd = conn.getMetaData();
            System.out.println("Metadata is available.");
        } catch (SQLException ex) {
            System.out.println("Error 3: " + ex);
        }
        
        // Creating the LOGIN table and adding an administrator user.
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "LOGIN", null);
            if (!rs.next()) {
                createStatement.execute("create table login(user_id INT not null primary key GENERATED ALWAYS AS IDENTITY" +
                        "(START WITH 1, INCREMENT BY 1),username varchar(50), password varchar(50))");
                String sql = "insert into login (username, password) values(?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, "admin");
                preparedStatement.setString(2, "password");
                preparedStatement.execute();
                System.out.println("LOGIN table and administrator user has been created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error while creating the LOGIN table: " + ex);
        }
        
        // Creating the PERSONS table
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "PERSONS", null);
            if (!rs.next()) {
                createStatement.execute("create table persons(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(50), category varchar(20), phone varchar(20), address varchar(50))");
                System.out.println("PERSONS table has been created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error while creating the PERSONS table: " + ex);
        }
        
        // Creating the ANIMALS table
        try {
            ResultSet rs2 = dbmd.getTables(null, "APP", "ANIMALS", null);
            if (!rs2.next()) {
                createStatement.execute("create table animals(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(50), breed varchar(20), sex varchar(10), owner varchar(50), status varchar(20), recordedAt varchar(20))");
                System.out.println("ANIMALS table has been created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error while creating the ANIMALS table: " + ex);
        }
        
        // Creating the ASSIGNMENTS table
        try {
            ResultSet rs3 = dbmd.getTables(null, "APP", "ASSIGNMENTS", null);
            if (!rs3.next()) {
                createStatement.execute("create table assignments(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),status varchar(20), subject varchar(50), assignee varchar(50), modifiedAt date)");
                System.out.println("ASSIGNMENTS table has been created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error while creating the ASSIGNMENTS table: " + ex);
        }
        
        
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="PERSONS table">
    public ArrayList<Person> getAllPersons() {
        String sql = "select * from persons";
        ArrayList<Person> allPersons = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            allPersons = new ArrayList<>();
            while (rs.next()) {
                Person actualPerson = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getString("phone"), rs.getString("address"));
                allPersons.add(actualPerson);
                System.out.println("Persons collected.");
            }
        } catch (SQLException ex) {
            System.out.println("Querying persons was unsuccessful: " + ex);
        }
        return allPersons;
    }
    
    public ArrayList<String> getAssociatesNames() {
        ArrayList<String> allPersons = new ArrayList<String>();
        String sql = "select * from persons where category='Associate'";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()) {
                allPersons.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("Querying associates was unsuccesful: " + ex);
        }
        return allPersons;
    }
    
    public void newPerson(Person person) {
        try {
            String sql = "insert into persons (name, category, phone, address) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getCategory());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Could not add new person: " + ex);
        }
    }
    
    public void modifyPerson(Person person) {
        try {
            String sql = "update persons set name = ?, category = ?, phone = ?, address = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getCategory());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.setInt(5, Integer.parseInt(person.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Could not modify person: "+ex);
        }
    }
    
    public void deletePerson(Person person) {
        try {
            String sql = "delete from persons where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(person.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Could not delete person: "+ex);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ANIMALS table">
    public ArrayList<Animal> getAllAnimals() {
        String sql2 = "select * from animals";
        ArrayList<Animal> allAnimals = null;
        try {
            ResultSet rs2 = createStatement.executeQuery(sql2);
            allAnimals = new ArrayList<>();
            while (rs2.next()) {
                Animal actualAnimal = new Animal(rs2.getInt("id"), rs2.getString("name"), rs2.getString("breed"),rs2.getString("sex"), rs2.getString("owner"), rs2.getString("status"), rs2.getString("recordedAt"));
                allAnimals.add(actualAnimal);
                System.out.println("Animals collected.");
            }
        } catch (SQLException ex) {
            System.out.println("Querying animals was unsuccessful: " + ex);
        }
        return allAnimals;
    }
    
    
    public void newAnimal(Animal animal) {
        try {
            String sql2 = "insert into animals (name, breed, sex, owner, status) values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getBreed());
            preparedStatement.setString(3, animal.getSex());
            preparedStatement.setString(4, animal.getOwner());
            preparedStatement.setString(5, animal.getStatusz());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Could not create new animal: " + ex);
        }
    }
    
    public void modifyAnimal(Animal animal) {
        try {
            String sql2 = "update animals set name = ?, breed = ?, sex = ?, owner = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getBreed());
            preparedStatement.setString(3, animal.getSex());
            preparedStatement.setString(4, animal.getOwner());
            preparedStatement.setString(5, animal.getStatusz());
            preparedStatement.setInt(6, Integer.parseInt(animal.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Could not modify animal: "+ex);
        }
    }
    
    public void deleteAnimal(Animal animal) {
        try {
            String sql2 = "delete from animals where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setInt(1, Integer.parseInt(animal.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Could not delete animal: "+ex);
        }
    }
    
    public ArrayList<String> getPetOwnersNames() {
        ArrayList<String> allPersons = new ArrayList<String>();
        String sql = "select * from persons where category='Animal keeper'";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()) {
                allPersons.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("Querying pet owner was unsuccessful: " + ex);
        }
        return allPersons;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ASSIGNMENTS table">
    public ArrayList<Assignment> getAllAssignments() {
        String sql3 = "select * from assignments";
        ArrayList<Assignment> allAssignments = null;
        try {
            ResultSet rs3 = createStatement.executeQuery(sql3);
            allAssignments = new ArrayList<>();
            while (rs3.next()) {
                Assignment actualAssignment = new Assignment(rs3.getInt("id"), rs3.getString("status"), rs3.getString("subject"), rs3.getString("assignee"), rs3.getString("modifiedAt"));
                allAssignments.add(actualAssignment);
                System.out.println("Assignments collected.");
            }
        } catch (SQLException ex) {
            System.out.println("Querying assignments was unsuccesful: " + ex);
        }
        return allAssignments;
    }
    
    public void newAssignment(Assignment assignment) {
        try {
            String sql3 = "insert into assignments (status, subject, assignee) values(?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setString(1, assignment.getStatus());
            preparedStatement.setString(2, assignment.getSubject());
            preparedStatement.setString(3, assignment.getAssignee());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Could not create new assignment: " + ex);
        }
    }
    
    public void modifyAssignment(Assignment assignment) {
        try {
            String sql3 = "update assignment set status = ?, subject = ?, assignee = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setString(1, assignment.getStatus());
            preparedStatement.setString(2, assignment.getSubject());
            preparedStatement.setString(3, assignment.getAssignee());
            preparedStatement.setInt(4, Integer.parseInt(assignment.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Could not modify assignment: "+ex);
        }
    }
    
    public void deleteAssignment(Assignment assignment) {
        try {
            String sql3 = "delete from assignments where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setInt(1, Integer.parseInt(assignment.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Could not delete assignment: "+ex);
        }
    }
    //</editor-fold>
    
}