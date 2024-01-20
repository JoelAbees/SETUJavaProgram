import java.util.HashMap;

class HighSpeedDataRetrieval {

    HashMap<Integer, Node> valueToNodeHM; //hashmap that gets the node corresponding to the value
    HashMap<Integer, Integer> referenceToValueHM; //hashmap used as an index to get random a random value
    Node tail;


    public HighSpeedDataRetrieval() {

        //initialize both the hashmaps
        valueToNodeHM = new HashMap<Integer, Node>();
        referenceToValueHM = new HashMap<Integer, Integer>();
    }
    
    public boolean insert(int val) {

        //value is already present return false
        if(valueToNodeHM.containsKey(val)) return false;

        //create node
        int newNodeReference = valueToNodeHM.size() + 1;
        Node newNode = new Node(val, newNodeReference);

        //insert the new node at the end
        if(tail == null) tail = newNode;
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        //update the hashmaps
        valueToNodeHM.put(val, newNode);
        referenceToValueHM.put(newNodeReference, val);
        return true;
    }
    
    public boolean remove(int val) {

        //value is not present return false;
        if(!valueToNodeHM.containsKey(val)) return false;


        //get node to be deleted
        Node nodeToBeDeleted = valueToNodeHM.get(val);
        

        //remove the node from the chain

        //if node is at the end
        if(nodeToBeDeleted == tail){

            //if node to be deleted is the only node
            if(nodeToBeDeleted.prev == null){
                tail = null;
                valueToNodeHM.remove(val);
                referenceToValueHM.remove(1);

            //node is at the tail and has preceeding nodes.
            }else{
                tail = nodeToBeDeleted.prev;
                tail.next = null;
                valueToNodeHM.remove(val);
                referenceToValueHM.remove(nodeToBeDeleted.reference);
            }

        //node is not the tail
        }else{
            
            if(nodeToBeDeleted.prev != null) nodeToBeDeleted.prev.next = nodeToBeDeleted.next; //execute only if node is not the first node
            nodeToBeDeleted.next.prev = nodeToBeDeleted.prev;

            //swap references of node to be deleted with node with last reference. 
            int referenceOfNodeToBeDeleted = nodeToBeDeleted.reference;
            int lastReference = valueToNodeHM.size();

            Node nodeWithLastReference = valueToNodeHM.get(referenceToValueHM.get(lastReference));
            nodeWithLastReference.reference = referenceOfNodeToBeDeleted;
            referenceToValueHM.put(referenceOfNodeToBeDeleted, nodeWithLastReference.val);
            referenceToValueHM.remove(lastReference);
            valueToNodeHM.remove(val);
        }
        return true;

    }
    
    public int getRandom() {
        int randomNumber = (int)(Math.random()*valueToNodeHM.size() + 1);
        return referenceToValueHM.get(randomNumber);
    }


    class Node{
        int val;
        Node prev;
        Node next;
        int reference;

        Node(int val, int reference){
            this.val = val;
            this.reference = reference;
        }
    }
}