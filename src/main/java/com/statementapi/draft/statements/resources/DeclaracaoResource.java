package com.statementapi.draft.statements.resources;

import com.statementapi.draft.statements.entities.Declaracao;
import com.statementapi.draft.statements.services.DeclaracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/declaracoes")
public class DeclaracaoResource {

    @Autowired
    private DeclaracaoService service;

    @PostMapping("/upload")
    public Declaracao upload(@RequestParam("file") MultipartFile file) throws IOException {
        return service.salvar(file);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws IOException {

        byte[] arquivo = service.baixar(id);
        Declaracao doc = service.buscar(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + doc.getNomeOriginal() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(arquivo);
    }
}
