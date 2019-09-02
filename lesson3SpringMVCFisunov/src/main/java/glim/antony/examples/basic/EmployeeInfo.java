package glim.antony.examples.basic;

import javax.persistence.*;

@Entity
@Table(name = "employees_info")
public class EmployeeInfo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @OneToOne(mappedBy = "info")
    Employee employee;
}
