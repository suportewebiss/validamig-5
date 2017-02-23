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
 * @author guilhermereis
 */
public class ValidaContribuinteIssqn implements InterfaceMigracao{
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String inscricao = null;
    private String regimeIssqn = null;
    private Integer qtdProfissionais = null;
    private BigDecimal valorEstimativa = null;
    private BigDecimal valorFixo = null;
    private Calendar dtInicioAutorizacao = null;
    private Calendar dtFimAutorizacao = null;
    private Integer indicaAvulso = null;
    private Calendar dtCadastro = null;
    private String nomeContatoContribuinte = null;
    private String emailContatoContribuinte = null;
    private String telefoneContatoContribuinte = null;
    private String logradouroEnderecoContribuinte = null;
    private String numeroEnderecoContribuinte = null;
    private String complementoEnderecoContribuinte = null;
    private String bairroEnderecoContribuinte = null;
    private String cidadeEnderecoContribuinte = null;
    private String ufEnderecoContribuinte = null;
    private ArrayList<String> log = new ArrayList<String>();

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

    public String getRegimeIssqn() {
        return regimeIssqn;
    }

    public void setRegimeIssqn(String regimeIssqn) {
        this.regimeIssqn = regimeIssqn;
    }

    public Integer getQtdProfissionais() {
        return qtdProfissionais;
    }

    public void setQtdProfissionais(Integer qtdProfissionais) {
        this.qtdProfissionais = qtdProfissionais;
    }

    public BigDecimal getValorEstimativa() {
        return valorEstimativa;
    }

    public void setValorEstimativa(BigDecimal valorEstimativa) {
        this.valorEstimativa = valorEstimativa;
    }

    public BigDecimal getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(BigDecimal valorFixo) {
        this.valorFixo = valorFixo;
    }

    public Calendar getDtInicioAutorizacao() {
        return dtInicioAutorizacao;
    }

    public void setDtInicioAutorizacao(Calendar dtInicioAutorizacao) {
        this.dtInicioAutorizacao = dtInicioAutorizacao;
    }

    public Calendar getDtFimAutorizacao() {
        return dtFimAutorizacao;
    }

    public void setDtFimAutorizacao(Calendar dtFimAutorizacao) {
        this.dtFimAutorizacao = dtFimAutorizacao;
    }

    public Integer getIndicaAvulso() {
        return indicaAvulso;
    }

    public void setIndicaAvulso(Integer indicaAvulso) {
        this.indicaAvulso = indicaAvulso;
    }

    public Calendar getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Calendar dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getNomeContatoContribuinte() {
        return nomeContatoContribuinte;
    }

    public void setNomeContatoContribuinte(String nomeContatoContribuinte) {
        this.nomeContatoContribuinte = nomeContatoContribuinte;
    }

    public String getEmailContatoContribuinte() {
        return emailContatoContribuinte;
    }

    public void setEmailContatoContribuinte(String emailContatoContribuinte) {
        this.emailContatoContribuinte = emailContatoContribuinte;
    }

    public String getTelefoneContatoContribuinte() {
        return telefoneContatoContribuinte;
    }

    public void setTelefoneContatoContribuinte(String telefoneContatoContribuinte) {
        this.telefoneContatoContribuinte = telefoneContatoContribuinte;
    }

    public String getLogradouroEnderecoContribuinte() {
        return logradouroEnderecoContribuinte;
    }

    public void setLogradouroEnderecoContribuinte(String logradouroEnderecoContribuinte) {
        this.logradouroEnderecoContribuinte = logradouroEnderecoContribuinte;
    }

    public String getNumeroEnderecoContribuinte() {
        return numeroEnderecoContribuinte;
    }

    public void setNumeroEnderecoContribuinte(String numeroEnderecoContribuinte) {
        this.numeroEnderecoContribuinte = numeroEnderecoContribuinte;
    }

    public String getComplementoEnderecoContribuinte() {
        return complementoEnderecoContribuinte;
    }

    public void setComplementoEnderecoContribuinte(String complementoEnderecoContribuinte) {
        this.complementoEnderecoContribuinte = complementoEnderecoContribuinte;
    }

    public String getBairroEnderecoContribuinte() {
        return bairroEnderecoContribuinte;
    }

    public void setBairroEnderecoContribuinte(String bairroEnderecoContribuinte) {
        this.bairroEnderecoContribuinte = bairroEnderecoContribuinte;
    }

    public String getCidadeEnderecoContribuinte() {
        return cidadeEnderecoContribuinte;
    }

    public void setCidadeEnderecoContribuinte(String cidadeEnderecoContribuinte) {
        this.cidadeEnderecoContribuinte = cidadeEnderecoContribuinte;
    }

    public String getUfEnderecoContribuinte() {
        return ufEnderecoContribuinte;
    }

    public void setUfEnderecoContribuinte(String ufEnderecoContribuinte) {
        this.ufEnderecoContribuinte = ufEnderecoContribuinte;
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
    public void addLog(String log) {
        this.log.add(log);
    }

    @Override
    public ArrayList<String> getLog() {
        return log;
    }
    public String toString()
    {
        // concatena todas as propriedades em uma string (Somente os campos que existem no template)
        return numDocumento + inscricao + regimeIssqn + String.valueOf(qtdProfissionais)+ 
               (new DecimalFormat("#0.##").format(valorEstimativa)).toString() +  
               (new DecimalFormat("#0.##").format(valorFixo)).toString() + (new SimpleDateFormat("dd/MM/yyyy").format(dtInicioAutorizacao)).toString() +
               (new SimpleDateFormat("dd/MM/yyyy").format(dtFimAutorizacao)).toString() + String.valueOf(indicaAvulso) + (new SimpleDateFormat("dd/MM/yyyy").format(dtCadastro)).toString() + nomeContatoContribuinte +
               emailContatoContribuinte + telefoneContatoContribuinte + logradouroEnderecoContribuinte +
               numeroEnderecoContribuinte + complementoEnderecoContribuinte + bairroEnderecoContribuinte +
               cidadeEnderecoContribuinte + ufEnderecoContribuinte;
                
    }
    
    
    
    
    
}
