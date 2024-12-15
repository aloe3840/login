package hello.login.web.login;

import org.springframework.stereotype.Service;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final MemberRepository memberRepository;
	
	//로그인 핵심 로직
	/*
	  회원 조회 후, 파라미터로 넘어온 password 와 비교하여
	  같으면 회원을 반환/다르면 null 을 반환
	*/
	public Member login(String loginId, String password) {
		return memberRepository.findByLoginId(loginId)
				.filter(m -> m.getPassword().equals(password))
				.orElse(null);   //람다식
	}
	
	
	
	
	
	
}
