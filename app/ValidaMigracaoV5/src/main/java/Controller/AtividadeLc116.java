/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author moises
 */
public class AtividadeLc116 implements InterfaceMigracao {
    private Integer excelRowNumber = null;
    private String codigoLc116 = null;
    private String descricaoAtividade = null;
    private String aliquotaPadrao = null;
    private ArrayList<String> log = new ArrayList<String>();
    
    public void setCodigoLc116(String value)
    {
        codigoLc116 = value;
    }
    
    public void setDescricaoAtividade(String value)
    {
        descricaoAtividade = value;
    }
    
    public void setAliquotaPadrao(String value)
    {
        aliquotaPadrao = value;
    }
    
    public String getCodigoLc116()
    {
        return codigoLc116;
    }
    
    public String getDescricaoAtividade()
    {
        return descricaoAtividade;
    }
    
    public String getAliquotaPadrao()
    {
        return aliquotaPadrao;
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
        return  codigoLc116 + descricaoAtividade + aliquotaPadrao;
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
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "codigolc116":
                ret = codigoLc116;
                break;
            case "descricaoatividade":
                ret = descricaoAtividade;
                break;
            case "aliquotapadrao":
                ret = aliquotaPadrao;
                break;
        }
        
        return ret;
    }

    @Override
    public void setString(String columnName, String value) {
       
        switch(columnName.toLowerCase())
        {
            case "codigolc116":
               // this.numDocumento = Integer.parseInt(value);
                this.codigoLc116 = value;
                break;
            case "descricaoatividade":
                this.descricaoAtividade = value;
                break;
            case "aliquotapadrao":
                this.aliquotaPadrao = value;
                break;
        }
    }
    
    
}
