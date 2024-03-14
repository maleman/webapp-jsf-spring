package sv.bandesal.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "blogs_readers",
uniqueConstraints = { @UniqueConstraint(columnNames = { "blogId", "readerId" }) })
public class BlogReader {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "blogId", nullable=false)
	private Blog blog;

	@ManyToOne
	@JoinColumn(name = "readerId", nullable=false)
	private Reader reader;
}
