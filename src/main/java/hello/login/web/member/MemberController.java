package hello.login.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor   //final 키워드로 선언하는 모든 필드들을 객체 생성시점에 생성자 생성 (의존성 주입) => 공부하기
@RequestMapping("/members")
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member) {
		return "members/addMemberForm";  //화면만 return 하는 메서드
	}
	
	@PostMapping("/add")  //애너테이션들은 따로 공부하기 
	public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) { //오류 있으면 addMemberForm 으로 보내기
			return "memers/addMemberForm";
		}
		
		memberRepository.save(member); //오류 없으면 save 에 member 추가
		
		return "redirect:/"; //redirect 로 아무것도 없는 화면 return (PostMapping이라 화면 관련 X 값만 저장 O)
	}
	
	
	
	
	
	
	
	
}
