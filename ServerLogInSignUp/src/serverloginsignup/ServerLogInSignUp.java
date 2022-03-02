/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverloginsignup;

import java.net.*;
import java.io.*;
import java.io.File;
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
            File credenziali = new File("credenziali.txt");
            FileWriter c = new FileWriter(credenziali);
            Scanner cred = new Scanner(credenziali);
            do {
                do {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String reg = in.readLine(), user = in.readLine(), password = in.readLine();
                    if (reg.equals("reg")) {
                        if (!cred.hasNextLine()) {
                            c.write(user + "\n" + password);
                            c.close();
                            out.println("OK");
                            System.out.println("OK");
                            break;
                        } else {
                            out.println("NO");
                            System.out.println("NO qui");
                        }
                    } else if (reg.equals("log")) {
                        if (cred.hasNextLine()) {
                            if (cred.nextLine().equals(user) && cred.nextLine().equals(password)) {
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
            } while (true);
//            in.close();
//            out.close();
//            clientSocket.close();
//            serverSocket.close();
        } catch (FileNotFoundException e) {
            System.err.println("file error.");
        } catch (IOException ex) {
            System.err.println("error io");
        }

    }
}
