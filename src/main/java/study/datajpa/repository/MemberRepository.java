package study.datajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

// 인터페이스
// 구현체 없는데 어떻게 동작할까 ??
// 스프링 data jpa가 인터페이스 보고 프록시 객체 (구현체) 알아서 넣음
// @Repository 생략 가능

// <엔티티, 엔티티에 매핑된 pk 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 쿼리 메소드 기능
    // 메소드 이름만으로 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}
