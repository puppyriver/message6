/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alcatelsbell.nms.util.protocol.tl1;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ronnie.Chen
 */
public class TL1Result {
    private List<String> columns = null;
    private List<List<String>> datas = null;

    /**
     * @return the columns
     */
    public List<String> getColumns() {
        return columns;
    }
    
    public int getRowSize() {
        return datas.size();
    }

    public TL1Row getRow(int row) {
        TL1Row tr = new TL1Row();
        tr.setColumns(columns);
        tr.setRowdata(datas.get(row));
        return tr;

    }
    public String getValue(String columnName,int rowNumber) {
        int idx = columns.indexOf(columnName);
        List<String> rowdata = datas.get(rowNumber);
        return rowdata.get(idx);
    }
    public HashMap<String,String> getValueMap(int rowNumber) {
        HashMap values = new HashMap();
        List<String> rowdata = datas.get(rowNumber);
        for (int i = 0; i < columns.size(); i++) {
            String column = columns.get(i);
            String value = rowdata.get(i);            
            values.put(column, value);
        }
        
        return values;
    }
    /**
     * @param columns the columns to set
     */
    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    /**
     * @return the datas
     */
    public List<List<String>> getDatas() {
        return datas;
    }

    /**
     * @param datas the datas to set
     */
    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }
 
    
}
