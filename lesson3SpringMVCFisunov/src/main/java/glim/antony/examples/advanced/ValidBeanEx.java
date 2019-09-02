package glim.antony.examples.advanced;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class ValidBeanEx {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Email
    @Column(name = "email")
    String email;

    @NotNull
    @Size(min = 6, max = 6)
    String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ValidBeanEx(Long id, String email, String postalCode) {
        this.id = id;
        this.email = email;
        this.postalCode = postalCode;
    }

    public static void main(String[] args) {
        ValidBeanEx obj = new ValidBeanEx(1L, "Hello", "1346500");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        validator.validate(obj);
        Set<ConstraintViolation<ValidBeanEx>> violations = validator.validate(obj);
        for (ConstraintViolation<ValidBeanEx> o : violations) {
            System.out.println(o);
        }
    }
}
