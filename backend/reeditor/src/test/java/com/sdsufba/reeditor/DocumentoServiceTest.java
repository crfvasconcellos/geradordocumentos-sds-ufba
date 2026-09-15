package com.sdsufba.reeditor;

import com.sdsufba.reeditor.dto.DocumentoDTO;
import com.sdsufba.reeditor.service.DocumentoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentoServiceTest {

    @Autowired
    private DocumentoService documentoService;

    @Test
    void testGerarDocumentoPdf() throws Exception {
        DocumentoDTO dto = new DocumentoDTO("Carlos Silva", "Copa Universitária");
        byte[] pdfBytes = documentoService.gerarDocumentoPdf(dto);

        Assertions.assertNotNull(pdfBytes);
        Assertions.assertTrue(pdfBytes.length > 0);

        // Verificar assinatura PDF (%PDF)
        String pdfHeader = new String(pdfBytes, 0, 4);
        Assertions.assertEquals("%PDF", pdfHeader);
    }
}
