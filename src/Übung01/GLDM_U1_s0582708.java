package Übung01;

import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class GLDM_U1_s0582708 implements PlugIn {

    final static String[] choices = {
            "Schwarzes Bild",
            "Niederländische Flagge",
            "Horiz.Türkis/Gelb",
            "Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf",
            "Flagge von Bhutan",
            "Flagge von Grönland"
    };

    private String choice;

    public static void main(String args[]) {
        ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
        ij.exitWhenQuitting(true);

        GLDM_U1_s0582708 imageGeneration = new GLDM_U1_s0582708();
        imageGeneration.run("");
    }

    public void run(String arg) {

        int width  = 566/3;  // Breite
        int height = 400/3;  // Hoehe

        // RGB-Bild erzeugen
        ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
        ImageProcessor ip = imagePlus.getProcessor();

        // Arrays fuer den Zugriff auf die Pixelwerte
        int[] pixels = (int[])ip.getPixels();

        dialog();

        ////////////////////////////////////////////////////////////////
        // Hier bitte Ihre Aenderungen / Erweiterungen

        if ( choice.equals("Schwarzes Bild") ) {
            generateBlackImage(width, height, pixels);
        } else if ( choice.equals("Niederländische Flagge") ) {
            generateNetherlandsFlagImage(width, height, pixels);
        } else if ( choice.equals("Horiz.Türkis/Gelb") ) {
            generateCyanYellowImage(width, height, pixels);
        } else if ( choice.equals("Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf") ) {
            generateBlackRedBlackBlueImage(width, height, pixels);
        } else if ( choice.equals("Flagge von Bhutan") ) {
            generateBhutanFlagImage(width, height, pixels);
        } else if ( choice.equals("Flagge von Grönland") ) {
            generateGreenlandFlagImage(width, height, pixels);
        }


        ////////////////////////////////////////////////////////////////////

        // neues Bild anzeigen
        imagePlus.show();
        imagePlus.updateAndDraw();
    }

    private void generateGreenlandFlagImage(int width, int height, int[] pixels) {
        //  int checkEquality1 = 0;
        //  int checkEquality2 = 0;
        //  Kreis innerhalb der Flagge festlegen, dabei wird erstmal nur die größe der Flagge definiert.
        FlagCircle voltobal = new FlagCircle(width, height);
        // Radius und Position des Kreises festlegen.
        voltobal.setGreenland();
        // Schleife ueber die y-Werte
        for (int y=0; y<height; y++) {
            // Schleife ueber die x-Werte
            for (int x=0; x<width; x++) {
                int pos = y*width + x; // Arrayposition bestimmen

                int r = 255;
                int g = 255;
                int b = 255;
                //  Ist der Pixel innerhalb des Kreises und innerhalb der oberen Hälfte der Flagge?
                if (voltobal.isInsideCircle(x, y) && y <= height / 2) {
                    //red
                    r = 239;
                    g = 51;
                    b = 64;
                    //  checkEquality1++;
                }
                // Ist der Pixel außerhalb des Kreises und innerhalb der unteren Hälfte der Flagge?
                if (!voltobal.isInsideCircle(x, y) && y > height / 2) {
                    //red
                    r = 239;
                    g = 51;
                    b = 64;
                    //  checkEquality2++;
                }

                // Werte zurueckschreiben
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
            }
        }
        //  System.out.println(((height * width) / 2) + " == " + (checkEquality1 + checkEquality2));
    }

    private void generateBhutanFlagImage(int width, int height, int[] pixels) {
        //int checkEquality1 = 0;
        //int checkEquality2 = 0;
        double incrementY = ((double)width)/((double)height);
        double countY = 0;
        // Schleife ueber die y-Werte
        for (int y=0; y<height; y++) {

            // Schleife ueber die x-Werte
            for (int x=0; x<width; x++) {
                int pos = y*width + x; // Arrayposition bestimmen



                int r = 0;
                int g = 0;
                int b = 0;

                if (x < width-(int)Math.round(countY)) {

                    //orange
                    r = 255;
                    g = 103;
                    b = 31;
                    //checkEquality1++;
                } else {
                    //yellow
                    r = 255;
                    g = 205;
                    b = 0;
                    //checkEquality2++;
                }
                // Werte zurueckschreiben
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
            }
            countY += incrementY;
        }
        //System.out.println("1: " + checkEquality1 + ", 2: " + checkEquality2);
    }

    private void generateBlackRedBlackBlueImage(int width, int height, int[] pixels) {
        // Schleife ueber die y-Werte
        for (int y=0; y<height; y++) {
            // Schleife ueber die x-Werte
            for (int x=0; x<width; x++) {
                int pos = y*width + x; // Arrayposition bestimmen

                int r = 0;
                int g = 0;
                int b = 0;

                r = r + ((x * 255) / width);
                b = b + ((y * 255) / height);

                // Werte zurueckschreiben
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
            }
        }
    }

    private void generateCyanYellowImage(int width, int height, int[] pixels) {
        // Schleife ueber die y-Werte
        for (int y=0; y<height; y++) {
            // Schleife ueber die x-Werte
            for (int x=0; x<width; x++) {
                int pos = y*width + x; // Arrayposition bestimmen

                int r = 0;
                int g = 255;
                int b = 255;

                r = r + ((x * 255) / width);
                b = 255 - r;
                // Werte zurueckschreiben
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
            }
        }
    }

    private void generateNetherlandsFlagImage(int width, int height, int[] pixels) {
        // Schleife ueber die y-Werte

        for (int y=0; y<height; y++) {
            // Schleife ueber die x-Werte
            for (int x=0; x<width; x++) {
                int pos = y*width + x; // Arrayposition bestimmen

                int r = 0;
                int g = 0;
                int b = 0;

                if (y <= height / 3) {
                    r = 200;
                    g = 16;
                    b = 46;
                } else if (y <= (height / 3) * 2) {
                    r = 255;
                    g = 255;
                    b = 255;
                } else {
                    r = 0;
                    g = 61;
                    b = 165;
                }

                // Werte zurueckschreiben
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
            }
        }
    }

    private void generateBlackImage(int width, int height, int[] pixels) {
        // Schleife ueber die y-Werte
        for (int y=0; y<height; y++) {
            // Schleife ueber die x-Werte
            for (int x=0; x<width; x++) {
                int pos = y*width + x; // Arrayposition bestimmen

                int r = 0;
                int g = 0;
                int b = 0;

                // Werte zurueckschreiben
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
            }
        }
    }


    private void dialog() {
        // Dialog fuer Auswahl der Bilderzeugung
        GenericDialog gd = new GenericDialog("Bildart");

        gd.addChoice("Bildtyp", choices, choices[0]);


        gd.showDialog();	// generiere Eingabefenster

        choice = gd.getNextChoice(); // Auswahl uebernehmen

        if (gd.wasCanceled())
            System.exit(0);
    }
}
