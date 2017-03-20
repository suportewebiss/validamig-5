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
public class Notify {
    //private Integer numRow = null;
    private boolean running = false;
    private boolean localizadoP1 = false;
    private boolean localizadoP2 = false;
    private int totalRow = 0;

//  private String name = "";
    private InterfaceMigracao entidadeP1 = null;
    private InterfaceMigracao entidadeP2 = null;
    private ArrayList<Header> headerP1 = new ArrayList<Header>();
    private ArrayList<Header> headerP2 = new ArrayList<Header>();
    
    
    
   /*
    public Integer getnumRow() {
        return numRow;
    }

    public void setnumRow(Integer numRow) {
        this.numRow = numRow;
    }
    */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean finalizado) {
        this.running = finalizado;
    }

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
*/
    public InterfaceMigracao getEntidadeP1() {
        return entidadeP1;
    }

    public void setEntidadeP1(InterfaceMigracao entidade) {
        this.entidadeP1 = entidade;
    }
    
    public InterfaceMigracao getEntidadeP2() {
        return entidadeP2;
    }

    public void setEntidadeP2(InterfaceMigracao entidade) {
        this.entidadeP2 = entidade;
    }

    public ArrayList<Header> getHeaderP1() {
        return headerP1;
    }

    public void setHeaderP1(ArrayList<Header> header) {
        this.headerP1 = new ArrayList<Header>(header);
    }
    
    public ArrayList<Header> getHeaderP2() {
        return headerP2;
    }

    public void setHeaderP2(ArrayList<Header> header) {
        this.headerP2 = new ArrayList<Header>(header);
    }

    public boolean isLocalizadoP1() {
        return localizadoP1;
    }

    public void setLocalizadoP1(boolean localizadoP1) {
        this.localizadoP1 = localizadoP1;
    }

    public boolean isLocalizadoP2() {
        return localizadoP2;
    }

    public void setLocalizadoP2(boolean localizadoP2) {
        this.localizadoP2 = localizadoP2;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
    
    
    
}
