-- Adiciona o código numérico do serviço (usado na composição da RUPE).

ALTER TABLE servicos
    ADD COLUMN codigo_numerico VARCHAR(4);

-- Atribuição explícita para os serviços IAPI já existentes.
UPDATE servicos SET codigo_numerico = '0001' WHERE codigo = 'IAPI-MAR-001';
UPDATE servicos SET codigo_numerico = '0002' WHERE codigo = 'IAPI-MAR-002';
UPDATE servicos SET codigo_numerico = '0003' WHERE codigo = 'IAPI-MAR-003';
UPDATE servicos SET codigo_numerico = '0004' WHERE codigo = 'IAPI-MAR-004';
UPDATE servicos SET codigo_numerico = '0005' WHERE codigo = 'IAPI-MAR-005';
UPDATE servicos SET codigo_numerico = '0006' WHERE codigo = 'IAPI-MAR-006';
UPDATE servicos SET codigo_numerico = '0007' WHERE codigo = 'IAPI-MAR-007';
UPDATE servicos SET codigo_numerico = '0008' WHERE codigo = 'IAPI-MAR-008';
UPDATE servicos SET codigo_numerico = '0009' WHERE codigo = 'IAPI-MAR-009';

UPDATE servicos SET codigo_numerico = '0010' WHERE codigo = 'IAPI-EST-001';
UPDATE servicos SET codigo_numerico = '0011' WHERE codigo = 'IAPI-EST-002';
UPDATE servicos SET codigo_numerico = '0012' WHERE codigo = 'IAPI-EST-003';

UPDATE servicos SET codigo_numerico = '0013' WHERE codigo = 'IAPI-IG-001';
UPDATE servicos SET codigo_numerico = '0014' WHERE codigo = 'IAPI-IG-002';

UPDATE servicos SET codigo_numerico = '0015' WHERE codigo = 'IAPI-PAT-001';
UPDATE servicos SET codigo_numerico = '0016' WHERE codigo = 'IAPI-PAT-002';
UPDATE servicos SET codigo_numerico = '0017' WHERE codigo = 'IAPI-PAT-003';
UPDATE servicos SET codigo_numerico = '0018' WHERE codigo = 'IAPI-PAT-004';
UPDATE servicos SET codigo_numerico = '0019' WHERE codigo = 'IAPI-PAT-005';
UPDATE servicos SET codigo_numerico = '0020' WHERE codigo = 'IAPI-PAT-006';
UPDATE servicos SET codigo_numerico = '0021' WHERE codigo = 'IAPI-PAT-007';
UPDATE servicos SET codigo_numerico = '0022' WHERE codigo = 'IAPI-PAT-008';
UPDATE servicos SET codigo_numerico = '0023' WHERE codigo = 'IAPI-PAT-009';
UPDATE servicos SET codigo_numerico = '0024' WHERE codigo = 'IAPI-PAT-010';
UPDATE servicos SET codigo_numerico = '0025' WHERE codigo = 'IAPI-PAT-011';
UPDATE servicos SET codigo_numerico = '0026' WHERE codigo = 'IAPI-PAT-012';
UPDATE servicos SET codigo_numerico = '0027' WHERE codigo = 'IAPI-PAT-013';
UPDATE servicos SET codigo_numerico = '0028' WHERE codigo = 'IAPI-PAT-014';
UPDATE servicos SET codigo_numerico = '0029' WHERE codigo = 'IAPI-PAT-015';
UPDATE servicos SET codigo_numerico = '0030' WHERE codigo = 'IAPI-PAT-016';
UPDATE servicos SET codigo_numerico = '0031' WHERE codigo = 'IAPI-PAT-017';
UPDATE servicos SET codigo_numerico = '0032' WHERE codigo = 'IAPI-PAT-018';
UPDATE servicos SET codigo_numerico = '0033' WHERE codigo = 'IAPI-PAT-019';
UPDATE servicos SET codigo_numerico = '0034' WHERE codigo = 'IAPI-PAT-020';
UPDATE servicos SET codigo_numerico = '0035' WHERE codigo = 'IAPI-PAT-021';
UPDATE servicos SET codigo_numerico = '0036' WHERE codigo = 'IAPI-PAT-022';

UPDATE servicos SET codigo_numerico = '0037' WHERE codigo = 'IAPI-MU-001';
UPDATE servicos SET codigo_numerico = '0038' WHERE codigo = 'IAPI-MU-002';
UPDATE servicos SET codigo_numerico = '0039' WHERE codigo = 'IAPI-MU-003';
UPDATE servicos SET codigo_numerico = '0040' WHERE codigo = 'IAPI-MU-004';
UPDATE servicos SET codigo_numerico = '0041' WHERE codigo = 'IAPI-MU-005';
UPDATE servicos SET codigo_numerico = '0042' WHERE codigo = 'IAPI-MU-006';
UPDATE servicos SET codigo_numerico = '0043' WHERE codigo = 'IAPI-MU-007';
UPDATE servicos SET codigo_numerico = '0044' WHERE codigo = 'IAPI-MU-008';
UPDATE servicos SET codigo_numerico = '0045' WHERE codigo = 'IAPI-MU-009';
UPDATE servicos SET codigo_numerico = '0046' WHERE codigo = 'IAPI-MU-010';
UPDATE servicos SET codigo_numerico = '0047' WHERE codigo = 'IAPI-MU-011';
UPDATE servicos SET codigo_numerico = '0048' WHERE codigo = 'IAPI-MU-012';
UPDATE servicos SET codigo_numerico = '0049' WHERE codigo = 'IAPI-MU-013';
UPDATE servicos SET codigo_numerico = '0050' WHERE codigo = 'IAPI-MU-014';
UPDATE servicos SET codigo_numerico = '0051' WHERE codigo = 'IAPI-MU-015';
UPDATE servicos SET codigo_numerico = '0052' WHERE codigo = 'IAPI-MU-016';
UPDATE servicos SET codigo_numerico = '0053' WHERE codigo = 'IAPI-MU-017';
UPDATE servicos SET codigo_numerico = '0054' WHERE codigo = 'IAPI-MU-018';
UPDATE servicos SET codigo_numerico = '0055' WHERE codigo = 'IAPI-MU-019';
UPDATE servicos SET codigo_numerico = '0056' WHERE codigo = 'IAPI-MU-020';
UPDATE servicos SET codigo_numerico = '0057' WHERE codigo = 'IAPI-MU-021';
UPDATE servicos SET codigo_numerico = '0058' WHERE codigo = 'IAPI-MU-022';

