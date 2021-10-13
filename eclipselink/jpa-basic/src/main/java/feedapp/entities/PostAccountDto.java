package feedapp.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAccountDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;

    @Column(unique = true)
    private String email;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String password;

}
