/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;

/**
 *
 * @author Nhatthang
 */
public class Menu extends ArrayList<String> {
    private String title = null;
    
    public Menu(String title) {
        this.title = title;
    }
    
    public void showOption() {
        System.out.printf("%-62s\n", "==================== " + title + " ====================");
        for (String op : this) {
            System.out.printf("|%-60s|\n", op);
        }
        System.out.printf("%-62s\n", "==============================================================");
    }
}
