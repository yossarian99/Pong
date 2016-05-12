
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yossarian
 */
public class Paint extends Canvas implements Etap , Runnable {
    public BufferStrategy strategia;
    private ArrayList act;
    int Pg;
     public Object tmp;
     
    int Pai;
   boolean watek =true;
public Paint(BufferStrategy k){
    strategia=k;
    
    Thread thread = new Thread(this);
    thread.start();
    
    


}
public void sets(BufferStrategy a){
    strategia=a;
}
public void scena(boolean a){
    watek=a;
}
   public synchronized void addd (ArrayList k){
  
   act=(ArrayList)k.clone();}
   public synchronized void addp(int a,int b){
       Pai=a;
       Pg=b;
   }
   
    public void run() {
   while(watek)  {   
        Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
g.setColor(Color.black);
g.fillRect(0,0,getWidth(),getHeight());
g.setColor(Color.WHITE);


Piłka m =(Piłka) act.get(0);
Paletka p =(Paletka)act.get(1);
Baner b =(Baner)act.get(2);


m.paint(g);
p.paint(g);
b.paint(g);

g.setColor(Color.white);
        
g.drawString("Punkty gracza -"+String.valueOf(Pg),Etap.SZEROKOSC/2/2-30,Etap.WYSOKOSC-50);
        
g.drawString("Punkty Komputera -"+String.valueOf(Pai),Etap.SZEROKOSC/2+Etap.SZEROKOSC/2/2,Etap.WYSOKOSC-50);

strategia.show();
   } 
      
    }

   

    @Override
    public void EtapOverOn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EtapOverOff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gameOver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
