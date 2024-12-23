package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="cart")
@Getter @Setter
@ToString
public class Cart extends BaseEntity {

    @Id
    @Column(name= "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 @OneToOne(fetch = FetchType.LAZY) // Member테이블 사용 되기 직전 조회   (지연로딩)
//    @OneToOne(fetch = FetchType.EAGER) // member테이블 cart테이블 전부 조회  (즉시로딩)
    @JoinColumn(name="member_id")  //Member 테이블 member_id를 외래키로 설정
    private Member member;

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;

    }
}
