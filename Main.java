import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        HighSpeedDataRetrieval hsdr = new HighSpeedDataRetrieval();
        System.out.println("This program stores Integers. You can insert, delete and get random values at O(1) time complexity");
        System.out.println("To insert a value use the command I <value>");
        System.out.println("To delete a value use the command D <value>");
        System.out.println("To get a random value use the command R");
        do{
            System.out.println("Enter command");
            command = scanner.nextLine();

            processCommand(command, hsdr);


        }while(!"exit".equals(command));
        scanner.close();

    }

    public static void processCommand(String command, HighSpeedDataRetrieval hsdr){
        String[] parts = command.split("\\s+");
        if (parts.length > 0) {
            String action = parts[0];
            try{
            switch (action) {
                case "I":
                    if (parts.length > 1) {
                        int valueToInsert = Integer.parseInt(parts[1]);
                        if(hsdr.insert(valueToInsert)) System.out.println("Value " + valueToInsert + " inserted.");
                        else System.out.println("value is already inserted");
                    }
                    break;

                case "D":
                    if (parts.length > 1) {
                        int valueToDelete = Integer.parseInt(parts[1]);
                        if(hsdr.remove(valueToDelete)) System.out.println("Value " + valueToDelete + " deleted.");
                        else System.out.println("value not stored");
                    }
                    break;

                case "R":
                    Integer randomValue = hsdr.getRandom();
                    if(randomValue != null) System.out.println("Random value: " + randomValue);
                    
                    break;

                case "exit":
                    break;

               

                default:
                    System.out.println("Invalid command. Use I <value>, D <value>, R, or exit.");
            }
            }catch(Exception e){
                System.out.println("Invalid command. Use I <value>, D <value>, R, or exit.");
            }
        }
    }
}