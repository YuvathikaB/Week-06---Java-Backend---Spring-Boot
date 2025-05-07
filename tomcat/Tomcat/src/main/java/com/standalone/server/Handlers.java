package com.standalone.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Handlers {

    public static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String response = "<h1>Welcome to SimpleHttpServer!</h1>" +
                    "<p>Try these paths:</p>" +
                    "<ul>" +
                    "<li><a href='/echoHeader'>/echoHeader</a> (Shows request headers)</li>" +
                    "<li><a href='/echoGet?param1=value1&param2=value2'>/echoGet?param1=value1&param2=value2</a> (Shows GET parameters)</li>" +
                    "<li><a href='/echoPost'>/echoPost</a> (Submit a POST request to see body)</li>" +
                    "</ul>";
            sendResponse(httpExchange, 200, "text/html", response);
        }
    }

    public static class EchoHeaderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Set<Map.Entry<String, java.util.List<String>>> headers = httpExchange.getRequestHeaders().entrySet();
            StringBuilder response = new StringBuilder("<h1>Request Headers:</h1>");
            for (Map.Entry<String, java.util.List<String>> header : headers) {
                response.append("<p>").append(header.getKey()).append(": ").append(header.getValue()).append("</p>");
            }
            sendResponse(httpExchange, 200, "text/html", response.toString());
        }
    }

    public static class EchoGetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            URI requestURI = httpExchange.getRequestURI();
            String query = requestURI.getQuery();
            Map<String, String> params = parseQuery(query);

            StringBuilder response = new StringBuilder("<h1>GET Parameters:</h1>");
            if (params.isEmpty()) {
                response.append("<p>No GET parameters found.</p>");
            } else {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    response.append("<p>").append(param.getKey()).append(": ").append(param.getValue()).append("</p>");
                }
            }
            sendResponse(httpExchange, 200, "text/html", response.toString());
        }

        private Map<String, String> parseQuery(String query) {
            Map<String, String> result = new HashMap<>();
            if (query == null || query.isEmpty()) {
                return result;
            }
            for (String param : query.split("&")) {
                int idx = param.indexOf("=");
                if (idx > 0) {
                    result.put(param.substring(0, idx), param.substring(idx + 1));
                } else {
                    result.put(param, "");
                }
            }
            return result;
        }
    }

    public static class EchoPostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }
            br.close();
            isr.close();

            String response = "<h1>POST Request Body:</h1><pre>" + requestBody.toString() + "</pre>" +
                    "<p>Try sending data using cURL:</p>" +
                    "<p><code>curl -X POST -d \"key1=value1&key2=value2\" http://localhost:9000/echoPost</code></p>" +
                    "<form action='/echoPost' method='post'>" +
                    "    <input type='text' name='post_data' placeholder='Enter POST data'><br>" +
                    "    <input type='submit' value='Send Post Data'>" +
                    "</form>";
            sendResponse(httpExchange, 200, "text/html", response);
        }
    }

    private static void sendResponse(HttpExchange httpExchange, int statusCode, String contentType, String responseBody) throws IOException {
        httpExchange.getResponseHeaders().set("Content-Type", contentType);
        httpExchange.sendResponseHeaders(statusCode, responseBody.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(responseBody.getBytes());
        os.close();
    }
}