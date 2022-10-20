package hello.core.order;


import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    @Autowired //필드주입은 추천하지 않음 자바코드로 테스트시 수정할수 없음
    private MemberRepository memberRepository;
//    @Autowired
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    @Qualifier("rateDiscountPolicy")
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    //        @Autowired //생성자가 1개일땐 생략 가능
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //단일 책임 원칙 설계 예 - 서비스는 할인과 관련된 내부 기능을 몰라도 구현이 됨
        //                    나중에 수정이 필요하거나 오류 발생시 discount 쪽만 수정하면 가능
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
