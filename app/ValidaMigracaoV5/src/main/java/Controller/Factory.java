/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author moises
 */
public class Factory {
    
    public static InterfaceMigracao getInstance(String templateName)
    {
        InterfaceMigracao obj = null;
        switch(templateName.toLowerCase())
        {
            case "atividadelc116r200": obj = new AtividadeLc116(); break;
            case "validapessoajuridicar101": obj = new PessoaJuridica(); break;
            case "validacontadorcontribuinter190": obj = new ContadorContribuinte(); break;
            case "validaemailpessoar120": obj = new EmailPessoa(); break;
            case "validaenderecopessoar110": obj = new EnderecoPessoa(); break;
            case "validasociopessoajuridicar140": obj = new SocioPessoaJuridica(); break;
            case "validatelefonepessoar130": obj = new TelefonePessoa(); break;
            case "validaatividadeeconomicapjr150": obj = new AtividadeEconomicaPj(); break;
            case "validaservicoautorizadocontribuinter180": obj = new ServicoAutorizadoContribuinte(); break;
            case "validapessoafisicar100": obj = new PessoaFisica(); break;
            case "validacontribuinteissqnr170": obj = new ContribuinteIssqn(); break;
            case "validanfser400": obj = new Nfse(); break;
            case "validaranfsr410": obj = new Ranfs(); break;
        }
        
        return obj;
     
    }
}
