/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import controller.ConexaoController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Pedro Müller
 */
public class CosturaClienteDesktop {

    public static ConexaoController ccont;

    public static void main(String[] args) {
        Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;

        try {
            socket = new Socket("localhost", 12345);

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Cliente conectado no servidor");

            ccont = new ConexaoController(out, in);

            FormLogin formLogin = new FormLogin();
            formLogin.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
