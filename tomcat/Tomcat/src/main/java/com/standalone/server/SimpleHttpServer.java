package com.standalone.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static int DEFAULT_PORT = 9000; // Default port for the server
    public static int port; // Current port the server is running on
    private HttpServer server; // The HttpServer instance

    private void start(int port) {
        this.port = port; // Set the current port
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0); // backlog: 0
            System.out.println("server started at " + port); // Print server start message

            server.createContext("/", new Handlers.RootHandler()); // Root path handler
            server.createContext("/echoHeader", new Handlers.EchoHeaderHandler()); // Echo headers handler
            server.createContext("/echoGet", new Handlers.EchoGetHandler());     // Echo GET handler
            server.createContext("/echoPost", new Handlers.EchoPostHandler());   // Echo POST handler

            server.setExecutor(null); // Use default executor
            server.start(); // Start the server

        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace on error
        }
    }

    public static void main(String[] args) {
        SimpleHttpServer httpsServer = new SimpleHttpServer(); // Create a new server instance
        httpsServer.start(DEFAULT_PORT); // Start the server on the default port
    }
}