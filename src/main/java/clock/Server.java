/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

/**
 *
 * @author TAN TAI
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo server socket để lắng nghe kết nối từ client
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running and waiting for client connection...");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                // Tạo luồng đọc từ client
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Tạo luồng ghi tới client
                PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);

                String input;
                while ((input = clientReader.readLine()) != null) {
                    if (input.equals("time")) {
                        // Gửi thời gian hiện tại tới client
                        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                        clientWriter.println(currentTime);
                    }
                }

                // Đóng kết nối với client
                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
