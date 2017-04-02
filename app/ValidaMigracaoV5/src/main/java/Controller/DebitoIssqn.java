/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;

/**
 *
 * @author moises
 */
public class DebitoIssqn implements InterfaceMigracao {
    
    private Integer excelRowNumber = null;
    private ArrayList<String> log = new ArrayList<String>();
    
    private String numDocumento = null;
    private String inscricao = null;
    private String tributoid = null;
    private String situacaodebito = null;
    private String nomeusuariocriacao = null;
    private String dtvencimento = null;
    private String anocompetencia = null;
    private String mescompetencia = null;
    private String vlroriginal = null;
    private String dtcancelamento = null;
    
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
            case "tributoid":
                ret = tributoid;
                break;
            case "situacaodebito":
                ret = situacaodebito;
                break;
            case "nomeusuariocriacao":
                ret = nomeusuariocriacao;
                break;
            case "dtvencimento":
                ret = dtvencimento;
                break;
            case "anocompetencia":
                ret = anocompetencia;
                break;
            case "mescompetencia":
                ret = mescompetencia;
                break;
            case "vlroriginal":
                ret = vlroriginal;
                break;
            case "dtcancelamento":
                ret = dtcancelamento;
                break;
            
        }
        
        return ret;
    }

    @Override
    public void setString(String columnName, String value) {
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
                this.numDocumento = value;
                break;
            case "inscricao":
                this.inscricao = value;
                break;
            case "tributoid":
                this.tributoid = value;
                break;
            case "situacaodebito":
                this.situacaodebito = value;
                break;
            case "nomeusuariocriacao":
                this.nomeusuariocriacao = value;
                break;
            case "dtvencimento":
                this.dtvencimento = value;
                break;
            case "anocompetencia":
                this.anocompetencia = value;
                break;
            case "mescompetencia":
                this.mescompetencia = value;
                break;
            case "vlroriginal":
                this.vlroriginal = value;
                break;
            case "dtcancelamento":
                this.dtcancelamento = value;
                break;
            
        }
    }
    
}
