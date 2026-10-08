package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "servicos",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_servico_codigo",
                        columnNames = "codigo"
                ),
                @UniqueConstraint(
                        name = "uk_servico_codigo_numerico",
                        columnNames = "codigo_numerico"
                )
        }
)
public class ServicoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String codigo;

    @Column(
            name = "codigo_numerico",
            nullable = false,
            length = 4
    )
    private String codigoNumerico;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(
            name = "codigo_organismo",
            nullable = false,
            length = 4
    )
    private String codigoOrganismo;

    @Column(
            name = "codigo_modulo",
            nullable = false,
            length = 2
    )
    private String codigoModulo;

    @Column(nullable = false)
    private boolean ativo;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "servico_id", nullable = false)
    private List<EmolumentoJpaEntity> emolumentos =
            new ArrayList<>();

    protected ServicoJpaEntity() {
    }

    public ServicoJpaEntity(
            String codigo,
            String codigoNumerico,
            String nome,
            String codigoOrganismo,
            String codigoModulo,
            boolean ativo,
            List<EmolumentoJpaEntity> emolumentos
    ) {
        this.codigo = codigo;
        this.codigoNumerico = codigoNumerico;
        this.nome = nome;
        this.codigoOrganismo = codigoOrganismo;
        this.codigoModulo = codigoModulo;
        this.ativo = ativo;
        this.emolumentos = new ArrayList<>(emolumentos);
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoNumerico() {
        return codigoNumerico;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public List<EmolumentoJpaEntity> getEmolumentos() {
        return emolumentos;
    }
}