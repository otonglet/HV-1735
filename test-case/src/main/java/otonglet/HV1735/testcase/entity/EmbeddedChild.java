package otonglet.HV1735.testcase.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Embeddable
public class EmbeddedChild {
    @NotNull
    @Transient
    private String field = null;

    @Column(name = "embedded_field")
    private String embeddedField;
}
