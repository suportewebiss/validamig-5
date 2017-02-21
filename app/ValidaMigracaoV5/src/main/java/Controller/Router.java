/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;





/**
 *
 * @author moises
 */
public class Router {
  
    
    public static Object getObject(String templateName)
    {
        Object classe = null;
     
        templateName = templateName.toLowerCase();
        
        
        switch(templateName)
        {
            case "atividadelc116r200": classe = new AtividadeLc116(); break;
            // continuar adicionando as entradas para as outras classes
        }
        
       
        return classe;
    }
    

}
