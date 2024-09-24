package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Repository : Entity 찾아주는 역할, DAO랑 비슷하다고 생각하면 된다.
@Repository
public class MemberRepository {

    //Test 단축키 (win) shift+ctrl+T
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
