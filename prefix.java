//https://leetcode.com/playground/aAN4cVgL
import java.util.*;
class TrieNode{
    public boolean isWord;
    public TrieNode[] children;
    public TrieNode(){
        children = new TrieNode[26];
        isWord = false;
    }
}
class Trie{
   public TrieNode root = new TrieNode();;
   public Trie(){}
   public void insert(String word){
        TrieNode pointer = root;
        for(char c : word.toCharArray()){
            if(pointer.children[c - 'a'] == null){
               pointer.children[c - 'a'] = new TrieNode(); 
            }
           pointer = pointer.children[c - 'a'];
        }
        pointer.isWord = true;
    }
}

class HelloWorld{
     public List<List<String>>groupWords(String[] words){
         Trie trie = new Trie();
         for(int i = 0; i < words.length; i++){
             trie.insert(words[i]);
         }
         List<List<String>>pair = new ArrayList<>();
         //iterating all children starting from root;
         for(int i = 0; i < 26; i++){
             if(trie.root.children[i] != null){
                 dfs(trie.root.children[i],i,new StringBuilder(),new ArrayList<>(),pair);
             }
         }
         return pair;
     }
     public void dfs(TrieNode node, int currValue, StringBuilder curr, List<String> path, List<List<String>>pair){
         curr.append((char)(currValue + 'a'));
        
         if(node.isWord){
             path.add(curr.toString());
             if(path.size() == 2){
                 pair.add(new ArrayList<>(path));
                 path = new ArrayList<>();
             }
         }
         //iterating 26 character
         for(int i = 0; i < 26; i++){
             if(node.children[i] != null){
                 dfs(node.children[i],i,curr,path,pair);
             }
         }
         curr.deleteCharAt(curr.length() - 1);
         
     }
}
public class H{
    public static void main(String []args){
          String[] words = {"abs","app","be","apple","bee","better","bet","absolute"};
          HelloWorld hc = new HelloWorld();
          List<List<String>>result = hc.groupWords(words);
          for(List<String> ele : result){
            for(String e : ele){
                System.out.println(e);
            }
         }
     }
}
