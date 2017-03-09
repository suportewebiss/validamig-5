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
public class PessoaFisica implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String nome = null;
    private String dtNascimento = null;
    private String numRegistroclasse = null;
    private String sociedadeClasse = null;

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNumRegistroclasse() {
        return numRegistroclasse;
    }

    public void setNumRegistroclasse(String numRegistroclasse) {
        this.numRegistroclasse = numRegistroclasse;
    }

    public String getSociedadeClasse() {
        return sociedadeClasse;
    }

    public void setSociedadeClasse(String sociedadeClasse) {
        this.sociedadeClasse = sociedadeClasse;
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
    
    @Override
    public String toString()
    {
        // concatena todas as propriedades em uma string (Somente os campos que existem no template)
        return  numDocumento + nome + dtNascimento + numRegistroclasse + sociedadeClasse;
    }

    @Override
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "numdocumento":
                ret = numDocumento;
                break;
            case "nome":
                ret = nome;
                break;
            case "dtnascimento":
                ret = dtNascimento;
                break;
            case "numregistroclasse":
                ret = numRegistroclasse;
                break;
            case "sociedadeclasse":
                ret = sociedadeClasse;
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
            case "nome":
                this.nome = value;
                break;
            case "dtnascimento":
                this.dtNascimento = value;
                break;
            case "numregistroclasse":
                this.numRegistroclasse = value;
                break;
            case "sociedadeclasse":
                this.sociedadeClasse = value;
                break;
        }
    }
    
}
