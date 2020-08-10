import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QUicksort2 {



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

        static int partitionMitte(ArrayList<Integer> a) {

            int i = a.get(0);//erste Element der Liste
            System.out.println(i);
            int j = a.get(a.size()-1);//letzte Element der Liste
            System.out.println(j);
            int pivot = a.get(a.size() / 2);
            System.out.println(pivot);

            //Liste links = a[0] - a.[pivot-1];
            //Liste rechts = a[pivot+1] - a.[a.length-1];

            while (a.get(i) < pivot) {
                System.out.println("Hello ihr seid liebe Katzen");
                i++;
            }
            while (a.get(j) > pivot) {
                j--;
            }

            if (i > j) {
                int tmp = a.get(i);
                a.add(i, j);
                a.add(j, tmp);
                // System.out.println(Arrays.toString(a.toArray()));
                ++i;
                --j;
            }

            return j;
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

