/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Department;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KIIT
 */
public class ExportList {
    private final DefaultTableModel model_list;
    int size;

    public ExportList(DefaultTableModel model_list, int size) {
        this.model_list = model_list;
        this.size = size;
    }
    
    
    public int writeFile(){
       
        String file_path = "C:\\Users\\KIIT\\Downloads\\";
        String file_name = "exportList";
       
        
        
        
        try{
            FileWriter fw = new FileWriter(file_path + file_name + ".csv");

            // headers
            fw.append("ID,");
            fw.append("Department Name,");
            fw.append("Manager Name,");
            fw.append("Location");
            fw.append("\n");
            
            
            // Inserting data
            
            for(int i=0;i<this.size;i++){
                fw.append((CharSequence) this.model_list.getValueAt(i, 0));
                fw.append(",");
                fw.append((CharSequence) this.model_list.getValueAt(i, 1));
                fw.append(",");
                fw.append((CharSequence) this.model_list.getValueAt(i, 2));
                fw.append(",");
                fw.append((CharSequence) this.model_list.getValueAt(i, 3));
                fw.append("\n");
                
            }
            
            fw.close();
            return 1;
            
        }catch(IOException e){
            System.out.println(e);
            return 0;
        }   
    }
    
    
    
}
