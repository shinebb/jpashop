package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//SpringBoot에서 TEST한다 -> 두 어노테이션 모두 필수
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional  //이거 없으면 에러발생, entitymanager를 통한 모든 데이터 변경은 트랜잭션 안에서 움직여야한다.
    @Rollback(false) //@Transactional이 테스트에 있으면 롤백을 시키기 때문에 실제 테이블에 데이터는 보이지 않는다. 보고싶다면 @Rollback(false)
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        // (memberRepository.save(member)입력 후  Ctrl+Alt+V : return 변수 생성
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then(검증)
        //Assetions 라이브러리
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

    }

}