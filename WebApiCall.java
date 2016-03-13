/**
 * Created by freddy on 10/3/2014.
 */
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.io.IOException;


public class WebApiCall {

    String urlString = null;
    URL urlObject = null;
    String urlData;

    public String getURLData(String inputString) {

        try {
            // get rid of the white spaces in a URL string
            inputString = inputString.replace(" ", "+");

            //now add the parameters for the web app call
            urlString = "http://www.omdbapi.com/?t=" + inputString + "&r=JSON"; //XML is also supported

            //create the URL object so that a web connection can be opened.
             urlObject = new URL(urlString);

            //connect the open connection to the scanner
            Scanner urlStream = new Scanner(urlObject.openStream());

            //request the return value from the server
            urlData = urlStream.nextLine();
            while (urlStream.hasNext()) //in case there are more lines to read from this stream.
                //add all of the lines together into one long string to return
                urlData = urlData + "\n" + urlStream.nextLine();
                urlData = urlData.replace(" ", " ").replace("\"", " ").replace(",", "\n");

            urlStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return urlData;
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What movie would you like to know about? ");
        String choice = keyboard.nextLine();
        WebApiCall webCall = new WebApiCall();
        String data = webCall.getURLData(choice); //you can enter any movie choice here
        System.out.println(data);
        keyboard.close();


    }
}
