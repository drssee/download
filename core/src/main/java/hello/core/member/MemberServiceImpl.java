package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("memberService2_changedName")
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired //자동 의존관계 주입 (component만 쓰면 의존관계 주입을 설정할수 없기 때문)
    //자바 코드로 구현한 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
