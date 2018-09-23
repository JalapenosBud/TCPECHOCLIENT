package com.company;


import com.company.MyServerStuff.NIOEchoClient;

public class Main {
    public static void main(String[] args) throws Exception {
    
        NIOEchoClient client = new NIOEchoClient();
        client.startClient();
        
    }

    
}
