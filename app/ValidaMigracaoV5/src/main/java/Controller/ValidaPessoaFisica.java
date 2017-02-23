/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author allanfraga
 */
public class ValidaPessoaFisica implements InterfaceMigracao {
    private ArrayList<String> log = new ArrayList<String>();
    private Integer excelRowNumber = null;
    private String numDocumento = null;
    private String nome = null;
    private Calendar dtNascimento = null;
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

    public Calendar getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Calendar dtNascimento) {
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
    
    public String toString()
    {
        // concatena todas as propriedades em uma string (Somente os campos que existem no template)
        return  numDocumento + nome + 
                (new SimpleDateFormat("dd/MM/yyyy").format(dtNascimento)).toString() + 
                numRegistroclasse + sociedadeClasse;
    }
    
}
