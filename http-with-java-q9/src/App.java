import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * App
 */
public class App {
  public static void main(String[] args) {
    try {
      // make a URL object which will be used to create a connection
      // multiple times
      URL url = new URL("http://localhost:3000/posts");

      /* POST REQUEST */

      // create and open a connection of type HttpURLConnection so we can
      // send a POST request to add data to the dummy json server
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

      // save the json as a string and then convert it into byte type
      // to transfer the data in the header in the POST request
      byte[] dataJson = "{\n\"title\": \"my-server2\",\n \"author\": \"smc181002\" \n}".getBytes("utf-8"); 

      urlConnection.setRequestMethod("POST");
      // set the content length
      urlConnection.setFixedLengthStreamingMode(dataJson.length);
      // set the content type to application.json and use utf-8 as 
      // the json stored in jsonData is encoded with utf-8
      urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      // enabling the option to send data
      urlConnection.setDoOutput(true);

      try(OutputStream os = urlConnection.getOutputStream()) {
        os.write(dataJson, 0 , dataJson.length);
      }
      System.out.println(urlConnection.getResponseMessage());
      System.out.println("Response status-line for POST: " + urlConnection.getResponseCode());

      // end the connection after the response is received
      urlConnection.disconnect();

      /* GET REQUEST */

      // create a new connection for sending a GET request
      HttpURLConnection urlConnection1 = (HttpURLConnection) url.openConnection();

      // set the method to GET
      urlConnection1.setRequestMethod("GET");
      // setting header data before sending the request
      urlConnection1.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.84");
      urlConnection1.setRequestProperty("Accept-Language", "hi");

      // print the response header
      System.out.println("Response status-line for GET: " + urlConnection1.getResponseCode());

      // create input stream to get the response to print the output
      InputStream input = urlConnection1.getInputStream();
      // create a reader to read the input stream
      InputStreamReader reader = new InputStreamReader(input);
      int data;

      // do while loop to print the input stream
      do {
        data = reader.read();
        if (data == -1) break;
        System.out.print((char)data);
      } while(data != -1 );

      // disconnect after completing the request
      urlConnection1.disconnect();

      /* PUT REQUEST */

      byte[] dataJsonUpdate = "{\n \"id\": \"1\",\n \"title\": \"my-server4\",\n \"author\": \"kayaba\" \n}".getBytes("utf-8"); 
      // select the post url with id in path
      URL urlPut = new URL("http://localhost:3000/posts/1");
      HttpURLConnection urlConnection2 = (HttpURLConnection) urlPut.openConnection();
      urlConnection2.setRequestMethod("PUT");
      // set the content length
      urlConnection2.setFixedLengthStreamingMode(dataJsonUpdate.length);
      // set the content type to application.json and use utf-8 as 
      // the json stored in jsonData is encoded with utf-8
      urlConnection2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      // enabling the option to send data
      urlConnection2.setDoOutput(true);

      try(OutputStream os = urlConnection2.getOutputStream()) {
        os.write(dataJsonUpdate, 0 , dataJsonUpdate.length);
      }

      System.out.println(urlConnection2.getResponseMessage());
      System.out.println("Response status-line for PUT: " + urlConnection2.getResponseCode());

      /* DELETE REQUEST */
      URL urlDelete = new URL("http://localhost:3000/posts/2");
      HttpURLConnection urlConnection3 = (HttpURLConnection) urlDelete.openConnection();
      urlConnection3.setRequestMethod("DELETE");
      System.out.println(urlConnection3.getResponseMessage());
      System.out.println("Response status-line for DELETE: " + urlConnection3.getResponseCode());

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}