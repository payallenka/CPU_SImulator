package MemoryManagementUnit;

public class Linkedlist {
    Integer PageFrame;
    Integer VirtualPageNumber;
    Linkedlist next;

    public Linkedlist(){
        this.next = null;
    }

    public void add(Integer VirtualPageNumber, Integer PageFrame){
//        SecondaryMemory secondaryMemory = new SecondaryMemory();

        Linkedlist newNode = new Linkedlist();


        newNode.VirtualPageNumber = VirtualPageNumber;
        newNode.PageFrame = PageFrame;

        Linkedlist currentNode = this;


        while(currentNode.next != null){
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;


    }

    public void display() {

        Linkedlist linkedlist = this.next;
        while(linkedlist!=null){
            System.out.print("["+linkedlist.PageFrame+"\t");
            System.out.print(linkedlist.VirtualPageNumber+"] ->");
//            System.out.println( );
            linkedlist = linkedlist.next;
        }
        System.out.println("null");
    }

    public Integer getValue(Integer locationAddress){
        Linkedlist linkedlist = this.next;
        while (linkedlist!=null){
            if(linkedlist.PageFrame == locationAddress){
                return linkedlist.VirtualPageNumber; //in this case it is out data
            }

            linkedlist = linkedlist.next;
        }

        return -1;
    }
}
