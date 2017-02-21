/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author moises
 */
public class AtividadeLc116 implements InterfaceMigracao {
    private Integer excelRowNumber = null;
    private String codigoLc116 = null;
    private String descricaoAtividade = null;
    private BigDecimal aliquotaPadrao = null;
    
    
    public void setCodigoLc116(String value)
    {
        codigoLc116 = value;
    }
    
    public void setDescricaoAtividade(String value)
    {
        descricaoAtividade = value;
    }
    
    public void setAliquotaPadrao(BigDecimal value)
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
    
    public BigDecimal getAliquotaPadrao()
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
        return  codigoLc116 + descricaoAtividade + 
                (new DecimalFormat("#0.##").format(aliquotaPadrao)).toString();
    }
}
