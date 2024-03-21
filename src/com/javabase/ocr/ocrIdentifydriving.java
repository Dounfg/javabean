package com.javabase.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class ocrIdentifydriving {
    private static final String strUrl =
        "http://10.32.20.95:8080//OcrWeb/servlet/OcrServlet";
    //        "http://localhost:8080/EtOcrWeb/servlet/PosOcrServlet ";
    private static final String strocrUrl =
        "http://localhost:9010/orc/identifyocr";

    public static void main(String[] args) {
        String strFilep = "C://temp/id/1.jpg";
        String strpid = "2";//pid
        File file = new File(strFilep);
        FileInputStream is;
        try {
            is = new FileInputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            String base64file = Base64.encode(data);
            System.out.println("************************************************");
            System.out.println(base64file);
            System.out.println("************************************************");
            String aa = URLEncoder.encode(base64file, "utf-8");
            System.out.println(aa);
            System.out.println("************************************************");
            String params = "filedata="
                + URLEncoder.encode(base64file, "utf-8");
            params+="&pid="+URLEncoder.encode(strpid, "utf-8");
            String readByGet = readStringByPOST(strUrl, params);
            System.out.println(readByGet);

//            OcrIdentifyReq req = new OcrIdentifyReq();
//            req.setPid("2");
//            req.setFiledata(base64file);
//
//            String params = "filedata="
//                + URLEncoder.encode(base64file, "utf-8");
//            params+="&pid="+URLEncoder.encode(strpid, "utf-8");
//            String readByGet = readByPOST(strocrUrl, req);
            System.out.println(readByGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String readStringByPOST(String inUrl, String params)throws IOException {
        StringBuffer sbf = new StringBuffer();
        String strRead = null;
        URL url = new URL(inUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        PrintWriter  out = new PrintWriter(connection.getOutputStream());
        out.print(params);
        out.flush();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        connection.disconnect();
        return sbf.toString();
    }

    private static String readByPOST(String inUrl, OcrIdentifyReq params)throws IOException {
        StringBuffer sbf = new StringBuffer();
        String strRead = null;
        URL url = new URL(inUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        PrintWriter  out = new PrintWriter(connection.getOutputStream());
        out.print(JSON.toJSONString(params));
        out.flush();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        connection.disconnect();
        return sbf.toString();
    }
}