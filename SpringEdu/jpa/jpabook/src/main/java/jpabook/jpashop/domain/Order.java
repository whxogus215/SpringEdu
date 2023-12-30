package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS") // DB에 ORDER가 예약어로 걸려있는 경우가 있어서 DB에는 주로 ORDERS로 표기
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /**
     * 관게형 DB에 맞춘 설계
     * ID를 참조할 경우, 이후에 이 ID로 다시 DB에서 찾아야 함.
     * 즉, 객체 자체를 참조함으로써 가능한 객체의 그래프 탐색이 이루어지지 않음.
     */
    @Column(name = "MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderDate;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}
