package com.company.forU.Main;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<String> ListOfTowns = new ArrayList<String>(read("D:\\1/a.txt"));
        ArrayList<String> ListOfCordinates = new ArrayList<String>();
        for (int i = 0; i < ListOfTowns.size(); i++) {
            System.out.println(ListOfTowns.get(i));
            try {
                InputStreamReader responce = new InputStreamReader(sendRequest1(ListOfTowns.get(i)));
                ListOfCordinates =responceFilter(responce);
                for(int t=0;t<ListOfCordinates.size();t++){
                    System.out.println(ListOfCordinates.get(t));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static ArrayList< String> read(String URL) throws IOException {

        File f=new File(URL);
        BufferedReader fin=new BufferedReader(new FileReader(f));
        String line;
        ArrayList<String> listOfTowns = new ArrayList<String>();
        while((line=fin.readLine())!=null)listOfTowns.add(line);
        return listOfTowns;
    }


    public static InputStream sendRequest1(String Town) throws Exception {
        String ELEVATION_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=";
        String urlParameters = "locations=6.9366681,79.9393521&sensor=true";

        URL obj = new URL(ELEVATION_API_URL +Town+ "?" + urlParameters);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        return connection.getInputStream();
    }

    public static ArrayList<String> responceFilter(InputStreamReader g) throws IOException {

        BufferedReader br = new BufferedReader(g);
        String line;
        String hj = "\"northeast\"";
        ArrayList<String> ListOfCordinates = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            if (line.contains(hj)) {;
                ListOfCordinates.add(br.readLine() + 1);
                ListOfCordinates.add(br.readLine() + 2);
                break;
            }
        }
        return ListOfCordinates;

    }

}

