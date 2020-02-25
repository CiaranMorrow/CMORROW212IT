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
public class PersonalContact extends Contact
{
    public String homeTel;
    
    public String GetHomeTel()
    {
        return this.homeTel;
    }
    public void SetHomeTel(String homeTel)
    {
        this.homeTel = homeTel;
    }
    
}
