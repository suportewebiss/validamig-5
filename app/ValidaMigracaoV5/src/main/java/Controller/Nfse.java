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
public class Nfse implements InterfaceMigracao {
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
    private String dataEmissaoNfse = null;
    private String dataEmissaoRps = null;
    private String descontoCondicionado = null;
    private String descontoIncondicionado = null;
    private String discriminacao = null;
    private String emailPrestador = null;
    private String emailTomador = null;
    private String enderecoPrestador = null;
    private String enderecoTomador = null;
    private String exigibilidadeIss = null;
    private String incentivoFiscal = null;
    private String inscricaoMunicipalIntermediario = null;
    private String inscricaoMunicipalTomador = null;
    private String inscricaoPrestador = null;
    private String issRetido = null;
    private String itemListaServico = null;
    private String municipioIncidencia = null;
    private String municipioPrestacaoServico = null;
    private String numeroEnderecoPrestador = null;
    private String numeroEnderecoTomador = null;
    private String numeroNfse = null;
    private String numeroNfseSubstituida = null;
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
    private String statusNfse = null;
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
    private String valorLiquidoNfse = null;
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
            case "ceptomador":  ret = cepTomador;  break;
            case "cidadeintermediario":  ret = cidadeIntermediario;  break;
            case "cidadeprestador":  ret = cidadePrestador;  break;
            case "cidadetomador":  ret = cidadeTomador;  break;
            case "codigocnae":  ret = codigoCnae;  break;
            case "codigomunicipiogerador":  ret = codigoMunicipioGerador;  break;
            case "codigoobra":  ret = codigoObra;  break;
            case "codigotributacaomunicipio":  ret = codigoTributacaoMunicipio;  break;
            case "codigoverificacao":  ret = codigoVerificacao;  break;
            case "competencia":  ret = competencia;  break;
            case "complementoenderecoprestador":  ret = complementoEnderecoPrestador;  break;
            case "complementoenderecotomador":  ret = complementoEnderecoTomador;  break;
            case "cpfcnpjintermediario":  ret = cpfcnpjIntermediario;  break;
            case "cpfcnpjprestador":  ret = cpfcnpjPrestador;  break;
            case "cpfcnpjtomador":  ret = cpfcnpjTomador;  break;
            case "dataemissaonfse":  ret = dataEmissaoNfse;  break;
            case "dataemissaorps":  ret = dataEmissaoRps;  break;
            case "descontocondicionado":  ret = descontoCondicionado;  break;
            case "descontoincondicionado":  ret = descontoIncondicionado;  break;
            case "discriminacao":  ret = discriminacao;  break;
            case "emailprestador":  ret = emailPrestador;  break;
            case "emailtomador":  ret = emailTomador;  break;
            case "enderecoprestador":  ret = enderecoPrestador;  break;
            case "enderecotomador":  ret = enderecoTomador;  break;
            case "exigibilidadeiss":  ret = exigibilidadeIss;  break;
            case "incentivofiscal":  ret = incentivoFiscal;  break;
            case "inscricaomunicipalintermediario":  ret = inscricaoMunicipalIntermediario;  break;
            case "inscricaomunicipaltomador":  ret = inscricaoMunicipalTomador;  break;
            case "inscricaoprestador":  ret = inscricaoPrestador;  break;
            case "issretido":  ret = issRetido;  break;
            case "itemlistaservico":  ret = itemListaServico;  break;
            case "municipioincidencia":  ret = municipioIncidencia;  break;
            case "municipioprestacaoservico":  ret = municipioPrestacaoServico;  break;
            case "numeroenderecoprestador":  ret = numeroEnderecoPrestador;  break;
            case "numeroenderecotomador":  ret = numeroEnderecoTomador;  break;
            case "numeronfse":  ret = numeroNfse;  break;
            case "numeronfsesubstituida":  ret = numeroNfseSubstituida;  break;
            case "numerorps":  ret = numeroRps;  break;
            case "numerorpssubstituido":  ret = numeroRpsSubstituido;  break;
            case "optantesimplesnacional":  ret = optantesimplesnacional;  break;
            case "outrasinformacoes":  ret = outrasInformacoes;  break;
            case "outrasretencoes":  ret = outrasRetencoes;  break;
            case "paisprestacaoservico":  ret = paisPrestacaoServico;  break;
            case "paistomador":  ret = paisTomador;  break;
            case "razaosocialintermediario":  ret = razaoSocialIntermediario;  break;
            case "razaosocialprestador":  ret = razaoSocialPrestador;  break;
            case "razaosocialtomador":  ret = razaoSocialTomador;  break;
            case "regimeespecialtributacao":  ret = regimeEspecialTributacao;  break;
            case "responsavelretencao":  ret = responsavelRetencao;  break;
            case "serierps":  ret = serieRps;  break;
            case "serierpssubstituido":  ret = serieRpsSubstituido;  break;
            case "statusnfse":  ret = statusNfse;  break;
            case "statusrps":  ret = statusRps;  break;
            case "telefoneprestador":  ret = telefonePrestador;  break;
            case "telefonetomador":  ret = telefoneTomador;  break;
            case "tiporps":  ret = tipoRps;  break;
            case "tiporpssubstituido":  ret = tipoRpsSubstituido;  break;
            case "ufdomunicipiogerador":  ret = ufDoMunicipioGerador;  break;
            case "ufprestador":  ret = ufPrestador;  break;
            case "uftomador":  ret = ufTomador;  break;
            case "valorcofins":  ret = valorCofins;  break;
            case "valorcredito":  ret = valorCredito;  break;
            case "valorcsll":  ret = valorCsll;  break;
            case "valordeducoes":  ret = valorDeducoes;  break;
            case "valorinss":  ret = valorInss;  break;
            case "valorir":  ret = valorIr;  break;
            case "valoriss":  ret = valorIss;  break;
            case "valorliquidonfse":  ret = valorLiquidoNfse;  break;
            case "valorpis":  ret = valorPis;  break;
            case "valorservicos":  ret = valorServicos;  break;
             
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
            case "ceptomador":  cepTomador = value;  break;
            case "cidadeintermediario":  cidadeIntermediario = value;  break;
            case "cidadeprestador":  cidadePrestador = value;  break;
            case "cidadetomador":  cidadeTomador = value;  break;
            case "codigocnae":  codigoCnae = value;  break;
            case "codigomunicipiogerador":  codigoMunicipioGerador = value;  break;
            case "codigoobra":  codigoObra = value;  break;
            case "codigotributacaomunicipio":  codigoTributacaoMunicipio = value;  break;
            case "codigoverificacao":  codigoVerificacao = value;  break;
            case "competencia":  competencia = value;  break;
            case "complementoenderecoprestador":  complementoEnderecoPrestador = value;  break;
            case "complementoenderecotomador":  complementoEnderecoTomador = value;  break;
            case "cpfcnpjintermediario":  cpfcnpjIntermediario = value;  break;
            case "cpfcnpjprestador":  cpfcnpjPrestador = value;  break;
            case "cpfcnpjtomador":  cpfcnpjTomador = value;  break;
            case "dataemissaonfse":  dataEmissaoNfse = value;  break;
            case "dataemissaorps":  dataEmissaoRps = value;  break;
            case "descontocondicionado":  descontoCondicionado = value;  break;
            case "descontoincondicionado":  descontoIncondicionado = value;  break;
            case "discriminacao":  discriminacao = value;  break;
            case "emailprestador":  emailPrestador = value;  break;
            case "emailtomador":  emailTomador = value;  break;
            case "enderecoprestador":  enderecoPrestador = value;  break;
            case "enderecotomador":  enderecoTomador = value;  break;
            case "exigibilidadeiss":  exigibilidadeIss = value;  break;
            case "incentivofiscal":  incentivoFiscal = value;  break;
            case "inscricaomunicipalintermediario":  inscricaoMunicipalIntermediario = value;  break;
            case "inscricaomunicipaltomador":  inscricaoMunicipalTomador = value;  break;
            case "inscricaoprestador":  inscricaoPrestador = value;  break;
            case "issretido":  issRetido = value;  break;
            case "itemlistaservico":  itemListaServico = value;  break;
            case "municipioincidencia":  municipioIncidencia = value;  break;
            case "municipioprestacaoservico":  municipioPrestacaoServico = value;  break;
            case "numeroenderecoprestador":  numeroEnderecoPrestador = value;  break;
            case "numeroenderecotomador":  numeroEnderecoTomador = value;  break;
            case "numeronfse":  numeroNfse = value;  break;
            case "numeronfsesubstituida":  numeroNfseSubstituida = value;  break;
            case "numerorps":  numeroRps = value;  break;
            case "numerorpssubstituido":  numeroRpsSubstituido = value;  break;
            case "optantesimplesnacional":  optantesimplesnacional = value;  break;
            case "outrasinformacoes":  outrasInformacoes = value;  break;
            case "outrasretencoes":  outrasRetencoes = value;  break;
            case "paisprestacaoservico":  paisPrestacaoServico = value;  break;
            case "paistomador":  paisTomador = value;  break;
            case "razaosocialintermediario":  razaoSocialIntermediario = value;  break;
            case "razaosocialprestador":  razaoSocialPrestador = value;  break;
            case "razaosocialtomador":  razaoSocialTomador = value;  break;
            case "regimeespecialtributacao":  regimeEspecialTributacao = value;  break;
            case "responsavelretencao":  responsavelRetencao = value;  break;
            case "serierps":  serieRps = value;  break;
            case "serierpssubstituido":  serieRpsSubstituido = value;  break;
            case "statusnfse":  statusNfse = value;  break;
            case "statusrps":  statusRps = value;  break;
            case "telefoneprestador":  telefonePrestador = value;  break;
            case "telefonetomador":  telefoneTomador = value;  break;
            case "tiporps":  tipoRps = value;  break;
            case "tiporpssubstituido":  tipoRpsSubstituido = value;  break;
            case "ufdomunicipiogerador":  ufDoMunicipioGerador = value;  break;
            case "ufprestador":  ufPrestador = value;  break;
            case "uftomador":  ufTomador = value;  break;
            case "valorcofins":  valorCofins = value;  break;
            case "valorcredito":  valorCredito = value;  break;
            case "valorcsll":  valorCsll = value;  break;
            case "valordeducoes":  valorDeducoes = value;  break;
            case "valorinss":  valorInss = value;  break;
            case "valorir":  valorIr = value;  break;
            case "valoriss":  valorIss = value;  break;
            case "valorliquidonfse":  valorLiquidoNfse = value;  break;
            case "valorpis":  valorPis = value;  break;
            case "valorservicos":  valorServicos = value;  break;
             
        }
    }
    
}
