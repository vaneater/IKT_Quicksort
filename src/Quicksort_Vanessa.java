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
    ArrayList<Integer> field;
    final int outputIndex;

    public Quicksort_Vanessa(String inputFile, Integer outputIndex) throws IOException {
        this.outputIndex = outputIndex;

        File file = new File(inputFile);
        DataInputStream in = new DataInputStream(new FileInputStream(file));

        for(int index = 0; index < this.field.size(); ++index) {
            this.field.add(index, in.readInt());
        }

        in.close();

    }

    static void quicksort(List<Integer> a, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = partition(a, lo, hi);
            quicksort(a, lo, pivotIndex);
            quicksort(a, pivotIndex + 1, hi);
        }

    }

    static int partition(List<Integer> a, int lo, int hi) {
        int pivot = a.get((hi + lo) / 2);
        int i = lo;
        int j = hi;

        while(true) {
            while(a.get(i) >= pivot) {
                while(a.get(j) > pivot) {
                    --j;
                }

                if (i >= j) {
                    return j;
                }

                int tmp = a.get(i);
                a.set(i, j);
                a.set(j, tmp);
                ++i;
                --j;
            }

            ++i;
        }
    }

    public void run() {
        quicksort(this.field, 0, this.field.size() - 1);
        int[] checks = new int[]{0, 5000, 600000, this.field.size() - 1};
        int[] var2 = checks;
        int var3 = checks.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int check = var2[var4];
            System.out.println("Element an Index " + check + ": " + this.field.get(check));
        }

        System.out.println("Element an Index " + this.outputIndex + ": " + this.field.get(this.outputIndex));
    }

    public static void main(String[] arguments) {
        //Profiler profiler = new Profiler(Quicksort_Vanessa.class, new Object(), Integer.parseInt(arguments[1]));
        Profiler profiler = new Profiler(Quicksort_Vanessa.class, new Object[]{arguments[0], Integer.parseInt(arguments[1])});
        profiler.start();
        profiler.printResults();
    }
}
