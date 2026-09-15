package com.sdsufba.reeditor.service;

import com.sdsufba.reeditor.dto.DocumentoDTO;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DocumentoService {

    public byte[] gerarDocumentoPdf(DocumentoDTO dto) throws Exception {
        InputStream templateStream = getClass().getResourceAsStream("/templates/modelo.docx");
        if (templateStream == null) {
            throw new RuntimeException("Arquivo modelo.docx não foi encontrado em /templates/modelo.docx!");
        }

        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = dto.getNome() != null ? dto.getNome() : "";
        String campeonato = dto.getCampeonato() != null ? dto.getCampeonato() : "";

        try (XWPFDocument document = new XWPFDocument(templateStream);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Processar parágrafos principais do documento
            substituirTextoEmParagrafos(document.getParagraphs(), nome, campeonato, dataHoje);

            // Processar tabelas do documento
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        substituirTextoEmParagrafos(cell.getParagraphs(), nome, campeonato, dataHoje);
                    }
                }
            }

            // Processar cabeçalhos
            for (XWPFHeader header : document.getHeaderList()) {
                substituirTextoEmParagrafos(header.getParagraphs(), nome, campeonato, dataHoje);
            }

            // Processar rodapés
            for (XWPFFooter footer : document.getFooterList()) {
                substituirTextoEmParagrafos(footer.getParagraphs(), nome, campeonato, dataHoje);
            }

            // Converter o documento DOCX editado em PDF
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, out, options);

            return out.toByteArray();
        }
    }

    private void substituirTextoEmParagrafos(List<XWPFParagraph> paragraphs, String nome, String campeonato, String dataHoje) {
        if (paragraphs == null) return;

        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            if (text != null && (
                    text.contains("XXXX") || text.contains("xxxx") ||
                    text.contains("YYYY") || text.contains("yyyy") ||
                    text.contains("ZZZZ") || text.contains("zzzz"))) {

                String newText = text
                        .replace("XXXX", nome)
                        .replace("xxxx", nome)
                        .replace("YYYY", campeonato)
                        .replace("yyyy", campeonato)
                        .replace("ZZZZ", dataHoje)
                        .replace("zzzz", dataHoje);

                // Preservar tamanho e formatação da primeira run, se existir
                XWPFRun firstRun = paragraph.getRuns().size() > 0 ? paragraph.getRuns().get(0) : null;
                String fontFamily = firstRun != null ? firstRun.getFontFamily() : null;
                int fontSize = firstRun != null ? firstRun.getFontSize() : -1;
                boolean isBold = firstRun != null && firstRun.isBold();

                // Remover todas as runs existentes no parágrafo
                while (paragraph.getRuns().size() > 0) {
                    paragraph.removeRun(0);
                }

                // Criar nova run com o texto formatado
                XWPFRun newRun = paragraph.createRun();
                newRun.setText(newText);
                if (fontFamily != null) newRun.setFontFamily(fontFamily);
                if (fontSize > 0) newRun.setFontSize(fontSize);
                if (isBold) newRun.setBold(true);
            }
        }
    }
}
