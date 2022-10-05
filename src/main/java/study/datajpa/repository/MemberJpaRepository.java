package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

 // 순수 jpa로 작성한 repository

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member); // 저장
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    // 전체 조회
    public List<Member> findAll() {
        // jpql -> 테이블 대상이 아닌 객체 대상 쿼리
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        // null일 수도 있다는 옵션으로 반환. 널일수도 아닐수도 있음
        // optional은 java 8 기본적인 기능. 알아두기
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    // 하나 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) { // :username은 파라미터로 넘어온 username이란 뜻
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age ", Member.class)
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    // 페이징
    // 나이가 10살이면서 이름으로 내림차순
    public List<Member> findByPage(int age, int offset, int limit) { // 몇번째부터 시작해서 몇개 가져오라는 파라미터
        return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset) // createQuery에서 jpql로 쿼리 날리는데 어디서부터 가져올건지
                .setMaxResults(limit) // 개수 몇개 가져올 지
                .getResultList();
    }

    // 보통 페이징 할 때 total count도 같이 가져옴
    // 내 페이지가 몇번째 페이지야
    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }
}