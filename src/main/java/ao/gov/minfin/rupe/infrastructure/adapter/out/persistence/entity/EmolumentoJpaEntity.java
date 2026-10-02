package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "emolumentos")
public class EmolumentoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    protected EmolumentoJpaEntity() {
    }

    public EmolumentoJpaEntity(
            String nome,
            BigDecimal valor
    ) {
        this.nome = nome;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }
}