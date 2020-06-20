package com.lazy.Sasha;

import com.company.SimpleFileReader;
import com.company.SimpleFileWriter;
import java.util.List;


public class Main {

    static String websiteNameAndIP = "workData/websiteNameAndIP.txt";

    public static void main(String[] args) {

        System.out.println("Программа начала свою работу, подождите.");


        SimpleFileReader simpleFileReader = new SimpleFileReader();
        SimpleFileWriter simpleFileWriter = new SimpleFileWriter();
        InternalProcessing internalProcessing = new InternalProcessing();

        List<String> listOfSite = simpleFileReader.readLines(websiteNameAndIP);
        List<String> listOfUrl =  internalProcessing.createUrlList(listOfSite);
        List<String> listOfIP =  internalProcessing.urlRequest(listOfUrl);
        List<String> unitedList =  internalProcessing.uniteList(listOfSite, listOfIP);
        simpleFileWriter.writeLines(unitedList, websiteNameAndIP);


        System.out.println("Запись завершенна и файл готов к чтению.");


    }
}
