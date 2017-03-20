/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.monitorjbl.xlsx.StreamingReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author moises
 */
public class ThreadExcelImport extends Observable implements java.lang.Runnable   {

    public boolean isRunning = false;
    private String messageProgress = null;
    private String templateName;
    private String srcFileP1;
    private String srcFileP2;
    public String threadName = null;
    private ArrayList<Header> headerP1 = null;
    private ArrayList<Header> headerP2 = null;
    private ArrayList<String> colunaChave = new ArrayList<String>();
    private Map<String,InterfaceMigracao> listaP2 = new HashMap<String,InterfaceMigracao>();
    
    
    public ThreadExcelImport(String templateName, String srcFileP1, String srcFileP2, ArrayList<Header> header,  ArrayList<String> colunaChave)
    {
        this.templateName = templateName;
        this.srcFileP1 = srcFileP1;
        this.srcFileP2 = srcFileP2;
        this.headerP1 = new ArrayList<Header>(header);
        this.headerP2 = new ArrayList<Header>(header);
        this.threadName = threadName;
        this.colunaChave = colunaChave;
        
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    
    
    @Override
    public void run()  {
         
       //******
       // CRIA STREAM DAS PLANILHAS
       // *******************
        
       // stream planilha 1
       InputStream stream1 = null;
        try {
            stream1 = new FileInputStream(new File(srcFileP1));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadExcelImport.class.getName()).log(Level.SEVERE, null, ex);
        }
        Workbook workbook1 = StreamingReader.builder()
        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
        .open(stream1); 
        
        
        // stream planilha 2
       InputStream stream2 = null;
        try {
            stream2 = new FileInputStream(new File(srcFileP2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadExcelImport.class.getName()).log(Level.SEVERE, null, ex);
        }
        Workbook workbook2 = StreamingReader.builder()
        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
        .open(stream2); 
        
        
        //******
       // VERIFICA OS CABECALHOS
       // *******************
        
        // cabeçalhos da planilha 1
        Sheet  sheet1 = null;
        sheet1 = workbook1.getSheetAt(0);

        // Pega de acordo com o cabeçalho as opções
        for (Row r : sheet1)
        {
            if (r.getRowNum() > 0) break;
            for (Integer i=0; i < headerP1.size(); i++)
            {
                for (Cell c : r)
                {
                    if ( c.getStringCellValue().toLowerCase().equals(headerP1.get(i).getColumnName().toLowerCase()  )  )
                    {
                        // Adiciona o numero da coluna ao header
                        headerP1.get(i).setColumnNumber(c.getColumnIndex());
                        break;
                    }
                }
                
                if ( headerP1.get(i).getColumnNumber() == null)
                {
                    // Alguma coluna do template está ausente
                    JOptionPane.showMessageDialog(null, "A coluna "+ headerP1.get(i).getColumnName().toLowerCase() + " do template não existe como cabeçalho na planilha 1" );
                    System.exit(0);
                }
                
            }  
        }
        // cabeçalhos da planilha 2
        Sheet  sheet2 = null;
        sheet2 = workbook2.getSheetAt(0);

        // Pega de acordo com o cabeçalho as opções
        for (Row r : sheet2)
        {
            if (r.getRowNum() > 0) break;
            for (Integer i=0; i < headerP2.size(); i++)
            {
                for (Cell c : r)
                {
                    if ( c.getStringCellValue().toLowerCase().equals(headerP2.get(i).getColumnName().toLowerCase()  )  )
                    {
                        // Adiciona o numero da coluna ao header
                        headerP2.get(i).setColumnNumber(c.getColumnIndex());
                        break;
                    }
                }
                
                if ( headerP2.get(i).getColumnNumber() == null)
                {
                    // Alguma coluna do template está ausente
                    JOptionPane.showMessageDialog(null, "A coluna "+ headerP2.get(i).getColumnName().toLowerCase() + " do template não existe como cabeçalho na planilha 2" );
                    System.exit(0);
                }
                
            }  
        }
        
        
        
        //******
        // GRAVA EM MEMÓRIA A PLANILHA 2 PARA EVITAR O REABRIMENTO DA MESMA A CADA ITERAÇÃO DA PLANILHA 1
        // *******************
        stream2 = null;
        try {
            stream2 = new FileInputStream(new File(srcFileP2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadExcelImport.class.getName()).log(Level.SEVERE, null, ex);
        }
         workbook2 = StreamingReader.builder()
                 .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                 .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                 .open(stream2); 
                 
               sheet2 = null;
               sheet2 = workbook2.getSheetAt(0);
               
        
             
            for (Row rowP2 : sheet2) 
            {   
                
                if (rowP2.getRowNum() > 0)
                {
                    InterfaceMigracao objInterfaceP2 =  Factory.getInstance(templateName);
                    
                    // calcula o hash
                    String hashChaveP2 = "";
                    for(String chaveP2 : colunaChave )
                    {
                        Integer columIndex = -1;
                        for (Header he2 : headerP2)
                        {
                            if (he2.getColumnName().equals(chaveP2)   )
                            {
                                columIndex = he2.getColumnNumber();
                                break;
                            }
                        }

                        if (columIndex > -1)
                        {
                            Cell cell = null;
                            cell = rowP2.getCell(columIndex, Row.CREATE_NULL_AS_BLANK );
                           // hashChaveP1 = DigestUtils.sha1Hex(cell.getStringCellValue().trim().toLowerCase() + hashChaveP1 );
                            hashChaveP2 = DigestUtils.sha1Hex(cell.getStringCellValue().trim().toLowerCase() + hashChaveP2 );
                        }

                    }
                    
                    
                    for (Header he2 : headerP2)
                     {

                        
                         Cell cell = rowP2.getCell(he2.getColumnNumber(), Row.CREATE_NULL_AS_BLANK );
                         objInterfaceP2.setString(he2.getColumnName(), cell.getStringCellValue().trim().toLowerCase()  );
                         objInterfaceP2.setExcelRowNumber((rowP2.getRowNum() + 1));
                         //System.out.println("Novo loop HeaderP2 da linhaP2 " + String.valueOf(rowP2.getRowNum()) + " coluna " + he2.getColumnName() );
                     }
                    
                    if (hashChaveP2.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "A linha "+ String.valueOf( ( rowP2.getRowNum()+1)  ) + " da planilha 2 tem as colunas chaves nula"   );
                        System.exit(0);
                    }else
                        listaP2.put(hashChaveP2, objInterfaceP2);
                    
                     
                }
            }
        
        // limpa da memoria a workbook2
        try {
            if (workbook2 != null)
             workbook2.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadExcelImport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // limpa da memoria o stream com workbook2
        if (stream2 != null)
            try {
                stream2.close();
       } catch (IOException ex) {
           Logger.getLogger(ThreadExcelImport.class.getName()).log(Level.SEVERE, null, ex);
       }
            
        
        //******
        // FAZ A VALIDAÇÃO
        // OBSERVE QUE POR TER FEITO O FOREACH NOS PLANILHAS SE TORNA NECESSÁRIO RECRIAR O STREAMING
        // *******************
        
        // Executa o loop nas linhas da planilha
        
        stream1 = null;
        try {
            stream1 = new FileInputStream(new File(srcFileP1));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadExcelImport.class.getName()).log(Level.SEVERE, null, ex);
        }
        workbook1 = StreamingReader.builder()
        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
        .open(stream1); 
        
        sheet1 = null;
        sheet1 = workbook1.getSheetAt(0);
        
        InterfaceMigracao objInterfaceP1 = null;
        
        
        for (Row rowP1 : sheet1) 
        {
           
            
           // Pega o hash dos campos chaves da planilha 1 a fim de localizar na planilha 1
           String hashChaveP1 = "";
           for(String chaveP1 : colunaChave )
           {
               Integer columIndex = -1;
               for (Header he1 : headerP1)
               {
                   if (he1.getColumnName().equals(chaveP1)   )
                   {
                       columIndex = he1.getColumnNumber();
                       break;
                   }
               }
               
               if (columIndex > -1)
               {
                   Cell cell = null;
                   cell = rowP1.getCell(columIndex, Row.CREATE_NULL_AS_BLANK );
                  // hashChaveP1 = DigestUtils.sha1Hex(cell.getStringCellValue().trim().toLowerCase() + hashChaveP1 );
                   hashChaveP1 = DigestUtils.sha1Hex(cell.getStringCellValue().trim().toLowerCase() + hashChaveP1 );
               }
                
           }
           
           
           objInterfaceP1 = Factory.getInstance(templateName);
          // objInterfaceP2 = Factory.getInstance(templateName);
           
           objInterfaceP1.setExcelRowNumber((rowP1.getRowNum() + 1));
           Notify notify = new Notify();
           
           if (hashChaveP1.equals(""))
               notify.setLocalizadoP1(false);
           else
           {
               notify.setLocalizadoP1(true); 
               //seta o numero da linha no excel
               
               // Preenche o objeto de interface da planilha 1 com seus respectivos dados
                for (Header he1 : headerP1)
                {
                    
                    Cell cell = null;
                    cell = rowP1.getCell(he1.getColumnNumber(), Row.CREATE_NULL_AS_BLANK );
                    objInterfaceP1.setString(he1.getColumnName(),  cell.getStringCellValue().trim().toLowerCase()   );
                }
                
               boolean p2Localizado = false;
               
                    // Preenche o objeto de interface da planilha 2 com seus respectivos dados
               if (rowP1.getRowNum() > 0)
               {
                    InterfaceMigracao objInterfaceMigracaoP2 = listaP2.get(hashChaveP1);
                     if (objInterfaceMigracaoP2 != null   )
                     {
                             p2Localizado = true;
                             notify.setEntidadeP2(objInterfaceMigracaoP2);
                     }
              
               }
               notify.setLocalizadoP2(p2Localizado);
               
               
           }
               
               
            isRunning = true;
           
            objInterfaceP1.setExcelRowNumber((rowP1.getRowNum() + 1));
            notify.setEntidadeP1(objInterfaceP1);
            notify.setTotalRow((sheet1.getLastRowNum() + 1) );
            
            notify.setRunning(isRunning);
            notify.setHeaderP1(headerP1);
            notify.setHeaderP2(headerP2);
            
            
            
            setChanged();
            notifyObservers(notify);
           
            
            
        }
        
        isRunning = false;
        // Notifica os observadores de que a execução terminou
        Notify notify = new Notify();
        notify.setRunning(false);
        setChanged();
        notifyObservers(notify);
        listaP2 = null;
       
    }

    private String getStringFromCell(Cell cell)
    {
        int tipo = cell.getCellType();
        String value = "";
         switch(tipo)
        {

            //NUMERIC
            case 0:   
                DataFormatter df = new DataFormatter();
               // value = df.formatCellValue(row.getCell(columnPos));
                value = String.valueOf( cell.getNumericCellValue()).trim().toLowerCase();
                //at.setString(columnName, value);
                break;
             //STRING
            case 1:                             
            value = cell.getStringCellValue().trim().toLowerCase();
            //at.setString(columnName, value);
            break;  
            // FORMULA

            // BLANK
            case 3:
                value = ""; //row.getCell(columnPos).getStringCellValue();
                //at.setString(columnName, value);
                break;
            // BOOLEAN
             case 4:
                value = String.valueOf(cell.getBooleanCellValue()).trim().toLowerCase()  ;
              //  at.setString(columnName, value);
                break;   
             // NONE (ERROR)
            case 5:
                value = cell.getStringCellValue().trim().toLowerCase()  ;
                //at.setString(columnName, value);
                break;

        }
         return value;
               
        //return  String.valueOf( cell.getNumericCellValue());
    }
   

    
    
}
