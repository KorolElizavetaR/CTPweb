package com.webxml.library.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private int personId;

	@Pattern(regexp = "[А-ЯЁ][-А-яЁё]+ [А-ЯЁ][-А-яЁё]+ [А-ЯЁ][-А-яЁё]+", message = "Некорректный ввод ФИО")
	@NotEmpty(message = "Full name cannot be empty")
	@Column(name = "full_name", nullable = false, unique = true)
	private String fullName;

	@NotNull(message = "Birth date cannot be null")
	@Past(message = "Birth date must be in the past or today")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Min(value = 1920, message = "Birth year must be 1920 or later")
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@OneToMany(mappedBy = "bookOwner", fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Book> books;

	public Person(String full_name, LocalDate birth_date) {
		setBirthDate(birth_date);
		setFullName(full_name);
	}

	public Person(String full_name, LocalDate birth_date, List<Book> books) {
		this(full_name, birth_date);
		this.books = books;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void removeBook(Book book) {
		books.remove(book);
	}

	public List<Book> getBooks() {
		return this.books;
	}
}
