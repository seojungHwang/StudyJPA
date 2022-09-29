package japbook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //DB 커넥션, 쓰레드 공유x
        EntityManager em = emf.createEntityManager();

        //jpa는 트랜잭션 연결 시작 필요!
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        em.close();
    }
}
