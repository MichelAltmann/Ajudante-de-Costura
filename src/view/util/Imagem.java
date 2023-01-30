/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.util;

import java.io.File;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author aluno
 */
public class Imagem {
    public static final int LARGURA = 150;
    public static final int ALTURA = 85;
    
    private byte[] imagem;

    public Imagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
    public byte[] getImagem() {
        return imagem;
    }
    
    public Imagem(File file){
        this.imagem = ImageToByte(file);
    }
    
    private byte[] ImageToByte(File file){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;){
                    bos.write(buf, 0, readNum);
                }
                    
                
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        byte[] bytes = bos.toByteArray();
        
        return bytes;
    }
    
    private ImageIcon ScaleImage(Dimension media){
        return new ImageIcon(new ImageIcon(imagem).getImage().getScaledInstance(media.width, media.height, java.awt.Image.SCALE_SMOOTH));
    }
    
    public ImageIcon getImageIcon(){
        try {
            BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(imagem));
            
            //calculando o aspect ratio
            
            float imageAspect = (float) bimg.getWidth() / (float) bimg.getHeight();
            
            float canvasAspect = (float) LARGURA / (float) ALTURA;
            
            int imgWidth = 150;
            int imgHeight = 150;
            
            if (imageAspect < canvasAspect){
                //se o aspect ratio da imagem for menor que o da tela altera a largura
                float w = (float) ALTURA * imageAspect;
                imgWidth = (int) w;
            } else {
                //senão alterar a altura
                float h = (float) LARGURA * imageAspect;
                imgHeight = (int) h;
            }
            Dimension dimensao = new Dimension (imgWidth, imgHeight);
            return ScaleImage(dimensao);
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
