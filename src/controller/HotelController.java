/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.HotelList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import model.Hotel;
import util.MyUtil;

/**
 *
 * @author Nhatthang
 */
public class HotelController {

    public static void addNewHotel(HotelList hotelList) {
        do {
            Hotel hotel = inputANewHotelInfo(hotelList);
            if (hotel == null) {
                System.out.println("Exist Hotel! Added failed!");
            } else {
                hotelList.add(hotel);
                System.out.println("Added successfully");
            }
        } while (MyUtil.selectYesNo("Do you want to add one more ? "
                + "\n\"Y\" - Continue "
                + "\n\"N\" - Go back to the menu"
                + "\nYour choice: ", "Y", "N"));
    }

    public static Hotel inputANewHotelInfo(HotelList hotelList) {
        System.out.println("Input information for hotel: ");
        String id = MyUtil.inputStringRegex("Hotel_id: ", "Hotel_id must be \"Hxx\"!", MyUtil.REGEX_ID);
        if (hotelList.searchByID(id) == null) {
            String name = MyUtil.inputStringNotEmpty("Hotel_Name: ");
            int roomAvail = MyUtil.inputInteger("Hotel_Room_Available: ", 0, null);
            String address = MyUtil.inputStringNotEmpty("Hotel_Address: ");
            String phone = MyUtil.inputStringRegex("Hotel_Phone: ", "Phone number start with \"0\" and length 10-12!", MyUtil.REGEX_PHONE);
            int rating = MyUtil.inputInteger("Hotel_Rating [1-10]: ", 1, 10);
            return new Hotel(id, name, roomAvail, address, phone, rating);
        } else {
            return null;
        }
    }

    public static void updateHotelInfo(HotelList hotelList) {
        String idUpdate = MyUtil.inputStringRegex("Hotel_id to update: ", "Hotel_id must be \"Hxx\"!", MyUtil.REGEX_ID);
        if (hotelList.searchByID(idUpdate) == null) {
            System.out.println("Hotel does not exist!");
        } else {
            String newName = MyUtil.inputString("New Hotel_Name: ");
            int newRoomAvail = MyUtil.inputInteger("New Hotel_Room_Available: ", 0, null, "", -1);
            String newAddress = MyUtil.inputString("New Hotel_Address: ");
            String newPhone = null;
            do {
                newPhone = MyUtil.inputString("New Hotel_Phone: ");
                if (newPhone.isEmpty() || newPhone.matches(MyUtil.REGEX_PHONE)) {
                    break;
                } else {
                    System.out.println("Phone number start with \"0\" and length 10-12!");
                }
            } while (true);
            int newRating = MyUtil.inputInteger("New Hotel_Rating [1-10]: ", 1, 10, "", -1);

            hotelList.update(new Hotel(idUpdate, newName, newRoomAvail, newAddress, newPhone, newRating));
            System.out.println("Updated Successfully");
        }

    }

    public static void checkExistHotel(HotelList hotelList) {
        do {
            String id = MyUtil.inputStringRegex("Hotel_id to check: ", "Hotel_id must be \"Hxx\"!", MyUtil.REGEX_ID);
            if (hotelList.searchByID(id) != null) {
                System.out.println("Exist Hotel!");
            } else {
                System.out.println("No Hotel Found!");
            }
        } while (MyUtil.selectYesNo("Do you want to check one more ? "
                + "\n\"Y\" - Continue "
                + "\n\"N\" - Go back to the menu"
                + "\nYour choice: ", "Y", "N"));
    }

    public static void deleteByID(HotelList hotelList) {
        String id = MyUtil.inputStringRegex("Hotel_id to delete: ", "Hotel_id must be \"Hxx\"!", MyUtil.REGEX_ID);
        if (hotelList.searchByID(id) != null) {
            if (MyUtil.selectYesNo("Do you ready want to delete this hotel (Y/N) ?"
                    + "\nYour choice: ", "Y", "N")) {
                hotelList.deletedByID(id);
                System.out.println("Deleted Successfully!");
            } else {
                System.out.println("Deleted fail!");
            }
        } else {
            System.out.println("Hotel_id does not exist!");
        }
    }

    public static void searchHotel(HotelList hotelList) {
        boolean typeSearch = MyUtil.selectYesNo("1 - Search by Hotel_id "
                + "\n2 - Search by Hotel_name"
                + "\nYour choice: ",
                "1", "2");
        if (typeSearch) {
            String id = MyUtil.inputStringRegex("Hotel_id to search: ", "Hotel_id must be \"Hxx\"!", MyUtil.REGEX_ID);
            Hotel sHotel = hotelList.searchByID(id);
            if (sHotel != null) {
                System.out.println(Hotel.getHeader());
                System.out.println(sHotel);
            } else {
                System.out.println("Hotel_id does not exist!");
            }
        } else {
            String name = MyUtil.inputStringNotEmpty("Hotel_name to search: ");
            HotelList srchNameHotelList = hotelList.searchByName(name);
            HotelController.displayHotelList(srchNameHotelList, "No hotel has name contains " + name + " !", "No hotel has name contains " + name + " !");
        }
    }

    public static HotelList sort(HotelList arr, boolean isAsc) {
        if (arr != null) {
            Comparator<Hotel> cmp = new Comparator<Hotel>() {
                @Override
                public int compare(Hotel o1, Hotel o2) {
                    if (isAsc) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return o2.getName().compareTo(o1.getName());
                    }
                }
            };
            HotelList tmp = new HotelList(arr);
            Collections.sort(tmp, cmp);
            return tmp;
        } else {
            return null;
        }
    }

    public static void displayHotelList(HotelList hotelList, String emptyMess, String nullMess) {
        if (hotelList != null) {
            if (!hotelList.isEmpty()) {
                System.out.println(Hotel.getHeader());
                for (Hotel hotel : hotelList) {
                    System.out.println(hotel);
                }
            } else {
                System.out.println(emptyMess);
            }
        } else {
            System.out.println(nullMess);
        }
    }

    public static void displayHotelListInFile(String filePath, HotelList hotelList) {
        try {
            HotelList descList = sort(readFromFile(filePath), false);
            displayHotelList(descList, "File is empty!", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveToFile(String filePath, HotelList hotelList) {
        try {
            HotelController.writeToFile(filePath, hotelList);
            System.out.println("Save to file successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadFromFile(String filePath, HotelList hotelList) {
        try {
            HotelList tmpList = HotelController.readFromFile(filePath);
            for (Hotel hotel : tmpList) {
                hotelList.add(hotel);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static HotelList readFromFile(String filePath) throws FileNotFoundException, IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException("File doesn't exist!");
        } else if (f.length() == 0) {
            return new HotelList();
        } else {
            try ( FileInputStream fis = new FileInputStream(f);  ObjectInputStream ois = new ObjectInputStream(fis);) {
                return (HotelList) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new IOException("Exception while reading file: " + e.getMessage());
            }
        }
    }

    public static void writeToFile(String filePath, ArrayList<Hotel> arrList) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException("File doesn't exist!");
        } else {
            try ( FileOutputStream fos = new FileOutputStream(f);  ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(arrList);
            } catch (IOException ioE) {
                throw new IOException("Exception while writing file: " + ioE.getMessage());
            }
        }
    }
}
