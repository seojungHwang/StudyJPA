package hellojpa;

import javax.persistence.*;
import java.util.Date;

//jap 사용하는 애라고 인식
@Entity
public class Member {

    @Id //PK라고 알려줘야함
   // @GeneratedValue(strategy = GenerationType.AUTO) // GenerationType.Auto -> 오라클이면 시퀀스로 자동 설정 해줌(db방언에 맞게 자동 설정)
    private String id;

    private String name1;

    //필드랑 컬럼 매
    @Column(name = "name") //DB에는 name 이라는 컬럼!
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //db 에서 enum 타입을 쓰기 위해! // String 타입 권장! ORDINAL 타입쓰면 꼬일 수 있음!
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)  //날짜 타입 시 / 요즘은 LocalDate, LocalDateTime 많이 씀!
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob  //문자는 CLOB 나머지는 BLOB 으로 매핑 됨
    private String description;

    public String getName1() {
        return name1;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }


    public Member(){
    }

}
