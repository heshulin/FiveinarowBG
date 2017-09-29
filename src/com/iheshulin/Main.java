package com.iheshulin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Main {
    public static ServerSocket serverSocket = null;
    public static Vector<Integer>socketState = new Vector<>();
    public static Vector<Socket>socketList = new Vector<>();
    public static Socket socket = null;
    public static void main(String[] args) {
	    // write your code here
        try{
            serverSocket = new ServerSocket(5417);
        }catch(IOException e){
            e.printStackTrace();
        }
        while (true){
            try{
                System.out.println("正在等待链接");
                socket = serverSocket.accept();
            }catch (IOException e) {
                e.printStackTrace();
            }
            boolean matchFlag = false;
            for(int i=0;i<socketList.size();i++){
                if(socketState.get(i)==1){

                    matchFlag = true;
                    socketState.set(i,2);
                    socketList.add(socket);
                    socketState.add(2);
                    CreateGame createGame1 = new CreateGame();
                    createGame1.initGame(socketList.get(i),socket,i,socketList.size()-1);
                    createGame1.start();
                }
            }
            if(matchFlag==false){
                socketList.add(socket);
                socketState.add(1);
            }

        }
    }
}
