package glim.antony.examples.basic;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books_readers")
public class Review {
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "reader_id")
        int readerId;

        @Column(name = "book_id")
        int bookId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return readerId == id.readerId &&
                    bookId == id.bookId;
        }

        @Override
        public int hashCode() {
            return readerId + bookId;
        }
    }

    @EmbeddedId
    Id id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @Column(name = "score")
    int score;
}
