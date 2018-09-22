package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPClient
{
    DatagramSocket clientSocket;

    {
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    InetAddress IPAddress;

    {
        try {
            IPAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        String menuInput = scanner.nextLine();

        switch (menuInput)
        {
            case "1":
                System.out.println("Enter a message: ");
                sendMessages();
                break;
            case "***QUIT***":
                System.out.println("Quitting");
                clientSocket.close();
                System.exit(1);
                break;

        }

    }

    public void sendMessages() throws IOException {
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));


        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("FROM SERVER:" + modifiedSentence);

    }
}