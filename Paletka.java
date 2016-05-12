/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kuba
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Paletka extends Obiekcik /*implements KeyListener*/ {
 protected int vy; 
 protected static final int Vpaletka = 4;
 private   boolean up,down;
 
    public Paletka(Etap etap) {
super(etap);
//addKeyListener(this);
this.setHeight(120);
this.setWidth(20);

}
public void act() {
  
y += vy;

if(y<0||y+getHeight()> Etap.WYSOKOSC )vy=-vy;
if(y<0) y=0;
if(y+getHeight()>Etap.WYSOKOSC)y=Etap.WYSOKOSC-getHeight();

}
public int getVy(){return vy;}
public void setVy(int i){vy=i;}
public void paint(Graphics2D g){
    g.setColor(Color.blue);
g.fillRect(0, y, getWidth(), getHeight());
}
protected  void updateSpeed() {
vy=0;
if (down) vy = Vpaletka;
if (up) vy = -Vpaletka;

}
public void keyReleased(KeyEvent e) {
switch (e.getKeyCode()) {
case KeyEvent.VK_DOWN : down = false; break;
case KeyEvent.VK_UP : up = false; break;

}
updateSpeed();
}
public  void keyPressed(KeyEvent e) {
switch (e.getKeyCode()) {
case KeyEvent.VK_UP : up = true; break;
case KeyEvent.VK_DOWN : down = true;break;

}updateSpeed();
}
public void keyTyped(KeyEvent e) {}    

}
