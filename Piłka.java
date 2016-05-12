/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics2D;


/**
 *
 * @author Kub
 */
    public class Piłka extends Obiekcik {
protected int vx;
protected int vy;

protected boolean kol1;
protected boolean kol2;

protected int Punkty;
public Piłka(Etap etap) {
super(etap);

this.setHeight(40);
this.setWidth(40);

}
public void act(Paletka p,Baner b) {
 if(czykolizjaP(p)){
     if(p.getVy()>0&vy>0){
      vy+=1;
      if(vx>0) vx+=1;
      else vx-=1;
     }
         
     if(p.getVy()<0&vy<0){
       vy-=1;
       if(vx>0)vx+=1;
       else vx-=1;
     }
         
 
     if(p.getVy()>0&vy<0){
          vy-=1;
          if(vx>0)vx+=1;
       else vx-=1;
     }
        
     if(p.getVy()>0&vy<0){
       vy+=1;
       if(vx>0)vx+=1;
       else vx-=1;
     }
         
     
     if(vy<-6) vy=-4;
     if(vy>6)  vy=4;
     if(vx<-6) vx=-4;
     if(vx>6)  vx=4;
     x=p.getWidth()+1;
     vx=-vx;
 }
 if(czykolizjaBaner(b)){
     vx=-vx;
     x=x-b.getWidth()-1;
 }
     

  

if(x<0){
    x=0;
    vx=-vx;
}
if(x>Etap.SZEROKOSC){
    x=Etap.SZEROKOSC-getWidth();
    vx=-vx;
}

if(y<0){
    y=0;
    vy=-vy;
}
if(y+vy>Etap.WYSOKOSC-getHeight()){
    y=Etap.WYSOKOSC-getHeight();
    vy=-vy;
}
x += vx;
y += vy;
}
public int getVx() { return vx; }
public void setVx(int i) {vx = i; }
public int getVy(){return vy;}
public void setVy(int i){vy=i;}
public void setkol1(boolean k){kol1=k;}
public boolean getkol1(){return kol1;}
public void setkol2(boolean k){kol2=k;}
public boolean getkol2(){return kol2;}
public void setPunkty(int i) {Punkty = i; }
public int getPunkty(){return Punkty;}

public void paint(Graphics2D g){
    g.setColor(Color.red);
g.fillOval(x, y,40,40);
}
public boolean czykolizjaP(Paletka p){
 if((getX()<=p.getWidth())&&((getY()+getHeight()>=p.getY())&(getY()<=p.getY()+p.getHeight())))   
        
       
        kol1=true;
    
   else kol1=false;
   return kol1;
    
}


public boolean czykolizjaBaner(Baner p){
 if((getX()+getWidth()>=Etap.SZEROKOSC-p.getWidth())&&((getY()+getHeight()>=p.getY())&(getY()<=p.getY()+p.getHeight())))   
 kol2=true;
    
   else kol2=false;
   return kol2;
    
}
public boolean strataGracza(){
    if(x<=0) return true;
    else return false;
}
public boolean strataAI(){
    if(x+getWidth()>=Etap.SZEROKOSC) return true;
    else return false;
}
    }


    
