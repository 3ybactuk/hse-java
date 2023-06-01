package ru.hse.javaprogramming;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private boolean isRunning = true;
    private int portNumber = 8888;
    private static final List<String> imageNames = getImageNames();

    public static void main(String[] args) {
        Server server = new Server();
        server.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
        }

        server.stopServer();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);
            printTimestampMessage("Server is listening on port " + serverSocket.getLocalPort());

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                printTimestampMessage("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }

            serverSocket.close();
        } catch (IOException e) {
            printTimestampMessage("Server shutting down...");
        }
    }

    public void stopServer() {
        isRunning = false;
        try {
            serverSocket.close();
            synchronized (this) {
                this.notify();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTimestampMessage(String message) {
        LocalTime currentTime = LocalTime.now();
        System.out.println("[" + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond() + "] " +
                message);
    }

    private static List<String> getImageNames() {
        List<String> images = new ArrayList<>();

        try {
            Enumeration<URL> e = ClassLoader.getSystemResources("images");
            URL url = null;
            while (e.hasMoreElements()) {
                url = e.nextElement();

            }
            System.out.println(url);
            String fileName = Paths.get(url.toURI()).toString();

            Path directoryPathObj = Paths.get(fileName);

            Files.walk(directoryPathObj)
                    .filter(Files::isRegularFile)
                    .forEach(file -> images.add(String.valueOf(file.getFileName())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return images;
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                boolean shouldExit = false;

                while (!shouldExit) {
                    String[] request = dataInputStream.readUTF().split(" ");

                    if (request.length < 1) {
                        printTimestampMessage("Invalid request from client");
                    }

                    switch (request[0]) {
                        case "GET_IMAGE_LIST": {
                            StringBuilder responseBuilder = new StringBuilder();

                            for (int i = 0; i < imageNames.size(); i++) {
                                responseBuilder.append(i).append(". ").append(imageNames.get(i)).append("\n");
                            }

                            String response = responseBuilder.toString();

                            dataOutputStream.writeInt(200);
                            dataOutputStream.writeUTF(response);
                            dataOutputStream.flush();

                            printTimestampMessage("Image list sent to the client");
                            break;
                        }
                        case "GET_IMAGE": {
                            if (request.length < 2) {
                                break;
                            }

                            int imageNumber = Integer.parseInt(request[1]);
                            if (imageNumber < 0 || imageNumber >= imageNames.size()) {
                                dataOutputStream.writeInt(404);
                                dataOutputStream.flush();
                                break;
                            }

                            String imagePath = "images/" + imageNames.get(imageNumber);
                            InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream(imagePath);
                            byte[] imageData = imageInputStream.readAllBytes();
                            imageInputStream.close();

                            dataOutputStream.writeInt(200);
                            dataOutputStream.writeUTF(imageNames.get(imageNumber));
                            dataOutputStream.writeInt(imageData.length);
                            dataOutputStream.write(imageData);
                            dataOutputStream.flush();

                            printTimestampMessage("Image sent to the client: " + imagePath);
                            break;
                        }
                        case "EXIT":
                            shouldExit = true;
                            break;
                        default: {
                            printTimestampMessage("Invalid request from client");
                        }
                    }
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            printTimestampMessage("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        }
    }
}
