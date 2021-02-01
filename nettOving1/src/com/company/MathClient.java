package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MathClient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        /* Uses simple scanner to read */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of the server machine ");
        String server = scanner.nextLine();

        /* Establishes connection */
        Socket socket = new Socket(server, PORTNR);
        System.out.println("Connection established.");

        /* Opens connection for communication */
        InputStreamReader inputStreamReader
                = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        /**
         * This is the main loop for the client, never writes anything but numbers and single symbols, so the steps are easier to understand from the server code
         * it assumes the client actually wants to do math once, so it doesn't check for this first part whether the string is ""
         */
        String request = reader.readLine();
        System.out.println(request);
        String response = scanner.nextLine();
        writer.println(response);

        System.out.println(reader.readLine());
        response = scanner.nextLine();
        writer.println(response);

        System.out.println(reader.readLine());
        response = scanner.nextLine();
        writer.println(response);
        while (true){
            System.out.println(reader.readLine());
            request = reader.readLine();
            System.out.println(request);
            response = scanner.nextLine();
            if (response.equals("")){
                break;
            }
            writer.println(response);

            System.out.println(reader.readLine());
            response = scanner.nextLine();
            if (response.equals("")){
                break;
            }
            writer.println(response);

            System.out.println(reader.readLine());
            response = scanner.nextLine();
            if (response.equals("")){
                break;
            }
            writer.println(response);
        }
        reader.close();
        writer.close();
        socket.close();

    }
}
