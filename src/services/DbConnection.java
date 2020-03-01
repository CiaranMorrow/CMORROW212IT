/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.BusinessContact; //Import Libary Models 
import models.PersonalContact;
import models.Users;

/**
 *
 * @author dell
 */
public class DbConnection 
{ 
    // database connection class, to be used within all pages requiring a database connection 
    // this saves duplicate commands 
    private Driver d = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
    // JDBC driver allows you to use the SQL Code 
    
    public DbConnection()
    {
        try
        {
            DriverManager.registerDriver(d); // registering to the driver to allow a connection between the code and db
        } 
        catch (SQLException e) // catching the exception 
        {
            System.out.println(e); 
        }
    }
    
    public Connection Connect()
    {
        Connection conn = null; 
        
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=212Tings;user=root;password=12345;";
        //server driver , Port, DB NAME, 212Tings, UserName, Password 
        try 
        {
            conn = DriverManager.getConnection(connectionUrl); // assigning the variable conn with the connection url - this essentially establishes the connection 
        }
        catch(SQLException ex)
        {
            System.out.println(ex); // print exception 
        }
        
        return conn; // returning the connection to the methods that call it for 
                    //example (From Add contact): conn = connectionManagementService.Connect();

    }
    
    //Create Business Contact
    public void AddBusinessContact(BusinessContact contact, Connection conn) 
    {
        //Inserting into table contact
        //column names eg : "contactFName" - no ContactID Column, this should not be available to edit
        // Values of prepared statements represented by ?'s
        String sql = "INSERT INTO contact" 
                + "(contactFName, contactLname, contactTel, contactEmail, contactAddr1, contactAddr2, contactCity, contactPostcode, businessTel) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try
        { 
            //prepares it as a string rather than passing the values through as SQL
            //prepared statements help prevent sql injections 
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, contact.contactFName);
            stmnt.setString(2, contact.contactLName);
            stmnt.setString(3, contact.contactTel);
            stmnt.setString(4, contact.contactEmail);
            stmnt.setString(5, contact.contactAddr1);
            stmnt.setString(6, contact.contactAddr2);
            stmnt.setString(7, contact.contactCity);
            stmnt.setString(8, contact.contactPostcode);
            stmnt.setString(9, contact.businessTel);
            stmnt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }  
    }
    
    public void UpdateBusinessContact(BusinessContact contact, Connection conn)
    {
        //Updates table called contact/
        // Setting table X with prepared statement represented by ? 
        String sql = "UPDATE contact "
                + "SET contactFName = ?, contactLName = ?, contactTel = ?, contactEmail = ?, contactAddr1 = ?, contactAddr2 = ?, contactCity = ?, contactPostcode = ?, businessTel = ? " 
                +"WHERE contactID = ?";
        
        try
        {
                PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
                stmnt.setString(1, contact.contactFName);
                stmnt.setString(2, contact.contactLName);
                stmnt.setString(3, contact.contactTel);
                stmnt.setString(4, contact.contactEmail);
                stmnt.setString(5, contact.contactAddr1);
                stmnt.setString(6, contact.contactAddr2);
                stmnt.setString(7, contact.contactCity);
                stmnt.setString(8, contact.contactPostcode);
                stmnt.setString(9, contact.businessTel);
                stmnt.setInt(10, contact.contactId);

                stmnt.executeUpdate();       
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        
    }
    
    public void DeleteBusinessContact(int contactId, Connection conn)
    {
        String sql = "DELETE FROM contact WHERE contactID = ? ";
        try
        {
            //Deletes from table contact where contact ID is inputted -  this is don using the JTABLE select
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            stmnt.setInt(1, contactId);// select statement
            stmnt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public void AddPersonalContact(PersonalContact contact, Connection conn)
    {
        // Insert a new entry into contact table 
        // in the column names such as contact FName 
        //Values of the prepares statement 
        String sql = "INSERT INTO contact"
                + "(contactFName, contactLname, contactTel, contactEmail, contactAddr1, contactAddr2, contactCity, contactPostcode, personalTel) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, contact.contactFName);
            stmnt.setString(2, contact.contactLName);
            stmnt.setString(3, contact.contactTel);
            stmnt.setString(4, contact.contactEmail);
            stmnt.setString(5, contact.contactAddr1);
            stmnt.setString(6, contact.contactAddr2);
            stmnt.setString(7, contact.contactCity);
            stmnt.setString(8, contact.contactPostcode);
            stmnt.setString(9, contact.homeTel);
        
            stmnt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    
    public void UpdatePersonalContact(PersonalContact contact, Connection conn)
    {
        //updating personal contact table 
        String sql = "UPDATE contact "
                + "SET contactFName = ?, contactLName= ?, contactTel = ?, contactEmail = ?, contactAddr1 = ?, contactAddr2 = ?, contactCity = ?, contactPostcode = ?, personalTel = ? " 
                +"WHERE contactID = ?"; 
        
        try
        {
                PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
                stmnt.setString(1, contact.contactFName);
                stmnt.setString(2, contact.contactLName);
                stmnt.setString(3, contact.contactTel);
                stmnt.setString(4, contact.contactEmail);
                stmnt.setString(5, contact.contactAddr1);
                stmnt.setString(6, contact.contactAddr2);
                stmnt.setString(7, contact.contactCity);
                stmnt.setString(8, contact.contactPostcode);
                stmnt.setString(9, contact.homeTel);
                stmnt.setInt(10, contact.contactId);

                stmnt.executeUpdate();
            }
        catch (SQLException e)
        {
                System.out.println(e);
        }  
    }

    public void DeletePersonalContact(int contactId, Connection conn)
    {
        // deleting entry using the contactID 
        String sql = "DELETE FROM contact WHERE contactID = ? ";
        
        try
        {
            PreparedStatement stmnt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmnt.setInt(1, contactId);
            stmnt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }       
    }
    
    public BusinessContact[] GetBusinessContacts(Connection conn)
    {
        // businessContact returning an array of businessContacts passing through the connection 
        ArrayList<BusinessContact> businessContacts = new ArrayList<BusinessContact>();
        BusinessContact[] bContactArray = {};
        //SQL string selecting entries where personalTEL is not populated 
        String sql = "SELECT * FROM contact WHERE businessTel IS NOT NULL AND personalTel IS NULL;";
        try
        {
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            ResultSet contacts = stmnt.executeQuery();
            
            while(contacts.next()) // while theres a result within result set, fills array, once all data is parsed to the model, array is returned 
            {
                BusinessContact contact = new BusinessContact();

                contact.contactId = contacts.getInt("contactID");
                contact.contactFName = contacts.getString("contactFName");
                contact.contactLName = contacts.getString("contactLName");
                contact.contactTel = contacts.getString("contactTel");
                contact.contactEmail = contacts.getString("contactEmail");
                contact.contactAddr1 = contacts.getString("contactAddr1");
                contact.contactAddr2 = contacts.getString("contactAddr2");
                contact.contactCity = contacts.getString("contactCity");
                contact.contactPostcode = contacts.getString("contactPostcode");
                contact.businessTel = contacts.getString("businessTel");

                businessContacts.add(contact);
                contact = null; // clears contact 
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        bContactArray = new BusinessContact[businessContacts.size()];
        return businessContacts.toArray(bContactArray);
    }
    
    public PersonalContact[] GetPersonalContacts(Connection conn)
    {
        ArrayList<PersonalContact> personalContacts = new ArrayList<PersonalContact>();
        PersonalContact[] pContactArray = {};
                //SQL string selecting entries where BusinessTEL is not populated 

        String sql = "SELECT * FROM contact WHERE personalTel IS NOT NULL AND businessTel IS NULL;";
        try
        {
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            ResultSet contacts = stmnt.executeQuery();
            
            while(contacts.next()) // while theres a result within result set, fills array, once all data is parsed to the model, array is returned 
            {
                PersonalContact contact = new PersonalContact();

                contact.contactId = contacts.getInt("contactID");
                contact.contactFName = contacts.getString("contactFName");
                contact.contactLName = contacts.getString("contactLName");
                contact.contactTel = contacts.getString("contactTel");
                contact.contactEmail = contacts.getString("contactEmail");
                contact.contactAddr1 = contacts.getString("contactAddr1");
                contact.contactAddr2 = contacts.getString("contactAddr2");
                contact.contactCity = contacts.getString("contactCity");
                contact.contactPostcode = contacts.getString("contactPostcode");
                contact.homeTel = contacts.getString("personalTel");

                personalContacts.add(contact);
                contact = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        pContactArray = new PersonalContact[personalContacts.size()];
        return personalContacts.toArray(pContactArray);
    }
    public void AddUser(Users user, Connection conn)
    {
        // Insert a new entry into contact table 
        // in the column names such as contact FName 
        //Values of the prepares statement 
        String sql = "INSERT INTO users "
                + "(username, password) "
                + "VALUES(?,?)";
        try
        {
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, user.username);
            stmnt.setString(2, user.password);
            
            stmnt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    public Users[] GetUsers(Connection conn)
    {
        ArrayList<Users> users = new ArrayList<Users>();
        Users[] usersArray = {};
                //SQL string selecting entries where BusinessTEL is not populated 

        String sql = "SELECT * FROM users;";
        try
        {
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            ResultSet userSet = stmnt.executeQuery();
            
            while(userSet.next()) // while theres a result within result set, fills array, once all data is parsed to the model, array is returned 
            {
                Users user = new Users();

                user.userID = userSet.getInt("userID");
                user.username = userSet.getString("username");
                user.password = userSet.getString("password");
               
                users.add(user);
                user = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        usersArray = new Users[users.size()];
        return users.toArray(usersArray);
    }
    
    
}
