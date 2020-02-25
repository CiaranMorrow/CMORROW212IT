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
public class BusinessContact extends Contact
{
    public String businessTel;
    
    public String GetBusinessTel()
    {
        return this.businessTel;
    }
    public void SetBusinessTel(String businessTel)
    {
        this.businessTel = businessTel;
    }
}
