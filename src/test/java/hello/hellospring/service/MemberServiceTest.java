package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemoryMemberRepository mr;
    MemberService ms;

    @BeforeEach
    public void beforeEach(){
        mr = new MemoryMemberRepository();
        ms = new MemberService(mr);
    }

    @AfterEach
    private void afterEach(){
        mr.clearStore();;
    }

    @Test
    void 회원가입() {
        //given 이 데이터를 기반으로 하구나
        Member member = new Member();
        member.setName("spring");

        //when 이걸 검증하구나
        Long saveId = ms.join(member);

        //then 검증부구나
        Member findMember = ms.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        ms.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> ms.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}