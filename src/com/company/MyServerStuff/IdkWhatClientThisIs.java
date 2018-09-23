package com.company.MyServerStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IdkWhatClientThisIs {
    private Socket clientSocket = null;
    private static final int PORT = 1234;
    
    public IdkWhatClientThisIs()
    {
        String fromServer = "";
        String fromUser = "";
        Scanner standardInput = new Scanner(System.in);
        try{
            clientSocket = new Socket("localhost",PORT);
            
            //outputStream = the OUTPUT stream associated with the socket
            //so everything that gets SENT over the socket will be sent to this stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            
            //INPUTSTREAM = inputstream for the socket
            //so it gets all the text or data that gets written
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Your message: ");
            //client listens for server input first and checks if it isnt null
            while(!fromUser.equals("**quit**"))
            {
                fromServer = in.readLine();
                if(fromServer != null)
                {
                    System.out.println(fromServer);
                }
                else{
                    System.out.print("> ");
                    fromUser = standardInput.nextLine();
                }
    
                System.out.print("> ");
                fromUser = standardInput.nextLine();
                
                
                out.println(fromUser);
            }
            
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
