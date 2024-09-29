package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기전용(조회시 성능 최적화)
//@AllArgsConstructor field의 생성자 자동 생성가능
@RequiredArgsConstructor //final이 있는 field만 가지고 생성자를 만들어준다.
public class MemberService {

    //@Autowired
    private final MemberRepository memberRepository;
    //위 인젝션은 리포짓토리를 바꿀 수 없는 단점이있다. 그러면 테스트주입도 어려움.
    //생성자 인젝션을 사용한다면 변경할 일이 없기 때문에 final로 지정해주는걸 권장한다.

    //그래서 생성자 인젝션을 사용하는 경우도 많다.
    //그리고 이렇게 생성자가 하나만 있다면 @Autowired 하지않아도 자동으로 인젝션 해준다.
    //@Autowired
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    /**
    * 회원 가입
    * */
    @Transactional //데이터 변경시에는 false(기본값)
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(Member member) {
        //실제로직에서는 name에 유니크 제약조건 설정까지 필요(안전하게)
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) { //List가 비어있지 않으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단건 회원 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
