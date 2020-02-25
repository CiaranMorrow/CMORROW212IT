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
public class MergeContact
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
    public String businessTel;
    public String homeTel;
    
    public MergeContact(BusinessContact contact)
    {
        this.contactId = contact.contactId;
        this.contactFName = contact.contactFName;
        this.contactLName = contact.contactLName;
        this.contactTel = contact.contactTel;
        this.contactEmail = contact.contactEmail;
        this.contactAddr1 = contact.contactAddr1;
        this.contactAddr2 = contact.contactAddr2;
        this.contactCity = contact.contactCity;
        this.contactPostcode = contact.contactPostcode;
        this.businessTel = contact.businessTel;
        this.homeTel = null;
    }
    
    public MergeContact(PersonalContact contact)
    {
        this.contactId = contact.contactId;
        this.contactFName = contact.contactFName;
        this.contactLName = contact.contactLName;
        this.contactTel = contact.contactTel;
        this.contactEmail = contact.contactEmail;
        this.contactAddr1 = contact.contactAddr1;
        this.contactAddr2 = contact.contactAddr2;
        this.contactCity = contact.contactCity;
        this.contactPostcode = contact.contactPostcode;
        this.businessTel = null;
        this.homeTel = contact.homeTel;
    }
}
