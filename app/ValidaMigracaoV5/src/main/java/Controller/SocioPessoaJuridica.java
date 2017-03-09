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
 * @author allanfraga
 */
public class SocioPessoaJuridica implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String numDocumentoSocio = null;
    private String prcParticipacao = null;

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNumDocumentoSocio() {
        return numDocumentoSocio;
    }

    public void setNumDocumentoSocio(String numDocumentoSocio) {
        this.numDocumentoSocio = numDocumentoSocio;
    }

    public String getPrcParticipacao() {
        return prcParticipacao;
    }

    public void setPrcParticipacao(String prcParticipacao) {
        this.prcParticipacao = prcParticipacao;
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
        return  numDocumento + numDocumentoSocio + prcParticipacao;
    }

    @Override
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
                ret = numDocumento;
                break;
            case "numdocumentosocio":
                ret = numDocumentoSocio;
                break;
            case "prcparticipacao":
                ret = prcParticipacao;
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
            case "numdocumentosocio":
                this.numDocumentoSocio = value;
                break;
            case "prcparticipacao":
                this.prcParticipacao = value;
                break;
        }
    }
}
