package graphs.lecture18;

	import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.concurrent.ConcurrentSkipListSet;

	/**
	 *
	 * @author gauragu3
	 */
	class WordNode implements Comparable<String>{
	    String wordStr;
	    WordNode parent;
	    int distance;
	    public WordNode(String wordStr, WordNode parent, int distance) {
	        super();
	        this.wordStr = wordStr;
	        this.parent = parent;
	        this.distance = distance;
	    }    
	    public WordNode(String wordStr) {
	        super();
	        this.wordStr = wordStr;
	        this.parent = null;
	        this.distance = -1;
	    }
	    //add getters setters.Make method private
	    @Override
	    public int compareTo(String o) {
	        return wordStr.compareTo(o);
	    }
	}

	public class WordLadderChugg {

	    private final String[] allWords = { "aaa", "abb", "bbb", "aba", "acc", "abc", "ccc", "pop", "hop", "top", "tap","hap", "tap",
	"aad" };
	    private SortedSet<String> dictionary = new ConcurrentSkipListSet(Arrays.asList(allWords));

	    public static void main(String args[]) {
	        String source = "pop";
	        String target = "tap";
	        WordNode wnS = new WordNode("pop",null, 1);
	        WordLadderChugg da = new WordLadderChugg();
	        Queue<WordNode> q = new LinkedList<>();
	        q.add(wnS);
	        boolean found = false;
	        
	        while(q.isEmpty() == false){
	            WordNode curr = q.peek();
	            q.remove();
	            
	            for(String eachWordStr : da.dictionary){
	                if(da.isOneLetterDifferent(curr.wordStr, eachWordStr)){
	                    WordNode temp = new WordNode(eachWordStr, curr, curr.distance+1);
	                    q.add(temp);
	                    da.dictionary.remove(eachWordStr);
	                    
	                    if(eachWordStr.equals(target)){
	                        System.out.println("Match Found. Printing ladder of length " + temp.distance);
	                        Stack<String> s = new Stack<>();
	                        int min  = temp.distance;
	                        while(temp != null){
	                            s.push(temp.wordStr);
	                            temp = temp.parent;
	                        }
	                        while(s.isEmpty() == false){
	                            System.out.print(s.pop() + " => " );
	                        }
	                        found = true;
	                        break;
	                    }
	                }
	            }
	            if(found)
	                break;
	        }
	        
	        if(!found)
	            System.out.println("Cannot reach target word");
	       /* Map<String,WordNode> lst = da.makeGraph(wnS);
	        WordNode wnT = lst.get(target);
	        while ( wnT.parent != null){
	            System.out.println(wnT.wordStr);
	            wnT = wnT.parent;
	        }*/

	    }

	    /*public Map<String,WordNode> makeGraph (WordNode sourcein){
	        Map<String,WordNode> g = new HashMap<String,WordNode> ();
	        if (!dictionary.contains(source)) throw new RuntimeException("FATAL : Source not found in dictionary");
	                    source = sourceIn;

	        while (! dictionary. isEmpty()){
	        for ( String eachWordStr : dictionary ) {
	            WordNode dicWord = new WordNode(eachWordStr);
	            if (isOneLetterDifferent(source, dicWord) ) {
	                dicWord.parent = source;
	                dicWord.distance = source.distance + 1;
	                //Also remove this word from the dictionary because we do not want to vist the node again . We need not color it 
	                dictionary.remove(source.wordStr);
	                g.put(dicWord.wordStr, dicWord);
	            }
	        }
	        
	        }
	        return g;

	    }*/
	    
	    public boolean isOneLetterDifferent(String source, String next) {
	        if (source.length() != next.length())
	            return false;
	        int count = 0;
	        
	        for(int i = 0; i<source.length();i++){
	            if(source.charAt(i) != next.charAt(i)){
	                count++;
	            }
	            if(count > 1)
	                return false;
	        }

	        return true;
	    }

	}

