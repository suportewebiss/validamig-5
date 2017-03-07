/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author viniciussilva
 */
public class PessoaJuridica implements InterfaceMigracao{
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String tipoPessoa = null;
    private String razaoSocial = null;
    private String nomeFantasia = null;
    private String situacaoPessoa = null;
    private String inscricaoEstadual = null;
    private BigDecimal capitalSocial = null;
    private Calendar dtAberturaEmpresa = null;
    private String porteEmpresa = null;
    private Integer indicaSubstitutoTributario = null;
    private Integer indicaRegimeCaixa = null;
    private ArrayList<String> log = new ArrayList<String>();
  

    public String getnumDocumento() {
        return numDocumento;
    }

    public void setnumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String gettipoPessoa() {
        return tipoPessoa;
    }

    public void settipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getrazaoSocial() {
        return razaoSocial;
    }

    public void setrazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getnomeFantasia() {
        return nomeFantasia;
    }

    public void setnomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getsituacaoPessoa() {
        return situacaoPessoa;
    }

    public void setsituacaoPessoa(String situacaoPessoa) {
        this.situacaoPessoa = situacaoPessoa;
    }

    public String getinscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setinscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public BigDecimal getcapitalSocial() {
        return capitalSocial;
    }

    public void setcapitalSocial(BigDecimal capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public Calendar getdtAberturaEmpresa() {
        return dtAberturaEmpresa;
    }

    public void setdtAberturaEmpresa(Calendar dtAberturaEmpresa) {
        this.dtAberturaEmpresa = dtAberturaEmpresa;
    }

    public String getporteEmpresa() {
        return porteEmpresa;
    }

    public void setporteEmpresa(String porteEmpresa) {
        this.porteEmpresa = porteEmpresa;
    }

    public Integer getindicaSubstitutoTributario() {
        return indicaSubstitutoTributario;
    }

    public void setindicaSubstitutoTributario(Integer indicaSubstitutoTributario) {
        this.indicaSubstitutoTributario = indicaSubstitutoTributario;
    }

    public Integer getindicaRegimeCaixa() {
        return indicaRegimeCaixa;
    }

    public void setindicaRegimeCaixa(Integer indicaRegimeCaixa) {
        this.indicaRegimeCaixa = indicaRegimeCaixa;
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
        return  numDocumento + tipoPessoa + razaoSocial + nomeFantasia + situacaoPessoa + inscricaoEstadual +
                (new DecimalFormat("#0.##").format(capitalSocial)) + 
                (new SimpleDateFormat("dd/MM/yyyy").format(dtAberturaEmpresa)) + 
                porteEmpresa + (new DecimalFormat("#0.##").format(indicaSubstitutoTributario)) +
                (new DecimalFormat("#0.##").format(indicaRegimeCaixa));
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
            case "numdocumento":
                ret = numDocumento;
                break;
            case "tipopessoa":
                ret = tipoPessoa;
                break;
            case "razaosocial":
                ret = razaoSocial;
                break;
            case "nomefantasia":
                ret = nomeFantasia;
                break;
            case "situacaopessoa":
                ret = situacaoPessoa;
                break;
            case "inscricaoestadual":
                ret = inscricaoEstadual;
                break;
            case "capitalsocial":
                ret = (new DecimalFormat("#0.##").format(capitalSocial));
                break;
            case "dtaberturaempresa":
                ret = (new SimpleDateFormat("dd/MM/yyyy").format(dtAberturaEmpresa));
                break;
            case "porteempresa":
                ret = porteEmpresa;
                break;
            case "indicasubstitutotributario":
                ret = (new DecimalFormat("#0.##").format(indicaSubstitutoTributario));
                break;
            case "indicaregimecaixa":
                ret = (new DecimalFormat("#0.##").format(indicaRegimeCaixa));
                break;
        }
        
        return ret;
    }

       
}
