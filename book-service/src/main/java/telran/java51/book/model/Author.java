package telran.java51.book.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter

@Setter
@EqualsAndHashCode(of = "name")

@Entity
@Embeddable
public class Author implements Serializable{

	private static final long serialVersionUID = 6072067234828047833L;
	
	@Id
	String name;
	LocalDate birthDate;
	
	
}
