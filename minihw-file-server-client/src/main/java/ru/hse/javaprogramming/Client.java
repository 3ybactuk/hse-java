package ru.hse.javaprogramming;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Checking args
        if (args.length < 3) {
            System.out.println("Error: You must provide server address, port, and save folder to command arguments!");
            return;
        }
        String serverAddress = args[0];

        String[] splittedAddress = serverAddress.split("\\.");

        boolean isAddressCorrect = true;

        for (String address : splittedAddress) {
            try {
                int i = Integer.parseInt(address);
                if (i < 0 || i > 255) {
                    isAddressCorrect = false;
                    break;
                }
            } catch (NumberFormatException e) {
                isAddressCorrect = false;
                break;
            }
        }

        // Almost correctly checks the server address to be either a string or an ip address of the 0.0.0.0 format.
        if (splittedAddress.length != 1 && (splittedAddress.length != 4 || !isAddressCorrect)) {
            System.out.println("Error: incorrect server address!");
            return;
        }

        int serverPort;

        try {
            serverPort = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: incorrect server port!");
            return;
        }

        Path saveFolderPath = Paths.get(args[2]);

        // Actual interaction with the server
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Connected to the server: " + serverAddress + ":" + serverPort);
            System.out.println();
            System.out.println("File list:");

            dataOutputStream.writeUTF("GET_IMAGE_LIST");
            dataOutputStream.flush();

            int serverReturnCode = dataInputStream.readInt();
            if (serverReturnCode != 200) {
                System.out.println("Can't get image list! Error " + serverReturnCode);
            } else {
                System.out.println(dataInputStream.readUTF());
            }

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose the number of a file to be downloaded:");
                System.out.print("> ");

                if (!scanner.hasNext()) {
                    dataOutputStream.writeUTF("EXIT");
                    dataOutputStream.flush();
                    break;
                }

                String input = scanner.nextLine();
                if (input.equals("exit")) {
                    dataOutputStream.writeUTF("EXIT");
                    dataOutputStream.flush();
                    break;
                }

                int fileNumber;
                try {
                    fileNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Error: you must provide the number of a file!");
                    continue;
                }

                String request = "GET_IMAGE " + fileNumber;
                dataOutputStream.writeUTF(request);
                dataOutputStream.flush();

                serverReturnCode = dataInputStream.readInt();
                if (serverReturnCode != 200) {
                    System.out.println("Can't download image! Error " + serverReturnCode);
                    continue;
                }
                String fileName = dataInputStream.readUTF();
                int imageSize = dataInputStream.readInt();
                byte[] imageData = new byte[imageSize];
                dataInputStream.readFully(imageData);

                Path imagePath = saveFolderPath.resolve(fileName);
                Files.createDirectories(imagePath.getParent());

                try (FileOutputStream fileOutputStream = new FileOutputStream(imagePath.toFile())) {
                    fileOutputStream.write(imageData);
                }

                System.out.println("Image received and saved: " + fileName);
                System.out.println(imagePath);
            }

        } catch (IOException e) {
            System.out.println("Couldn't connect to server!");
        }

        System.out.println("Goodbye!");
    }
}
