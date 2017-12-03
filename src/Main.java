import java.util.Scanner;

public class Main {

    public static final String API_KEY = "75875021ae73554ded9dcfd8113b6389";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the city: ");
        String city = sc.nextLine();
        System.out.print("\nPlease enter check interval: ");
        int interval = Integer.parseInt(sc.nextLine());
        System.out.println("\nStarting communication....");
        ArduinoThread weatherThread = new ArduinoThread(city, interval, API_KEY);
        weatherThread.start();
        System.out.println("\nType 'exit' to quit application");
        while(true){
            String text = sc.nextLine().toLowerCase();
            if (text.equals("exit")){
                weatherThread.end();
                System.exit(0);
            }
        }
    }
}
