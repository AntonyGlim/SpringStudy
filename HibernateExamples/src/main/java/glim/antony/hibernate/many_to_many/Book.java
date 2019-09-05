package glim.antony.hibernate.many_to_many;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    private static final long serialVersionUID = -6183116139588984602L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "books_readers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    private List<Reader> readers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return String.format("Book [id = %d, title = %s]", id, title);
    }
}
