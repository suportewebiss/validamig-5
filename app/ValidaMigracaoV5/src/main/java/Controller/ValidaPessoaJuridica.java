/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Calendar;

/**
 *
 * @author viniciussilva
 */
public class ValidaPessoaJuridica implements InterfaceMigracao{
    private Integer excelRowNumber = null;
    private String NumDocumento = null;
    private String TipoPessoa = null;
    private String RazaoSocial = null;
    private String NomeFantasia = null;
    private String SituacaoPessoa = null;
    private String InscricaoEstadual = null;
    private Integer CapitalSocial = null;
    private Calendar DtAberturaEmpresa = null;
    private String PorteEmpresa = null;
    private Integer IndicaSubstitutoTributario = null;
    private Integer IndicaRegimeCaixa = null;
  

    public String getNumDocumento() {
        return NumDocumento;
    }

    public void setNumDocumento(String NumDocumento) {
        this.NumDocumento = NumDocumento;
    }

    public String getTipoPessoa() {
        return TipoPessoa;
    }

    public void setTipoPessoa(String TipoPessoa) {
        this.TipoPessoa = TipoPessoa;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String NomeFantasia) {
        this.NomeFantasia = NomeFantasia;
    }

    public String getSituacaoPessoa() {
        return SituacaoPessoa;
    }

    public void setSituacaoPessoa(String SituacaoPessoa) {
        this.SituacaoPessoa = SituacaoPessoa;
    }

    public String getInscricaoEstadual() {
        return InscricaoEstadual;
    }

    public void setInscricaoEstadual(String InscricaoEstadual) {
        this.InscricaoEstadual = InscricaoEstadual;
    }

    public Integer getCapitalSocial() {
        return CapitalSocial;
    }

    public void setCapitalSocial(Integer CapitalSocial) {
        this.CapitalSocial = CapitalSocial;
    }

    public Calendar getDtAberturaEmpresa() {
        return DtAberturaEmpresa;
    }

    public void setDtAberturaEmpresa(Calendar DtAberturaEmpresa) {
        this.DtAberturaEmpresa = DtAberturaEmpresa;
    }

    public String getPorteEmpresa() {
        return PorteEmpresa;
    }

    public void setPorteEmpresa(String PorteEmpresa) {
        this.PorteEmpresa = PorteEmpresa;
    }

    public Integer getIndicaSubstitutoTributario() {
        return IndicaSubstitutoTributario;
    }

    public void setIndicaSubstitutoTributario(Integer IndicaSubstitutoTributario) {
        this.IndicaSubstitutoTributario = IndicaSubstitutoTributario;
    }

    public Integer getIndicaRegimeCaixa() {
        return IndicaRegimeCaixa;
    }

    public void setIndicaRegimeCaixa(Integer IndicaRegimeCaixa) {
        this.IndicaRegimeCaixa = IndicaRegimeCaixa;
    }

    public String getNaturezaJuridica() {
        return NaturezaJuridica;
    }

    public void setNaturezaJuridica(String NaturezaJuridica) {
        this.NaturezaJuridica = NaturezaJuridica;
    }

    

    @Override
    public void setExcelRowNumber(Integer row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getExcelRowNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public String toString()
    {
        // concatena todas as propriedades em uma string (Somente os campos que existem no template)
        return  NumDocumento + TipoPessoa + RazaoSocial + NomeFantasia + SituacaoPessoa + InscricaoEstadual +
                (new DecimalFormat("#0.##").format(CapitalSocial)).toString() + 
                (new SimpleDateFormat("dd/MM/yyyy").format(DtAberturaEmpresa)).toString() + 
                PorteEmpresa + (new DecimalFormat("#0.##").format(IndicaSubstitutoTributario)).toString() +
                (new DecimalFormat("#0.##").format(IndicaRegimeCaixa)).toString();
    }
    
}
