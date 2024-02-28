package MemoryDecoder;

import java.util.*;
public class LogicalAddressReader {

    public Integer PageNumber(String pageNumber){

        Hashtable<String,Integer> pn = new Hashtable<>();
        pn.put("0000",0);
        pn.put("0001",1);
        pn.put("0010",2);
        pn.put("0011",3);
        pn.put("0100",4);
        pn.put("0101",5);
        pn.put("0110",6);
        pn.put("0111",7);
        pn.put("1000",8);
        pn.put("1001",9);
        pn.put("1010",10);
        pn.put("1011",11);
        pn.put("1100",12);
        pn.put("1101",13);
        pn.put("1110",14);
        pn.put("1111",15);

        return pn.get(pageNumber);
    }


    public Integer OffSet(String pageNumber){

        Hashtable<String,Integer> ofst = new Hashtable<>();
        ofst.put("00",0);
        ofst.put("01",1);
        ofst.put("10",2);
        ofst.put("11",3);

        return ofst.get(pageNumber);
    }



}
