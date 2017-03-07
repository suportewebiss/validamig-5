/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author moises
 */
public class RunPessoaJuridica 
{
    private ArrayList<Header> header = new ArrayList<Header>();
    private ArrayList<PessoaJuridica> dadosAntigo = new ArrayList<PessoaJuridica>();
    private ArrayList<PessoaJuridica> dadosNovo = new ArrayList<PessoaJuridica>();
    private JLabel progress;
    JSONObject template;
    XSSFWorkbook oldWorkbook;
    XSSFWorkbook newWorkbook;
    public RunPessoaJuridica(FileInputStream streamOldFile, FileInputStream streamNewFile, JSONObject template, JLabel progress)
    {
        this.progress = progress;
        this.template = template;
        // Prepara um arraylist para armazenar o indice de cada coluna
        //System.out.println("Tamanho: " + String.valueOf( template.length() )); System.exit(1);
        Iterator<?> keys = this.template.keys();
        while( keys.hasNext() ) {
            String key = ((String)keys.next()).toLowerCase();
            // Insere os nomes dos headers
            //System.out.println("chave " + key);
            if (! key.equals("templatename"))
                header.add(new Header(key));
        }
       // for(Integer i = 0 ; i < header.size(); i++)
         //   System.out.println(header.get(i).getColumnName());
        
       // Inicia as WorkBooks (Excel) através do ponteiro do stream
        try {
            oldWorkbook = new XSSFWorkbook(streamOldFile);
            newWorkbook = new XSSFWorkbook(streamNewFile);
        } catch (IOException ex) {
            Logger.getLogger(RunPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setColumnIndexHeader();
        
        // verifica se alguma coluna do excel não foi lida para o header
        boolean isHeaderFormated = true;
        for (Integer i=0; i < header.size(); i++)
        {
          //  System.out.println( header.get(i).getColumnName() + ": " +  String.valueOf(  header.get(i).getColumnNumber()) );
            if (header.get(i).getColumnNumber() == null)
                isHeaderFormated = false;
        }
        
        if (isHeaderFormated)
            start();
        else
            JOptionPane.showMessageDialog(null, "Os cabeçalhos da planilha não seguem os nomes do template");
    }
    
    
    private void setColumnIndexHeader()
    {
        XSSFSheet sheetAntigo;
        sheetAntigo = oldWorkbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheetAntigo.iterator();
        Row row = null;
        if (rowIterator.hasNext())
            // Atribui para a variavel row a primeira linha da sheet
            row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        for (Integer i=0; i < header.size(); i++)
        {
            while(cellIterator.hasNext())
            {
                Cell cell = (Cell) cellIterator.next();
               // System.out.println(cell.getStringCellValue());
                if ( cell.getStringCellValue().toLowerCase().equals(header.get(i).getColumnName())  )
                    // Adiciona o numero da coluna ao header
                    header.get(i).setColumnNumber(cell.getColumnIndex());
                
            }
            cellIterator = row.cellIterator();
        }
    }
    
    
    
    private void start()
    {
        
        progress.setText("Iniciando validação");
        new Thread() {
			
            @Override
            public void run() {
               
                // PRCESSA SHEET ANTIGA
                XSSFSheet sheetAntigo;
                sheetAntigo = oldWorkbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheetAntigo.iterator();
                
                // passa para a segunda linha do arquivo, pois a primeira é header
                if (rowIterator.hasNext())
                    rowIterator.next();
                //System.out.println(" iterator " + String.valueOf(rowIterator.hasNext()));
                //System.out.println(" teste " + String.valueOf(header.size()));
                // Executa o loop nas linhas da planilha
                while (rowIterator.hasNext())
                {
                    Row row = rowIterator.next();
                    // atualiza opro
                    progress.setText("Carregando linha "+ String.valueOf(row.getRowNum()) + " de " + String.valueOf(sheetAntigo.getLastRowNum()) + " da planilha antiga" );
                    PessoaJuridica at = new PessoaJuridica();
                    at.setExcelRowNumber(row.getRowNum());
                    
                    for (Integer index = 0; index < header.size(); index++)
                    {
                        // vai pegar o valor no excel de acordo com o header
                        Integer columnPos = header.get(index).getColumnNumber();
                        String columnName = header.get(index).getColumnName().toLowerCase();
                        String value  = null;
                        //System.out.println("Nome da Coluna " + columnName);
                        switch(columnName)
                        {
                            case "numdocumento":
                                value = row.getCell(columnPos).getStringCellValue();
                                //System.out.println("peguei o nome " + value);
                                at.setnumDocumento(value);
                                break;
                            case "tipopessoa":
                                 progress.setText(String.valueOf(columnPos));
                                value = row.getCell(columnPos).getStringCellValue();
                                at.settipoPessoa(value);
                                break;
                            case "razaosocial":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setrazaoSocial(value);
                                break;
                            case "nomefantasia":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setnomeFantasia(value);
                                break;
                            case "situacaopessoa":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setsituacaoPessoa(value);
                                break;
                            case "inscricaoestadual":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setinscricaoEstadual(value);
                                break;
                            case "capitalsocial":
                                double capSoc = row.getCell(columnPos).getNumericCellValue();
                                BigDecimal capSocial = new BigDecimal(capSoc);
                                at.setcapitalSocial(capSocial);
                                break;
                            case "dtaberturaempresa":
                                value = row.getCell(columnPos).getStringCellValue();
                                Calendar cal = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY");
                                {
                                    try {
                                        cal.setTime(sdf.parse(value));
                                    } catch (ParseException ex) {
                                        Logger.getLogger(RunPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                at.setdtAberturaEmpresa(cal);
                                break;
                            case "porteempresa":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setporteEmpresa(value);
                                break;
                            case "indicasubstitutotributario":
                                Double valueint = row.getCell(columnPos).getNumericCellValue();
                                at.setindicaSubstitutoTributario(valueint.intValue());
                                break;
                            case "indicaregimecaixa":
                                Double regCai = row.getCell(columnPos).getNumericCellValue();
                                at.setindicaRegimeCaixa(regCai.intValue());
                                break;
                        }
                    }
                    dadosAntigo.add(at);
                }
                
                 // PRCESSA SHEET NOVA
                XSSFSheet sheetNova;
                sheetNova = newWorkbook.getSheetAt(0);
                rowIterator = sheetNova.iterator();
                
                // passa para a segunda linha do arquivo, pois a primeira é header
                if (rowIterator.hasNext())
                    rowIterator.next();
               
                // Executa o loop nas linhas da planilha
                while (rowIterator.hasNext())
                {
                    Row row = rowIterator.next();
                    // atualiza opro
                    progress.setText("Carregando linha "+ String.valueOf(row.getRowNum()) + " de " + String.valueOf(sheetAntigo.getLastRowNum()) + " da planilha nova");
                    PessoaJuridica at = new PessoaJuridica();
                    at.setExcelRowNumber(row.getRowNum());
                    
                    for (Integer index = 0; index < header.size(); index++)
                    {
                        // vai pegar o valor no excel de acordo com o header
                        Integer columnPos = header.get(index).getColumnNumber();
                        String columnName = header.get(index).getColumnName().toLowerCase();
                        String value  = null;
                        //System.out.println("Coluna " + String.valueOf(columnPos) + " Nome " + columnName);
                        switch(columnName)
                        {
                            case "numdocumento":
                                value = row.getCell(columnPos).getStringCellValue();
                                System.out.println("Posição " + String.valueOf(columnPos) + " " + value);
                                at.setnumDocumento(value);
                                break;
                            case "tipopessoa":
                                 progress.setText(String.valueOf(columnPos));
                                value = row.getCell(columnPos).getStringCellValue();
                                at.settipoPessoa(value);
                                break;
                            case "razaosocial":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setrazaoSocial(value);
                                break;
                            case "nomefantasia":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setnomeFantasia(value);
                                break;
                            case "situacaopessoa":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setsituacaoPessoa(value);
                                break;
                            case "inscricaoestadual":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setinscricaoEstadual(value);
                                break;
                            case "capitalsocial":
                                double capSoc = row.getCell(columnPos).getNumericCellValue();
                                BigDecimal capSocial = new BigDecimal(capSoc);
                                at.setcapitalSocial(capSocial);
                                break;
                            case "dtaberturaempresa":
                                value = row.getCell(columnPos).getStringCellValue();
                                Calendar cal = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY");
                                {
                                    try {
                                        cal.setTime(sdf.parse(value));
                                    } catch (ParseException ex) {
                                        Logger.getLogger(RunPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                at.setdtAberturaEmpresa(cal);
                                break;
                            case "porteempresa":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setporteEmpresa(value);
                                break;
                            case "indicasubstitutotributario":
                                Double valueint = row.getCell(columnPos).getNumericCellValue();
                                at.setindicaSubstitutoTributario(valueint.intValue());
                                break;
                            case "indicaregimecaixa":
                                Double regCai = row.getCell(columnPos).getNumericCellValue();
                                at.setindicaRegimeCaixa(regCai.intValue());
                                break;
                                
                        }
                    }
                    dadosNovo.add(at);
                }
                
                //for (Integer index2 = 0; index2 < dadosNovo.size(); index2++ )
                    //System.out.println(dadosNovo.get(index2).getnumDocumento());
                
                Iterator<PessoaJuridica> iterator = dadosAntigo.iterator();
                while (iterator.hasNext())
                {
                    // Pega o objeto com os dados da planilha antiga
                    PessoaJuridica old = iterator.next();
                    // faz um loop em todos os dados da planilha nova para verificar se encontra
                    // o registro da planilha antiga
                    for (Integer index = 0; index < dadosNovo.size(); index++ )
                    {
                        //System.out.println(old.getnumDocumento());
                        //System.out.println(dadosNovo.get(index).getnumDocumento().trim().toLowerCase());
                        // verifica se o item da antiga é o mesmo da nova a fim de fazer a comparação
                        
                        if (old.getnumDocumento().trim().toLowerCase().equals(dadosNovo.get(index).getnumDocumento().trim().toLowerCase()))
                        {
                           // Encontrado o registro da versão nova a ser comparado
                           // Inicamos a comparacao
                            Boolean successMigrate = true;
                            PessoaJuridica neww = dadosNovo.get(index);
                            // laço no header para pegar os campos a serem comparados
                            for (Integer a = 0; a < header.size(); a++)
                            {
                                String columnName = header.get(a).getColumnName();
                                //System.out.println(template.getJSONArray(columnName));
                                JSONArray arrayJsonParams =  template.getJSONArray(columnName);
                                JSONObject jsonParams = arrayJsonParams.getJSONObject(0);
                                String comparisonType = jsonParams.getString("tipocomparacao");
                                String messageLog = null;
                                
                                // Verifica o tipo de comparação que irá ser feita
                                
                                switch(comparisonType)
                                {
                                    case "igualdade":
                                        boolean success = comparaIgualdade(columnName, old, neww);
                                        if (! success )
                                        {
                                            old.addLog("Linha "+ String.valueOf( old.getExcelRowNumber()) + "; coluna " +columnName + " inválida: valor antigo - " + old.getString(columnName) + "; valor novo - "+ neww.getString(columnName));
                                            successMigrate = false;
                                        }
                                    break;
                                        
                                    case "referencia":
                                        success = comparaReferencia(columnName, old, neww);
                                        if (! success )
                                        {
                                            old.addLog("Linha "+ String.valueOf( old.getExcelRowNumber()) + "; coluna " +columnName + " inválida: valor antigo - " + old.getString(columnName) + "; valor novo - "+ neww.getString(columnName));
                                            successMigrate = false;
                                        }
                                        break;
                                        
                                }
                            }
                            if (successMigrate)
                                old.addLog("Linha "+ String.valueOf( old.getExcelRowNumber()) + "; Migrado com Sucesso");
                            break;
                        }
                    }
                }
                
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Arquivo de LOG");   
                fileChooser.setSelectedFile(new File("log.txt"));
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                File fileToSave = null;
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.getSelectedFile();
                    System.out.println("Salvar arquivo: " + fileToSave.getAbsolutePath());
                }
                String textLog = "";
                for (Integer i = 0; i < dadosAntigo.size(); i++)
                {
                    ArrayList<String> log = dadosAntigo.get(i).getLog();
                    for (Integer a = 0; a < log.size(); a++ )
                    {
                        textLog +=  log.get(a) + System.getProperty("line.separator");
                        System.out.println(log.get(a));
                    }
                }
                
                FileWriter arquivo;
		try {
			arquivo = new FileWriter(new File(fileToSave.getAbsolutePath()));
			arquivo.write(textLog);
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

            }
        }.start();
        
      
        
    }
    
    private boolean comparaIgualdade(String columnName, PessoaJuridica a, PessoaJuridica b)  
    {
        boolean success = true;  
        
        switch (columnName.toLowerCase())
        {
            case "numdocumento":
                    // success = verdadeiro ou falso
                    success = a.getnumDocumento().trim().toLowerCase().equals(b.getnumDocumento().trim().toLowerCase());
                break;
            case "tipopessoa":
                    // success = verdadeiro ou falso
                    success = a.gettipoPessoa().trim().toLowerCase().equals(b.gettipoPessoa().trim().toLowerCase());
                break;
            case "razaosocial":
                    // success = verdadeiro ou falso
                    success = a.getrazaoSocial().trim().toLowerCase().equals(b.getrazaoSocial().trim().toLowerCase());
                break;
            case "nomefantasia":
                    // success = verdadeiro ou falso
                    success = a.getnomeFantasia().trim().toLowerCase().equals(b.getnomeFantasia().trim().toLowerCase());
                break;
            case "inscricaoestadual":
                    // success = verdadeiro ou falso
                    success = a.getinscricaoEstadual().trim().toLowerCase().equals(b.getinscricaoEstadual().trim().toLowerCase());
                break;
            case "capitalsocial":
                // compareTo retorna -1 quando um é menos; 0 quando são iguais e 1 quando um é maior
                Integer compare = a.getcapitalSocial().compareTo(b.getcapitalSocial());
                if (compare != 0)
                    success = false;
                break;
            case "dtaberturaempresa":
                    // success = verdadeiro ou falso
                    success = a.getdtAberturaEmpresa().equals(b.getdtAberturaEmpresa());
                break;
            case "indicasubstitutotributario":
                // compareTo retorna -1 quando um é menos; 0 quando são iguais e 1 quando um é maior
                Integer compareIndSubs = a.getindicaSubstitutoTributario().compareTo(b.getindicaSubstitutoTributario());
                if (compareIndSubs != 0)
                    success = false;
                break; 
            case "indicaregimecaixa":
                // compareTo retorna -1 quando um é menos; 0 quando são iguais e 1 quando um é maior
                Integer compareIndReg = a.getindicaRegimeCaixa().compareTo(b.getindicaRegimeCaixa());
                if (compareIndReg != 0)
                    success = false;
                break;
        }
        return success;
    }
    
    private boolean comparaReferencia(String columnName, PessoaJuridica a, PessoaJuridica b)  
    {
        boolean success = true;
        
        switch (columnName.toLowerCase())
        {
            case "situacaopessoa":
                // success = verdadeiro ou falso
                
                if((a.getsituacaoPessoa().trim().equals("ativo")) & (b.getsituacaoPessoa().trim().equals("ativa")))
                    success = true;
                else
                if(((a.getsituacaoPessoa().trim().equals("baixado")) || (a.getsituacaoPessoa().trim().equals("inativo")) || (a.getsituacaoPessoa().trim().equals("não disponível")) || (a.getsituacaoPessoa().trim().equals("paralisado a revelia")) || (a.getsituacaoPessoa().trim().equals("Paralizado Temporariamente")) || (a.getsituacaoPessoa().trim().equals("Provisório")) || (a.getsituacaoPessoa().trim().equals("Suspenso"))) & (b.getsituacaoPessoa().trim().equals("baixada")))
                    success = true;
                else
                    success = false;
            break;
            case "porteempresa":
                if((a.getporteEmpresa().trim().equals("ge")) & (b.getporteEmpresa().trim().equals("gp")))
                    success = true;
                else
                if(((a.getporteEmpresa().trim().equals("md")) || (a.getporteEmpresa().trim().equals("mg"))) & (b.getporteEmpresa().trim().equals("mp")))
                    success = true;
                else
                if(((a.getporteEmpresa().trim().equals("me")) || (a.getporteEmpresa().trim().equals("nd"))) & (b.getporteEmpresa().trim().equals("me")))
                    success = true;
                else
                if((a.getporteEmpresa().trim().equals("pe")) & (b.getporteEmpresa().trim().equals("epp")))
                    success = true;
                else
                if((a.getporteEmpresa().trim().equals("mei")) & (b.getporteEmpresa().trim().equals("mei")))
                    success = true;
                else
                    success = false;
        }
        
        return success;   
    }
    
}
