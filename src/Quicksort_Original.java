//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import profiler.ProfiledClass;
import profiler.Profiler;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Quicksort_Original extends ProfiledClass {
    int[] field;
    final int outputIndex;

    public Quicksort_Original(String inputFile, Integer outputIndex) {
        this.outputIndex = outputIndex;

        try {
            File file = new File(inputFile);
            this.field = new int[(int)(file.length() / 4L)];
            DataInputStream in = new DataInputStream(new FileInputStream(file));

            for(int index = 0; index < this.field.length; ++index) {
                this.field[index] = in.readInt();
            }

            in.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    static void quicksort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = partition(a, lo, hi);
            quicksort(a, lo, pivotIndex);
            quicksort(a, pivotIndex + 1, hi);
        }

    }

    static int partition(int[] a, int lo, int hi) {
        int pivot = a[(hi + lo) / 2];
        int i = lo;
        int j = hi;

        while(true) {
            while(a[i] >= pivot) {
                while(a[j] > pivot) {
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
            }

            ++i;
        }
    }

    public void run() {
        quicksort(this.field, 0, this.field.length - 1);
        int[] checks = new int[]{0, 5000, 600000, this.field.length - 1};
        int[] var2 = checks;
        int var3 = checks.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int check = var2[var4];
            System.out.println("Element an Index " + check + ": " + this.field[check]);
        }

        System.out.println("Element an Index " + this.outputIndex + ": " + this.field[this.outputIndex]);
    }

    public static void main(String[] arguments) {
        Profiler profiler = new Profiler(Quicksort_Original.class, new Object[]{arguments[0], Integer.parseInt(arguments[1])});
        profiler.start();
        profiler.printResults();
    }
}
