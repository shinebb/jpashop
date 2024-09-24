package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    //@Id : 식별자 Id로 매핑
    //@GeneratedValue : 자동생성(DB)
    @Id @GeneratedValue
    private Long id;
    private String username;
}
