package study.datajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

// 인터페이스
// 구현체 없는데 어떻게 동작할까 ??
// 스프링 data jpa가 인터페이스 보고 프록시 객체 (구현체) 알아서 넣음
// @Repository 생략 가능

// <엔티티, 엔티티에 매핑된 pk 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {

    // -> 실무에서는 간단한 쿼리일때 메소드 이름으로 쿼리 생성
    // -> 좀 복잡해지면 쿼리 직접 정의, 메소드 이름은 심플하게
    // 동적 쿼리는 쿼리 dsl

    // 쿼리 메소드 기능
    // 메소드 이름만으로 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // jpql 바로 쓰기 (실무에서 많이 씀)
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 지금까지는 엔티티 타입만 조회
    // 단순한 값이나 dto 조회하는 법
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // 멤버랑 팀 둘다 가져와야 함 -> join
    // dto로 조회할 때는 new operation 써야 함.
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
