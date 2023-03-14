
public record Book(String ISBN, String Authors, int PublicationYear, String OriginalTitle, String Title, double AverageRating) implements Comparable<Book>{
	
	@Override
	public int compareTo(Book book) {
		// TODO Auto-generated method stub
		 return  Integer.parseInt(ISBN)  -  Integer.parseInt(book.ISBN); //converting ISBN from String into int and minus them
	         
	}
	
	public boolean equals(Object obj) { //equals method
		if(obj == null || obj.getClass() != getClass()) {
			return false;
		}else {
			return compareTo((Book)obj)==0; //false if ISBN does not match
		}
	}
	
	public int hashCode(Book book) {
		return book.ISBN.hashCode(); //get ISBN as Hash Code
	}

	

}
