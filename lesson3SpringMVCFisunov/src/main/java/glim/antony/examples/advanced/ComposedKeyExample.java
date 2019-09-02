package glim.antony.examples.advanced;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class ComposedKeyExample {
    @Embeddable
    public class SerialNumber implements Serializable {
        @Column(name = "serial")
        String serial;

        @Column(name = "number")
        String number;

        public SerialNumber() {
        }

        public SerialNumber(String serial, String number) {
            this.serial = serial;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SerialNumber that = (SerialNumber) o;
            return Objects.equals(serial, that.serial) &&
                    Objects.equals(number, that.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(serial, number);
        }
    }

    @EmbeddedId
    SerialNumber id;
}
