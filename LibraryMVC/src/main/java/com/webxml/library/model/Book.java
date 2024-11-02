package com.webxml.library.model;

import lombok.*;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@EqualsAndHashCode (exclude = {"bookId", "bookOwner", "takenAt", "isExpired"})
public class Book {
	@Transient
	private final Duration EXPIRATION_DAYS = Duration.ofDays(10);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer bookId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_owner", referencedColumnName = "person_id", nullable = true)
	@ToString.Exclude
	private Person bookOwner;

	@NotNull(message = "Book name cannot be null")
	@NotEmpty(message = "Book name cannot be empty")
	@Column(name = "book_name", nullable = false)
	private String bookName;

	@NotNull(message = "Book author cannot be null")
	@NotEmpty(message = "Book author cannot be empty")
	@Column(name = "book_author", nullable = false)
	private String bookAuthor;

	@Column(name = "taken_at", nullable = false)
	private LocalDateTime takenAt;

	@Transient
	private boolean isExpired;

	@URL(message = "Cover URL should be a valid URL")
	private String coverUrl;

	Book(String bookName, String bookAuthor) {
		setBookAuthor(bookAuthor);
		setBookName(bookName);
	}

	public void setExpired() {
		if (takenAt != null)
			this.isExpired = Duration.between(takenAt, LocalDateTime.now()).compareTo(EXPIRATION_DAYS) > 0;
	}

	public void setTimestamp(LocalDateTime takenAt) {
		this.takenAt = takenAt;
		setExpired();
	}

	public void setTimestamp() {
		takenAt = LocalDateTime.now();
		setExpired(false);
	}
}
