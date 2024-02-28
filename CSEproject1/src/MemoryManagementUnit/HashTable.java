package MemoryManagementUnit;

import java.awt.print.Pageable;
import java.util.*;
import MemoryStorage.SecondaryMemory;


public class HashTable {
    private static int pageFrame = 0;
    private static int VirtualPageNumber =0;

    private static HashSet<Integer> pageNumber = new HashSet<>();
    private static Hashtable<Integer,Linkedlist> pageTable = new Hashtable<>();

    public HashTable(){




        for(int i =0;i<16;i++){


            if(pageTable.get(i%4) == null){
                Linkedlist link = new Linkedlist();
                link.add(pageFrame,VirtualPageNumber);
                pageTable.put(i%4,link);
                pageNumber.add(i%4);
                pageFrame++;
                VirtualPageNumber++;
            }else{
                Linkedlist link = pageTable.get(i%4);
                link.add(pageFrame,VirtualPageNumber);
                pageTable.put(i%4,link);
                pageNumber.add(i%4);
                pageFrame++;
                VirtualPageNumber++;
            }
        }

//        System.out.println(pageNumber);

//        for(Integer i: pageNumber){
//            Linkedlist l = pageTable.get(i);
//            l.display();
//
//        }
    }

    public Integer fetch(Integer pageNumber, Integer pageFrameAddress){
        Linkedlist currentList = pageTable.get(pageNumber);

        while(currentList!=null){

            if(currentList.PageFrame == pageFrameAddress){
                return currentList.VirtualPageNumber;
            }

            currentList = currentList.next;
        }

        return -1;
    }

}
