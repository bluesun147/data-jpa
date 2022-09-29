package study.datajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

// 인터페이스
public interface MemberRepository extends JpaRepository<Member, Long> {

}