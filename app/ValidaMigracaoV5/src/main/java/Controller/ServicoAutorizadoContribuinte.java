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
public class ServicoAutorizadoContribuinte implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String inscricao = null;
    private String codigoLc116 = null;
    private String aliquota = null;     

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getCodigoLc116() {
        return codigoLc116;
    }

    public void setCodigoLc116(String codigoLc116) {
        this.codigoLc116 = codigoLc116;
    }

    public String getAliquota() {
        return aliquota;
    }

    public void setAliquota(String aliquota) {
        this.aliquota = aliquota;
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
        return  numDocumento + inscricao + codigoLc116 + aliquota;
    }

    @Override
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
                ret = numDocumento;
                break;
            case "inscricao":
                ret = inscricao;
                break;
            case "codigolc116":
                ret = codigoLc116;
                break;
            case "aliquota":
                ret = aliquota;
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
            case "inscricao":
                this.inscricao = value;
                break;
            case "codigolc116":
                this.codigoLc116 = value;
                break;
            case "aliquota":
                this.aliquota = value;
                break;    
        }
    }
}
