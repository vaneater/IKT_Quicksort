//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

/*
    die files die wir haben sind .java
    das original von herr witt ist .class

    hat das einen einfluss auf den code? ich babe den originalen code kopiert, und dieser funktioniert nämlich nicht

    ich hab erst mal versucht das einlesen der datei schneller zu machen
    dazu statt einem array eine liste benutzen, da diese angeblich schneller sind lol

    den rest habe ich vorerst gleich gelassen, um zu gucken ob das mit der liste funktioniert
    (was es nicht tut, endweder wegen der allgemeinen nicht-funktionierenden eigenschaft des originalen codes,
    oder weil ich zu doof war lol
 */

import profiler.ProfiledClass;
import profiler.Profiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quicksort_Vanessa extends ProfiledClass {
    ArrayList<Integer> field; // Liste ist schneller als Array
    final int outputIndex;  //

    public Quicksort_Vanessa(String inputFile, Integer outputIndex) throws IOException {
        this.outputIndex = outputIndex;

        File file = new File(inputFile); //File wird in 'file' gepspeichert
        DataInputStream in = new DataInputStream(new FileInputStream(file)); //File wird gelesen und in 'in' gespeichert

        for(int index = 0; index < this.field.size(); ++index) { //wir lesen jeden INT in unserer liste 'field' und füht es in 'in' ein
            this.field.add(index, in.readInt()); //wir speichern den Index also stelle der zu speichernden Zahl und die Zahl selber.
            // Bei einem normalen array ist dieser Schritt nicht notwendig da man bei array direkt über den index zugreifen kann
        }

        in.close(); //wir schliessen die Datei 'in'

    }

    static void quicksort(List<Integer> a, int lo, int hi) { //lo=linke seite und hi = rechte seite
        if (lo < hi) {
            int pivotIndex = partition(a, lo, hi); //wir teilen unsere Liste in 2 Hälften
            quicksort(a, lo, pivotIndex); //und rekursion angewendet auf die linke Seite
            quicksort(a, pivotIndex + 1, hi); //und rekursion auf rechter seite
        }

    }

    static int partition(List<Integer> a, int lo, int hi) {
        int pivot = a.get((hi + lo) / 2); //pivot ist unsere Mitte(index)
        int i = lo;
        int j = hi;

        while(true) { //wann ist false und wann true?
            while(a.get(i) >= pivot) { // wir vergleichen linke seite mit pivot/mitte
                while(a.get(j) > pivot) { //wenn rechte seite größer ist als pivot
                    --j; //dann rückt rechte seite eine stelle bzw index nach links
                }

                if (i >= j) { //überprüft ob linke seite größer gleich ist als rechte seite
                    return j; //warum return j?
                }

                int tmp = a.get(i); //warum tauschen wir hier linke mit rechter seite? warum wird i in tmp gespeichert
                a.set(i, j);   // set(index, die zahlt selber)
                a.set(j, tmp);
                ++i;
                --j;
            }

            ++i;
        }
    }

    public void run() {
        quicksort(this.field, 0, this.field.size() - 1);
        int[] checks = new int[]{0, 5000, 600000, this.field.size() - 1};//woher kommen die zahlen 5000 und 600.000?
        int[] var2 = checks; //dasselbe array unter einem anderen namen kopiert
        int var3 = checks.length; //varibale die die Länge des array speichert

        for(int var4 = 0; var4 < var3; ++var4) {
            int check = var2[var4];
            System.out.println("Element an Index " + check + ": " + this.field.get(check));
        }

        System.out.println("Element an Index " + this.outputIndex + ": " + this.field.get(this.outputIndex));
    }

    public static void main(String[] arguments) {
        //Profiler profiler = new Profiler(Quicksort_Vanessa.class, new Object(), Integer.parseInt(arguments[1]));
        Profiler profiler = new Profiler(Quicksort_Vanessa.class, new Object[]{arguments[0], Integer.parseInt(arguments[1])});
        profiler.start(); //profiler für Laufzeit rechnung
        profiler.printResults();
    }
}
