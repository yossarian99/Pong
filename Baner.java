
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
public class Baner extends Obiekcik {
     protected int Punkty=100;
     protected int vy; 
     public double impactDistance, impactTime, targetY, speed;
    
    public Baner(Etap etap){
        super(etap);
        setHeight(120);
        setWidth(30);
        setX(Etap.SZEROKOSC-getWidth());
        setY((int)(Etap.WYSOKOSC/2-getHeight()));
       speed = .25;
        
    
    
 
}
    public void paint(Graphics2D g){
    g.setColor(Color.PINK);
g.fillRect(getX(),getY(), getWidth(), getHeight());
g.setColor(Color.BLACK);
g.drawString(String.valueOf(Punkty),getX()+1,getY()+getHeight()/2);
}
public int getP(){
    return Punkty;
}    
public void setP(int p){
    Punkty=p;
}
public int getVy(){return vy;}
public void setVy(int i){vy=i;}
public void act(Piłka p) {
if (p.getVx() < 0) {
       
       speed=0;
    } 
else speed =Etap.Vgracza;
impactDistance = Etap.SZEROKOSC - p.getWidth() - p.getX();
    impactTime = impactDistance / (p.getVx() * .25 * 1000);
    targetY = p.getY() + (p.getVy() * .25 * 1000) * impactTime;
  if (Math.abs(targetY - (getY() + getHeight()/2)) < 10) {
        
        speed =0;
    } 
  else speed =Etap.Vgracza;
  if (targetY < getY() + (getHeight() / 2)) {
       
        speed = -speed;
    }
  y += speed * vy;


if(y<0) y=0;
if(y+getHeight()>Etap.WYSOKOSC)y=Etap.WYSOKOSC-getHeight();

}

    
}

