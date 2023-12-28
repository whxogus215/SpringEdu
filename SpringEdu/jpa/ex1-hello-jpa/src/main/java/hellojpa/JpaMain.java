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
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("AAAA");

            em.detach(findMember); // 엔티티 매니저에서 관리하지 않게 만듦(준영속화)
            // em.clear(); 그 외에 준영속화를 할 수 있는 메서드들
            // em.close();

            tx.commit(); // 커밋 시 UPDATE 쿼리 안나감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
