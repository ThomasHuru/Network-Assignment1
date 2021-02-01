package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MathServer {
    public static void main(String[] args) throws IOException {
        final int PORTNR=1250;
        ServerSocket host = new ServerSocket(PORTNR);
        int count=0;
        /**
         * loops when new client connects
         */
        while(true){
            count++;
            Socket forbindelse = host.accept();
            System.out.println("Client "+count+" connected");
            MultiThreadAdd thread = new MultiThreadAdd(forbindelse);
            thread.start();
        }
    }
}
class MultiThreadAdd extends Thread {
    Socket socket;
    public MultiThreadAdd(Socket socket) {
        this.socket = socket;

    }

    public void run(){
        try {
            math(socket);
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Pointless to have it here and not in the run itself, but i wrote it as a function first and it's late
     * @param socket
     * @throws IOException
     */
    private static void math(Socket socket) throws IOException {
        InputStreamReader inputStreamReader
                = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        boolean add = false;
        writer.println("Do you want to add or subtract? + for add - subtract");
        String answer = reader.readLine();
        if (answer.equals("+")) {
            add = true;
        } else if (answer.equals("-")) {
            add = false;
        }
        else {
            writer.println("Should be + or -, haven't written code that loops the code so this is an error");
            reader.close();
            writer.close();
            socket.close();
        }
        writer.println("first number:");
        String tempNum1 = reader.readLine();
        writer.println("second number:");
        String tempNum2 = reader.readLine();
        /**
         * The central loop, closes if an error occurs or if the user responds with an empty line
         */
        while (tempNum1 !=null || tempNum2 !=null){
            int num1 = 0;
            int num2 = 0;
            try {
                num1 = Integer.parseInt(tempNum1);
                num2 = Integer.parseInt(tempNum2);
            } catch (NumberFormatException e){
                writer.println("Wrong format on numbers");
                reader.close();
                writer.close();
                socket.close();
            }
            if (add) {
                writer.println(num1 + " + " + num2 + " = " + (num1 + num2));
            }
            else {
                writer.println(num1 + " - " + num2 + " = " + (num1 - num2));
            }

            writer.println("Do you want to add or subtract? + for add - subtract");
            answer = reader.readLine();
            if (answer.equals("+")) {
                add = true;
            } else if (answer.equals("-")) {
                add = false;
            }
            else {
                writer.println("Should be + or -, haven't written code that loops the code so this is an error");
                reader.close();
                writer.close();
                socket.close();
            }
            writer.println("first integer:");
            tempNum1 = reader.readLine();
            writer.println("second integer:");
            tempNum2 = reader.readLine();
        }
        reader.close();
        writer.close();
        socket.close();
    }
}

