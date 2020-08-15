import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Quicksort_Lan {



    static int partition(int[] a, int lo, int hi) {
        Random random = new Random();
        int pivot = a[(hi + lo) / 2];
        // int pivot = random.nextInt(a.length - 1); Nicht random Zahl sondern Random indexe
        int i = lo;
        int j = hi;

        boolean statusOfTheList = arrayIsOrdered(a);

        while (statusOfTheList != true) {
            while (true) {
                while (a[i] >= pivot) {
                    while (a[j] > pivot) {
                        --j;
                    }
                    if (i >= j) {
                        return j;
                    }
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                    ++i;
                    --j;
                    System.out.println(Arrays.toString(a));
                }

                ++i;
            }
        }

        System.out.println(Arrays.toString(a));
        return j;
    }

    public static boolean arrayIsOrdered(int[] a) {
        boolean result = true;
        if (a != null && a.length > 0) {
            int firstNumber = a[0];
            for (int i = 1; i < a.length; i++) {
                int currentNumber = a[i];

                if (currentNumber < firstNumber) {
                    result = false;
                    break;
                }

                firstNumber = currentNumber;
                // update the firstname with the next number in the arra
            }
        } else {
            result = false;
        }

        return (result);
    }

    /*

    das hier sind nur seltsame ideen aus wirren gedanken
    (es ist wichtig oft wasser zu tanken)
    ich habe das hier nicht zum laufen gebracht
    (nur schnell irgendwas gemacht)

     wir sollten auch für leer liste checken
     oder tuen wir das schon irgendwo?
     */

    static void partitionMitte(ArrayList<Integer> a) {

        int i = a.get(0); //erste Element der Liste
        int j = a.get(a.size()-1); //letzte Element der Liste
        int pivot = a.get(a.size() / 2);

        while (a.get(i) < pivot) {
            i++;
        }
        while (a.get(j) > pivot) {
            j--;
        }

        if (i > j) {
            // Collections.swap(a, i, j); <- ich weiß nicht ob das eine gute idee ist lol
            int temp = a.get(i);
            a.add(i, (a.get(j)));
            a.add(j, temp);
            ++i;
            --j;
        }

        /*
        google sagt: das muss jetzt rekursiv passieren, damit es weiter sortiert wird
        low und high werden dabei hier im kunstruktor mitgegeben
        es sind anfang und ende der liste
        kp ob das mit unserem aufbau möglich ist, anfang und ende der liste schon
        beim aufrufen der funktion anzugeben..

        if(lo < j)
        {
            partitionMitte(a, lo, j);
        }

        if(high > i)
        {
            partitionMitte(a, i, high);
        }
        */
    }


    //ist Liste komplett sortiert? Weil wir sortieren jeweils nur 2 listen.
    // wann überprüfen wir die koplm

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] b = new int[]{5, 3, 7, 2, 6};
        int[] c = new int[]{6, 7, 8, 2, 4, 9, 1};
        System.out.println(" Der Status der Liste ist : " + arrayIsOrdered(c));

        System.out.println(partition(c, 0, 6));

        /**  ArrayList<Integer> a = new ArrayList<Integer>();
         a.add(0,1);
         a.add(1,5);
         a.add(2,3);
         a.add(3,8);
         partitionMitte(a);
         System.out.println(Arrays.toString(a.toArray()))
         ***/;



    }
}