import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String bookName;
    String authorName;
    int bookID;

    public Book(String bookName, String authorName, int bookID) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookID = bookID;
    }
}

class Member {
    String memberName;
    int memberBookRentID;
    int memberBookRentTime;

    public Member(String memberName, int memberBookRentID, int memberBookRentTime, int memberID) {
        this.memberName = memberName;
        this.memberBookRentID = memberBookRentID;
        this.memberBookRentTime = memberBookRentTime;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<Member> memberList = new ArrayList<>();

        bookList.add(new Book("War and peace", "Lev Tolstoy", 1));
        bookList.add(new Book("1984", "George Orwell", 2));
        bookList.add(new Book("Blindness", "José Saramago", 3));
        bookList.add(new Book("Notes from Underground", "Fyodor Dostoyevski", 4));
        bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", 5));

        System.out.println("BOOK LIST");
        for (Book book : bookList) {
            System.out.printf("Book Name: %s | Author Name: %s | Book ID: %d%n",
                    book.bookName, book.authorName, book.bookID);
        }

        System.out.println("How many members will be logged in?");
        int memberCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < memberCount; i++) {
            System.out.println("Name-Surname:");
            String name = scanner.nextLine();

            System.out.println("ID number of the book you want to rent:");
            int bookID = scanner.nextInt();

            boolean validBook = false;
            for (Book book : bookList) { //bookList'te kullanıcının girdiği ID değerine sahip bir kitap olup olmadığını kontrol eder.
                if (book.bookID == bookID) {
                    validBook = true;
                    break;
                }
            }

            if (!validBook) {
                System.out.println("Invalid Book ID. Try again.");
                i--;
                scanner.nextLine();
                continue;
            }

            System.out.println("How long do you want to rent it (days): ");
            int rentTime = scanner.nextInt();
            scanner.nextLine();

            // Üyeyi listeye ekler.
            memberList.add(new Member(name, bookID, rentTime, i + 1));
        }
        
        scanner.close();

        System.out.println("MEMBERS LIST");
        for (Member member : memberList) {
            // Üyenin kiraladığı kitabın ID'si ile kitap listesindeki kitapları eşleştiriyoruz.
            for (Book book : bookList) {
                if (book.bookID == member.memberBookRentID) {
                    // Eşleşme bulunduğunda bilgileri ekrana yazdırıyoruz.
                    System.out.printf("Member: %s | Rented Book: %s | Rent Time: %d days%n",
                            member.memberName, book.bookName, member.memberBookRentTime);
                    break;
                }
            }
        }
    }
}