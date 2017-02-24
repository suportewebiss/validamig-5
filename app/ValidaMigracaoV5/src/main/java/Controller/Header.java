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
public class Header {
    private String columnName;
    private Integer columnNumber;
    
    public Header()
    {
        columnName = null;
        columnNumber = null;
    }
    
    public Header(String name)
    {
        columnName = name;
        columnNumber = null;
    }
    

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }
    
    
}
