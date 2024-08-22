package hellojpa;

import hellojpa.cascade.Child;
import hellojpa.cascade.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {

            final Child child1 = new Child();
            final Child child2 = new Child();

            final Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent); // 영속성 전이로 인해 자식 엔티티도 함께 저장.

            em.flush();
            em.clear();

            final Parent parentFound = em.find(Parent.class, parent.getId());
            parentFound.getChildren().remove(0);

            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
