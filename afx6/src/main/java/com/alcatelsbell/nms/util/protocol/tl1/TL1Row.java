package com.alcatelsbell.nms.util.protocol.tl1;

import java.util.HashMap;
import java.util.List;

/**
 * User: Ronnie.Chen
 * Date: 11-5-27
 * Time:
 */
public class TL1Row {
    private List<String> columns = null;
    private List<String> rowdata = null;
    private int rowIndex = -1;

    /**
     * @return the columns
     */
    public List<String> getColumns() {
        return columns;
    }

    public List<String> getRowData() {
        return rowdata;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String getValue(String columnName) throws Exception {
        int idx = columns.indexOf(columnName);
        if (idx < 0 )
            throw new Exception("Failed to find column:"+columnName);
        if (idx >rowdata.size()-1) return null;
        return rowdata.get(idx);
    }
    public HashMap<String,String> getValueMap(int rowNumber) {
        HashMap values = new HashMap();

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

    public void setRowdata(List<String> row) {
        rowdata = (row);
    }

}
