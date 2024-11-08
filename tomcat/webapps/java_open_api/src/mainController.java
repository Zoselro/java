import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.*;

public class  mainController{
  public static void main(String[] args) {
    try {
      String API_KEY = "";
  
      String characterName = URLEncoder.encode("리마", StandardCharsets.UTF_8.name());

      String urlString = "https://open.api.nexon.com/maplestory/v1/id?character_name=" + characterName;
      URL url = new URL(urlString);

      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("x-nxopen-api-key", API_KEY);

      int responseCode = connection.getResponseCode();

      BufferedReader in;
      if(responseCode == 200) {
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      } else {
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      System.out.println(response.toString());
    } catch (Exception exception) {
      System.out.println("Excepttion : " + exception);
    }
  }
}