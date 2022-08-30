package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)     //JPA가 조회하는 곳에 성능 최적화 / 데이터 변경 안됨
//@AllArgsConstructor  // 생성자 만들어줌
@RequiredArgsConstructor //final을 가지고 있는 필드만 생성자 만들어 줌 / 이방법 권장
public class MemberService {

    // @Autowired
    private final MemberRepository memberRepository;

    //생성자 인젝션
    //테스트 케이스 작성 할때 MemberService에 Mock 안 넣어 줘도 됨
    // @Autowired -> 없어도 스프링이 자동으로 인젝션 해줌
   /* public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    //회원 가입
    @Transactional  //JPA에 모든 데이터 변경이나 로직들은 트랙잭션 안에서 실행되야 함
    public Long join(Member member){
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
      List<Member> findMembers = memberRepository.fineByName(member.getName());
      if(!findMembers.isEmpty()){
          throw new IllegalStateException("이미 존재하는 회원입니다.");
      }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.fineOne(memberId);
    }

}
