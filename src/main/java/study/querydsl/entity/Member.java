package study.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@ToString(of={"id", "username", "age"}) // team도 넣으면 무한반복되므로 에러남
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String usename;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String usename, int age, Team team) {
        this.usename = usename;
        this.age = age;
        if (team != null) {
            this.team = team;
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
