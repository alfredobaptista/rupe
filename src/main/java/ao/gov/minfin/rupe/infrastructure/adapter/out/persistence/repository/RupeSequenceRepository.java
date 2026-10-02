package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RupeSequenceRepository {

    private final JdbcTemplate jdbcTemplate;

    public RupeSequenceRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long obterProximoSequencial() {

        Long valor = jdbcTemplate.queryForObject(
                "SELECT nextval('rupe_sequencial_seq')",
                Long.class
        );

        if (valor == null) {
            throw new IllegalStateException(
                    "Não foi possível obter o próximo sequencial RUPE."
            );
        }

        return valor;
    }
}