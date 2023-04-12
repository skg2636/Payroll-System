/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollapplication;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KIIT
 */
public class ExportList {

    private final DefaultTableModel model_list;
    int size;
    private String[] column_names;

    public ExportList(DefaultTableModel model_list, int size, String[] column_names) {
        this.model_list = model_list;
        this.size = size;
        this.column_names = column_names;
    }

    public int writeFile() {

        String file_path = "C:\\Users\\KIIT\\Downloads\\";
        String file_name = "exportList";

        try {
            FileWriter fw = new FileWriter(file_path + file_name + ".csv");

            // headers
            int number_of_header = this.column_names.length;
            for (int i = 0; i < number_of_header - 1; i++) {
                fw.append(column_names[i] + ",");
            }
            fw.append(column_names[number_of_header - 1]);
            fw.append("\n");

            // Inserting data
            for (int i = 0; i < this.size; i++) {

                for (int j = 0; j < number_of_header - 1; j++) {
                    String value = (String) (CharSequence) this.model_list.getValueAt(i, j);

                    fw.append(value.replaceAll(",", ""));
                    fw.append(",");

                }
                String value = (String) (CharSequence) this.model_list.getValueAt(i, number_of_header - 1);
                fw.append(value.replaceAll(",", ""));
                fw.append("\n");

            }

            fw.close();
            return 1;

        } catch (IOException e) {
            System.out.println(e);
            return 0;
        }
    }

}
