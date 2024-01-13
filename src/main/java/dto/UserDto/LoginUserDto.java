package dto.UserDto;

import com.example.learningplatformsql.entity.AbstractEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginUserDto {
    private AbstractEntity abstractEntity;

    @NotEmpty
    @NotBlank
    private Long id;

    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    @Range(min = 4,max = 16)
    private String password;

/*    @NotEmpty
    @NotBlank
    private LocalDateTime lastVisitDate;*/
}
