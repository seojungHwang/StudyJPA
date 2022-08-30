package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //스프링 JPA 데이터가 @Autowired 지원해줘서 가능 (스프링부트가 Autowired도 인젝션 되게 지원해줌)
public class MemberRepository {

    /*//엔티티를 관리하는 역할
    //여러 엔티티 매니저가 하나의 영속성 컨텍스트를 공유 가능
     @PersistenceContext
     private EntityManager em;*/

    private final EntityManager em;

    public void save(Member member){
        //persist 역할 -> 영속성 컨텍스트에 member 객체 넣음
        //트랙잭션 하는 시점에 db 저장
        em.persist(member);
    }

    //단건 조회
    public Member fineOne(Long id){
           //find 메서드 (타입       , PK)
       return em.find(Member.class, id);
    }

    // JPQL 사용
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
              .getResultList();
    }

    //특정 회원만 조회
    public List<Member> fineByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
