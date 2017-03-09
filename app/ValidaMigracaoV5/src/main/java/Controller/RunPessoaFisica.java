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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author allanfraga
 */
public class RunPessoaFisica 
{
    private ArrayList<Header> header = new ArrayList<Header>();
    private ArrayList<PessoaFisica> dadosAntigo = new ArrayList<PessoaFisica>();
    private ArrayList<PessoaFisica> dadosNovo = new ArrayList<PessoaFisica>();
    private JLabel progress;
    private String ColunaChave;
    JSONObject template;
    XSSFWorkbook oldWorkbook;
    XSSFWorkbook newWorkbook;
    public RunPessoaFisica(FileInputStream streamOldFile, FileInputStream streamNewFile, JSONObject template, JLabel progress)
    {
        this.progress = progress;
        ColunaChave = template.getString("colunachave");       
        // Prepara um arraylist para armazenar o indice de cada coluna
        Iterator<?> keys = template.keys();
        while( keys.hasNext() ) {
            String key = ((String)keys.next()).toLowerCase();
            // Insere os nomes dos headers
            if (! key.equals("templatename") && !key.equals("colunachave"))
                header.add(new Header(key));
        }
        
        this.template = template;
        
       // Inicia as WorkBooks (Excel) através do ponteiro do stream
        try {
            oldWorkbook = new XSSFWorkbook(streamOldFile);
            newWorkbook = new XSSFWorkbook(streamNewFile);
        } catch (IOException ex) {
            Logger.getLogger(RunPessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
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
                    PessoaFisica at = new PessoaFisica();
                    at.setExcelRowNumber(row.getRowNum());
                    
                    for (Integer index = 0; index < header.size(); index++)
                    {
                        // vai pegar o valor no excel de acordo com o header
                        Integer columnPos = header.get(index).getColumnNumber();
                        String columnName = header.get(index).getColumnName().toLowerCase();
                        String value  = null;
                        CellType typeCell = row.getCell(columnPos).getCellTypeEnum();
                        
                        switch(typeCell.name())
                        {
                            case "_NONE":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setString(columnName, value);
                                break;
                            case "BLANK":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setString(columnName, value);
                                break;
                            case "BOOLEAN":
                                value = String.valueOf(row.getCell(columnPos).getBooleanCellValue());
                                at.setString(columnName, value);
                                break;
                            case "NUMERIC":   
                                DataFormatter df = new DataFormatter();
                                value = df.formatCellValue(row.getCell(columnPos));
                                at.setString(columnName, value);
                                break;
                            case "STRING":                             
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setString(columnName, value);
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
                    PessoaFisica at = new PessoaFisica();
                    at.setExcelRowNumber(row.getRowNum());
                    
                    for (Integer index = 0; index < header.size(); index++)
                    {
                        // vai pegar o valor no excel de acordo com o header
                        Integer columnPos = header.get(index).getColumnNumber();
                        String columnName = header.get(index).getColumnName().toLowerCase();
                        String value  = null;
                        CellType typeCell = row.getCell(columnPos).getCellTypeEnum();
                        
                        switch(typeCell.name())
                        {
                            case "_NONE":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setString(columnName, value);
                                break;
                            case "BLANK":
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setString(columnName, value);
                                break;
                            case "BOOLEAN":
                                value = String.valueOf(row.getCell(columnPos).getBooleanCellValue());
                                at.setString(columnName, value);
                                break;
                            case "NUMERIC":    
                                DataFormatter df = new DataFormatter();
                                value = df.formatCellValue(row.getCell(columnPos));
                                at.setString(columnName, value);
                                break;
                            case "STRING":                             
                                value = row.getCell(columnPos).getStringCellValue();
                                at.setString(columnName, value);
                                break;        
                        }
                    }
                    dadosNovo.add(at);
                }
                
                Iterator<PessoaFisica> iterator = dadosAntigo.iterator();
                while (iterator.hasNext())
                {
                    // Pega o objeto com os dados da planilha antiga
                    PessoaFisica old = iterator.next();
                    // faz um loop em todos os dados da planilha nova para verificar se encontra
                    // o registro da planilha antiga
                    boolean indicaMigrado = false;
                    for (Integer index = 0; index < dadosNovo.size(); index++ )
                    {
                        
                        // verifica se o item da antiga é o mesmo da nova a fim de fazer a comparação
                        if (old.getNumDocumento().trim().toLowerCase().equals(dadosNovo.get(index).getNumDocumento().trim().toLowerCase()))
                        {
                           // Encontrado o registro da versão nova a ser comparado
                           // Inicamos a comparacao
                            indicaMigrado = true;
                            Boolean successMigrate = true;
                            PessoaFisica neww = dadosNovo.get(index);
                            // laço no header para pegar os campos a serem comparados
                            for (Integer a = 0; a < header.size(); a++)
                            {
                                String columnName = header.get(a).getColumnName();
                                //System.out.println(template.getJSONArray(columnName));
                                JSONArray arrayJsonParams =  template.getJSONArray(columnName);
                                JSONObject jsonParams = arrayJsonParams.getJSONObject(0);
                                String comparisonType = jsonParams.getString("tipocomparacao");
                              //  String messageLog = null;
                                
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
                    if (indicaMigrado == false)
                        old.addLog("Linha "+ String.valueOf( old.getExcelRowNumber()) + "; Não Migrado");
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
    
    private boolean comparaIgualdade(String columnName, PessoaFisica a, PessoaFisica b)  
    {

       return a.getString(columnName).trim().toLowerCase().equals(b.getString(columnName).trim().toLowerCase());

    }
    
    private boolean comparaReferencia(String columnName, PessoaFisica a, PessoaFisica b)  
    {
        boolean success = true;  
        
        // Obtem do template um array para o valorantigo e outro array para o valornovo
        JSONArray arrayJsonParams =  template.getJSONArray(columnName);
        JSONObject jsonParams = arrayJsonParams.getJSONObject(0);
        JSONArray arValorAntigo = jsonParams.getJSONArray("valorantigo");
        JSONArray arValorNovo = jsonParams.getJSONArray("valornovo");
        
        // localiza em qual indice do array antigo o valor da planilha está
        Integer indice = -1;
        for (Integer i = 0; i < arValorAntigo.length(); i++)
            if (arValorAntigo.getString(i).toLowerCase().equals(a.getString(columnName).trim().toLowerCase()))
            {
                indice = i;
                break;
            }
        String va = a.getString(columnName).trim().toLowerCase();
        String vn = b.getString(columnName).trim().toLowerCase();
        
        if (indice == -1)
            // Se não localizar o indice no valorantigo do template significa que o valor na planilha antiga não
           // foi especificado no template
            success = false;
        else
        {
            // pega o valor da planilha nova e compara com o valornovo do template
            if ( b.getString(columnName).trim().toLowerCase().equals(arValorNovo.getString(indice).toLowerCase() ) )
                success = true;
            else
                success = false;
        }
        
        
        return success;   
    }
    
}
