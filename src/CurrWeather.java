import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CurrWeather {

    private int temp;
    private int code;
    private HashMap<Integer, String> descriptions;
    public CurrWeather(int temp_, int code){
        this.temp = (int)(temp_*(9.0/5) - 459.67);
        descriptions = decode();
        this.code = code;
    }
    public int getTemp(){
        return temp;
    }
    public String getDescrip(){
        return descriptions.get(code);
    }
    private HashMap<Integer, String> decode(){
        HashMap<Integer, String> keyMap = new HashMap<>();
        try(
                BufferedReader br = new BufferedReader(new FileReader("Codes.txt"))
                ){
            String line = "";
            while((line = br.readLine()) != null){
                int value = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                String text = line.substring(line.indexOf(' '), line.length());
                keyMap.put(value, text);
            }
        }catch (FileNotFoundException f){
            System.out.println("Can't find file");
        }catch (IOException e){
            System.out.println("Couldn't read from file");
        }
        return keyMap;
    }
}
