/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.JLabel;
import org.json.JSONObject;

/**
 *
 * @author allanfraga
 */
public class AtividadeEconomicaPj implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String cnae = null;
    private String descricaoCnae = null;
    private String indicaPrincipal = null;

   
    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getDescricaoCnae() {
        return descricaoCnae;
    }

    public void setDescricaoCnae(String descricaoCnae) {
        this.descricaoCnae = descricaoCnae;
    }

    public String getIndicaPrincipal() {
        return indicaPrincipal;
    }

    public void setIndicaPrincipal(String indicaPrincipal) {
        this.indicaPrincipal = indicaPrincipal;
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
    
    public String toString()
    {
        // concatena todas as propriedades em uma string (Somente os campos que existem no template)
        return  numDocumento + cnae + descricaoCnae + indicaPrincipal;
    }

    @Override
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
                ret = numDocumento;
                break;
            case "cnae":
                ret = cnae;
                break;
            case "descricaocnae":
                ret = descricaoCnae;
                break;
            case "indicaprincipal":
                ret = indicaPrincipal;
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
            case "cnae":
                this.cnae = value;
                break;
            case "descricaocnae":
                this.descricaoCnae = value;
                break;
            case "indicaprincipal":
                this.indicaPrincipal = value;
                break;
        }
    }
            
}
