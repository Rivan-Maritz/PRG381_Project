package com.mycompany.prg381_project;

import com.mycompany.prg381_project.ui.MainFrame;
import javax.swing.SwingUtilities;

public class PRG381_Project {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() ->{
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}