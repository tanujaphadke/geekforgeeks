package misc;

public class SumTwoNumbers {


    int add(int x, int y){
        while(y!=0){
            int carry = (x&y)<< 1;
            x=x^y;
            y=carry;
        }
        return x;
    }
}
