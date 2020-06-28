import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import profiler.ProfiledClass;
import profiler.Profiler;

/**
 *  Optimisierter Quicksort Algorithmus.
 *  Die Sortierende Liste wird mithilfe eines Pivotelementes in zwei
 *  Teile geteilt. Alle Elemente welche kleiner als das Pivotelement
 *  sind kommen in die linke Teilliste. Alle größeren kommen in die
 *  rechte Teilliste. Daraufhin wird jede Teilliste erneut auf diese
 *  Art sortiert bis alle Elemente sortiert sind.
 *
 * @author Michael Witt
 * @author Lan Anh Phan
 * @author Kübra Nur Demir
 * @author Meltem Adigüzel
 * @author Rabiye Hassan Nejad
 * @author Vanessa Sandra Gatner
 *
 * @version 1.0
 *
 * @reference QuickSort.class
 */
public class Quicksort_Vanessa {


    public class QuickSort_Vanessa extends ProfiledClass {
        int[] field;
        final int outputIndex;

        public QuickSort_Vanessa(String inputFile, Integer outputIndex) {

            /**
             * Auslesen der zu sortierenden Elemente aus einer Datei.
             *
             * @param file Die einzulesende Datei.
             * @param in Der Datastream mit den eingelesenen Elementen.
             */
            this.outputIndex = outputIndex;

            try {
                File file = new File(inputFile);
                this.field = new int[(int) (file.length() / 4L)];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                for (int index = 0; index < this.field.length; ++index) {
                    this.field[index] = in.readInt();
                }

                in.close();
            } catch (Exception var6) {
                var6.printStackTrace();
            }

        }

        int quicksort(int[] a, int lo, int hi) {

            /**
             *
             *
             * @param pivotIndex Index des Pivotelements
             */

            // hallo hier bei mir muss ich eine while schreife einbauen
            if (lo < hi) {
                int pivotIndex = partition(a, lo, hi);

                if (pivotIndex - lo < hi - pivotIndex) {
                    quicksort(a, lo, pivotIndex - 1);
                } else {
                    quicksort(a, pivotIndex + 1, hi);
                    hi = pivotIndex - 1;
                }
            }
            return hi;
        }

        int partition(int[] a, int lo, int hi) {

            /**
             *
             *
             * @param pivot Das Pivotelemnt in der Mitte.
             * @param i Die niedrigerere Teilliste.
             * @param j Die höhrere Teilliste,
             */
            int pivot = a[(hi + lo) / 2];
            int i = lo;
            int j = hi;

            for (int l = lo; l < j; l++) {
                if (a[l] <= pivot) {
                    swap(a, lo, i);
                    i++;

                }
            }
            swap(a, i, hi);

            return pivot;
        }

        public void swap(int[] array, int alt, int neu) {
            /**
             * Methode zum Tauschen.
             */
            int temp = array[alt];
            array[alt] = array[neu];
            array[neu] = temp;
        }

        public void run() {
            quicksort(this.field, 0, this.field.length - 1);
            int[] checks = new int[]{0, 5000, 600000, this.field.length - 1};
            int[] var2 = checks;
            int var3 = checks.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                int check = var2[var4];
                System.out.println("Element an Index " + check + ": " + this.field[check]);
            }

            System.out.println("Element an Index " + this.outputIndex + ": " + this.field[this.outputIndex]);
        }

    }
    public static void main(String[] arguments) {
        Profiler profiler = new Profiler(QuickSort_Vanessa.class, new Object[]{arguments[0], Integer.parseInt(arguments[1])});
        profiler.start();
        profiler.printResults();
    }
}

