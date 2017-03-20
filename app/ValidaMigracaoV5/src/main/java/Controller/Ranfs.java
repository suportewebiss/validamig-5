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
public class Ranfs implements InterfaceMigracao{
     private Integer excelRowNumber = null;
    private ArrayList<String> log = new ArrayList<String>();
    private String aliquota = null;
    private String artObra = null;  
    private String bairroPrestador = null; 
    private String bairroTomador = null; 
    private String baseCalculo = null;
    private String cepPrestador = null;
    private String cepTomador = null;
    private String cidadeIntermediario = null;
    private String cidadePrestador = null;
    private String cidadeTomador = null;
    private String codigoCnae = null;
    private String codigoMunicipioGerador = null;
    private String codigoObra = null;
    private String codigoTributacaoMunicipio = null;
    private String codigoVerificacao = null;
    private String competencia = null;
    private String complementoEnderecoPrestador = null;
    private String complementoEnderecoTomador = null;
    private String cpfcnpjIntermediario = null;
    private String cpfcnpjPrestador = null;
    private String cpfcnpjTomador = null;
    private String dataEmissaoRanfs = null;
    private String dataEmissaoRps = null;
    private String descontoCondicionado = null;
    private String descontoIncondicionado = null;
    private String discriminacao = null;
    private String emailPrestador = null;
    private String emailTomador = null;
    private String enderecoPrestador = null;
    private String enderecoTomador = null;
    private String exigibilidadeIss = null;
    private String icentivoFiscal = null;
    private String inscricaoMunicipalIntermediario = null;
    private String inscricaoMunicipalTomador = null;
    private String inscricaoPrestador = null;
    private String issRetido = null;
    private String itemListaServico = null;
    private String municipioIncidencia = null;
    private String municipioPrestacaoServico = null;
    private String numeroEnderecoPrestador = null;
    private String numeroEnderecoTomador = null;
    private String numeroRanfs = null;
    private String numeroRanfsSubstituido = null;
    private String numeroRps = null;
    private String numeroRpsSubstituido = null;
    private String optantesimplesnacional = null;
    private String outrasInformacoes = null;
    private String outrasRetencoes = null;
    private String paisPrestacaoServico = null;
    private String paisTomador = null;
    private String razaoSocialIntermediario = null;
    private String razaoSocialPrestador = null;
    private String razaoSocialTomador = null;
    private String regimeEspecialTributacao = null;
    private String responsavelRetencao = null;
    private String serieRps = null;
    private String serieRpsSubstituido = null;
    private String statusRanfs = null;
    private String statusRps = null;
    private String telefonePrestador = null;
    private String telefoneTomador = null;
    private String tipoRps = null;
    private String tipoRpsSubstituido = null;
    private String ufDoMunicipioGerador = null;
    private String ufPrestador = null;
    private String ufTomador = null;
    private String valorCofins = null;
    private String valorCredito = null;
    private String valorCsll = null;
    private String valorDeducoes = null;
    private String valorInss = null;
    private String valorIr = null;
    private String valorIss = null;
    private String valorLiquidoRanfs = null;
    private String valorPis = null;
    private String valorServicos = null;


    
    
    
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
    public String getString(String columnName) {
        String ret = null;
        switch(columnName.toLowerCase())
        {
            case "aliquota": ret = aliquota; break;
            case "artobra":  ret = artObra;  break;
            case "bairroprestador":  ret = bairroPrestador;  break;
            case "bairrotomador":  ret = bairroTomador;  break;
            case "basecalculo":  ret = baseCalculo;  break;
            case "cepprestador":  ret = cepPrestador;  break;
            case "cepTomador":  ret = cepTomador;  break;
            case "cidadeIntermediario":  ret = cidadeIntermediario;  break;
            case "cidadePrestador":  ret = cidadePrestador;  break;
            case "cidadeTomador":  ret = cidadeTomador;  break;
            case "codigoCnae":  ret = codigoCnae;  break;
            case "codigoMunicipioGerador":  ret = codigoMunicipioGerador;  break;
            case "codigoObra":  ret = codigoObra;  break;
            case "codigoTributacaoMunicipio":  ret = codigoTributacaoMunicipio;  break;
            case "codigoVerificacao":  ret = codigoVerificacao;  break;
            case "competencia":  ret = competencia;  break;
            case "complementoEnderecoPrestador":  ret = complementoEnderecoPrestador;  break;
            case "complementoEnderecoTomador":  ret = complementoEnderecoTomador;  break;
            case "cpfcnpjIntermediario":  ret = cpfcnpjIntermediario;  break;
            case "cpfcnpjPrestador":  ret = cpfcnpjPrestador;  break;
            case "cpfcnpjTomador":  ret = cpfcnpjTomador;  break;
            case "dataEmissaoRanfs":  ret = dataEmissaoRanfs;  break;
            case "dataEmissaoRps":  ret = dataEmissaoRps;  break;
            case "descontoCondicionado":  ret = descontoCondicionado;  break;
            case "descontoIncondicionado":  ret = descontoIncondicionado;  break;
            case "discriminacao":  ret = discriminacao;  break;
            case "emailPrestador":  ret = emailPrestador;  break;
            case "emailTomador":  ret = emailTomador;  break;
            case "enderecoPrestador":  ret = enderecoPrestador;  break;
            case "enderecoTomador":  ret = enderecoTomador;  break;
            case "exigibilidadeIss":  ret = exigibilidadeIss;  break;
            case "icentivoFiscal":  ret = icentivoFiscal;  break;
            case "inscricaoMunicipalIntermediario":  ret = inscricaoMunicipalIntermediario;  break;
            case "inscricaoMunicipalTomador":  ret = inscricaoMunicipalTomador;  break;
            case "inscricaoPrestador":  ret = inscricaoPrestador;  break;
            case "issRetido":  ret = issRetido;  break;
            case "itemListaServico":  ret = itemListaServico;  break;
            case "municipioIncidencia":  ret = municipioIncidencia;  break;
            case "municipioPrestacaoServico":  ret = municipioPrestacaoServico;  break;
            case "numeroEnderecoPrestador":  ret = numeroEnderecoPrestador;  break;
            case "numeroEnderecoTomador":  ret = numeroEnderecoTomador;  break;
            case "numeroRanfs":  ret = numeroRanfs;  break;
            case "numeroRanfsSubstituida":  ret = numeroRanfsSubstituido;  break;
            case "numeroRps":  ret = numeroRps;  break;
            case "numeroRpsSubstituido":  ret = numeroRpsSubstituido;  break;
            case "optantesimplesnacional":  ret = optantesimplesnacional;  break;
            case "outrasInformacoes":  ret = outrasInformacoes;  break;
            case "outrasRetencoes":  ret = outrasRetencoes;  break;
            case "paisPrestacaoServico":  ret = paisPrestacaoServico;  break;
            case "paisTomador":  ret = paisTomador;  break;
            case "razaoSocialIntermediario":  ret = razaoSocialIntermediario;  break;
            case "razaoSocialPrestador":  ret = razaoSocialPrestador;  break;
            case "razaoSocialTomador":  ret = razaoSocialTomador;  break;
            case "regimeEspecialTributacao":  ret = regimeEspecialTributacao;  break;
            case "responsavelRetencao":  ret = responsavelRetencao;  break;
            case "serieRps":  ret = serieRps;  break;
            case "serieRpsSubstituido":  ret = serieRpsSubstituido;  break;
            case "statusRanfs":  ret = statusRanfs;  break;
            case "statusRps":  ret = statusRps;  break;
            case "telefonePrestador":  ret = telefonePrestador;  break;
            case "telefoneTomador":  ret = telefoneTomador;  break;
            case "tipoRps":  ret = tipoRps;  break;
            case "tipoRpsSubstituido":  ret = tipoRpsSubstituido;  break;
            case "ufDoMunicipioGerador":  ret = ufDoMunicipioGerador;  break;
            case "ufPrestador":  ret = ufPrestador;  break;
            case "ufTomador":  ret = ufTomador;  break;
            case "valorCofins":  ret = valorCofins;  break;
            case "valorCredito":  ret = valorCredito;  break;
            case "valorCsll":  ret = valorCsll;  break;
            case "valorDeducoes":  ret = valorDeducoes;  break;
            case "valorInss":  ret = valorInss;  break;
            case "valorIr":  ret = valorIr;  break;
            case "valorIss":  ret = valorIss;  break;
            case "valorLiquidoRanfs":  ret = valorLiquidoRanfs;  break;
            case "valorPis":  ret = valorPis;  break;
            case "valorServicos":  ret = valorServicos;  break;
             
        }
        
        return ret;
    }

