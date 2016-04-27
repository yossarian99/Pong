/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

private ArrayList actors;
public long usedTime;
public long usedTime2;
public BufferStrategy strategia;
public boolean Ptime=true;
long czas;
public int Pgracza,PAI; 
private boolean gameEnded=false;
private boolean etapOver=false;
public boolean runda=false;


public PingPong() {
    
    
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
actors = new ArrayList();
Piłka m = new Piłka(this);
Paletka p = new Paletka(this);
Baner b = new Baner(this);
actors.add(m);
actors.add(p);
actors.add(b);
}
public void initWorld() {
Piłka m =(Piłka) actors.get(0);
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
public void paintWorld() {
Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
g.setColor(Color.black);
g.fillRect(0,0,getWidth(),getHeight());
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
if (usedTime > 0)
g.drawString(String.valueOf(1000/usedTime)+" fps",0,Etap.WYSOKOSC-50);
else
g.drawString("--- fps",0,Etap.WYSOKOSC-50);
strategia.show();
}




public void gameOver() { gameEnded = true;}
public void EtapOverOff(){etapOver = true;}
public void EtapOverOn(){etapOver = false;}
public boolean Statusgry(){return gameEnded;}
public void rundagracza(){runda=false;}
public boolean kogoserw(){return runda;}
public void rundaAI(){runda=true;}
 public void updateWorld() {

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
                         usedTime=1000;
                            if (Ptime){
                                    Baner b =(Baner)actors.get(2);  
                                         if(b.Punkty>100) b.Punkty=0;
                                                else b.Punkty+=1;
                                Ptime=false;
    
                                }

                    long startTime = System.currentTimeMillis();

                    updateWorld();
                    paintWorld();

                    usedTime = System.currentTimeMillis()-startTime;
                    try {
  
                    Thread.sleep(Etap.SZYBKOSC);



                    } catch (InterruptedException e) {}
                usedTime2=System.currentTimeMillis()-startTime;
if(czas>1000){
    
    Ptime=true;
    czas=0;
}
else czas=czas+usedTime2;


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






    public static void main(String[] args) {
        // TODO code application logic here
        PingPong inv = new PingPong();
        inv.game();
    }
}
