package com.lazy.Sasha;

import com.company.FileReader;
import com.company.FileWriter;
import com.company.SimpleFileReader;
import com.company.SimpleFileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Main {

    static String websiteName = "workData/websiteName.txt";
    static String websiteAndIP = "./workData/websiteAndIP.txt";


    public static void main(String[] args) throws IOException {

        System.out.println("Программа начала свою работу, подождите.");


//       new FileReader() {
//
//           @Override
//           public List<String> readLines(String s) throws IOException {
//               return null;
//           }
//       };
//
//        Function<String, List<String>> function = (String s) -> {
//
//
//
//           return new ArrayList<String>();
//        };

        FileReader simpleFileReader = new SimpleFileReader();
        FileWriter simpleFileWriter = new SimpleFileWriter();
        InternalProcessing internalProcessing = new InternalProcessing();
        
        List<String> listOfSite = simpleFileReader.readLines(websiteName);
        List<String> listOfUrl =  internalProcessing.createUrlList(listOfSite);
        List<String> listOfIP =  internalProcessing.urlRequest(listOfUrl);
        List<String> unitedList =  internalProcessing.uniteList(listOfSite, listOfIP);
        simpleFileWriter.writeLines(unitedList, websiteAndIP);


        System.out.println("Запись завершенна и файл готов к чтению.");

    }
}
