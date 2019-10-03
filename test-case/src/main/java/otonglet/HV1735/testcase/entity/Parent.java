package otonglet.HV1735.testcase.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Transient
    private String field = null;

    @Valid
    @Embedded
    private EmbeddedChild embeddedChild;

    @Valid
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EagerChild> eagerChildren;

    @Valid
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<LazyChild> lazyChildren;

//    Uncomment those getters to have the "TestCase.java" test pass

//    @Valid
//    public EmbeddedChild getEmbeddedChild() {
//        return embeddedChild;
//    }
//
//    @Valid
//    public List<EagerChild> getEagerChildren() {
//        return eagerChildren;
//    }
//
//    @Valid
//    public List<LazyChild> getLazyChildren() {
//        return lazyChildren;
//    }
}
