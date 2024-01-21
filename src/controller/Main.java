/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.HotelList;
import java.util.ArrayList;
import model.Hotel;
import util.MyUtil;
import view.Menu;

/**
 *
 * @author Nhatthang
 */
public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu("The Hotel Management");
        menu.add("1.Adding new Hotel");
        menu.add("2.Checking exits Hotel");
        menu.add("3.Updating Hotel information");
        menu.add("4.Deleting Hotel");
        menu.add("5.Searching Hotel");
        menu.add("6.Displaying a hotel list (descending by Hotel_Name)");
        menu.add("7.Save to file");
        menu.add("8.Exit");

        String filePath = "src/data/Hotel.dat";
        HotelList hotelList = new HotelList();
        HotelController.loadFromFile(filePath, hotelList);
        int choice;

        do {
            menu.showOption();
            choice = MyUtil.inputInteger("Choose your option (1 - " + menu.size() + "): ", 1, menu.size());
            switch (choice) {
                case 1:
                    HotelController.addNewHotel(hotelList);
                    break;
                case 2:
                    HotelController.checkExistHotel(hotelList);
                    break;
                case 3:
                    HotelController.updateHotelInfo(hotelList);
                    break;
                case 4:
                    HotelController.deleteByID(hotelList);
                    break;
                case 5:
                    HotelController.searchHotel(hotelList);
                    break;
                case 6:
                    System.out.println("HOTEL IN FILE");
                    HotelController.displayHotelListInFile(filePath, hotelList);
                    System.out.println("=============================================");
                    break;
                case 7:
                    HotelController.saveToFile(filePath, hotelList);
                    break;
                case 8:
                    System.out.println("Thank You!");
                    break;
            }
        } while (choice != 8);
    }
}
