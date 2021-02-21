package com.company.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Api {
    static String HOST = "localhost";
    static int SRV_PORT = 65530;

    private static JSONObject SendnRecv(JSONObject jso) throws Exception {

        //connect
        Socket sc = new Socket(HOST, SRV_PORT);
        System.out.println("conected to " + HOST + ":" + SRV_PORT);
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
        System.out.println("received : " + json.toJSONString());
        dataOutputStream.close();
        sc.close();
        return json;
    }

    public static <T>  List<T> GetState() {
        JSONObject req = new JSONObject();
        JSONArray jsa = new JSONArray();
        Map m1 = new LinkedHashMap();
        m1.put("action","GetState");
        jsa.add(m1);
        req.put("pkg", jsa);
        List<T> states = null;
        JSONObject resp = null;
        System.out.println(req);
        try {
             resp = SendnRecv(req);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if(resp != null)
        {
            JSONArray js = (JSONArray) resp.get("pkg");
            Map x = (HashMap) js.get(0);
            states = (List<T>) x.get("response");
        }

        return states;
    }

    public static String PushChange(int nmbr)
    {
        JSONObject req = new JSONObject();
        JSONArray jsa = new JSONArray();
        Map m1 = new LinkedHashMap();
        m1.put("action","Put");
        m1.put("number", nmbr);
        jsa.add(m1);
        req.put("pkg", jsa);
        String response = null;
        JSONObject resp = null;
        System.out.println(req);
        try {
            resp = SendnRecv(req);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if(resp != null)
        {
            JSONArray js = (JSONArray) resp.get("pkg");
            Map x = (HashMap) js.get(0);
            response = (String) x.get("response");
        }

        return response;

    }

    public static void main(String[] args) throws Exception {
        //JSONParser parser = new JSONParser();
        //SendnRecv((JSONObject) parser.parse("{\"pkg\": [{ \"action\" : \"Put\", \"number\" : 3 }] }"));
        PushChange(1);
        PushChange(3);
        PushChange(5);
        PushChange(7);
        List<Integer> list = GetState();
        System.out.println("\nfinal result : ");
        System.out.println(Arrays.toString(list.toArray()));

    }

}
