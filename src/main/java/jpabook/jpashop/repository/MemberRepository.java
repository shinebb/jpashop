package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //final 필드 생성자 생성
public class MemberRepository {

    //@PersistenceContext -> 이게 정석이지만 스프링부트에서 @Autowired도 가능하게 해줌.
    //따라서 @RequiredArgsConstructor 가능
    private EntityManager em;

    public void save(Member member) { //저장
        em.persist(member);
    }

    public Member findOne(Long id) { //단건 조회
        return em.find(Member.class, id); //타입, PK
    }

    public List<Member> findAll() { //리스트 조회
        //(JPQL(테이블이 아닌 엔티티객체를 대상으로 쿼리작성), 반환타입)
        //ctrl+alt+n
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) { //id가 아닌 name으로 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
