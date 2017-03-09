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
public class ContribuinteIssqn implements InterfaceMigracao{
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String inscricao = null;
    private String regimeIssqn = null;
    private String qtdProfissionais = null;
    private String valorEstimativa = null;
    private String valorFixo = null;
    private String dtInicioAutorizacao = null;
    private String dtFimAutorizacao = null;
    private String indicaAvulso = null;
    private String dtCadastro = null;
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

    public String getQtdProfissionais() {
        return qtdProfissionais;
    }

    public void setQtdProfissionais(String qtdProfissionais) {
        this.qtdProfissionais = qtdProfissionais;
    }

    public String getValorEstimativa() {
        return valorEstimativa;
    }

    public void setValorEstimativa(String valorEstimativa) {
        this.valorEstimativa = valorEstimativa;
    }

    public String getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(String valorFixo) {
        this.valorFixo = valorFixo;
    }

    public String getDtInicioAutorizacao() {
        return dtInicioAutorizacao;
    }

    public void setDtInicioAutorizacao(String dtInicioAutorizacao) {
        this.dtInicioAutorizacao = dtInicioAutorizacao;
    }

    public String getDtFimAutorizacao() {
        return dtFimAutorizacao;
    }

    public void setDtFimAutorizacao(String dtFimAutorizacao) {
        this.dtFimAutorizacao = dtFimAutorizacao;
    }

    public String getIndicaAvulso() {
        return indicaAvulso;
    }

    public void setIndicaAvulso(String indicaAvulso) {
        this.indicaAvulso = indicaAvulso;
    }

    public String getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(String dtCadastro) {
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
        return numDocumento + inscricao + regimeIssqn + qtdProfissionais + 
               valorEstimativa + valorFixo + dtInicioAutorizacao + dtFimAutorizacao +
               indicaAvulso + dtCadastro + nomeContatoContribuinte +
               emailContatoContribuinte + telefoneContatoContribuinte + logradouroEnderecoContribuinte +
               numeroEnderecoContribuinte + complementoEnderecoContribuinte + bairroEnderecoContribuinte +
               cidadeEnderecoContribuinte + ufEnderecoContribuinte;
                
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
            case "regimeissqn":
                ret = regimeIssqn;
                break;
            case "qtdprofissionais":
                ret = qtdProfissionais;
                break;
            case "valorestimativa":
                ret = valorEstimativa;
                break;
            case "valorfixo":
                ret = valorFixo;
                break;
            case "dtinicioautorizacao":
                ret = dtInicioAutorizacao;
                break;
            case "dtfimautorizacao":
                ret = dtFimAutorizacao;
                break;    
            case "indicaavulso":
                ret = indicaAvulso;
                break;
            case "dtcadastro":
                ret = dtCadastro;
                break;
            case "nomecontatocontribuinte":
                ret = nomeContatoContribuinte;
                break;
            case "emailcontatocontribuinte":
                ret = emailContatoContribuinte;
                break;
            case "telefonecontatocontribuinte":
                ret = telefoneContatoContribuinte;
                break;    
            case "logradouroenderecocontribuinte":
                ret = logradouroEnderecoContribuinte;
                break;
            case "numeroenderecocontribuinte":
                ret = numeroEnderecoContribuinte;
                break;    
            case "complementoenderecocontribuinte":
                ret = complementoEnderecoContribuinte;
                break;
            case "bairroenderecocontribuinte":
                ret = bairroEnderecoContribuinte;
                break;    
            case "cidadeenderecocontribuinte":
                ret = cidadeEnderecoContribuinte;
                break;    
            case "ufenderecocontribuinte":
                ret = ufEnderecoContribuinte;
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
            case "regimeissqn":
                this.regimeIssqn = value;
                break;
            case "qtdprofissionais":
                this.qtdProfissionais = value;
                break;
            case "valorestimativa":
                this.valorEstimativa = value;
                break;
            case "valorfixo":
                this.valorFixo = value;
                break;
            case "dtinicioautorizacao":
                this.dtInicioAutorizacao = value;
                break;
            case "dtfimautorizacao":
                this.dtFimAutorizacao = value;
                break;
            case "indicaavulso":
                this.indicaAvulso = value;
                break;
            case "dtcadastro":
                this.dtCadastro = value;
                break;
            case "nomecontatocontribuinte":
                this.nomeContatoContribuinte = value;
                break;
            case "emailcontatocontribuinte":
                this.emailContatoContribuinte = value;
                break;    
            case "telefonecontatocontribuinte":
                this.telefoneContatoContribuinte = value;
                break;
            case "logradouroenderecocontribuinte":
                this.logradouroEnderecoContribuinte = value;
                break;
            case "numeroenderecocontribuinte":
                this.numeroEnderecoContribuinte = value;
                break;
            case "complementoenderecocontribuinte":
                this.complementoEnderecoContribuinte = value;
                break;
            case "bairroenderecocontribuinte":
                this.bairroEnderecoContribuinte = value;
                break;
            case "cidadeenderecocontribuinte":
                this.cidadeEnderecoContribuinte = value;
                break;
            case "ufenderecocontribuinte":
                this.ufEnderecoContribuinte = value;
                break;
        }
    }
    
}