UPDATE servicos SET codigo_numerico = '0059' WHERE codigo = 'IAPI-MDI-001';
UPDATE servicos SET codigo_numerico = '0060' WHERE codigo = 'IAPI-MDI-002';
UPDATE servicos SET codigo_numerico = '0061' WHERE codigo = 'IAPI-MDI-003';
UPDATE servicos SET codigo_numerico = '0062' WHERE codigo = 'IAPI-MDI-004';
UPDATE servicos SET codigo_numerico = '0063' WHERE codigo = 'IAPI-MDI-005';
UPDATE servicos SET codigo_numerico = '0064' WHERE codigo = 'IAPI-MDI-006';
UPDATE servicos SET codigo_numerico = '0065' WHERE codigo = 'IAPI-MDI-007';
UPDATE servicos SET codigo_numerico = '0066' WHERE codigo = 'IAPI-MDI-008';
UPDATE servicos SET codigo_numerico = '0067' WHERE codigo = 'IAPI-MDI-009';
UPDATE servicos SET codigo_numerico = '0068' WHERE codigo = 'IAPI-MDI-010';
UPDATE servicos SET codigo_numerico = '0069' WHERE codigo = 'IAPI-MDI-011';
UPDATE servicos SET codigo_numerico = '0070' WHERE codigo = 'IAPI-MDI-012';
UPDATE servicos SET codigo_numerico = '0071' WHERE codigo = 'IAPI-MDI-013';
UPDATE servicos SET codigo_numerico = '0072' WHERE codigo = 'IAPI-MDI-014';
UPDATE servicos SET codigo_numerico = '0073' WHERE codigo = 'IAPI-MDI-015';
UPDATE servicos SET codigo_numerico = '0074' WHERE codigo = 'IAPI-MDI-016';
UPDATE servicos SET codigo_numerico = '0075' WHERE codigo = 'IAPI-MDI-017';
UPDATE servicos SET codigo_numerico = '0076' WHERE codigo = 'IAPI-MDI-018';
UPDATE servicos SET codigo_numerico = '0077' WHERE codigo = 'IAPI-MDI-019';
UPDATE servicos SET codigo_numerico = '0078' WHERE codigo = 'IAPI-MDI-020';
UPDATE servicos SET codigo_numerico = '0079' WHERE codigo = 'IAPI-MDI-021';

UPDATE servicos SET codigo_numerico = '0080' WHERE codigo = 'IAPI-ATO-001';
UPDATE servicos SET codigo_numerico = '0081' WHERE codigo = 'IAPI-ATO-002';
UPDATE servicos SET codigo_numerico = '0082' WHERE codigo = 'IAPI-ATO-003';
UPDATE servicos SET codigo_numerico = '0083' WHERE codigo = 'IAPI-ATO-004';
UPDATE servicos SET codigo_numerico = '0084' WHERE codigo = 'IAPI-ATO-005';
UPDATE servicos SET codigo_numerico = '0085' WHERE codigo = 'IAPI-ATO-006';
UPDATE servicos SET codigo_numerico = '0086' WHERE codigo = 'IAPI-ATO-007';
UPDATE servicos SET codigo_numerico = '0087' WHERE codigo = 'IAPI-ATO-008';
UPDATE servicos SET codigo_numerico = '0088' WHERE codigo = 'IAPI-ATO-009';
UPDATE servicos SET codigo_numerico = '0089' WHERE codigo = 'IAPI-ATO-010';
UPDATE servicos SET codigo_numerico = '0090' WHERE codigo = 'IAPI-ATO-011';
UPDATE servicos SET codigo_numerico = '0091' WHERE codigo = 'IAPI-ATO-012';
UPDATE servicos SET codigo_numerico = '0092' WHERE codigo = 'IAPI-ATO-013';
UPDATE servicos SET codigo_numerico = '0093' WHERE codigo = 'IAPI-ATO-014';
UPDATE servicos SET codigo_numerico = '0094' WHERE codigo = 'IAPI-ATO-015';

ALTER TABLE servicos
    ALTER COLUMN codigo_numerico SET NOT NULL;

ALTER TABLE servicos
    ADD CONSTRAINT ck_servico_codigo_numerico
        CHECK (codigo_numerico ~ '^[0-9]{4}$');