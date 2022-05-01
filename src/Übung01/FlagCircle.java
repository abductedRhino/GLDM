package Übung01;

public class FlagCircle {
    int xPos;
    int yPos;
    int radius;

    public FlagCircle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getRadius() {
        return radius;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isInsideCircle(int x, int y) {
        //Is the pixel (x,y) inside (not just touching) the circle?
        return (x-this.xPos)*(x-this.xPos)+(y-this.yPos)*(y-this.yPos) < this.radius*this.radius;
    }
}

