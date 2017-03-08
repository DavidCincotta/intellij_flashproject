import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getElevationJava {

    public static double getElevationJava(double lat, double lng) throws Exception {
        String key = "AIzaSyDnX0RR5nivgnSZmBtI9o5gM0aZ2qVkHSI";
        String urlToRead = "https://maps.googleapis.com/maps/api/elevation/json?locations="+lat+","+lng+"&key=" + key;
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String json = result.toString();
        String elevationString = json.substring(json.indexOf("elevation\" : ")+13, json.indexOf(","));
        double elevation = Double.parseDouble(elevationString);
        return elevation;
    }
}
