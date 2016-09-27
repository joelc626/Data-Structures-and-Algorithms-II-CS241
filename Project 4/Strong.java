/* Joel Castro
 * CS241 - Data Structures and Algorithms II
 * Project 4
 *
 * Develop a program to compute the strongly connected components of a directed graph.
 * (Use the various graph classes & text files provided)
 */
package strong;

import graph.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Strong {
    ArrayList<Integer> list = new ArrayList<>();
    int pass;
    
    public static void main(String[] args) {
        String name;
        Scanner kb = new Scanner(System.in);
        
        //Get the existing file
        do {
            //System.out.print("Enter name of file: ");
            //name = kb.nextLine();
            name = "graph1.txt";
            
            try {
                File fileName = new File(name);
                Scanner g = new Scanner(fileName);
            }
            catch(java.io.FileNotFoundException e) {
                System.out.println("File \"" + name + "\" Not Found");
                name = "";
                System.out.println();
            }
        }
        while(name.equals(""));
        
        new Strong(name);
    }
    
    public Strong(String name) {
        try {
            //read file and build graph
            MyDfsGraph g = new MyDfsGraph(name);
            System.out.print(g);//Prints adjancent list
            //first dfs stack
            g.firstPass();
            System.out.println("Vertex stack:" + list);
            MyDfsGraph h = g.reverse();
            System.out.print(h);//prints reverse
            h.secondPass();
        }
        catch (java.io.IOException e){
            System.out.println("File No Found: " + name);
        }
    }
    
    public class MyDfsGraph extends DfsGraph {
        public MyDfsGraph(String name) throws java.io.IOException {
            //builds graph from file and initializes variables
            super(name);
        }
        
        public MyDfsGraph(String name, int order, int size, boolean directed, boolean weighted) throws java.io.IOException {
            super(name, order, size, directed, weighted);
        }
        
        public void firstPass() {
            pass = 1;
            //persistent dfs
            for (int v = 0; v < order; v++) {
                if (!vertexMarked(v)) {
                    dfs(v);
                }
            }
        }
        
        public void dfs(int v){
            preVisit(v);
            markVertex(v);
            for (int w : getNeighbors(v)) {
                if (vertexMarked(w))
                    doMarked(v, w);
                else {
                    doUnmarked(v, w);
                    dfs(w);
                }
            }
            postVisit(v);
        }
        
        public MyDfsGraph reverse() throws IOException {
            MyDfsGraph h = new MyDfsGraph("reverse(" + getName() + ")", getOrder(), getSize(), isDirected(), isWeighted());
            Edge[] edges = getEdges();
            for (Edge e : edges) {
                h.addEdge(e.getHead(), e.getTail());
            }
            return h;
        }
        
        public void secondPass() {
            pass = 2;
            int component = 0;
            //process vertices in order determined by stack
            while (!list.isEmpty()) {
                int v = list.get(list.size() - 1);
                if (!vertexMarked(v)) { //does not go in because nothing is marked
                    System.out.println("Component " + (++component));
                    dfs(v);
                }
            }
        }
        
        public void preVisit(int v) {
            if (pass == 2) {
                System.out.println("   " + v);
                list.remove(new Integer(v));
            }
        }
        
        public void postVisit(int v) {
            if (pass == 1)
                list.add(v);
        }
    }
}

/* SAMPLE OUTPUT!!!!!!!!!!!!!!!!!
 Name=graph3.txt, order=5, size=8, directed=true, weighted=false
 0 => [4, 3, 1]
 1 => [3, 2]
 2 => [3]
 3 => [4]
 4 => [1]
 Vertex stack:[3, 2, 1, 4, 0]
 Name=reverse(graph3.txt), order=5, size=8, directed=true, weighted=false
 0 => []
 1 => [4, 0]
 2 => [1]
 3 => [2, 1, 0]
 4 => [3, 0]
 Component 1
 0
 Component 2
 4
 3
 2
 1
 */