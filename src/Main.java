import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This program inserts double numbers into B plus tree, binary tree and RB tree 
 * and checks the run time of insertion, search, remove 
 * The program exports the summary into csv file for review
 * @author 200681872, 302875646
 */

public class Main {
	//static variables for easy accessing by reference
	private static ArrayList<Double> ARRAY;
	private static StopWatch SW;
	private static PrintWriter WRITER;
        private static BinaryTree bTree;
        private static RedBlackTree rbTree;
	// BPT, RBT BT

	public static void main(String[] args) {
		int n = 1024;
		SW = new StopWatch();
		
		//open static print writer to save the data
		try{
			WRITER = new PrintWriter (new FileWriter("Analyze.csv"));
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.err.println("================= /n SYSTER EXITED /n==================");
			System.exit(0);
		}
		
                 
                
		//while exception isn't happened continue increasing n, and insert it to the trees.
		WRITER.print("n, BT, RBT, BPT, \n");
		int i = 1;
		while(true){
			try{
				WRITER.print(n*i);
                                //Create a new array on given N numbers
				createArray(n*i);

                                /**
                                 * Define Trees
                                 */
				testBinary();
				testRB();
				insertBP();

                                
				WRITER.print("\n");
				
				i*=2;
				//if (i > 65) break;
				

			} catch (Exception e){
				e.printStackTrace();
				WRITER.close();
				break;
			}
                        
                        WRITER.flush();
			
			//while exception isn't happened search the value in the trees
			//while exception isn't happened remove the value from the tree
		}

	}

	/**
	 * reallocate array with new size on n
	 * @param n
	 */
	private static void createArray(int n) throws Exception{
		ARRAY = new ArrayList<Double>();
		Random rand = new Random();
		for (int i = 0; i < n; i++) 
			ARRAY.add(rand.nextDouble());
		

		Collections.sort(ARRAY);
	}


	// ======================== Tree Methods (Insert, Remove) ==============================

	private static void testBinary() throws Exception{	
            bTree = new BinaryTree();
            System.err.println("Testing b tree");
            //Add new BNodes
                
           
            for(double d : ARRAY)
                bTree.insert(d);
          
             SW.start();
             BNode l = bTree.find(ARRAY.get(0));
             l = bTree.find(ARRAY.get(ARRAY.size()-1));
             bTree.find(1.0);
             bTree.find((double)ARRAY.size()/2);



            SW.finish();
            System.err.println(ARRAY.size()+"," + SW.getTotalRumTime());
            WRITER.print("," + SW.getTotalRumTime());
                
                
                
	}	


	private static void testRB() throws Exception{
               rbTree = new RedBlackTree();
            double last=0;
            for(double d : ARRAY){
                if(d==last)
                    continue;
                 rbTree.insert(d);
            }
                
               
            
		SW.start();
                  
                Comparable l = rbTree.find(ARRAY.get(0));
                l = rbTree.find(ARRAY.get(ARRAY.size()-1));
                rbTree.find(1.0);
                rbTree.find((double)ARRAY.size()/2);


		SW.finish();
		WRITER.print("," + SW.getTotalRumTime());
	}

	


	private static void insertBP() throws Exception{
         
		SW.start();






		SW.finish();
		WRITER.print("," + SW.getTotalRumTime());
}

}

