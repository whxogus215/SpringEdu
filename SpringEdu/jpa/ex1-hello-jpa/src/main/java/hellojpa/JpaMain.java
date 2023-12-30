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

            Member member = new Member();
            member.setName("C");

            System.out.println("---------------------------");
            em.persist(member); // IDENTITY일 경우, 이 때 INSERT 쿼리가 나감 (1차 캐시에 저장하기 위한 ID 값이 필요하기 때문!)
            System.out.println("member.id = " + member.getId()); // 커밋하기 전에 이미 ID 값을 알 수 있음
            System.out.println("---------------------------");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
