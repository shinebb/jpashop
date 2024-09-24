package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL쓰면 인덱스 숫자 처리되어 중간에 하나 추가되면 다 틀어짐. 무조건 STRING으로 사용할것
    private DeliveryStatus status; //READY, COMP
}
