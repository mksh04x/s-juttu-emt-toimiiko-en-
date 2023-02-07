
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.*;
import com.google.gson.reflect.*;
import javafx.scene.control.TextArea;
import javax.swing.JFrame;

public class saa {

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }

    public static void main(String[] args) {
        
        TextArea area = new TextArea();
        GUI gui = new GUI();

        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gui.setUndecorated(true);
        gui.setVisible(true);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HH:mm");
        Date date = new Date();
        String API_KEY = "552c032ae53915af7a29f27d397c2e9c";
        String LOCATION = "643492"; // no oulu tietty
        String urlString = "http://api.openweathermap.org/data/2.5/weather?id=" + LOCATION + "&APPID=" + API_KEY
                + "&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
//            System.out.println(result); // tää printtaa ihan kaiken

            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            System.out.println("Lämpötila: " + mainMap.get("temp") + "°C");
            System.out.println("Kosteus: " + mainMap.get("humidity") + " %");
            System.out.println("Tuulen nopeus: " + windMap.get("speed") + " m/s");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.println(formatter.format(date));
            try {
                Thread.sleep(60 * 1000); // minuutti!
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
