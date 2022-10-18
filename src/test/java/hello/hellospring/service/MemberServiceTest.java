package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach()
    public void beforEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService =  new MemberService(memoryMemberRepository);
    }

    @AfterEach()
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        //given (이런 상황이 주워져서~)
        Member member = new Member();
        member.setName("spring1");


        //when (이 상황을 실행했을때)
        Long saveId = memberService.join(member);

        //then (이게 나와야한다.)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외상황테스트케이스(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        //when
        memberService.join(member1);

        /*
        //첫번째 에러 관련 테스트 코드
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //두번째 테스트 코드
        //IllegalStateException에러가 터져야하는데 뭘할때 터져야해 -> memberService.join(member2)를 실행하면 에러가 터져야해
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}