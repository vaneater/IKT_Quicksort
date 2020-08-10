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



    static int partitionMitte(List<Integer> a) {

        int i = a.get[0];//erste Element der Liste
        int j = a.get[a.length-1];//letzte Element der Liste
        int pivot = (a.length / 2);

        //Liste links = a[0] - a.[pivot-1];
        //Liste rechts = a[pivot+1] - a.[a.length-1];

            while(a.get(i) < pivot) {
                i++;
            }
            while(a.get(j) > pivot) {
                 j--;
            }

                if (i > j) {
                    int a = a.get(i);
                    a.get(i) = a.get(j);
                    a.get(j) = a;
                }
                else{
                    return j;
                }
        }//ist Liste komplett sortiert? Weil wir sortieren jeweils nur 2 listen.
        // wann überprüfen wir die koplmette liste und wie oft?
    }

    static int partitionRandom(List<Integer> a) {
        Random rand = new Random();
        int n = rand.nextInt(a.length(-1));
        int pivot = n;
        int i = a.get(0);
        int j = a.get(a.length-1);

            while(a.get(i) < pivot) {
                i++;

            while(a.get(j) > pivot) {
                j--;
            }

            if (i < j) {
                int a = a.get(i);
                a.get(i) = a.get(j);
                a.get(j) = a;
            }
            else{
                return j;
            }
        }
    }

    public static void main(String[] arguments) {
        List <Integer> a = new ArrayList<Integer>();
        a.add(3);
        a.add(6);
        a.add(1);
        a.add(8);
        a.add(7);


        System.out.println(partitionMitte(a));

        //Profiler profiler = new Profiler(Quicksort_Vanessa.class, new Object(), Integer.parseInt(arguments[1]));
        //Profiler profiler = new Profiler(Quicksort_Meltem.class, new Object[]{arguments[0], Integer.parseInt(arguments[1])});
        //profiler.start(); //profiler für Laufzeit rechnung
        //profiler.printResults();
    }
}
