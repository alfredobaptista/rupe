-- ============================================================
-- IAPI - Instituto Angolano da Propriedade Industrial
-- Tabela de taxas baseada na documentação oficial do IAPI
-- / Decreto Presidencial n.º 62/20, de 04 de Março.
--
-- codigo_organismo = 0001
-- Código interno utilizado pelo projecto para identificar o
-- organismo. Não representa um código oficial SIGFE.
-- ============================================================


-- ============================================================
-- MARCAS
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-MAR-001', 'Registo de Marca', '0001', '01', TRUE),
    ('IAPI-MAR-002', 'Por cada produto adicional', '0001', '01', TRUE),
    ('IAPI-MAR-003', 'Renovação de Marca', '0001', '01', TRUE),
    ('IAPI-MAR-004', 'Renovação de Marca com acréscimo de 50%', '0001', '01', TRUE),
    ('IAPI-MAR-005', 'Revalidação de Marca', '0001', '01', TRUE),
    ('IAPI-MAR-006', 'Averbamento de Transmissão', '0001', '01', TRUE),
    ('IAPI-MAR-007', 'Outros Averbamentos', '0001', '01', TRUE),
    ('IAPI-MAR-008', 'Declaração de Caducidade', '0001', '01', TRUE),
    ('IAPI-MAR-009', 'Classificador', '0001', '01', TRUE);


-- ============================================================
-- INSÍGNIA E NOME DE ESTABELECIMENTO
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-EST-001', 'Registo de Insígnia e Nome de Estabelecimento', '0001', '02', TRUE),
    ('IAPI-EST-002', 'Renovação de Insígnia e Nome de Estabelecimento', '0001', '02', TRUE),
    ('IAPI-EST-003', 'Averbamentos de Insígnia e Nome de Estabelecimento', '0001', '02', TRUE);


-- ============================================================
-- INDICAÇÕES GEOGRÁFICAS / RECOMPENSAS
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-IG-001', 'Registo de Indicações Geográficas e Recompensas', '0001', '03', TRUE),
    ('IAPI-IG-002', 'Averbamento por Correcção de Indicações Geográficas e Recompensas', '0001', '03', TRUE);


-- ============================================================
-- PATENTES DE INVENÇÃO
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-PAT-001', 'Depósito de Patente até 15 reivindicações', '0001', '04', TRUE),
    ('IAPI-PAT-002', 'Cada reivindicação adicional de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-003', 'Exame Substancial de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-004', 'Antecipação da Publicação de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-005', 'Adiamento da Publicação de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-006', 'Licença de Exploração Obrigatória de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-007', 'Averbamentos de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-008', '1.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-009', '2.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-010', '3.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-011', '4.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-012', '5.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-013', '6.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-014', '7.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-015', '8.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-016', '9.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-017', '10.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-018', '11.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-019', '12.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-020', '13.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-021', '14.ª Anuidade de Patente', '0001', '04', TRUE),
    ('IAPI-PAT-022', '15.ª Anuidade de Patente', '0001', '04', TRUE);


