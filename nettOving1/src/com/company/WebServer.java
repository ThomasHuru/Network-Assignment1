package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class WebServer {
    public static void main(String[] args) throws IOException {
        final int PORTNR=80;
        ServerSocket host = new ServerSocket(PORTNR);
        Socket socket = host.accept();
        InputStreamReader inputStreamReader
                = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        String header = "temp";
        /**
         * receive and print http header
         */
        while(!header.equals("")){
            header = reader.readLine();
            System.out.println(header);
        }
        /**
         * send to webpage
         */
        writer.println("HTTP/1.0 200 OK"+"\n"+"Content-Type: text/html; charset=utf-8"+"\n"+"\n" +
                "<HTML><BODY> <H1> Hilsen. Du har koblet deg opp til min enkle web-tjener </h1> Header " +
                "fra klient er: <UL> <LI> ...... </LI></UL></BODY></HTML>");
        socket.close();
        host.close();

    }
}
