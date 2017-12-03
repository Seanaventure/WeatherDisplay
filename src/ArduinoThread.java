public class ArduinoThread extends Thread {
    private OwmClient weather;
    private String city;
    private boolean alive = true;
    private int interval;
    public ArduinoThread(String city, int interval, String key){
        this.city = city;
        this.interval = interval;
        weather = new OwmClient(key);
    }
    public void run(){
        CurrWeather currW;
        ArduinoComm comm = new ArduinoComm();
        comm.openPort();
        while (alive){
            currW = weather.getCurrWeather(city);
            String message = String.format("%d° F and %s ", currW.getTemp(), currW.getDescrip());
            comm.writeMsg(message);
            try{
                Thread.sleep(interval);
            }catch (InterruptedException e){
                System.out.println("Thread interrupted while sleeping");
            }
        }
        comm.closePort();

    }
    public void end(){
        alive = false;
    }
}
