package com.cutieMath02;
import java.util.HashMap;

public class TwoSum {
    private HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();

    public void add(int number){
        if(count.containsKey(number)){
            count.put(number, count.get(number) + 1);
        } else {
            count.put(number, 1);
        }
    }

    public boolean find(int value){
        for (Integer key:count.keySet()){
            if(2 * key == value && count.get(key) >= 2){
                return true;
            }
            if(2 * key != value && count.containsKey(value - key)){
                return true;
            }
        }
        return false;
    }



}
