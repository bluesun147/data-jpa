package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // setter는 entity에 없는게 좋음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 객체 찍을때 바로 출력. 연관관계 필드는 안하는 게 좋음
@ToString(of = {"id", "name"})
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    // member가 다. team이 일.
    // fk는 다(member)쪽에.
    // 서로 매핑되어있는 경우 한 쪽에 mapped
    // mappedBy는 fk 없는 쪽에 거는게 좋음.
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
