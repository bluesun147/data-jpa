package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter // setter는 entity에 없는게 좋음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 객체 찍을때 바로 출력. 연관관계 필드는 안하는 게 좋음
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    // member와 team은 다대일 관계
    // 다대일 관계에서 fk는 다 쪽에.
    // member가 다. team이 일
    @ManyToOne(fetch = FetchType.LAZY) // 웬만하면 다 lazy (지연 로딩: 멤버 조회시에는 멤버만 <-> eager)
    @JoinColumn(name = "team_id")
    private Team team;


    /*protected Member() { // jpa는 기본 생성자 있어야 함.
    }*/ // NoArgsConstructor 쓰면 이거 안써도 됨. private은 안됨

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;

        if (team != null) {
            changeTeam(team);
        }
    }

    // 팀 변경 메소드
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 팀에 있는 멤버에다가도 세팅 걸어줌
    }
}
