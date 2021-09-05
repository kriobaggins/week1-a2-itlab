import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * App
 */
public class App {
  public static void main(String[] args) {
    try {
      URL url = new URL("http://localhost:3000/posts");
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // TODO: send post request

      byte[] dataJson = "{\n\"title\": \"my-server2\",\n \"author\": \"smc181002\" \n}".getBytes("utf-8"); 
      urlConnection.setRequestMethod("POST");
      // urlConnection.setFixedLengthStreamingMode(dataJson.length);
      urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      urlConnection.setDoOutput(true);

      try(OutputStream os = urlConnection.getOutputStream()) {
        os.write(dataJson, 0 , dataJson.length);
      }
      System.out.println(urlConnection.getResponseMessage());
      System.out.println("Response status-line for GET: " + urlConnection.getResponseCode());

      urlConnection.disconnect();
      HttpURLConnection urlConnection1 = (HttpURLConnection) url.openConnection();

      urlConnection1.setRequestMethod("GET");
      urlConnection1.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.84");
      urlConnection1.setRequestProperty("Accept-Language", "hi");

      System.out.println("Response status-line for GET: " + urlConnection1.getResponseCode());

      InputStream input = urlConnection1.getInputStream();
        
      InputStreamReader reader = new InputStreamReader(input);
      int data;

      do {
        data = reader.read();
        if (data == -1) break;
        System.out.print((char)data);
      } while(data != -1 );


      urlConnection1.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
