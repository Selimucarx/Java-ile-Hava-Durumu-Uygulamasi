package havadurumuuygulamasi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class WeatherApp {

    public static final String BASE_URL = "https://api.collectapi.com/weather/getWeather?";

    public static void main(String[] args) throws IOException {
        
        Properties properties = new Properties();
        try (InputStream input = WeatherApp.class.getResourceAsStream("/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return; 
        }

        String apiKey = properties.getProperty("API_KEY");


        String[] cityNames = {
                "","adana", "adıyaman", "afyon", "ağrı", "amasya", "ankara", "antalya",
    "artvin", "aydın", "balıkesir", "bilecik", "bingöl", "bitlis", "bolu",
    "burdur", "bursa", "çanakkale", "çankırı", "çorum", "denizli", "diyarbakır",
    "edirne", "elazığ", "erzincan", "erzurum", "eskişehir", "gaziantep", "giresun",
    "gümüşhane", "hakkari", "hatay", "ısparta", "mersin", "istanbul", "izmir",
    "kars", "kastamonu", "kayseri", "kırklareli", "kırşehir", "kocaeli", "konya",
    "kütahya", "malatya", "manisa", "kahramanmaraş", "mardin", "muğla", "muş",
    "nevşehir", "niğde", "ordu", "rize", "sakarya", "samsun", "siirt", "sinop",
    "sivas", "tekirdağ", "tokat", "trabzon", "tunceli", "şanlıurfa", "uşak",
    "van", "yozgat", "zonguldak","aksaray","bayburt","karaman","kırıkkale","batman","sırnak","bartın",
        "ardahan","ığdır","yalova","karabük","kilis","osmaniye","düzce"
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hava durumunu goruntulemek istediginiz sehrin plaka kodunu girin (1-81):   :)) ");
        int plateCode = scanner.nextInt();

        if (plateCode >= 1 && plateCode <= 81) {
            String cityName = cityNames[plateCode];
            System.out.println("Sehir: " + cityName);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(BASE_URL + "data.lang=tr&data.city=" + cityName);
            httpGet.addHeader("authorization", "apikey " + apiKey);

            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            String weatherData = EntityUtils.toString(entity);
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(weatherData);

            JsonNode resultArray = rootNode.get("result");
            for (JsonNode dayNode : resultArray) {
                String date = dayNode.get("date").asText();
                String day = dayNode.get("day").asText();
                String degree = dayNode.get("degree").asText();
                String night = dayNode.get("night").asText();

                System.out.println(" Tarih : " + date);
                System.out.println(" Gun : " + day);
                System.out.println(" Sicaklik : " + degree);
                System.out.println(" Gece Sicakligi : " + night);
                System.out.println(" ---------------------------- ");

            }

            response.close();
            httpClient.close();
        } else {
            System.out.println("Gecersiz Plaka Kodu");
        }
    }
}
