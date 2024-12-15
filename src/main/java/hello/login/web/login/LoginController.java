package hello.login.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hello.login.domain.member.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm form) {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
						HttpServletResponse response) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		
		//binding Result 객체가 매핑한 error 가 있는지 확인
		if(bindingResult.hasErrors() || loginMember == null) {
			bindingResult.reject("loginFail", "아이디와 비밀번호가 맞지 않습니다.");
			return "login/loginForm";  
		}
		
		//세션 쿠키, 영속 쿠키 
		//쿠키 정보 사용
		//로그인 성공시에 쿠키를 생성하고, httpServletResponse에 담음
		//쿠키 이름이 :memberId", 쿠키값이 로그인 한 회원의 id
		//웹브라우저 종료 전까지 쿠키 사용 가능
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);
		
		log.info("loginMember={}", loginMember); //로그 찍어서 저장됐는지 확인
		response.addCookie(idCookie);
		
		//로그인 성공시 루트페이지로 이동
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
