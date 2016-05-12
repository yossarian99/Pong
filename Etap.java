/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kuba
 */

    import java.awt.image.ImageObserver;
public interface Etap extends ImageObserver {
public static final int SZEROKOSC = 800;
public static final int WYSOKOSC = 600;
public static final int SZYBKOSC = 5;
public static final double Vgracza =.50;
public static final int Napisy=10;

public void EtapOverOn();
public void EtapOverOff();
public void gameOver();
    
}
