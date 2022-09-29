package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter // setter는 entity에 없는게 좋음
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    protected Member() { // jpa는 기본 생성자 있어야 함.
    }

    public Member(String username) {
        this.username = username;
    }
}
