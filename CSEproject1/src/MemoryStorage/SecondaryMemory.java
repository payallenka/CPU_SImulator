package MemoryStorage;

import MemoryManagementUnit.Linkedlist;

import java.util.*;
import java.util.Random;

/*
* Main permanent memory storage
* */


public class SecondaryMemory {

    Hashtable<Integer, Linkedlist> secondaryStorage = new Hashtable<>();

    Hashtable<Integer,Integer> Secondarystorage = new Hashtable<>();

    public SecondaryMemory(){
        Random random = new Random();


        for(int i =0; i<128;i++){
            Linkedlist node = new Linkedlist();

            Integer DummyData = random.nextInt(500);

//            System.out.println("processing");
            for(int j=0; j<4;j++){
//                System.out.println("check");
                node.add(DummyData,j+i);
            }

            secondaryStorage.put(i,node);
        }

//        System.out.println("data loaded");
//
//        Secondarystorage.put(0,91);
//        Secondarystorage.put(1,92);
//        Secondarystorage.put(2,93);
//        Secondarystorage.put(3,94);
//        Secondarystorage.put(4,95);
//        Secondarystorage.put(5,96);
//        Secondarystorage.put(6,97);
//        Secondarystorage.put(7,98);
//        Secondarystorage.put(8,99);
//        Secondarystorage.put(9,100);
//        Secondarystorage.put(10,101);
//        Secondarystorage.put(11,102);
//        Secondarystorage.put(12,103);
//        Secondarystorage.put(13,104);
//        Secondarystorage.put(14,105);
//        Secondarystorage.put(15,106);



    }

    public void read(){
        for(int i=0; i<10;i++){
            Linkedlist list = secondaryStorage.get(i);
//            System.out.println("display");
            list.display();
        }
    }
    public Linkedlist getAddress(Integer MemoryLocation){
        return secondaryStorage.get(MemoryLocation);
    }
    public Integer SecondaryStorage(Integer Byte){
        Hashtable<Integer,Integer> Secondarystorage = new Hashtable<>();


        return Secondarystorage.get(Byte);
    }

//    public static void main(String[] args){
//        System.out.println("jhd");
//        SecondaryMemory secondaryMemory = new SecondaryMemory();
//        secondaryMemory.read();
//    }

}
