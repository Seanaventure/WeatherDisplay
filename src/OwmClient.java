
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class OwmClient {
    private final String API_KEY;

    public OwmClient(String api_key){
        API_KEY = api_key;
    }

    public CurrWeather getCurrWeather(String city){
        JSONObject data = new JSONObject();
        try{
            URL dataURL = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + API_KEY);
            URLConnection connection = dataURL.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser parse = new JSONParser();
            data = (JSONObject) parse.parse(input.readLine());

        }catch (MalformedURLException m){
            System.out.println("URL malformed");
        }catch (IOException e){
            System.out.println("Error making connection");
        }catch (ParseException p){
            System.out.println("Error reading data into JSON object");
        }
        JSONObject subData = (JSONObject)data.get("main");
        JSONObject subDescrip = (JSONObject)((JSONArray)data.get("weather")).get(0);
        Double temp = (Double)subData.get("temp");
        int code = ((Long)subDescrip.get("id")).intValue();
        return new CurrWeather(temp.intValue(), code);
    }

}
