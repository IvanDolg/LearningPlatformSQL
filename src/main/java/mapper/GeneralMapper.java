package mapper;


import com.example.learningplatformsql.entity.User;
import dto.UserDto.LoginUserDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface GeneralMapper{


    User mapToUser(LoginUserDto loginUserDto);
    LoginUserDto mapToLoginUserDto(User user);
}