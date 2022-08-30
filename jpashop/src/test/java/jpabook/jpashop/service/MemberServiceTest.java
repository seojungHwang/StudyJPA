package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)  //junit 실행 할 때 스프링이랑 같이 실행
@SpringBootTest  //스프링 부트를 띄운 상태로 테스트 할려면 필요 (없으면 Autowired 실패함)
@Transactional  //테스트케이스에 있으면 기본적으로 rollback 해버림 (테스트 끝나면 rollback 함)
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
  //  @Autowired EntityManager em; // @Rollback(false) 이거 아니면 EntityManager 사용해서 엔티티 영속성 생성!

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
     //   em.flush(); //db 쿼리 날림
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.fineOne(saveId));
    }

    @Test(expected = IllegalStateException.class)  // try catch 사용 안해도됨!
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 함!

      /*  try{
            memberService.join(member2); //예외가 발생해야 함!
        } catch (IllegalStateException e){
            return;
        }
       */

        //then
        fail("예외가 발생해야 한다");

    }

}