/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;

/**
 *
 * @author allanfraga
 */
public class TelefonePessoa implements InterfaceMigracao {
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String fonePessoa = null;
    private String tipoFone = null;
    private ArrayList<String> log = new ArrayList<String>();
    

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getFonePessoa() {
        return fonePessoa;
    }

    public void setFonePessoa(String fonePessoa) {
        this.fonePessoa = fonePessoa;
    }

    public String getTipoFone() {
        return tipoFone;
    }

    public void setTipoFone(String tipoFone) {
        this.tipoFone = tipoFone;
    }
       
    @Override
    public void addLog(String log) {
        this.log.add(log);
    }

    @Override
    public ArrayList<String> getLog() {
        return log;
    }
    
    @Override
    public void setExcelRowNumber(Integer row) {
        excelRowNumber = row;
    }

    @Override
    public Integer getExcelRowNumber() {
         return excelRowNumber;
    }
    
    @Override
    public String toString()
    {
        // concatena todas as propriedades em uma string (Somente os campos que existem no template)
        return  numDocumento + fonePessoa + tipoFone;
    }

    @Override
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
                ret = numDocumento;
                break;
            case "fonepessoa":
                ret = fonePessoa;
                break;
            case "tipofone":
                ret = tipoFone;
                break;
                
        }
        
        return ret;
    }

    @Override
    public void setString(String columnName, String value) {
       
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
               // this.numDocumento = Integer.parseInt(value);
                this.numDocumento = value;
                break;
            case "fonepessoa":
                this.fonePessoa = value;
                break;
            case "tipofone":
                this.tipoFone = value;
                break;
        }
    }
    
    
}
