package simplebean;
import java.beans.*; import java.io.Serializable;
import java.awt.*; //добавлено вручную
import javax.swing.*;

public class SimpleBean extends JComponent implements Serializable { private int mMouthWidth = 90;
private boolean mSmile = true;
private boolean mEyes = true;
private PropertyChangeSupport propertySupport; public SimpleBean() {
super();
propertySupport = new PropertyChangeSupport(this);
}

@Override
public void paint(Graphics g) { 
//метод добавлен вручную 
Graphics2D g2 = (Graphics2D)g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);
// Face
int w = getWidth(); int h = getHeight(); int pad = 12;
int cw = w - pad * 2; int ch = h - pad * 2;
g2.setColor(getBackground()); g2.fillArc(pad, pad, cw, ch, 0, 360); g2.setColor(getForeground()); g2.drawArc(pad, pad, cw, ch, 0, 360);
// Mouth
int sw = cw / 2; int sh = ch / 2;
 
if (mSmile == true)
g2.drawArc(w / 2 - sw / 2, h / 2 - sh / 2, sw, sh, 270 - mMouthWidth / 2, mMouthWidth);
else
g2.drawArc(w / 2 - sw / 2, h / 2 + sh / 3, sw, sh, 90 - mMouthWidth / 2, mMouthWidth);
// Eyes 

int er = 4;
g2.fillArc(w / 2 - cw * 1 / 8 - er / 2, h / 2 - ch / 4 - er, er, er, 0, 360);
g2.fillArc(w / 2 + cw * 1 / 8 - er / 2, h / 2 - ch / 4 - er, er, er, 0, 360);
        
if (mEyes == true){
            er = 6;
        g2.fillArc(w / 2 - cw * 1 / 8 - er / 2, h / 2 - ch / 4 - er, er, er, 0, 360);
        g2.fillArc(w / 2 + cw * 1 / 8 - er / 2, h / 2 - ch / 4 - er, er, er, 0, 360);}
else er = 4;
}

public int getMouthWidth() { return mMouthWidth;
}

public void setMouthWidth (int mw) { mMouthWidth = mw;
repaint();
}

public void smile() { //метод добавлен вручную 
    mSmile = true;
repaint();
}

public void frown() { //метод добавлен вручную 
    mSmile = false;
repaint();
}

/////////////////////////////

public void eyes() { //метод добавлен вручную 
    mEyes = true;
repaint();
}

public void feyes() { //метод добавлен вручную 
    mEyes = false;
repaint();
}
///////////////
public void addPropertyChangeListener(PropertyChangeListener listener) { 
    propertySupport.addPropertyChangeListener(listener);
}

public void removePropertyChangeListener(PropertyChangeListener listener){ 
    propertySupport.removePropertyChangeListener(listener);
}
}
