import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

public class lab1 {
    //n^2
    public static int CountTripletsSumEfficient(int[] list, int sum) {
        int triplets = 0;
        Arrays.sort(list); //nlogn

//        for(int i = 0; i < list.length; i++) {
//            System.out.println(list[i]);
//        }

        //-3, -3, 0, 1, 2, 5

        //n^2
        for(int i = 0; i < list.length; i++) {
            if(i == 0 || (i > 0 && list[i] != list[i-1])) {
                int left = i+1;
                int right = list.length-1;
                int target = 0-list[i];

                while(left < right) {
                    if(list[left]+list[right] > target) {
                        right--;
                    }
                    else if(list[left]+list[right] < target) {
                        left++;
                    }
                    else {
                        triplets++;
                        while(left < right && list[left] == list[left+1]) {
                            left++;
                        }
                        while(left < right && list[right] == list[right-1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }

        return triplets;
    }

    //brute force method
    public static int CountTripleSumBrute(int[] list, int sum) {
        int triplets = 0;
        int len = list.length;
        len--;

        for(int i = 0; i < len-2; i++) {
            for(int j = 0; j < len-1; j++) {
                for(int k = 0; k < len; k++) {
                    //System.out.println(list.get(i) + ", " + list.get(j) + ", " + list.get(k));
                    if(list[i] + list[j] + list[k] == sum) {
                        triplets++;
                    }
                }
            }
        }


        return triplets;
    }
    public static void main(String[] args) {

        int[] mylist = new int[10001];
        int counter = 0;

        try {
            File myObj = new File("./files-lab1/10Kint_1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                mylist[counter++] = Integer.parseInt(data.trim());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //last element in the list will be the sum we want to get to
        int mysum = mylist[mylist.length-1];


//        long startTimer = System.currentTimeMillis();
//        int resBrute = CountTripleSumBrute(mylist, mysum);
//        long endTimer = System.currentTimeMillis();
//        System.out.println(resBrute);
//        System.out.println(endTimer-startTimer + "ms");

        long startTimer = System.currentTimeMillis();
        int resEff = CountTripletsSumEfficient(mylist, mysum);
        long endTimer = System.currentTimeMillis();
        System.out.println(resEff);
        System.out.println(endTimer-startTimer + "ms");
    }
}