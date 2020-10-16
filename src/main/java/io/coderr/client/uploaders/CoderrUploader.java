package io.coderr.client.uploaders;

import io.coderr.client.ErrorDispatcher;
import io.coderr.client.dto.ErrorReportDTO;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class CoderrUploader implements ReportUploader {
    private URL url;
    private String appKey;
    private String sharedSecret ;

    public CoderrUploader(URL coderrServer, String applicationKey, String sharedSecret){
        if (coderrServer == null){
            throw new IllegalArgumentException("coderrServer");
        }
        if (applicationKey == null){
            throw new IllegalArgumentException("applicationKey");
        }
        if (sharedSecret == null){
            throw new IllegalArgumentException("sharedSecret");
        }

        this.url = coderrServer;
        this.appKey=applicationKey;
        this.sharedSecret = sharedSecret;
    }

    @Override
    public void upload(ErrorReportDTO report) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");


        JSONObject jo = new JSONObject(demo);


        HttpURLConnection con;

        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            con.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();
        } catch (IOException e) {
            ErrorDispatcher.Instance.publish(this, e, "Failed to connect report.");
            return;
        }


        try {
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            ErrorDispatcher.Instance.publish(this, e, "Failed to get a response from the Coderr Server.");
            return;
        }


        con.disconnect();

    }
}

class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
