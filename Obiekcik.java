/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kuba
 */


import java.awt.Graphics2D;

public class Obiekcik {
protected int x,y;

protected static int Szerokosc;
protected static  int Wysokosc;

protected Etap etap;
//protected SpriteCache spriteCache;
public Obiekcik(Etap etap) {
this.etap = etap;

//spriteCache = stage.getSpriteCache();
}



public int getX() { return x; }
public void setX(int i) { x = i; }
public int getY() { return y; }
public void setY(int i) { y = i; }

public static int getHeight() { return Wysokosc; }
public static int getWidth() { return Szerokosc; }
public void setHeight(int i) {Wysokosc = i; }
public void setWidth(int i) { Szerokosc = i; }
public void act() { }
public void paint(Graphics2D g){
   
}
}


    