-- ============================================================
-- MODELOS DE UTILIDADE
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-MU-001', 'Depósito de Modelo de Utilidade até 15 reivindicações', '0001', '05', TRUE),
    ('IAPI-MU-002', 'Cada reivindicação adicional de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-003', 'Exame Substancial de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-004', 'Adiamento da Publicação de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-005', 'Licença de Exploração Obrigatória de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-006', 'Averbamentos de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-007', 'Prorrogação da Renovação de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-008', '1.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-009', '2.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-010', '3.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-011', '4.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-012', '5.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-013', '6.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-014', '7.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-015', '8.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-016', '9.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-017', '10.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-018', '11.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-019', '12.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-020', '13.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-021', '14.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE),
    ('IAPI-MU-022', '15.ª Anuidade de Modelo de Utilidade', '0001', '05', TRUE);


-- ============================================================
-- MODELO E DESENHO INDUSTRIAL
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-MDI-001', 'Registo de Modelo e Desenho Industrial até 5 produtos', '0001', '06', TRUE),
    ('IAPI-MDI-002', 'Cada produto adicional de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-003', 'Exame Substancial de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-004', 'Antecipação da Publicação de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-005', 'Prorrogação da Renovação de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-006', 'Averbamentos de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-007', '1.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-008', '2.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-009', '3.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-010', '4.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-011', '5.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-012', '6.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-013', '7.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-014', '8.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-015', '9.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-016', '10.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-017', '11.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-018', '12.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-019', '13.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-020', '14.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE),
    ('IAPI-MDI-021', '15.ª Anuidade de Modelo e Desenho Industrial', '0001', '06', TRUE);


-- ============================================================
-- ACTOS COMUNS
-- ============================================================

INSERT INTO servicos (
    codigo,
    nome,
    codigo_organismo,
    codigo_modulo,
    ativo
)
VALUES
    ('IAPI-ATO-001', 'Duplicação ou 2.ª Via do Documento', '0001', '07', TRUE),
    ('IAPI-ATO-002', 'Junção', '0001', '07', TRUE),
    ('IAPI-ATO-003', 'Informação sobre Processos', '0001', '07', TRUE),
    ('IAPI-ATO-004', 'Busca', '0001', '07', TRUE),
    ('IAPI-ATO-005', 'Prorrogação de Entrega de Documentos por 30 dias', '0001', '07', TRUE),
    ('IAPI-ATO-006', 'Prorrogação de Entrega de Documentos por 60 dias', '0001', '07', TRUE),
    ('IAPI-ATO-007', 'Oposição', '0001', '07', TRUE),
    ('IAPI-ATO-008', 'Contestação', '0001', '07', TRUE),
    ('IAPI-ATO-009', 'Exposição Suplementar ou Peças Análogas', '0001', '07', TRUE),
    ('IAPI-ATO-010', 'Prorrogação do Prazo de Oposição ou Contestação', '0001', '07', TRUE),
    ('IAPI-ATO-011', 'Suspensão de Estudo', '0001', '07', TRUE),
    ('IAPI-ATO-012', 'Boletim', '0001', '07', TRUE),
    ('IAPI-ATO-013', 'Classificador', '0001', '07', TRUE),
    ('IAPI-ATO-014', 'Admissão como Agente da Propriedade Industrial', '0001', '07', TRUE),
    ('IAPI-ATO-015', 'Publicitação dos Agentes Oficiais da PI no Boletim da PI por Edição', '0001', '07', TRUE);


-- ============================================================
-- EMOLUMENTOS
-- ============================================================

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 11176.00
FROM servicos WHERE codigo = 'IAPI-MAR-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa por unidade adicional', 792.00
FROM servicos WHERE codigo = 'IAPI-MAR-002';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 5632.00
FROM servicos WHERE codigo = 'IAPI-MAR-003';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 8448.00
FROM servicos WHERE codigo = 'IAPI-MAR-004';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 22528.00
FROM servicos WHERE codigo = 'IAPI-MAR-005';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 7744.00
FROM servicos WHERE codigo = 'IAPI-MAR-006';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 7392.00
FROM servicos WHERE codigo = 'IAPI-MAR-007';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 9152.00
FROM servicos WHERE codigo = 'IAPI-MAR-008';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 1144.00
FROM servicos WHERE codigo = 'IAPI-MAR-009';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 17956.00
FROM servicos WHERE codigo = 'IAPI-EST-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 10208.00
FROM servicos WHERE codigo = 'IAPI-EST-002';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 7392.00
FROM servicos WHERE codigo = 'IAPI-EST-003';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 22352.00
FROM servicos WHERE codigo = 'IAPI-IG-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 7392.00
FROM servicos WHERE codigo = 'IAPI-IG-002';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 18656.00
FROM servicos WHERE codigo = 'IAPI-PAT-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa por reivindicação adicional', 792.00
FROM servicos WHERE codigo = 'IAPI-PAT-002';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 27280.00
FROM servicos WHERE codigo = 'IAPI-PAT-003';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 5456.00
FROM servicos WHERE codigo = 'IAPI-PAT-004';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 5456.00
FROM servicos WHERE codigo = 'IAPI-PAT-005';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 7392.00
FROM servicos WHERE codigo = 'IAPI-PAT-006';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 10032.00
FROM servicos WHERE codigo = 'IAPI-PAT-007';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '1.ª Anuidade', 4400.00
FROM servicos WHERE codigo = 'IAPI-PAT-008';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '2.ª Anuidade', 4666.00
FROM servicos WHERE codigo = 'IAPI-PAT-009';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '3.ª Anuidade', 5016.00
FROM servicos WHERE codigo = 'IAPI-PAT-010';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '4.ª Anuidade', 5632.00
FROM servicos WHERE codigo = 'IAPI-PAT-011';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '5.ª Anuidade', 5984.00
FROM servicos WHERE codigo = 'IAPI-PAT-012';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '6.ª Anuidade', 6248.00
FROM servicos WHERE codigo = 'IAPI-PAT-013';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '7.ª Anuidade', 6864.00
FROM servicos WHERE codigo = 'IAPI-PAT-014';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '8.ª Anuidade', 7216.00
FROM servicos WHERE codigo = 'IAPI-PAT-015';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '9.ª Anuidade', 7568.00
FROM servicos WHERE codigo = 'IAPI-PAT-016';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '10.ª Anuidade', 8184.00
FROM servicos WHERE codigo = 'IAPI-PAT-017';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '11.ª Anuidade', 8448.00
FROM servicos WHERE codigo = 'IAPI-PAT-018';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '12.ª Anuidade', 8800.00
FROM servicos WHERE codigo = 'IAPI-PAT-019';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '13.ª Anuidade', 9064.00
FROM servicos WHERE codigo = 'IAPI-PAT-020';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '14.ª Anuidade', 9680.00
FROM servicos WHERE codigo = 'IAPI-PAT-021';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '15.ª Anuidade', 10296.00
FROM servicos WHERE codigo = 'IAPI-PAT-022';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 10032.00
FROM servicos WHERE codigo = 'IAPI-MU-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa por reivindicação adicional', 792.00
FROM servicos WHERE codigo = 'IAPI-MU-002';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 17336.00
FROM servicos WHERE codigo = 'IAPI-MU-003';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 5456.00
FROM servicos WHERE codigo = 'IAPI-MU-004';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 7392.00
FROM servicos WHERE codigo = 'IAPI-MU-005';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 10032.00
FROM servicos WHERE codigo = 'IAPI-MU-006';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 5016.00
FROM servicos WHERE codigo = 'IAPI-MU-007';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '1.ª Anuidade', 3432.00
FROM servicos WHERE codigo = 'IAPI-MU-008';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '2.ª Anuidade', 3432.00
FROM servicos WHERE codigo = 'IAPI-MU-009';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '3.ª Anuidade', 3432.00
FROM servicos WHERE codigo = 'IAPI-MU-010';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '4.ª Anuidade', 3432.00
FROM servicos WHERE codigo = 'IAPI-MU-011';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '5.ª Anuidade', 3432.00
FROM servicos WHERE codigo = 'IAPI-MU-012';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '6.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MU-013';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '7.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MU-014';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '8.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MU-015';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '9.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MU-016';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '10.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MU-017';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '11.ª Anuidade', 4752.00
FROM servicos WHERE codigo = 'IAPI-MU-018';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '12.ª Anuidade', 4752.00
FROM servicos WHERE codigo = 'IAPI-MU-019';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '13.ª Anuidade', 4752.00
FROM servicos WHERE codigo = 'IAPI-MU-020';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '14.ª Anuidade', 4752.00
FROM servicos WHERE codigo = 'IAPI-MU-021';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '15.ª Anuidade', 4752.00
FROM servicos WHERE codigo = 'IAPI-MU-022';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 5192.00
FROM servicos WHERE codigo = 'IAPI-MDI-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa por produto adicional', 792.00
FROM servicos WHERE codigo = 'IAPI-MDI-002';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 10032.00
FROM servicos WHERE codigo = 'IAPI-MDI-003';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 1584.00
FROM servicos WHERE codigo = 'IAPI-MDI-004';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 2640.00
FROM servicos WHERE codigo = 'IAPI-MDI-005';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 10032.00
FROM servicos WHERE codigo = 'IAPI-MDI-006';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '1.ª Anuidade', 2904.00
FROM servicos WHERE codigo = 'IAPI-MDI-007';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '2.ª Anuidade', 2904.00
FROM servicos WHERE codigo = 'IAPI-MDI-008';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '3.ª Anuidade', 2904.00
FROM servicos WHERE codigo = 'IAPI-MDI-009';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '4.ª Anuidade', 2904.00
FROM servicos WHERE codigo = 'IAPI-MDI-010';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '5.ª Anuidade', 2904.00
FROM servicos WHERE codigo = 'IAPI-MDI-011';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '6.ª Anuidade', 3520.00
FROM servicos WHERE codigo = 'IAPI-MDI-012';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '7.ª Anuidade', 3520.00
FROM servicos WHERE codigo = 'IAPI-MDI-013';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '8.ª Anuidade', 3520.00
FROM servicos WHERE codigo = 'IAPI-MDI-014';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '9.ª Anuidade', 3520.00
FROM servicos WHERE codigo = 'IAPI-MDI-015';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '10.ª Anuidade', 3520.00
FROM servicos WHERE codigo = 'IAPI-MDI-016';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '11.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MDI-017';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '12.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MDI-018';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '13.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MDI-019';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '14.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MDI-020';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, '15.ª Anuidade', 4136.00
FROM servicos WHERE codigo = 'IAPI-MDI-021';


INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 2024.00
FROM servicos WHERE codigo = 'IAPI-ATO-001';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 1232.00
FROM servicos WHERE codigo = 'IAPI-ATO-002';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 1584.00
FROM servicos WHERE codigo = 'IAPI-ATO-003';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 8536.00
FROM servicos WHERE codigo = 'IAPI-ATO-004';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 1232.00
FROM servicos WHERE codigo = 'IAPI-ATO-005';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 2464.00
FROM servicos WHERE codigo = 'IAPI-ATO-006';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 9152.00
FROM servicos WHERE codigo = 'IAPI-ATO-007';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 9152.00
FROM servicos WHERE codigo = 'IAPI-ATO-008';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 9152.00
FROM servicos WHERE codigo = 'IAPI-ATO-009';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 4576.00
FROM servicos WHERE codigo = 'IAPI-ATO-010';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 3256.00
FROM servicos WHERE codigo = 'IAPI-ATO-011';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 13728.00
FROM servicos WHERE codigo = 'IAPI-ATO-012';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 1144.00
FROM servicos WHERE codigo = 'IAPI-ATO-013';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa oficial', 75944.00
FROM servicos WHERE codigo = 'IAPI-ATO-014';

INSERT INTO emolumentos (servico_id, nome, valor)
SELECT id, 'Taxa por edição', 3784.00
FROM servicos WHERE codigo = 'IAPI-ATO-015';

