/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author checchia.simone
 */
public class ServerLogInSignUp {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static final int port = 5000;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            FileWriter credenzialiWriter = new FileWriter("credenziali.txt");
            File credenziali = new File("credenziali.txt");
            Scanner cred = new Scanner(credenziali);
            do {
                clientSocket = serverSocket.accept();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String reg = in.readLine(), user = in.readLine(), password = in.readLine();
                if (reg.equals("reg")) {
                    try {
                        credenzialiWriter.write(user + " " + password);
                        credenzialiWriter.close();
                        out.println("OK");
                        System.out.println("OK");
                    } catch (Exception e) {
                        System.err.println("error");
                    }
                } else if (reg.equals("log")) {
                    if (cred.hasNextLine()) {
                        if (cred.nextLine().equals(user + " " + password)) {
                            out.print("OK");
                            System.out.println("OK");
                            break;
                        } else {
                            out.print("NO");
                            System.out.println("NO");
                        }
                    } else {
                        out.print("NO");
                        System.out.println("NO");
                    }
                }
            } while (true);
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
