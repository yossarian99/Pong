
import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kuba
 */
public class BanerDown extends Obiekcik {
 
     protected int Punkty=100;
     protected int vx; 
    
    public BanerDown(Etap etap){
        super(etap);
        setHeight(30);
        setWidth(120);
        setX(Etap.SZEROKOSC-getWidth()-30);
        setY(Etap.WYSOKOSC-getHeight());
       
        
    
    
 
}
    public void paint(Graphics2D g){
    g.setColor(Color.GREEN);
g.fillRect(getX(),getY(), getWidth(), getHeight());
g.setColor(Color.BLACK);
g.drawString(String.valueOf(Punkty),getX()+getWidth()/2,getY()+getHeight()-2);
}
public int getP(){
    return Punkty;
}    
public void setP(int p){
    Punkty=p;
}
public int getVx(){return vx;}
public void setVx(int i){vx=i;}
public void act() {
  


if(x<30||x+getWidth()> Etap.SZEROKOSC-30 )vx=-vx;
if(x<30) x=30;
if(x+getWidth()>Etap.SZEROKOSC-30)x=Etap.SZEROKOSC-getWidth()-30;
x += vx;
}
}

    
   

