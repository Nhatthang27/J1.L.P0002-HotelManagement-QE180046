/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import model.Hotel;

/**
 *
 * @author Nhatthang
 */
public class MyUtil {

    private static Scanner sc = new Scanner(System.in);
    public static final String REGEX_PHONE = "^[0]\\d{9,11}";
    public static final String REGEX_ID = "^H\\d{2}";

    public static String inputString(String mess) {
        System.out.print(mess);
        return sc.nextLine();
    }

    public static String inputStringNotEmpty(String mess) {
        do {
            String str = inputString(mess);
            if (str.isEmpty()) {
                System.out.println("Input is required!");
            } else {
                return str;
            }
        } while (true);
    }

    public static String inputStringRegex(String mess, String errMess, String regex) {
        do {
            String str = inputString(mess);
            if (str.matches(regex)) {
                return str;
            } else {
                System.out.println(errMess);
            }
        } while (true);
    }

    public static int inputInteger(String mess, Integer low, Integer high, String except, int exceptOut) {
        do {
            try {
                String strInp = inputString(mess);
                if (strInp.equals(except)) {
                    return exceptOut;
                } else {
                    int x = Integer.parseInt(strInp);
                    if ((low != null && x < low) || (high != null && x > high)) {
                        throw new Exception("Input must be an integer in range ["
                                + (low == null ? "-INFINITY" : low) + (high == null ? " -> INFINITY" : (" - " + high)) + "]!");
                    } else {
                        return x;
                    }
                }
            } catch (NumberFormatException nE) {
                System.out.println("Input must be an integer!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static int inputInteger(String mess, Integer low, Integer high) {
        do {
            try {
                int x = Integer.parseInt(inputStringNotEmpty(mess));
                if ((low != null && x < low) || (high != null && x > high)) {
                    throw new Exception("Input must be an integer in range ["
                            + (low == null ? "-INFINITY" : low) + (high == null ? " -> INFINITY" : (" - " + high)) + "]!");
                } else {
                    return x;
                }
            } catch (NumberFormatException nE) {
                System.out.println("Exception when parsing number: " + nE.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static boolean selectYesNo(String mess, String yes, String no) {
        do {
            String slt = inputStringNotEmpty(mess);
            if (slt.equals(yes)) {
                return true;
            } else if (slt.equals(no)) {
                return false;
            } else {
                System.out.println("Input must be \"" + yes + "\" or \"" + no + "\" !");
            }
        } while (true);
    }
}
