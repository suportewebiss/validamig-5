/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.json.JSONObject;





/**
 *
 * @author moises
 */
public class Router {
    private FileInputStream  oldFile;
    private FileInputStream  newFile;
    private File  templateFile;
    private JSONObject template;
    private JLabel progress;
    
    private ArrayList<Header> header = new ArrayList<Header>();
    

    public Router(String pathOldVersion, String pathNewVersion, String pathTemplate, JLabel progress) {
        this.progress = progress;
        try {
            oldFile = new FileInputStream(new File(pathOldVersion));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            newFile = new FileInputStream(new File(pathNewVersion));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        templateFile = new File(pathTemplate);
        
        
        try {
             String txt = new String(Files.readAllBytes(templateFile.toPath()));
             //System.out.println(txt);  
             template =  new JSONObject(txt);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        routeDispatch();
	    
    }
    
    private void routeDispatch()
    {
        switch(template.getString("templatename"))
        {
            case "AtividadeLc116R200": executeAtividadeLc116(); break;
            case "ValidaPessoaJuridicaR101": executePessoaJuridicaR101(); break;
            case "ValidaContadorContribuinteR190": executeContadorContribuinteR190(); break;
        }
    }
    
    
    public void executeAtividadeLc116()
    {
      
        RunContadorContribuinte run = new RunContadorContribuinte(oldFile, newFile, template, progress);
        
    }

    private void executePessoaJuridicaR101() 
    {
        
        RunPessoaJuridica run = new RunPessoaJuridica(oldFile, newFile, template, progress);
      
    }
    
     private void executeContadorContribuinteR190() 
    {
        
        RunContadorContribuinte run = new RunContadorContribuinte(oldFile, newFile, template, progress);
      
    }
    
}
