package Übung01;

public class FlagCircle {
    int xPos;
    int yPos;
    int radius;
    int flagWidth;
    int flagHeight;

    public FlagCircle(int flagWidth, int flagHeight, int radius) {
        this.flagWidth = flagWidth;
        this.flagHeight = flagHeight;
        // position Circle in middle of Flag.
        this.xPos = flagWidth/2;
        this.yPos = flagHeight/2;
        this.radius = radius;
    }
    public FlagCircle(int flagWidth, int flagHeight) {
        this.flagWidth = flagWidth;
        this.flagHeight = flagHeight;
        // position Circle in middle of Flag.
        this.xPos = flagWidth/2;
        this.yPos = flagHeight/2;
        this.radius = flagHeight;
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

    public int getFlagWidth() {
        return flagWidth;
    }

    public int getFlagHeight() {
        return flagHeight;
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

    public void setFlagHeight(int flagHeight) {
        this.flagHeight = flagHeight;
    }

    public void setFlagWidth(int flagWidth) {
        this.flagWidth = flagWidth;
    }

    public void setGreenland() {
        setxPos(getFlagWidth()*7/18);
        setyPos(getFlagHeight()/2);
        setRadius(getFlagHeight()*4/12);
    }

    public boolean isInsideCircle(int x, int y) {
        //Is the pixel (x,y) inside (not just touching) the circle?
        return (x-this.xPos)*(x-this.xPos)+(y-this.yPos)*(y-this.yPos) < this.radius*this.radius;
    }
}

