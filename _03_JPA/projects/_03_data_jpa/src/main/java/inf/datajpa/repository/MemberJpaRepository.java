package inf.datajpa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import inf.datajpa.entity.Member;

@Repository
public class MemberJpaRepository {

	@PersistenceContext
	private EntityManager em;

	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	public void delete(Member member) {
		em.remove(member);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	public long count() {
		return em.createQuery("select count(m) from Member m", Long.class)
			.getSingleResult();
	}

	public Member find(Long id) {
		return em.find(Member.class, id);
	}

	/**
	 * - 아래 조건을 페이징 하고 싶다고 한다.
	 *   - 검색 조건: 나이가 10살
	 *   - 정렬 조건: 이름으로 내림차순
	 *   - 페이징 조건: 첫 번째 페이지, 페이지당 보여줄 데이터는 3건
	 */
	public List<Member> findByPage(int age, int offset, int limit) {
		return em.createQuery(
			"select m from Member m where m.age = :age order by m.username desc",
				Member.class
			)
			.setParameter("age", age)
			.setFirstResult(offset)
			.setMaxResults(limit)
			.getResultList();
	}

	public long totalCount(int age) {
		return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
			.setParameter("age", age)
			.getSingleResult();
	}

}
