package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //protected 생성자는 함부로 new 생성하면 안되는 것을 알수 있음! jpa의 스펙
    protected Address() {

    }

    // 값 타입은 생성할 때만 getter 제공하고 setter 제공 안하는 것이 좋음
    // 값 타입은 변경 불가능 하게 설계 하여야 함
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
