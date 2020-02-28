/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.BusinessContact; //Import Libary Models 

import models.PersonalContact;

/**
 *
 * @author dell
 */
public class DbConnection 
{ 
    private Driver d = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
    
    public DbConnection()
    {
        try
        {
            DriverManager.registerDriver(d);
        } 
        catch (SQLException e)
        {
            System.out.println(e); 
        }
    }
    
    public Connection Connect()
    {
        Connection conn = null;
        
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=212Tings;user=root;password=12345;";

        try 
        {
            conn = DriverManager.getConnection(connectionUrl);
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        
        return conn;
    }
    
    //Create Business Contact
    public void AddBusinessContact(BusinessContact contact, Connection conn) 
    {
        String sql = "INSERT INTO contact"
                + "(contactFName, contactLname, contactTel, contactEmail, contactAddr1, contactAddr2, contactCity, contactPostcode, businessTel) "
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
            PreparedStatement stmnt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            stmnt.setInt(1, contactId);
            stmnt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public void AddPersonalContact(PersonalContact contact, Connection conn)
    {
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
        ArrayList<BusinessContact> businessContacts = new ArrayList<BusinessContact>();
        BusinessContact[] bContactArray = {};
        
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
                contact = null;
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
}
