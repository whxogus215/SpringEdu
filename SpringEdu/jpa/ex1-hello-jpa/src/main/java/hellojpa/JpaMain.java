package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member(200L, "test");
            em.persist(member);
            
            em.flush(); // 직접 flush를 호출하게 되면, 쓰기 지연 SQL 저장소에 있는 쿼리문이 바로 DB에 나가게 됨

            tx.commit(); // 실제 DB에 영구적으로 저장되는 것은 커밋 이후
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
