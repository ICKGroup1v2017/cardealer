/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.utils;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 *
 * @author ahmet
 */
public class Convert {

    public static String toMysql(String prm) {
        String[] prms = prm.split("-");
        return prms[2] + "-" + prms[1] + "-" + prms[0];
    }

    public static String toSqlDate(String prm) {
        String[] prms = prm.split("-");
        return prms[2] + "-" + prms[1] + "-" + prms[0];
    }
}
