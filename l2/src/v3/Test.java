package v3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Test {

	public static void main(String[] args) {
		String fileName = "libv3.ser";
		Library lib;
		
		Path filePath = Paths.get(fileName);
		if (Files.exists(filePath)) {
			lib = (Library) deSerializeObject(fileName);
		} else {
			System.out.println("Object " + fileName + " is not found");
			
			lib = new Library("My Library v3");

			System.out.println("\n" + lib.toString());

			lib.addBookReader("Book ", "   Reader0 ", 0);
			lib.addBookReader("Book", "Reader1", 1);

			lib.addBook("Book0", new ArrayList<Author>(Arrays.asList(lib.findAuthor("Author", "0"))), 2024, 3);
			lib.addBook("Book1", new ArrayList<Author>(
					Arrays.asList(lib.findAuthor(" Author", " 0"), lib.findAuthor(" Author", " 1"))), 2024, 4);
			lib.addBook("Book2", new ArrayList<Author>(Arrays.asList(lib.findAuthor("Author", "2"))), 2024, 1);
		}

		System.out.println("\n" + lib.toString());
		System.out.println("Books:\n" + lib.getBooksList().toString());
		System.out.println("BookReaders:\n" + lib.getBookReadersList().toString());
		System.out.println("Author:\n" + lib.getAuthorsList().toString());

		System.out.println("\n" + lib.getAuthorsList().get(0).toString() + ":\n"
				+ lib.getAuthorsList().get(0).findBooks(lib).toString());

		lib.borrowBook(lib.findBookReader(0), lib.getBooksList().get(2));
		System.out.println("\nBookReader 0 <- Book 2 <- lib");
		System.out.println("\n" + lib.toString());
		System.out.println("Books:\n" + lib.getBooksList().toString());
		System.out.println("BookReaders:\n" + lib.getBookReadersList().toString());
		System.out.println("Author:\n" + lib.getAuthorsList().toString());
		System.out.println("BookReader 0: '" + lib.findBookReader(0).toString() + "'");
		System.out.println(lib.findBookReader(0).getReceiveBooks().toString());

		lib.returnBorrowBook(lib.findBookReader(0), lib.findBookReader(0).getReceiveBooks().get(0));
		System.out.println("\nBookReader 0 -> Book 0 -> lib");
		System.out.println("\n" + lib.toString());
		System.out.println("Books:\n" + lib.getBooksList().toString());
		System.out.println("BookReaders:\n" + lib.getBookReadersList().toString());
		System.out.println("Author:\n" + lib.getAuthorsList().toString());
		System.out.println("BookReader 0: '" + lib.findBookReader(0).toString() + "'");
		System.out.println(lib.findBookReader(0).getReceiveBooks().toString());
		
		serializeObject(fileName, lib);
	}

	public static void serializeObject(String fileName, Object obj) {
		try (FileOutputStream fileOut = new FileOutputStream(fileName); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			((Library) obj).writeExternal(out);
			System.out.println("Object " + fileName + " serialized successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Library deSerializeObject(String fileName) {
		Library obj = new Library("");
		try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream in = new ObjectInputStream(fileIn)) {
			((Library) obj).readExternal(in);
			System.out.println("Object " + fileName + " deserialized successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
