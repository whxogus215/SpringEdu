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
            // 자바 컬렉션에서 꺼내오는 것처럼 사용 가능!
//            Member findMember = em.find(Member.class, 1L);
            /**
             * 삭제 쿼리
             * em.remove(findMember);
             */
//            findMember.setName("HelloJPA");
            /**
             * 객체(엔티티)를 대상으로 쿼리를 날림 - JPQL
             */
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
