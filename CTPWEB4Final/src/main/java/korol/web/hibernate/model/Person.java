package korol.web.hibernate.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "people")
@NoArgsConstructor
@Data
@Accessors (chain = true)
public class Person {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "person_id")
    private int personId;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String phone;

    @Column
    private String mail;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private AuthUser user;

	public Person(String surname, String name, int age, String phone, String mail) {
		super();
		this.surname = surname;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.mail = mail;
	}

}
