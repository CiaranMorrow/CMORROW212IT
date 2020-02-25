/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author dell
 */
public class Contact 
{
    public int contactId;
    public String contactFName;
    public String contactLName;
    public String contactTel; 
    public String contactEmail;
    public String contactAddr1;
    public String contactAddr2;
    public String contactCity;
    public String contactPostcode;
    
    public void SetContactId(int contactId)
    {
        this.contactId = contactId;
    }
    public int GetContactId()
    {
        return this.contactId;
    }
          
    public String GetContactFName()
    {
        return this.contactFName;
    }
    public void SetContactFName(String contactFName)
    {
        this.contactFName = contactFName;
    }
       
        public String GetContactLName()
    {
        return this.contactLName;
    }
    public void SetContactLName(String contactLName)
    {
        this.contactLName = contactLName;
    }
    
    public String GetContactTel()
    {
        return this.contactTel;
    }
    public void SetContactTel(String contactTel)
    {
        this.contactTel = contactTel;
    }
        
    public String GetContactEmail()
    {
        return this.contactEmail;
    }
    public void SetContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }
        
     public String GetContactAddr1()
    {
        return this.contactAddr1;
    }
    public void SetContactAddr1(String contactAddr1)
    {
        this.contactAddr1 = contactAddr1;
    }
         
    public String GetContactAddr2()
    {
        return this.contactAddr2;
    }
    public void SetContactAddr2(String contactAddr2)
    {
        this.contactAddr2= contactAddr2;
    }
      
    public String GetContactCity()
    {
        return this.contactCity;
    }
    public void SetContactCity(String contactCity)
    {
        this.contactCity= contactCity;
    }
    
    public String GetContactPostcode()
    {
        return this.contactPostcode;
    }
    public void SetContactPostcode(String contactPostcode)
    {
        this.contactPostcode= contactPostcode;
    }
    
    
}
