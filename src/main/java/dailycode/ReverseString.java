package dailycode;

/**
 * Given a string of words delimited by spaces, reverse the words in string. For example,
 * given "he world happy new year here", return "here year new happy world he"
 *
 * Follow-up: given a mutable string representation, can you perform this operation in-place?
 */
public class ReverseString {

    public  static  void main(String args[]){
        StringBuilder sb = new StringBuilder("Hello world");
        sb.deleteCharAt(0);
        sb.deleteCharAt(1);
        System.out.println(sb.toString());

    }
    public void reverse(String inword ){
        StringBuilder sb = new StringBuilder(inword);
        //"te world happy new year here"
        int i = 0; int k =0; char prev = '0'; int j = sb.length()-1 ; StringBuilder endsb = new StringBuilder();StringBuilder beginsb = new StringBuilder();
        for (;;) { int count = 0; ;
            for ( i = k; i < j; i++) {
                char c = sb.charAt(i);
                if ( prev == ' ' && c != ' ' ){
                    prev = '0';
                    break;
                }
                else if (c != ' ') {count ++;
                    sb.deleteCharAt(i);
                    beginsb.insert(beginsb.length(), c);
                } else ;
                prev = c;
            }
            i = i - count; count = 0;
            for ( j = sb.length()-1 ; j > i ; j --){ count ++ ;
                char c = sb.charAt(j);
                if ( c!= ' ') {
                    count ++;
                    sb.deleteCharAt(j);
                    endsb.insert(0, c);
                }else break;
            }
            sb.insert(i,endsb);
            sb.insert(j,beginsb);
            k =  i + count;

        }
        //return null;
    }
}



