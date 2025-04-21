package jan2023;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This problem was asked by Epic.
 *
 * The "look and say" sequence is defined as follows: beginning with the term 1, each subsequent term visually describes the digits appearing in the previous term.
 * The first few terms are as follows:
 *
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * As an example, the fourth term is 1211, since the third term consists of one 2 and one 1.
 *
 * Given an integer N, print the Nth term of this sequence.
 */

public class LookAndSaySequence {

    public static int [] stringToInt(String s){
        int[] ret = new int[s.toCharArray().length];
        char[] chars = s.toCharArray();
        int i = 0;
        for ( char c: chars){
            ret[i++] = Integer.valueOf(String.valueOf(c)).intValue();
        }
        return ret;
    }

    public static  String  printNthValue(int n ) throws Exception {
        if (n < 1) {
            Exception e =  new Exception("N cannot be less than 1");

        }
        String prev = "1"; String next = "";
        for (int i=1; i < n; i++){
            next  = lookAndSay(prev);
            prev = next;
        }
        return prev;
    }
    public static String lookAndSay (String s){

        int [] digits = stringToInt(s);
        StringBuilder stringOfDigits = new StringBuilder();
        int prev =-1 ; int next = -1;
        Map<Integer, Integer> map = new HashMap<>();
        //1211
        for (int d : digits ){
            if (d != prev ){
                map.put(d, 1); //1:1* 2:1* 1:1
                Integer times = map.remove(prev);
                if (times !=null) {
                    stringOfDigits.append(times).append(prev); //1112
                }else;
            }else {
                int count = map.get(d);
                map.put(d, count +1);
            }
            prev=d;


        }
        Integer times = map.get(prev);
        stringOfDigits.append(times).append(prev);
        System.out.println("look and say : next value is: " + stringOfDigits.toString());
        return stringOfDigits.toString();
    }


    public static  void  main (String args[]) throws Exception {
       int[] val = stringToInt("1211");

        Arrays.stream(val).forEach(System.out::print);
        System.out.println();
        System.out.println(printNthValue(4));

    }




}
