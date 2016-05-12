/*
 * To change this template, choose Tools | Templates
 * and open the templa*/
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import java.awt.Color;


import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 *
 * @author Kuba
 */
public class PingPong extends Canvas implements Etap ,KeyListener{
private static ArrayList actors;
public long usedTime;
public long usedTime2;
public static BufferStrategy strategia;
public BufferStrategy w;
public boolean Ptime=true;
long czas;
public static int Pgracza,PAI; 
private boolean gameEnded=false;
private boolean etapOver=false;
public boolean runda=false;
public Paint obj;
public static boolean watek;
public PingPong() {
    ThreadPool threadPool = new ThreadPool();

        // Uruchomienie przyk³adowych w¹tków:
        
            threadPool.runTask(createTask(1));
        
        // Zamkniêcie puli i oczekiwanie na zakoñczenie wszystkich zadañ.
        threadPool.join();
         
  
    JFrame okno = new JFrame("PingPong");
    JPanel panel = (JPanel)okno.getContentPane();
    setBounds(0,0,Etap.SZEROKOSC,Etap.WYSOKOSC);
//panel.setPreferredSize(new Dimension(Etap.SZEROKOSC,Etap.WYSOKOSC));
panel.setLayout(null);
panel.add(this);
addKeyListener(this);
okno.setBounds(0,0,Etap.SZEROKOSC,Etap.WYSOKOSC);
okno.setVisible(true);
okno.addWindowListener( new WindowAdapter() {
public void windowClosing(WindowEvent e) {
System.exit(0);
}
});
okno.setResizable(false);
 createBufferStrategy(2);
strategia = getBufferStrategy();
requestFocus();
 
 Pgracza=0;
PAI=0;   
ArrayList aktors = new ArrayList();
Piłka m = new Piłka(this);
Paletka p = new Paletka(this);
Baner b = new Baner(this);
aktors.add(m);
aktors.add(p);
aktors.add(b);

    
 
  return;
}
public void initWorld() {

Piłka m =(Piłka)actors.get(0);
Paletka p =(Paletka)actors.get(1);
Baner b =(Baner)actors.get(2);

m.setX((int)(Etap.SZEROKOSC/2)) ;
m.setY((int)(Etap.WYSOKOSC/2) );
p.setX((int)(Etap.WYSOKOSC/2-p.getHeight()));
if(!kogoserw()){
 m.setVx( -((int)(Math.random()*3)+3 ));   
}
else m.setVx( (int)(Math.random()*3)+3 );

m.setVy( (int)(Math.random()*3)+3 );
b.setVy(4);


}





public void paintEnd(){
       
  Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
  while(true){
g.setColor(Color.black);
g.fillRect(0,0,getWidth(),getHeight());
g.setColor(Color.WHITE);
g.setFont(new Font("Arial",Font.BOLD,20));
g.drawString("Koniec Gry",Etap.SZEROKOSC/2-30,Etap.WYSOKOSC/2/2);
if(Pgracza>PAI){
    g.drawString("Zwyciężyłeś !!!",Etap.SZEROKOSC/2-80,Etap.WYSOKOSC/2);
}
else g.drawString("Zwycieżył komputer",Etap.SZEROKOSC/2-80,Etap.WYSOKOSC/2);
g.drawString("Punkty gracza "+String.valueOf(Pgracza),Etap.SZEROKOSC/2/2,Etap.WYSOKOSC/2+Etap.WYSOKOSC/2/2);
g.drawString("Punkty Komputera "+String.valueOf(PAI),Etap.SZEROKOSC/2+Etap.SZEROKOSC/2/2,Etap.WYSOKOSC/2+Etap.WYSOKOSC/2/2);
strategia.show();
 try {
  
                    Thread.sleep(Etap.Napisy);



                    } catch (InterruptedException e) {}

}
}





public void gameOver() { gameEnded = true;}
public void EtapOverOff(){etapOver = true;}
public void EtapOverOn(){etapOver = false;}
public boolean Statusgry(){return gameEnded;}
public void rundagracza(){runda=false;}
public boolean kogoserw(){return runda;}
public void rundaAI(){runda=true;}
 public synchronized void updateWorld() {

Piłka m = (Piłka)actors.get(0);
Paletka p =(Paletka)actors.get(1);
Baner b =(Baner)actors.get(2);


p.act();

m.act(p,b);
b.act(m);
if (m.czykolizjaP(p)) Pgracza+=1;
if (m.czykolizjaBaner(b)) PAI+=1;
if(m.strataGracza()){
    EtapOverOn();
    Pgracza-=1;
    rundaAI();
}
if(m.strataAI()){
    EtapOverOn();
    PAI-=1;
    rundagracza();
}

if(Pgracza<0)Pgracza=0;
if(PAI<0)PAI=0;
if(Pgracza>=10) gameOver();
if(PAI>=10) gameOver();
  
  
}
 
 public void game() {

while (!gameEnded){
        initWorld();
        
                while (isVisible()&&(etapOver)) {
                    if(gameEnded)EtapOverOff();
                        


                   updateWorld();
                 
               

                 
                    
                   /* try {
  
                    Thread.sleep(10);



                    } catch (InterruptedException e) {}
*/
}

EtapOverOff();

}
paintEnd();
}
public void  keyPressed(KeyEvent e) {
 Paletka p =(Paletka)actors.get(1);   
p.keyPressed(e);
}public void keyReleased(KeyEvent e) {
    Paletka p =(Paletka)actors.get(1);
p.keyReleased(e);
}
public void keyTyped(KeyEvent e) {

}

 private static Runnable createTask(final int taskID) {
        return new Runnable() {
            public void run() {
            if (taskID==1){
              while(watek)  {   
        Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
g.setColor(Color.black);
g.fillRect(0,0,Etap.SZEROKOSC,Etap.WYSOKOSC);
g.setColor(Color.WHITE);


Piłka m =(Piłka) actors.get(0);
Paletka p =(Paletka)actors.get(1);
Baner b =(Baner)actors.get(2);


m.paint(g);
p.paint(g);
b.paint(g);

g.setColor(Color.white);
        
g.drawString("Punkty gracza -"+String.valueOf(Pgracza),Etap.SZEROKOSC/2/2-30,Etap.WYSOKOSC-50);
        
g.drawString("Punkty Komputera -"+String.valueOf(PAI),Etap.SZEROKOSC/2+Etap.SZEROKOSC/2/2,Etap.WYSOKOSC-50);

strategia.show();
 try {
  
                    Thread.sleep(100);



                    } catch (InterruptedException e) {}
   }   
                
                
                
                
                
                
            }
            }
        };
    }





    public static void main(String[] args) {
        // TODO code application logic here
        PingPong inv = new PingPong();
      //
      // obraz_start.scena(true);
       inv.game();
    }
}
