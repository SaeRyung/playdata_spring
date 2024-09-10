package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "entityMember")
@Table(name = "tbl_member")
/*@TableGenerator(
        //@TableGenerator : 넘버링 할 값을 저장하여
        name = "member_seq_tbl_generator",
        table = "tbl_my_sequences",
        pkColumnValue = "my_sql_member_no"
)*/

// 필드접근 or 메소드 접근 분리하는 기능
// 특정 필드 위에도 붙일 수 있다.
@Access(AccessType.FIELD) // 클래스 레벨에 설정 가능하며 모든 필드를 대상으로 적용하겠다는 의미 -> 단 FIELD가 default라 영향X
public class Member {

    @Id // @Id: primary key 역할을 하는 필드 지정
    @Column(name = "member_no")
    // IDENTITY: 기본키 생성을 DB에 위임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_tbl_generator")
    // ㄴ @TableGenerator , 테이블을 활용하여 취급하겠다.
    private int memberNo;


    @Access(AccessType.FIELD) // 필드 레벨에도 설정 가능하며 해당 필드를 대상으로 적용하겠다는 의미 -> 역시 default라 영향X
    @Column(
            name = "member_id", unique = true,
            nullable = false, columnDefinition = "varchar(10)"
    )
    private String memberId;

    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Column(name = "member_name")
    private String memberName;

    @Transient
    @Column(name = "phone")
    private String phone;

    @Column(name = "address", length = 900)
    private String address;

    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    //Enumerated: 기본타입은 숫자
    private MemberRole memberRole;

    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;

    protected Member() {}

    public Member(
            String memberId, String memberPwd, String memberName,
            String phone, String address, LocalDateTime enrollDate,
            MemberRole memberRole, String status
    ) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }


    //////------------------
    // 필드 접근이 아닌 프로퍼티를 활용해서 접근 > getter, setter
    // 가공이 필요할 경우 PROPERTY 로 Access => getter, setter 모두 해야한다.
    @Access(AccessType.PROPERTY)
    public String getMemberName(){
        System.out.println("getMemberName 메소드를 통한 Access 확인");
        return memberName + "님";
    }

    public void setMemberName(String memberName){
        this.memberName = memberName;
    }
    /////---------------------

}