/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author leath
 */
public class SuitesMathematiques {
    public static ArrayList<Integer> fibonacci(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        while (!list.contains(n)){
            int t = list.size();
            list.add(list.get(t-2) + list.get(t-1));
        }
        return list;
    }
    
}
