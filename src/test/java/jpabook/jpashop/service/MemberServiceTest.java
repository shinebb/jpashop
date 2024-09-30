package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)  //Junit 실행시 스프링이랑 엮어서 실행한다.
@SpringBootTest //메모리모드로 JPA로 DB까지 도는걸 확인하기위해 위에 두 @를 사용한다.
@Transactional  //트랜잭션 롤백
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false) //확인용
    public void 회원가입() throws Exception {
        //given :이렇게 주어졌을 때
        Member member = new Member();
        member.setName("kim");

        //when : 이렇게 하면
        Long savedId = memberService.join(member);

        //then :이렇게 된다.
        em.flush(); //롤백없이 쿼리만 찍고 싶을때
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        /*try{
            memberService.join(member2); //예외가 발생해야 한다!!!
        } catch(IllegalStateException e) {
            return; //예외 발생시 return하기 때문에 테스트케이스 성공
        }*/

        //then
        //코드가 돌다가 여기오면 안됨!! 그럼 실패한거임
        fail("예외가 발생해야 한다.");
    }

}