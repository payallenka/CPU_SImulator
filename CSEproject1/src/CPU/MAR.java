package CPU;/*
* Simulator of a basic transactions of data from the CPU to the main memory.
* The system represents a 6 bit system with 4 bit page number and 2 bit offset value.
* TO keep the data sets concise we are considering 10-bit RAM instead of 64 bytes RAM = 2^6 = 64 bytes,
* with bucket size 4 of the page table and
* 16-bit secondary memory instead of 4096 bytes = (Number of page table entries)*(page size) = 2^4 * 2^6 = 4096
* */


import MemoryDecoder.LogicalAddressReader;
import MemoryManagementUnit.Linkedlist;
import MemoryStorage.SecondaryMemory;
import MemoryStorage.PrimaryMemory;
import MemoryManagementUnit.HashTable;

import java.util.*;

public class MAR {


    public Integer mdr() throws Throwable {

        Integer dataFetched = null;

        //creating an instance of the primary memory
        PrimaryMemory primaryMemory = new PrimaryMemory();

        //creating an instance of the secondary memory
        SecondaryMemory secondaryMemory = new SecondaryMemory();


        //simulating start of the system
        System.out.println("Booting the system....");

        Thread.sleep(2000);  //2000 miliseconds = 2 seconds


        /*
        * loading the primary memory - basic feature like
        * BIOS,
        * Boot loader,
        * Operating system kernel,
        * device drivers,
        * System services and Daemons,
        * User Space processes
        * */

        System.out.println("Loading the primary memory from the secondary memeory");
        for(int i=0; i<10; i++){
            primaryMemory.load((secondaryMemory.getAddress(i)));
        }

        Thread.sleep(3000);  //2000 miliseconds = 3 seconds

        System.out.println("Essential data loaded from the secondary memory to the main memory");

        Thread.sleep(2000);  //2000 miliseconds = 2 seconds

        System.out.println("Displaying the data from the primary memory");
        System.out.println("Memory Location\t\tData Stored");


        for(int i =0; i<10;i++){
            Linkedlist list = primaryMemory.read(i);
            System.out.print(i+"\t\t\t\t\t\t");
            list.display();

        }


        //simulating CPU requesting for a specific data from the Main memory

        /*
        * CPU sends the logical address - here accepted by the user.
        * The logical address contains the page number and the offset value
        * */
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter logical address -- CPU requesting for data from a sprcific memory address");

        String logicalAddress = sc.next();


        //since the system accepts 6-bit logical address value - if not throws an error
        if(logicalAddress.length()!=6){

            throw new Throwable("Enter a 6 bit value");
        }


//        System.out.println(decimalValueAddress.substring(0,4)+" "+decimalValueAddress.substring(4,6));



        //creating an instance of the Logical Address reader class
        LogicalAddressReader logicalAddressReader = new LogicalAddressReader();

        //splitting the logical address into pageNumber and offset value
        Integer PageNumber = logicalAddressReader.PageNumber(logicalAddress.substring(0,4));
        Integer OffsetValue = logicalAddressReader.OffSet(logicalAddress.substring(4,6));




//        System.out.println(hash.Hashing(PageNumber)%4);
//        System.out.println(PageNumber);
//        System.out.println(primaryMemory.read(PageNumber));

        //checking if the requested memory exits in the primary memory -
        // if not will be loaded from the secondary memory via the page table

        if(primaryMemory.read(PageNumber) == null){

            System.out.println("Page fault occured loading data from the secondary memory...");
            HashTable hashtable = new HashTable();

            /*
            * The page-number of the logical address is hashed by modulus 4
            * Constraining it to the page table size i.e., 4 buckets.
            * The hashed value obtained acts as the page number of the pagetable
            * The page table is a hashed linkedlist in which each node stores the page frame, virtual page value and the address of the next node
            *
            * */
            Integer VirtualPageValue = hashtable.fetch(PageNumber%4,PageNumber);

//            System.out.println(VirtualPageValue);
//            System.out.println(secondaryMemory.getAddress(VirtualPageValue));


            primaryMemory.removeElement(PageNumber);
            primaryMemory.load(secondaryMemory.getAddress(VirtualPageValue));

            System.out.println("Data loaded successfully...displaying the updated primary memory");

            //primary memory updated with newly loaded value from the secondary storage
            System.out.println("Memory Location\t\tData Stored");
            for(int i =0; i<10;i++){
                Linkedlist list = primaryMemory.read(i);
                System.out.print(i+"\t\t\t\t\t\t");
                list.display();

            }

            //reteriving data from the primary memory
            System.out.println("The data stored at the requested memory is");
            Linkedlist list = primaryMemory.read(9);

            Integer data = list.getValue(PageNumber+OffsetValue);
            if(data == -1){
                throw new Throwable("Memory segmeantation fault - Accessing data that hasn't been alloted");
            }

            dataFetched = data;
//            System.out.println("Data at the requested location is "+ data);
//            list.display();
//            System.out.println(primaryMemory.read(9));

        }else{
            System.out.println("Retrieving data from the requested memory location");
            Linkedlist list = primaryMemory.read(PageNumber);

            Integer data = list.getValue(PageNumber+OffsetValue);
            if(data == -1){
                throw new Throwable("Memory segmeantation fault - Accessing data that hasn't been alloted");
            }

            dataFetched = data;
//            System.out.println("Data at the requested location is "+ data);

//            list.display();
//            System.out.println(primaryMemory.read(PageNumber));
        }



        return dataFetched;

    }
}