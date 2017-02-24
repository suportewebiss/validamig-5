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
public class ValidaAtividadeEconomicaPj implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String cnae = null;
    private String descricaoCnae = null;
    private Integer indicaPrincipal = null;

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

    public Integer getIndicaPrincipal() {
        return indicaPrincipal;
    }

    public void setIndicaPrincipal(Integer indicaPrincipal) {
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
        return  numDocumento + cnae + descricaoCnae + String.valueOf(indicaPrincipal);
    }
            
}
