public class Convolucoes{

public BufferedImage convolucao(float[][] mascara, BufferedImage img){
        int imgNova[][] = new int[img.getWidth()][img.getHeight()];
        int min=999999;
        int max=-999999;
                       
        BufferedImage imgRes = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int w = img.getWidth();
        int h = img.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {                
                int soma=0;                
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        float val = mascara[i+1][j+1];
                        if(val!=0){
                            if(x+i>=0 && y+j>=0 && (x+i)< w && (y+j)<h){
                                Color c = new Color(img.getRGB(x+i, y+j));                                
                                int corRes = (c.getRed()*30 + c.getGreen()*59 + c.getBlue()*11)/100;                                                               
                                soma += corRes*val;                                                              
                            }//if
                        }//if                        
                    }//j - coluna da mascara                    
                }//i - linha da mascara
                
                float corRes = (float)soma;
                /*
                corRes = (corRes<0f)?corRes*(-1):corRes;
                corRes = (corRes>255f)?255f:corRes;
                */
                soma = (int)corRes;                                                     
                if(soma>max)
                    max = soma;
                if(soma<min)
                    min=soma;
                imgNova[x][y] = soma;                                   
            }//coluna da imagem            
        }//linha da imagem
        //NORMALIZANDO VALORES...
        
         for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {                
                float f = ((float)(imgNova[x][y] - min)/(float)(max-min))*255f;                
                int valorRGB = (int)f;
                valorRGB = (valorRGB<0)?valorRGB*(-1):valorRGB;
                valorRGB = (valorRGB>255)?255:valorRGB;
                
                Color corNOva = new Color(valorRGB,valorRGB,valorRGB);
                imgRes.setRGB(x, y, corNOva.getRGB());
            }
             //System.out.println("");
         }        
        return imgRes;
    }
        
    public BufferedImage convolucaoCor(float[][] mascara, BufferedImage img){
        BufferedImage imgRes = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int w = img.getWidth();
        int h = img.getHeight();
        
        int imgNova2[][][] = new int[img.getWidth()][img.getHeight()][3];
        
        int minR=999999;
        int maxR=-999999;
        
        int minG=999999;
        int maxG=-999999;
        
        int minB=999999;
        int maxB=-999999;
        
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {                
                int somaR=0;
                int somaG = 0;
                int somaB = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        float val = mascara[i+1][j+1];
                        if(val!=0){
                            if(x+i>=0 && y+j>=0 && (x+i)< w && (y+j)<h){
                                Color c = new Color(img.getRGB(x+i, y+j));                                
                                somaR += c.getRed()*val;
                                somaG += c.getGreen()*val;
                                somaB += c.getBlue()*val;
                            }//if
                        }//if                        
                    }//j - coluna da mascara                    
                }//i - linha da mascara
                /*             
                somaR= (somaR<0)?somaR*(-1):somaR;
                int valorR = (somaR>255)?255:somaR;
                
                somaG= (somaG<0)?somaG*(-1):somaG;
                int valorG = (somaG>255)?255:somaG;
                
                somaB= (somaB<0)?somaB*(-1):somaB;
                int valorB = (somaB>255)?255:somaB;
                */
                if(somaR>maxR)
                    maxR = somaR;
                if(somaR<minR)
                    minR=somaR;                               
                if(somaG>maxG)
                    maxG = somaG;
                if(somaG<minG)
                    minG=somaG;                               
                if(somaB>maxB)
                    maxB = somaB;
                if(somaB<minB)
                    minB=somaB;
                
                imgNova2[x][y][0] = somaR; 
                imgNova2[x][y][1] = somaG;
                imgNova2[x][y][2] = somaB;
                                                                                
                //Color corNOva = new Color(valorR,valorG,valorB);
                //imgRes.setRGB(x, y, corNOva.getRGB());
                
            }//coluna da imagem            
        }//linha da imagem
        
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                
                float fR = ((float)(imgNova2[x][y][0] - minR)/(float)(maxR-minR))*255f;
                //System.out.print(f+"|");
                int valorR = (int)fR+1;
                //valorR = (valorR<0)?valorR*(-1):valorR;
                valorR = (valorR>255)?255:valorR;
                
                float fG = ((float)(imgNova2[x][y][1] - minG)/(float)(maxG-minG))*255f;
                //System.out.print(f+"|");
                int valorG = (int)fG+1;
                //valorG = (valorG<0)?valorG*(-1):valorG;
                valorG = (valorG>255)?255:valorG;
                
                float fB = ((float)(imgNova2[x][y][2] - minB)/(float)(maxB-minB))*255f;
                //System.out.print(f+"|");
                int valorB = (int)fB+1;
                //valorB = (valorB<0)?valorB*(-1):valorB;
                valorB = (valorB>255)?255:valorB;
                
                
                Color corNOva = new Color(valorR,valorG,valorB);
                imgRes.setRGB(x, y, corNOva.getRGB());
            }
             //System.out.println("");
         }        
        return imgRes;
    }
}
