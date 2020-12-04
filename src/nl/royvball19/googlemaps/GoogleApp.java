package nl.royvball19.googlemaps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GoogleApp extends Application {
    //Attributes
    private Stage stage;
    private Scene scene;
    private Object Pane;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //Test Arraylist with Route locations and lat/lng
        ArrayList<Route> routes = new ArrayList<>();
        routes.add(new Route(0, "Utrecht", "Amersfoort", "52.08957871415173", "5.109997627964837", "52.153589380814765","5.374055299129765"));
        routes.add(new Route(1, "Amsterdam", "Den Haag", "52.379216910847205", "4.90035136904373", "52.08058204860559","4.3249960978655775"));
        int idtje;

        System.out.println("Choose: \n[1]Utrecht -> Amersfoort\n[2]Amsterdam -> Den Haag");
        do {
            switch (in.nextInt())
            {
                case 1:
                    System.out.println("You chose Utrecht to Amersfoort");
                    idtje = 0;
                    break;
                case 2:
                    System.out.println("You chose Amsterdam to Den Haag");
                    idtje = 1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + in.nextInt());
            }


        } while (false);

        //Change the pathname to ur own location of the index.html
        File f = new File("C:/Users/royva/IdeaProjects/GoogleMaps/src/nl/royvball19/googlemaps/index.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));

        //Writer to HTML to change the locations
        bw.write("<!DOCTYPE html> \n" +
                "<html> \n" +
                "<head> \n" +
                "<script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBIwzALxUPNbatRBj3Xi1Uhp0fFzwWNBkE&callback=initMap&libraries=&v=weekly\" defer></script>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> \n" +
                "    <script>\n" +
                "        function initMap() {\n" +
                "        const directionsRenderer = new google.maps.DirectionsRenderer();\n" +
                "        const directionsService = new google.maps.DirectionsService();\n" +
                "        const map = new google.maps.Map(document.getElementById(\"map\"), {\n" +
                "            mapTypeControl: false,\n" +
                "            streetViewControl: false,\n" +
                "            zoom: 14,\n" +
                "            restriction: {\n" +
                "                latLngBounds: {\n" +
                "                    north: 53.79305,\n" +
                "                    south: 50.66916,\n" +
                "                    east: 7.45822,\n" +
                "                    west: 2.88242,\n" +
                "                },\n" +
                "            },\n" +
                "        });\n" +
                "        directionsRenderer.setMap(map);\n" +
                "        calculateAndDisplayRoute(directionsService, directionsRenderer);\n" +
                "    }\n" +
                "\n" +
                "    function calculateAndDisplayRoute(directionsService, directionsRenderer) {\n" +
                "        directionsService.route(\n" +
                "            {\n" +
                "                origin: { lat: " + routes.get(idtje).getLocationFromLat() + ", lng: " + routes.get(idtje).getLocationFromLng() + " },\n" +
                "                destination: { lat: " + routes.get(idtje).getLocationToLat() + ", lng: " + routes.get(idtje).getLocationToLng() + " },\n" +
                "                // Note that Javascript allows us to access the constant\n" +
                "                // using square brackets and a string value as its\n" +
                "                // \"property.\"\n" +
                "                travelMode: 'TRANSIT',\n" +
                "            },\n" +
                "            (response, status) => {\n" +
                "                if (status == \"OK\") {\n" +
                "                    directionsRenderer.setDirections(response);\n" +
                "                } else {\n" +
                "                    window.alert(\"Directions request failed due to \" + status);\n" +
                "                }\n" +
                "            }\n" +
                "        );\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
/*                "<div id=\"floating-panel\">\n" +
                "    <b>Van: </b>\n" +
                "    <select id=\"start\">\n" +
                "        <option value=\"Utrecht Centraal\">Utrecht</option>\n" +
                "    </select>\n" +
                "    <b>Naar: </b>\n" +
                "    <select id=\"end\">\n" +
                "        <option value=\"Amersfoort Centraal\">Amersfoort</option>\n" +
                "    </select>\n" +
                "</div>\n" +*/
                "<div id=\"map\"></div>\n" +
                "</body>\n" +
                "</html>");

        bw.write(""); bw.write(""); bw.close(); /*Desktop.getDesktop().browse(f.toURI());*/


        launch(args);
    }

    //Startup from app displays Webview inside of a Pane
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;


        Pane pane = new Pane();
        Scene scene = new Scene(pane, 750, 550);
        stage.getIcons().add(new Image("https://i.ibb.co/zxym2f8/train.png"));
        stage.setTitle("OVApp Kaart");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        //Change the pathname to ur own location of the index.html
        String url = "file:///C:/Users/royva/IdeaProjects/GoogleMaps/src/nl/royvball19/googlemaps/index.html";
        webEngine.load(url);
        pane.getChildren().add(webView);


    }

      //Original class to launch the pane
/*    class MyBrowser extends Pane {


        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();


        public MyBrowser() {

            String url = "file:///C:/Users/royva/IdeaProjects/GoogleMaps/src/nl/royvball19/googlemaps/source.html";
            webEngine.load(url);
            getChildren().add(webView);
        }
    }*/
}