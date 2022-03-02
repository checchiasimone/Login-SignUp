/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientloginsignup;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author checchia.simone
 */
public class ClientLogInSignUp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String resp = null;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("vuoi registrarti o fare il login?"
                        + "\n [reg/log]");
                String choice = sc.next(), user = null, password = null;
                try {
                    if (!choice.equals("reg") &&! choice.equals("log")) {
                        throw new Exception("error");
                    }
                } catch (Exception e) {
                    System.err.println("err");
                }

                System.out.println("inserire user");
                user = sc.next();
                System.out.println("inserire password");
                password = sc.next();
                Socket clientSocket = new Socket("10.1.33.14", 5000);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println(choice);
                out.println(user);
                out.println(password);
                if (in.readLine().equals("OK")) {
                    if (choice.equals("log")) {
                        System.out.println("login effettuato");
                        break;
                    } else {
                        System.out.println("registrazione effettuata");
                    }

                } else if (in.readLine().equals("NO")) {
                    if (choice.equals("log")) {
                        System.out.println("login fallito");
                    } else {
                        System.out.println("registrazione fallita");
                    }
                }
//                in.close();
//                out.close();
//                clientSocket.close();

            } catch (IOException ex) {

            }
        } while (true);
    }

}
