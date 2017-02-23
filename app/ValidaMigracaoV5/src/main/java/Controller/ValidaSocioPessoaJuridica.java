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
public class ValidaSocioPessoaJuridica implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String numDocumentoSocio = null;
    private BigDecimal prcParticipacao = null;

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

    public BigDecimal getPrcParticipacao() {
        return prcParticipacao;
    }

    public void setPrcParticipacao(BigDecimal prcParticipacao) {
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
        return  numDocumento + numDocumentoSocio + 
                (new DecimalFormat("#0.##").format(prcParticipacao)).toString();
    }
}
