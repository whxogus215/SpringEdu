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
            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속 : 엔티티 매니저 안에 있는 영속성 컨텍스트 안에서 관리되는 상태
            em.persist(member); // 여기서는 실제 DB에 저장되지 않는 상태

            tx.commit(); // 실제 쿼리는 이 때 나감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
