package com.company;

import java.io.IOException;

import java.net.*;
import java.util.Scanner;

public class UDPEchoClient {

    private static InetAddress host;
    private static final int PORT = 1234;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    
    public void sendMessage() throws SocketException {
        try{
            host = InetAddress.getLocalHost();
        }
        catch (UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }

        accessServer();

        String messageIn, messageOut;
        int numMessages = 0;
        InetAddress clientAddress = null;
        int clientPort;

        DatagramSocket datagramSocket = new DatagramSocket();

        messageIn = new String(inPacket.getData(),0,inPacket.getLength());

        System.out.println("Message received");
        numMessages++;
        messageOut = "Message " + numMessages + ": " + messageIn;

        outPacket = new DatagramPacket(messageOut.getBytes(),messageOut.length(),clientAddress,1234);



    }

    public void accessServer()
    {
        try{
            datagramSocket = new DatagramSocket();
            Scanner userEntry = new Scanner(System.in);

            String message="", response="";
            do {
                System.out.println("enter msg");
                message = userEntry.nextLine();
                if(!message.equals("***CLOSE***"))
                {
                    outPacket = new DatagramPacket(message.getBytes(),
                            message.length(),host, PORT);

                    datagramSocket.send(outPacket);
                    buffer = new byte[256];
                    inPacket = new DatagramPacket(buffer,buffer.length);
                    datagramSocket.receive(inPacket);
                    response = new String(inPacket.getData(),0,inPacket.getLength());

                    System.out.println("\nSERVER> " + response);
                }
            }while(!message.equals("***CLOSE***"));
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally {
            System.out.println("\n* Closing connection... *");
            datagramSocket.close();
        }
    }
}