    @Override
    public void setString(String columnName, String value) {
        switch(columnName.toLowerCase())
        {
            case "aliquota": aliquota = value; break;
            case "artobra":  artObra = value;  break;
            case "bairroprestador": bairroPrestador = value;  break;
            case "bairrotomador": bairroTomador = value;  break;
            case "basecalculo": baseCalculo = value;  break;
            case "cepprestador":  cepPrestador = value;  break;
            case "cepTomador":  cepTomador = value;  break;
            case "cidadeIntermediario":  cidadeIntermediario = value;  break;
            case "cidadePrestador":  cidadePrestador = value;  break;
            case "cidadeTomador":  cidadeTomador = value;  break;
            case "codigoCnae":  codigoCnae = value;  break;
            case "codigoMunicipioGerador":  codigoMunicipioGerador = value;  break;
            case "codigoObra":  codigoObra = value;  break;
            case "codigoTributacaoMunicipio":  codigoTributacaoMunicipio = value;  break;
            case "codigoVerificacao":  codigoVerificacao = value;  break;
            case "competencia":  competencia = value;  break;
            case "complementoEnderecoPrestador":  complementoEnderecoPrestador = value;  break;
            case "complementoEnderecoTomador":  complementoEnderecoTomador = value;  break;
            case "cpfcnpjIntermediario":  cpfcnpjIntermediario = value;  break;
            case "cpfcnpjPrestador":  cpfcnpjPrestador = value;  break;
            case "cpfcnpjTomador":  cpfcnpjTomador = value;  break;
            case "dataEmissaoRanfs":  dataEmissaoRanfs = value;  break;
            case "dataEmissaoRps":  dataEmissaoRps = value;  break;
            case "descontoCondicionado":  descontoCondicionado = value;  break;
            case "descontoIncondicionado":  descontoIncondicionado = value;  break;
            case "discriminacao":  discriminacao = value;  break;
            case "emailPrestador":  emailPrestador = value;  break;
            case "emailTomador":  emailTomador = value;  break;
            case "enderecoPrestador":  enderecoPrestador = value;  break;
            case "enderecoTomador":  enderecoTomador = value;  break;
            case "exigibilidadeIss":  exigibilidadeIss = value;  break;
            case "icentivoFiscal":  icentivoFiscal = value;  break;
            case "inscricaoMunicipalIntermediario":  inscricaoMunicipalIntermediario = value;  break;
            case "inscricaoMunicipalTomador":  inscricaoMunicipalTomador = value;  break;
            case "inscricaoPrestador":  inscricaoPrestador = value;  break;
            case "issRetido":  issRetido = value;  break;
            case "itemListaServico":  itemListaServico = value;  break;
            case "municipioIncidencia":  municipioIncidencia = value;  break;
            case "municipioPrestacaoServico":  municipioPrestacaoServico = value;  break;
            case "numeroEnderecoPrestador":  numeroEnderecoPrestador = value;  break;
            case "numeroEnderecoTomador":  numeroEnderecoTomador = value;  break;
            case "numeroRanfs":  numeroRanfs = value;  break;
            case "numeroRanfsSubstituida":  numeroRanfsSubstituido = value;  break;
            case "numeroRps":  numeroRps = value;  break;
            case "numeroRpsSubstituido":  numeroRpsSubstituido = value;  break;
            case "optantesimplesnacional":  optantesimplesnacional = value;  break;
            case "outrasInformacoes":  outrasInformacoes = value;  break;
            case "outrasRetencoes":  outrasRetencoes = value;  break;
            case "paisPrestacaoServico":  paisPrestacaoServico = value;  break;
            case "paisTomador":  paisTomador = value;  break;
            case "razaoSocialIntermediario":  razaoSocialIntermediario = value;  break;
            case "razaoSocialPrestador":  razaoSocialPrestador = value;  break;
            case "razaoSocialTomador":  razaoSocialTomador = value;  break;
            case "regimeEspecialTributacao":  regimeEspecialTributacao = value;  break;
            case "responsavelRetencao":  responsavelRetencao = value;  break;
            case "serieRps":  serieRps = value;  break;
            case "serieRpsSubstituido":  serieRpsSubstituido = value;  break;
            case "statusRanfs":  statusRanfs = value;  break;
            case "statusRps":  statusRps = value;  break;
            case "telefonePrestador":  telefonePrestador = value;  break;
            case "telefoneTomador":  telefoneTomador = value;  break;
            case "tipoRps":  tipoRps = value;  break;
            case "tipoRpsSubstituido":  tipoRpsSubstituido = value;  break;
            case "ufDoMunicipioGerador":  ufDoMunicipioGerador = value;  break;
            case "ufPrestador":  ufPrestador = value;  break;
            case "ufTomador":  ufTomador = value;  break;
            case "valorCofins":  valorCofins = value;  break;
            case "valorCredito":  valorCredito = value;  break;
            case "valorCsll":  valorCsll = value;  break;
            case "valorDeducoes":  valorDeducoes = value;  break;
            case "valorInss":  valorInss = value;  break;
            case "valorIr":  valorIr = value;  break;
            case "valorIss":  valorIss = value;  break;
            case "valorLiquidoRanfs":  valorLiquidoRanfs = value;  break;
            case "valorPis":  valorPis = value;  break;
            case "valorServicos":  valorServicos = value;  break;
             
        }
    }
}
