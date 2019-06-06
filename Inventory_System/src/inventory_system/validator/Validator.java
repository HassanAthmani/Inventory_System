/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system.validator;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Validator {
    
    public void specialChar(String name){
        String specialCharacters = "!#$%^&*()_-+={}[]`~|/+:';,<>";
        String str2[]=name.split("");
        int count=0;
        for (int i=0;i<str2.length;i++){
            if (specialCharacters.contains(str2[i])){
                count++;
            }
    }
        if (name!=null && count==0){
                        
        }
        else {
            int response = JOptionPane.showConfirmDialog(
        null,"The field should not be empty and no special characters or numbers should be used ","Bad Input",JOptionPane.OK_OPTION);
            System.exit(1);
        }
     }
    

}
