/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

/**
 *
 * @author TAN TAI
 */
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClockGUI extends JFrame {
    private JLabel timeLabel;

    public ClockGUI() {
        setTitle("Clock");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel);

        setVisible(true);
        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                serverWriter.println("time");
                String serverResponse = serverReader.readLine();
                timeLabel.setText(serverResponse);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClockGUI::new);
    }
}