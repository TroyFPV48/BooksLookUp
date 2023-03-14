import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;

public class BookSearch extends JFrame{ 
	 
	public BookSearch() 
	{		
		// frame
	     JFrame f; //JFrame variable
		// label
		 JLabel l, l1, l2, l3, l4; //label variables
		//textfield
		 JTextField t, t1, t2, t3, t4; //textfield variables
		 
		//create HashMap
		HashMap<String, Book> bookHashMap = new HashMap<String, Book>();
		
		// create labels for student textfields
		l = new JLabel("ISBN");
		l1 = new JLabel("Authors");
		l2 = new JLabel("Title");
		l3 = new JLabel("Year");
		l4 = new JLabel("Rating");
		
		//create textfield
		t= new JTextField(20);
		t1= new JTextField(20);
		t2= new JTextField(20);
		t3= new JTextField(30);
		t4= new JTextField(10);
		
		//make textfields non-editable 
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);

		// create a new panel
		JPanel p = new JPanel();	

		// create a new frame
		f = new JFrame("Book Search by ISBN");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close when press "X"
		
		// set the size of frame
		f.setSize(400, 300);
		f.setVisible(true);
				
		// add panel to frame
		f.add(p);
			
		p.setLayout(null);

		l.setBounds(20,10, 90, 40);
		l1.setBounds(20,50, 90, 40);
		l2.setBounds(20,90, 90, 40);
		l3.setBounds(20,130, 90, 40);
		l4.setBounds(20,170, 90, 40);
		
		t.setBounds(75,15, 280, 30);
		t1.setBounds(75,55, 280, 30);
		t2.setBounds(75,95, 280, 30);
		t3.setBounds(75,135, 280, 30);
		t4.setBounds(75,175, 280, 30);		
		

		//add labels to panel
		p.add(l);
		p.add(l1);
		p.add(l2);
		p.add(l3);
		p.add(l4);
		
		//add textfield to panel
	    p.add(t);
	    p.add(t1);
	    p.add(t2);
	    p.add(t3);
	    p.add(t4);
	    
		f.show();		
		
		//textfield will perform an action when enter is clicked 
		t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String tempISBN = t.getText();
 	    		if(bookHashMap.containsKey(tempISBN)) {
 	    			Book result = bookHashMap.get(tempISBN); 	    		
	 				t1.setText(result.Authors());
	 				t2.setText(result.Title());
	 				t3.setText(Integer.toString(result.PublicationYear()));
	 				t4.setText(Double.toString(result.AverageRating())); 
 	    		}else {
 	    		JOptionPane.showMessageDialog(null, "Can't find");
 	    		}
		}
		});
		
		//read data
				File myInFile = new File("BooksDataFile.txt"); //file name
				
				Scanner scBook = null; 
				
				try {
					scBook = new Scanner(myInFile);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();	
					JOptionPane.showMessageDialog(null, "Problem reading file, no book to lookup"); //display error message
					t.setEditable(false); //ISBN field won't be editable once this happens
				}
				scBook.nextLine(); //skip header from text file
				
				while(scBook.hasNextLine()) {
					String line = scBook.nextLine(); 
					String[] tempArray1 = line.split("~"); //splitting text by "~" and store them into a String array			
					Book tempBook = new Book(tempArray1[2],tempArray1[3],Integer.parseInt(tempArray1[4]),tempArray1[5],tempArray1[6],Double.parseDouble(tempArray1[7]));
					bookHashMap.put(tempBook.ISBN(), tempBook); //put the ISBN and Book object into the HashMap
				}
	}
	
	
	
	public static void main (String[]args) {
		new BookSearch(); //calling constructor
	}
 
}

