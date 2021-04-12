package invoice;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

@SuppressWarnings("serial")
class QueryTableModel extends AbstractTableModel {
    Vector modelData; //will hold String[] objects
    int colCount;
    String[] headers = new String[0];
    Connection con;
    PreparedStatement stmt = null;
    String[] record;
    ResultSet rs = null;

    public QueryTableModel() {
        modelData = new Vector();
    }//end constructor QueryTableModel

    public String getColumnName(int i) {
        return headers[i];
    }

    public int getColumnCount() {
        return colCount;
    }

    public int getRowCount() {
        return modelData.size();
    }

    public Object getValueAt(int row, int col) {
        return ((String[]) modelData.elementAt(row))[col];
    }

    public void refreshFromDB(PreparedStatement stmt1) {
        modelData = new Vector();
        stmt = stmt1;
        try {
            //Execute the query and store the result set and its metadata

            rs = stmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            //to get the number of columns
            colCount = meta.getColumnCount();
            // Now must rebuild the headers array with the new column names
            headers = new String[colCount];

            for (int h = 0; h < colCount; h++) {
                headers[h] = meta.getColumnName(h + 1);
            }//end for loop

            // fill the cache with the records from the query, ie get all the rows

            while (rs.next()) {
                record = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    if (i == 5) {
                        double aDouble = rs.getDouble(i + 1);
                        record[i] = String.format("%.1f", aDouble);
                        System.out.println();
                    } else {
                        record[i] = rs.getString(i + 1);
                    }
                }//end for loop
                modelData.addElement(record);
            }// end while loop
            fireTableChanged(null); //Tell the listeners a new table has arrived.
        }//end try clause
        catch (Exception e) {
            System.out.println("Error with refreshFromDB Method\n" + e.toString());
        } // end catch clause to query table
    }//end refreshFromDB method
}// end class QueryTableModel
