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
import java.util.ArrayList;
import java.util.Iterator;
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
public class RunAtividadeLc116 
{
    private ArrayList<Header> header = new ArrayList<Header>();
    private ArrayList<AtividadeLc116> dadosAntigo = new ArrayList<AtividadeLc116>();
    private ArrayList<AtividadeLc116> dadosNovo = new ArrayList<AtividadeLc116>();
    private JLabel progress;
    JSONObject template;
    XSSFWorkbook oldWorkbook;
    XSSFWorkbook newWorkbook;
    public RunAtividadeLc116(FileInputStream streamOldFile, FileInputStream streamNewFile, JSONObject template, JLabel progress)
    {
        this.progress = progress;
        // Prepara um arraylist para armazenar o indice de cada coluna
        Iterator<?> keys = template.keys();
        while( keys.hasNext() ) {
            String key = ((String)keys.next()).toLowerCase();
            // Insere os nomes dos headers
            if (! key.equals("templatename"))
                header.add(new Header(key));
        }
        
        this.template = template;
        
       // Inicia as WorkBooks (Excel) através do ponteiro do stream
        try {
            oldWorkbook = new XSSFWorkbook(streamOldFile);
            newWorkbook = new XSSFWorkbook(streamNewFile);
        } catch (IOException ex) {
            Logger.getLogger(RunAtividadeLc116.class.getName()).log(Level.SEVERE, null, ex);
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
               
                // Executa o loop nas linhas da planilha
                while (rowIterator.hasNext())
                {
                    Row row = rowIterator.next();
                    // atualiza opro
                    progress.setText("Carregando linha "+ String.valueOf(row.getRowNum()) + " de " + String.valueOf(sheetAntigo.getLastRowNum()) + " da planilha antiga" );
                    AtividadeLc116 at = new AtividadeLc116();
                    at.setExcelRowNumber(row.getRowNum());
                    
                    for (Integer index = 0; index < header.size(); index++)
                    {
                        // vai pegar o valor no excel de acordo com o header
                        Integer columnPos = header.get(index).getColumnNumber();
                        String columnName = header.get(index).getColumnName().toLowerCase();
                        String value  = null;
                        switch(columnName)
                        {
                            case "codigolc116":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setCodigoLc116(value);
                                break;
                            case "descricaoatividade":
                                 progress.setText(String.valueOf(columnPos));
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setDescricaoAtividade(value);
                                break;
                            case "aliquotapadrao":
                                double v = row.getCell(columnPos).getNumericCellValue();
                                BigDecimal aliquota = new BigDecimal(v);
                                at.setAliquotaPadrao(aliquota);
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
                    AtividadeLc116 at = new AtividadeLc116();
                    at.setExcelRowNumber(row.getRowNum());
                    
                    for (Integer index = 0; index < header.size(); index++)
                    {
                        // vai pegar o valor no excel de acordo com o header
                        Integer columnPos = header.get(index).getColumnNumber();
                        String columnName = header.get(index).getColumnName().toLowerCase();
                        String value  = null;
                        switch(columnName)
                        {
                            case "codigolc116":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setCodigoLc116(value);
                                break;
                            case "descricaoatividade":
                                 progress.setText(String.valueOf(columnPos));
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setDescricaoAtividade(value);
                                break;
                            case "aliquotapadrao":
                                double v = row.getCell(columnPos).getNumericCellValue();
                                BigDecimal aliquota = new BigDecimal(v);
                                at.setAliquotaPadrao(aliquota);
                                break;
                                
                        }
                    }
                    dadosNovo.add(at);
                }
                
                Iterator<AtividadeLc116> iterator = dadosAntigo.iterator();
                while (iterator.hasNext())
                {
                    // Pega o objeto com os dados da planilha antiga
                    AtividadeLc116 old = iterator.next();
                    // faz um loop em todos os dados da planilha nova para verificar se encontra
                    // o registro da planilha antiga
                    for (Integer index = 0; index < dadosNovo.size(); index++ )
                    {
                        
                        // verifica se o item da antiga é o mesmo da nova a fim de fazer a comparação
                        if (old.getCodigoLc116().trim().toLowerCase().equals(dadosNovo.get(index).getCodigoLc116().trim().toLowerCase()))
                        {
                           // Encontrado o registro da versão nova a ser comparado
                           // Inicamos a comparacao
                            Boolean successMigrate = true;
                            AtividadeLc116 neww = dadosNovo.get(index);
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
    
    private boolean comparaIgualdade(String columnName, AtividadeLc116 a, AtividadeLc116 b)  
    {
        boolean success = true;  
        
        switch (columnName.toLowerCase())
        {
            case "codigolc116":
                    // success = verdadeiro ou falso
                    success = a.getCodigoLc116().trim().toLowerCase().equals(b.getCodigoLc116().trim().toLowerCase());
                break;
            case "descricaoatividade":
                    // success = verdadeiro ou falso
                    success = a.getDescricaoAtividade().trim().toLowerCase().equals(b.getDescricaoAtividade().trim().toLowerCase());
                break;
            case "aliquotapadrao":
                // compareTo retorna -1 quando um é menos; 0 quando são iguais e 1 quando um é maior
                Integer compare = a.getAliquotaPadrao().compareTo(b.getAliquotaPadrao());
                if (compare != 0)
                    success = false;
                break;
                
        }
        return success;
    }
    
    private boolean comparaReferencia(String columnName, AtividadeLc116 a, AtividadeLc116 b)  
    {
        boolean success = true;  
        
        return success;   
    }
    
}
