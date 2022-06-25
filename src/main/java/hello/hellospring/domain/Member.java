package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {


    // db에 자동으로 값을 너헝주는 걸 Identity라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "username")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
