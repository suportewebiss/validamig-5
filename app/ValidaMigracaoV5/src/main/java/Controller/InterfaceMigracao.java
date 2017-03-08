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
public interface InterfaceMigracao {
    public void addLog(String log);
    public ArrayList<String> getLog();
    public void setExcelRowNumber(Integer row);
    public Integer getExcelRowNumber();
    public String getString(String columnName);
    public String toString();
    public void setString(String columnName, String value);
}
