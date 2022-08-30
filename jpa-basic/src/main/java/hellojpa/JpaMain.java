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
          //em.persist(findMember); -> 수정시에는 필요 없음

          // em.remove(findMember); 삭제

            //  em.persist(member); //실질 DB 저장은 아님  //영속상태
            // em.flush(); //쿼리에 바로 반영
            //em.detach(member); //영속성 컨텍스트 관리x / 특정 엔티티만 준영속 상태로 전환

           /*  비영속
          속Member member = new Member();
            member.setId(100L);*/




          tx.commit();  //DB 쿼리 날라감
        } catch (Exception e){
          tx.rollback();
        }finally {
          em.close();
        }
        emf.close();
    }
}
