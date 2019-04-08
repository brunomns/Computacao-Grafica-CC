/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagebasics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author bmnso
 */
public class ImgBasicOps {
    /*Ler a imagem pixel a pixel*/        
    public static void main(String[] args) {
        
        int histogram[] = new int[256];        
        int histR[] = new int[256];        
        int histG[] = new int[256]; 
        int histB[] = new int[256]; 
        for (int i = 0; i < histogram.length; i++) {
            histogram[i]=0;
            
        }
        try{
            File fimage = new File("D:\\imgsAula\\frutas.jpg");
            BufferedImage img = ImageIO.read(fimage);
            
            
            
            BufferedImage outImg = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
            
            int w = img.getWidth();
            int h = img.getHeight();
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int cRGB = img.getRGB(i, j);
                    Color c = new Color(cRGB);
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();                                        
                    int limiar = 93;
                    
                    histogram[g]++;
                    
                    //int rgbVal = 0xff00ff00;
                    Color newCor;
                    if(g>limiar)
                        newCor = new Color(255,255,255);
                    else
                        newCor = new Color(0,0,0);
                    outImg.setRGB(i, j, newCor.getRGB());                 
                }                                
            }
            
            for (int i = 0; i < histogram.length; i++) {
                int j = histogram[i];
                System.out.println("Cor "+i+" = "+j);                
            }
            //escala de cinza 30% vermelho 59% verde e 11% azul
            File out = new File ("D:\\imgsAula\\outfrutasBinaria.jpg");
            ImageIO.write(outImg, "jpg", out);
        }
        catch (IOException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        
    }
    
}
