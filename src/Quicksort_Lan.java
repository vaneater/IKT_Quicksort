import java.util.Random;

public class Quicksort_Lan {

    static int partition(int[] a, int lo, int hi) {
        Random random = new Random();
        //int pivot = a[(hi + lo) / 2];
        int pivot = random.nextInt();
        int i = lo;
        int j = hi;

        boolean statusOfTheList = arrayIsOrdered(a);

        while(statusOfTheList !=true) {
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
                }

                ++i;
            }
        }

        return j;
    }

    public static boolean arrayIsOrdered(int [] a )
    {
        boolean result = true;
        if(a != null && a.length > 0)
        {
            int firstNumber = a[0];
            for(int i = 1; i < a.length; i++)
            {
                int currentNumber = a [i];

                if(currentNumber < firstNumber )
                {
                    result = false;
                    break;
                }

                firstNumber = currentNumber;
                // update the firstname with the next number in the arra
            }
        }
        else
        {
            result = false;
        }

        return  (result);
    }

    public static void main(String[] args) {
        int [] a = new int []{1,2,3,4,5};
        int [] b = new int []{5,3,7,2,6};
        int [] c = new int []{6,7,8,2,4,9,1};
        //  System.out.println( " Der Status der Liste ist : " +arrayIsOrdered(c));

        //System.out.println(partition(c,c[0],c[4]));





    }
}
