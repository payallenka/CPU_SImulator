package MemoryStorage;

import MemoryManagementUnit.Linkedlist;

import java.util.Hashtable;


/*
* Volatile Memory
* */
public class PrimaryMemory {

    Hashtable<Integer, Linkedlist> primaryMemory = new Hashtable<>();
    private static Integer index = 0;
    public void load(Linkedlist value){
        primaryMemory.put(index,value);
        index++;
    }

    public Linkedlist read(Integer address){

        return primaryMemory.get(address);
    }

    public void removeElement(Integer memoryLocation){
        primaryMemory.remove(memoryLocation);
        index--;
    }
}
