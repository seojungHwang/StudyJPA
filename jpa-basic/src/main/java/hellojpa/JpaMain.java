package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

      //DB 커넥션, 쓰레드 공유x
        EntityManager em = emf.createEntityManager();

        //jpa는 트랜잭션 연결 시작 필요!
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
          // 입력
         /* Member member = new Member();
          member.setId(2L);
          member.setName("HelloB");*/

          //조회
          //JPQL 객체지향 쿼리
         List<Member> result = em.createQuery("select m from Member as m", Member.class)
                 .setFirstResult(5)   //페이징 할때 편함!
                 .setMaxResults(8)
                 .getResultList();

         for(Member member : result){
           System.out.println("member = " + member.getName());
         }

          //수정
          Member findMember = em.find(Member.class, 1L);
          findMember.setName("Hello Update");

          // em.remove(findMember); 삭제

        //  em.persist(member); //DB 저장
          tx.commit();
        } catch (Exception e){
          tx.rollback();
        }finally {
          em.close();
        }
        emf.close();
    }
}
