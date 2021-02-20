package com.company.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.Socket;

public class Api {
    static String HOST = "localhost";
    static int SRV_PORT = 65530;

    public static JSONObject SendnRecv(JSONObject jso) throws Exception {

        //connect
        Socket sc = new Socket(HOST, SRV_PORT);
        System.out.println("conected to " + HOST + ":"+SRV_PORT);
        //send
        OutputStream outputStream = sc.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        System.out.println("sending ...");
        dataOutputStream.writeUTF(jso.toJSONString());
        dataOutputStream.flush();
        System.out.println("send : " + jso.toJSONString());
        //recv
        InputStream inputStream = sc.getInputStream();
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(inputStream));
        String message = in.readLine();
        //convert to json object
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(message);
        //close
        System.out.println("received : "+ json.toJSONString());
        dataOutputStream.close();
        sc.close();
        return json;
    }

    public static void main(String [] args) throws Exception {
        JSONParser parser = new JSONParser();
        SendnRecv((JSONObject) parser.parse("{\"pkg\": [{ \"action\" : \"Put\", \"number\" : 3 }] }"));
        SendnRecv((JSONObject) parser.parse("{\"pkg\": [{ \"action\" : \"GetState\"}] }"));

    }

}
