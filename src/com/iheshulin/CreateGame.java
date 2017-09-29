package com.iheshulin;


import com.google.gson.Gson;

import java.awt.*;
import java.net.Socket;

/**
 * Created by HeShulin on 2017/9/27.
 */
public class CreateGame{
    Socket socket1 = null;
    Socket socket2 = null;
    int socketNum1;
    int socketNum2;
    GetData getOneData = null;
    GetData getTwoData = null;
    JsonData character1,character2 = null;
    private Gson gson = new Gson();

    public void initGame(Socket socket1,Socket socket2,int socketNum1,int socketNum2){
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.socketNum1 = socketNum1;
        this.socketNum2 = socketNum2;
    }

    public void start(){
        character1 = new JsonData(1,new Point(0,0),"",1);
        character2 = new JsonData(1,new Point(0,0),"",2);
        PostData.sendData(socket1,gson.toJson(character1));
        PostData.sendData(socket2,gson.toJson(character2));

        getOneData = new GetData(socket1,socket2);
        getTwoData = new GetData(socket2,socket1);

        getOneData.start();
        getTwoData.start();
    }

}
