package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    public void testMember() {
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass()); // memberRepository = class com.sun.proxy.$Proxy119
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member); // extends한 JpaRepository가 제공하는 것.
        // 인터페이스 밖에 없는데 다 제공. -> 스프링 데이터 jpa

        // 있을 수도 있고 없을 수도 있기 때문에 타입 optional
        Optional<Member> byId = memberRepository.findById(savedMember.getId());
        Member findMember = byId.get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // 같다
    }
}