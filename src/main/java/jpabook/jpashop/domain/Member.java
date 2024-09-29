package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    //@Id : 식별자 Id로 매핑
    //@GeneratedValue : 자동생성(DB)
    @Id @GeneratedValue
    @Column(name = "member_id") //PK
    private Long id;

    private String name;

    @Embedded //JPA 내장타입 포함
    private Address address;

    //Order테이블에있는 member 필드에 의해서 맵핑되었다. 내가 매핑하는애 아니야! -> 읽기전용 / 값을 넣는다고 해서 외래키값이 변경되지 않음
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
