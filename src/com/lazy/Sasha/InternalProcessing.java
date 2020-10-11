package com.lazy.Sasha;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InternalProcessing {

    public List<String> createUrlList(List<String> listOfSite) {
        System.out.println("Идёт создание ссылок для запроса.");
        List<String> listOfUrl = new ArrayList<>();

        for (String domain : listOfSite) {
            listOfUrl.add(createURL(domain));
        }
        return listOfUrl;
    }

    public String createURL(String domain) {
        String initialUrl = "https://api.viewdns.info/dnsrecord/?domain=xxxxx&recordtype=A&apikey=cfb771b655ce28c66be331bfcd27dc24cb39809e&output=json";
        String target = "xxxxx";
        String replacement = domain;
        String replaceUrl = initialUrl.replace(target, replacement);
        return replaceUrl;
    }

    public List<String> urlRequest(List<String> listOfUrl) {
        List<String> listOfIP = new ArrayList<>();

        for (String url : listOfUrl) {
            listOfIP.add(takeIP(url));
        }
        System.out.println("Нужные IP  уже созданны.");
        return listOfIP;
    }


    private String takeIP(String url) {
        String response = makeRequest(url);

        StringBuilder stringBuilder = new StringBuilder(response);
        int startPositionOfIP = response.indexOf("data") + 9;

        String cutBeforeIP = stringBuilder.substring(startPositionOfIP);

        int endPositionOfIP = cutBeforeIP.indexOf(" " );
        StringBuilder stringBuilder1 = new StringBuilder(cutBeforeIP);

        String gottenIP = stringBuilder1.substring(0, endPositionOfIP -1);

        return gottenIP;
    }

    public String makeRequest(String url) {

        try {
            URL innerURl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) innerURl.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "Mozilla");

            httpURLConnection.setRequestMethod("GET");

            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                    return String.valueOf(response);
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> uniteList(List<String> listOfSite, List<String> listOfIP) {
        List<String> unitedList = new ArrayList<>();
        StringBuilder neededSpace;

        int index = 0;
        for (String nameOfSite : listOfSite) {
            int sizeOfDomain = 15 - nameOfSite.length();
                neededSpace = spaceCounter(sizeOfDomain);
            unitedList.add((nameOfSite + neededSpace + " IP is: " + listOfIP.get(index)));
                index++;
            }
        System.out.println("Требуемый документ сформирован и вскоре будет записан.");
        return unitedList;
    }

    private static StringBuilder spaceCounter(int size){
        String space = " ";
        StringBuilder neededSpace = new StringBuilder();

        for (int i = 0; i <= size; i++) {
            neededSpace.append(space);
        }
        return neededSpace;
    }

}
