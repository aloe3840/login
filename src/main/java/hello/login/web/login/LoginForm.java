package hello.login.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotEmpty  //비어있으면 안 됨
	private String loginId;
	@NotEmpty  //위와같음
	private String password;
	
	
}
