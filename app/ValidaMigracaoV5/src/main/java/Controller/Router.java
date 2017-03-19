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
   // private FileInputStream  oldFile;
   // private FileInputStream  newFile;
  //  private File  templateFile;
    private String pathTemplate;
    private String pathOldVersion;
    private String pathNewVersion;
    private JLabel progress;
    
    private ArrayList<Header> header = new ArrayList<Header>();
    

    public Router(String pathOldVersion, String pathNewVersion, String pathTemplate, JLabel progress) {
        this.progress = progress;
        this.pathOldVersion = pathOldVersion;
        this.pathNewVersion = pathNewVersion;
        this.pathTemplate = pathTemplate;
        
        /* try {
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
        */
        
        routeDispatch();
	    
    }
    
    private void routeDispatch()
    {
        
        Runnable run = new Runnable(pathOldVersion, pathNewVersion, pathTemplate, progress);
        /*switch(template.getString("templatename"))
        {
          //  case "AtividadeLc116R200": executeAtividadeLc116(); break;
            case "ValidaPessoaJuridicaR101": executePessoaJuridicaR101(); break;
            case "ValidaContadorContribuinteR190": executeContadorContribuinteR190(); break;
            case "ValidaEmailPessoaR120": executeEmailPessoaR120(); break;
            case "ValidaEnderecoPessoaR110": executeEnderecoPessoaR110(); break;
            case "ValidaSocioPessoaJuridicaR140": executeSocioPessoaJuridicaR140(); break;
            case "ValidaTelefonePessoaR130": executeTelefonePessoaR130(); break;
            case "ValidaAtividadeEconomicaPjR150": executeAtividadeEconomicaPjR150(); break;
            case "ValidaServicoAutorizadoContribuinteR180": executeServicoAutorizadoContribuinteR180(); break;
            case "ValidaPessoaFisicaR100": executePessoaFisicaR100(); break;
            case "ValidaContribuinteIssqnR170": executeContribuinteIssqnR170(); break;
            default: start(); break;

        }
        */
    }
    
    
    /*public void executeAtividadeLc116()
    {
      
        Runnable run = new Runnable(oldFile, newFile, template, progress);
        
    }

    private void executePessoaJuridicaR101() 
    {
        
        RunPessoaJuridica run = new RunPessoaJuridica(oldFile, newFile, template, progress);
      
    }
    
     private void executeContadorContribuinteR190() 
    {
        
        RunContadorContribuinte run = new RunContadorContribuinte(oldFile, newFile, template, progress);
      
    }
     
    private void executeEmailPessoaR120() 
    {
        
        RunEmailPessoa run = new RunEmailPessoa(oldFile, newFile, template, progress);
      
    }
    private void executeEnderecoPessoaR110() 
    {
        
        RunEnderecoPessoa run = new RunEnderecoPessoa(oldFile, newFile, template, progress);
      
    }
    private void executeSocioPessoaJuridicaR140() 
    {
        
        RunSocioPessoaJuridica run = new RunSocioPessoaJuridica(oldFile, newFile, template, progress);
      
    }
    
    private void executeTelefonePessoaR130() 
    {
        
        RunTelefonePessoa run = new RunTelefonePessoa(oldFile, newFile, template, progress);
      
    }
    private void executeAtividadeEconomicaPjR150() 
    {
        
        RunAtividadeEconomicaPj run = new RunAtividadeEconomicaPj(oldFile, newFile, template, progress);
    }
    
    private void executeServicoAutorizadoContribuinteR180() 
    {
        
        RunServicoAutorizadoContribuinte run = new RunServicoAutorizadoContribuinte(oldFile, newFile, template, progress);
    }
    
    private void executePessoaFisicaR100() 
    {
        
        RunPessoaFisica run = new RunPessoaFisica(oldFile, newFile, template, progress);
      
    }
    
    private void executeContribuinteIssqnR170() 
    {
        
        RunContribuinteIssqn run = new RunContribuinteIssqn(oldFile, newFile, template, progress);
      
    }
    
    private void start() 
    {
        
       Runnable run = new Runnable(oldFile, newFile, template, progress);
      
    }*/
    
}
