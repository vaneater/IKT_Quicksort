//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import profiler.ProfiledClass;
import profiler.Profiler;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Quicksort_Kubra extends ProfiledClass {
    int[] field;
    final int outputIndex;

    public QuicksortP(String inputFile, Integer outputIndex) {
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

    public static void main(String[] args) throws IOException {
        Profiler profiler = new Profiler(QuicksortP.class, new Object[]{args[0], Integer.parseInt(args[1])});
        profiler.start();
        profiler.printResults();
    }

    public static void sort(int[] input, int start, int end) {
        if (end - start >= 2) {
            if (start < end) {
                if (end - start < 9) {
                    InsertionSort(input, start, end + 1);
                } else {
                    int part = partition(input, start, end);
                    sort(input, start, part - 1);
                    sort(input, part + 1, end);
                }
            }

        }
    }

    public static void InsertionSort(int[] list, int start, int end) {
        for(int x = start + 1; x < end; ++x) {
            int val = list[x];

            int j;
            for(j = x - 1; j >= 0 && val < list[j]; --j) {
                list[j + 1] = list[j];
            }

            list[j + 1] = val;
        }

    }

    public boolean arrayIsOrdered(int[] a) {
        boolean result = true;
        if (a != null && a.length > 0) {
            int firstNumber = a[0];

            for(int i = 1; i < a.length; ++i) {
                int currentNumber = a[i];
                if (currentNumber < firstNumber) {
                    result = false;
                    break;
                }

                firstNumber = currentNumber;
            }
        } else {
            result = false;
        }

        return result;
    }

    public static int partition(int[] input, int start, int end) {
        int pivot = input[start];
        int i = start;
        int j = end;

        while(i < j) {
            while(i < j) {
                --j;
                if (input[j] < pivot) {
                    break;
                }
            }

            if (i < j) {
                input[i] = input[j];
            }

            while(i < j) {
                ++i;
                if (input[i] >= pivot) {
                    break;
                }
            }

            if (i < j) {
                input[j] = input[i];
            }
        }

        input[j] = pivot;
        return j;
    }

    public void run() {
        sort(this.field, 0, this.field.length - 1);
        System.out.println(this.field[this.outputIndex]);
    }
}
