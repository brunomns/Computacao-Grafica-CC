/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagebasics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author bmnso
 */
public class ImageBasics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        hsi();
       
    }
    public static void ycbcr(){
         try {//Colocar o caminho da imagem
            File f = new File("D:\\imgsAula\\fire1.jpg");
            BufferedImage img = ImageIO.read(f);
            BufferedImage yimage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType()); 
            BufferedImage cbImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType()); 
            BufferedImage crImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType()); 
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    Color c = new Color(img.getRGB(x, y));
                    int rgb = img.getRGB(x, y);
                    //System.out.println("");
                    //System.out.print("Pos("+x+","+y+")");
                    //System.out.print(" RGB: " + rgb);
                    //System.out.print(" RGB Hex: " + Integer.toHexString(rgb));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                   
                    int colorNew;                                                          
                    
                    double chY = (double)r*0.2568 + (double)g*0.5041 + (double)b*0.0979 + 16;
                    double chCb = -(double)r*0.1482 - (double)g*0.2910 + (double)b*0.4392 + 128;
                    double chCr = (double)r*0.4392 - (double)g*0.3678 - (double)b*0.0714 + 128; 
                    
                    //Normalizando
                    double nY = ((chY-16)/218)*255;
                    double nCb = ((chCb-16)/224)*255;
                    double nCr = ((chCr-16)/224)*255;
                    
                    int newColorY = (int)nY;
                    int newColorCb = (int)nCb;
                    int newColorCr = (int)nCr;
                    
                    //System.out.println("valor -- "+valor);
                    //colorNew = (int)valor;
                                        
                    Color novaCorY = new Color(newColorY,newColorY,newColorY);
                    yimage.setRGB(x, y, novaCorY.getRGB());
                    Color novaCorCb = new Color(newColorCb,newColorCb,newColorCb);
                    cbImage.setRGB(x, y, novaCorCb.getRGB());
                    Color novaCorCr = new Color(newColorCr, newColorCr, newColorCr);
                    crImage.setRGB(x, y, novaCorCr.getRGB());
                    
                    
                    
                    //System.out.print(" R= " + r + " G= " + g + " B= " + b);
                }
            }
            File out = new File("D:\\imgsAula\\fire1Y.jpg");
            ImageIO.write(yimage, "JPG", out);
            
            File outG = new File("D:\\imgsAula\\fire1Cb.jpg");
            ImageIO.write(cbImage, "JPG", outG);
            
            File outB = new File("D:\\imgsAula\\fire1Cr.jpg");
            ImageIO.write(crImage, "JPG", outB);
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    public static void hsi(){
        try {//Colocar o caminho da imagem
            File f = new File("D:\\imgsAula\\fire1.jpg");
            BufferedImage img = ImageIO.read(f);
            BufferedImage himage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType()); 
            BufferedImage sImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType()); 
            BufferedImage iImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType()); 
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    Color c = new Color(img.getRGB(x, y));
                    int rgb = img.getRGB(x, y);
                    //System.out.println("");
                    //System.out.print("Pos("+x+","+y+")");
                    //System.out.print(" RGB: " + rgb);
                    //System.out.print(" RGB Hex: " + Integer.toHexString(rgb));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                   
                    double r0 = (double)r/255;
                    double g0 = (double)g/255;
                    double b0 = (double) b/255;
                    
                    double teta = Math.acos((0.5*((r0-g0)+(r0-b0)))/(Math.pow(Math.pow(r0-g0,2)+(r0-b0)*(g0-b0),0.5)));
                    
                    double h;
                    double s;
                    double i;
                    if (b<=g)
                        h = teta;
                    else
                        h = 2*Math.PI - teta;
                    
                    i = ((double)r0 + (double)g0 + (double)b0)/3;
                    
                    double min;
                    if(r0<g0 && r0<b0)
                        min = r0;
                    else if (g0<r0 && g0<b0)
                        min = g0;
                    else
                        min = b0;
                    
                    if(r0!=0 && g0!=0 && b0!=0)
                        s = 1 - (3/(r0+g0+b0))*min;
                    else
                        s=0;
                    s = s<0?0:s;
                    s = s>255?255:s;
                    
                    h = (h/(2*Math.PI))*255f;
                    s = (s*255f);
                    i = (i*255f);
                    //dSystem.out.println(h+" "+s+" "+i);
                    //System.out.println("valor -- "+valor);
                    //colorNew = (int)valor;
                    int newColorH = (int)h;
                    int newColorS = (int) s;
                    int newColorI = (int) i;
                                        
                    Color novaCorH = new Color(newColorH,newColorH,newColorH);
                    himage.setRGB(x, y, novaCorH.getRGB());
                    Color novaCorS = new Color(newColorS,newColorS,newColorS);
                    sImage.setRGB(x, y, novaCorS.getRGB());
                    Color novaCorI = new Color(newColorI, newColorI, newColorI);
                    iImage.setRGB(x, y, novaCorI.getRGB());
                    
                    
                    
                    //System.out.print(" R= " + r + " G= " + g + " B= " + b);
                }
            }
            File out = new File("D:\\imgsAula\\fire1H.jpg");
            ImageIO.write(himage, "JPG", out);
            
            File outG = new File("D:\\imgsAula\\fire1S.jpg");
            ImageIO.write(sImage, "JPG", outG);
            
            File outB = new File("D:\\imgsAula\\fire1I.jpg");
            ImageIO.write(iImage, "JPG", outB);
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
