/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.impl.StreamingCell;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author moises
 */
public class Runnable implements Observer {
    private ArrayList<Header> header = new ArrayList<Header>();
    private ArrayList<Header> headerP1 = new ArrayList<Header>();
    private ArrayList<Header> headerP2 = new ArrayList<Header>();
    private boolean runningThreadP1 = false;
    private boolean runningThreadP2 = false;
    private ArrayList<InterfaceMigracao> dadosAntigo;
    private ArrayList<InterfaceMigracao> dadosNovo;
    private JLabel progress;
    private ArrayList<String> colunaChave = new ArrayList<String>();
    private String templateName;
    private JSONObject template;
    private XSSFWorkbook oldWorkbook;
    private XSSFWorkbook newWorkbook;
    
    
    private static String TMP_DIRECTORY;
    private static String FILE_LOG_NAME;
    private FileOutputStream outputStream = null;
    private Thread thread = null;
    public Runnable(String srcOldFile, String srcNewFile, String srcTemplate, JLabel progress)
    {
        // Cria o diretório tmp
        File f = new File(   System.getProperty("user.dir") + File.separator + "tmp"  );  
        if (! f.exists())
            f.mkdir();
        
        // seta o diretório tmp
        Runnable.TMP_DIRECTORY = f.getAbsolutePath();
        
        
        this.dadosNovo = new ArrayList<InterfaceMigracao>();
        this.dadosAntigo = new ArrayList<InterfaceMigracao>();
        this.progress = progress;
        
        //*****
         // Pega o nome do template
        
         progress.setText("Carregando arquivo de template...");
        File templateFile = new File(srcTemplate);
        try {
             String txt = new String(Files.readAllBytes(templateFile.toPath()));
             //System.out.println(txt);  
             template =  new JSONObject(txt);
        } catch (IOException ex) {
            Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // Como a coluna chave pode ser uma combinação
        // o trecho abaixo adicionará todos os campos chaves em um arraylist
        String aux = null;
        if (template.get("colunachave") instanceof String )
            aux =  template.getString("colunachave");
        else
            aux = template.getJSONArray("colunachave").toString();
      
        if ( aux.indexOf("[") < 0 )
            // significa que não é um array
            colunaChave.add(template.getString("colunachave"));
        else
        {
            JSONArray ja = new JSONArray(aux);
            for (Integer i = 0; i < ja.length(); i++)
                colunaChave.add(ja.getString(i));
        }
        
        templateName = template.getString("templatename");
        // Prepara um arraylist para armazenar o indice de cada coluna
        Iterator<?> keys = template.keys();
        while( keys.hasNext() ) {
            String key = ((String)keys.next()).toLowerCase();
            
            // Insere os nomes dos headers
            if (! key.equals("templatename") && !key.equals("colunachave"))
                header.add(new Header(key));
        }
        
        this.template = template;
        // ***********************
        
        
        
        
        //******
        // cria um stream para um arquivo de log temporario
        
        File tmpFile = new File(Runnable.TMP_DIRECTORY + File.separator + "log.txt"  );                
        try {
            outputStream =  new FileOutputStream(tmpFile);
            Runnable.FILE_LOG_NAME = tmpFile.getAbsolutePath();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir um stream com o arquivo de log");
            Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        progress.setText("Iniciando processamento...");
        
        ThreadExcelImport tRunnable = new ThreadExcelImport(templateName, srcOldFile, srcNewFile, header, colunaChave);
        
        
      //  Observable.fromCallable(tRunnableOld);
        tRunnable.addObserver(this);
       
        thread = new Thread(tRunnable, "thread-importa-excel");
        
        
       // teste(srcOldFile);
        
        try
        {    
            
            thread.start();
   
        } catch(Exception e){
           e.printStackTrace();
        }
        
      
      
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
        
        Notify n = (Notify) arg;
        if (n != null)
        {
              if (! n.isRunning())
              {
                  progress.setText("Processamento finalizado");
              }else
              {
                  // se a thread estiver correndo
                if (! n.isLocalizadoP1())
                    escreveLog("Linha: "+ String.valueOf(n.getEntidadeP1().getExcelRowNumber()) + "Não foram identificado as chaves da planilha 1");
                else
                {
                    
                    if (! n.isLocalizadoP2())
                        escreveLog("Linha: "+ String.valueOf(n.getEntidadeP1().getExcelRowNumber()) + " da planilha 1. Não migrado");
                    else
                    {
                       // Se P2 foi localizado 
                        progress.setText("Validando linha "+ String.valueOf(n.getEntidadeP1().getExcelRowNumber()  )+ " de " + String.valueOf( n.getTotalRow()) + " da planilha 1 com linha " + String.valueOf( n.getEntidadeP2().getExcelRowNumber() ) + " da planilha 2" );
                        for (Header he : header)
                        {
                            //**** para cada posição do header verifica se estão iguais
                            
                            // Identifica o tipo de comparação a ser feita
                            String columnName = he.getColumnName();
                            //System.out.println(template.getJSONArray(columnName));
                            JSONArray arrayJsonParams =  template.getJSONArray(columnName);
                            JSONObject jsonParams = arrayJsonParams.getJSONObject(0);
                            String comparisonType = jsonParams.getString("tipocomparacao");
                            InterfaceMigracao objInterfaceP1 = n.getEntidadeP1();
                            InterfaceMigracao objInterfaceP2 = n.getEntidadeP2();
                            boolean success = false;
                            switch(comparisonType)
                            {
                                case "igualdade":
                                     success = comparaIgualdade(columnName, objInterfaceP1, objInterfaceP2);
                                    if (! success )
                                    {
                                        escreveLog("Linha: "+ String.valueOf( objInterfaceP1.getExcelRowNumber()) + " | coluna: " +columnName + " | log:  valor antigo: " + objInterfaceP1.getString(columnName) + " | valor novo: "+ objInterfaceP2.getString(columnName));
                                        System.out.println("Linha: "+ String.valueOf( objInterfaceP1.getExcelRowNumber()) + " migrada com erros na coluna " + columnName);
                                    }
                                break;

                                case "referencia":
                                    success = comparaReferencia(columnName, objInterfaceP1, objInterfaceP2);
                                    if (! success )
                                    {
                                        escreveLog("Linha: "+ String.valueOf( objInterfaceP1.getExcelRowNumber()) + " | coluna: " +columnName + " | log:  valor antigo: " + objInterfaceP1.getString(columnName) + " | valor novo: "+ objInterfaceP2.getString(columnName));
                                        System.out.println("Linha: "+ String.valueOf( objInterfaceP1.getExcelRowNumber()) + " migrada com erros na coluna " + columnName);
                                    }
                                    break;

                            }
                            
                        }
                    }
                }
                
              }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Uma das threads de leitura dos arquivos excel não se apresentou. Contate o suporte");
            System.exit(0);
        }
        
        if (n != null && n.isRunning() == false  )
        {
            thread.interrupt();
            closeOutputStream();
        }
    
    }
    
   
    
    
    private void escreveLog(String message)
    {
        if (outputStream == null)
        {
            JOptionPane.showMessageDialog(null, "Não é possível escrever no arquivo de log, o outputstream é null. Contate o suporte");
            System.out.println("Não é possível escrever no arquivo de log, o outputstream é null. Contate o suporte");
        }else
        {
            message += System.getProperty("line.separator");
            byte[] strToBytes = message.getBytes();
            try {
                outputStream.write(strToBytes);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Exception ao escrever a mensagem de log: " + message   );
                Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } 
           
    }
    
    private void closeOutputStream()
    {
        
        try {
                outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (Runnable.FILE_LOG_NAME == null)
        {
            JOptionPane.showMessageDialog(null, "Não é possível salvar o log. O path não está definido. Contate o suporte");
            System.out.println("Não é possível salvar o log. O path não está definido. Contate o suporte");
        }else
        {
            
            
            // Pergunta onde quer salvar o arquivo de log temporario
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Arquivo de LOG");   
            fileChooser.setSelectedFile(new File("log.txt"));
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            
            String pathNewFile = "";
            File fileToSave = null;
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fileChooser.getSelectedFile();
                System.out.println("Salvar arquivo: " + fileToSave.getAbsolutePath());
            }
            
            File tmpFile = new File(Runnable.FILE_LOG_NAME);
            
            FileInputStream in = null;
            FileOutputStream out = null;
            if (! fileToSave.exists() )
            {
                try {
                    fileToSave.createNewFile();
                    in = new FileInputStream(tmpFile);
                    out = new FileOutputStream(fileToSave);
                    // buffer para transportar os dados
                    byte[] buffer = new byte[1024];
                    int length;
                    // enquanto tiver dados para ler..
                    while((length = in.read(buffer)) > 0 ){
                        // escreve no novo arquivo
                        out.write(buffer, 0 , length);
                    }
                     JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso");
                    
                } catch (IOException ex) {
                    Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
               // arquivo = new FileWriter(new File(fileToSave.getAbsolutePath()));
                Object[] options = { "Confirmar", "Cancelar" };
                
                 if ( JOptionPane.showOptionDialog(null, "O arquivo já existe. Deseja substituir?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0 ) 
                 {
                     try {
                        fileToSave.createNewFile();
                        in = new FileInputStream(tmpFile);
                        out = new FileOutputStream(fileToSave);
                        // buffer para transportar os dados
                        byte[] buffer = new byte[1024];
                        int length;
                        // enquanto tiver dados para ler..
                        while((length = in.read(buffer)) > 0 ){
                            // escreve no novo arquivo
                            out.write(buffer, 0 , length);
                        }
                        JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso");
                    } catch (IOException ex) {
                        Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }

            }
            
            try {
                if (in != null)
                   in.close();
                if (out != null)
                   out.close();
            } catch (IOException ex) {
                Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // deleta o arquivo de log temporario
            if (tmpFile.exists())
                tmpFile.delete();
            
        }
        
        
    }
   
    
    
    private boolean comparaIgualdade(String columnName, InterfaceMigracao a, InterfaceMigracao b)  
    {
       // System.out.println("Compara Igualdade " + columnName + "; valor antigo " + a.getString(columnName) + "; valor novo " + b.getString(columnName) );
       return a.getString(columnName).trim().toLowerCase().equals(b.getString(columnName).trim().toLowerCase());

    }
    
    private boolean comparaReferencia(String columnName, InterfaceMigracao a, InterfaceMigracao b)  
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
