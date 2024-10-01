package korol.web.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "users")
@NoArgsConstructor
@Data
public class AuthUser {
	@Column (name = "user_id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "login")
	@NotEmpty
	@Size (min = 4, max = 50)
	private String login;
	
	@Column (name = "password")
	@NotEmpty
	private String password;
	
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
	
	@Column (name = "role")
	private String role;
	
	public AuthUser (String login, String password)
	{
		this.password = password;
		this.login = login;
		role = "ROLE_USER";
	}
}
