package com.statementapi.draft.statements.services;

import com.statementapi.draft.statements.entities.Declaracao;
import com.statementapi.draft.statements.repositories.DeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DeclaracaoService {

    @Autowired
    private DeclaracaoRepository repository;

    private final Path root = Paths.get("uploads");

    public Declaracao salvar(MultipartFile file) throws IOException {

        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Somente PDF é aceito");
        }

        Files.createDirectories(root);

        String nomeArmazenado = UUID.randomUUID() + ".pdf";
        Path destino = root.resolve(nomeArmazenado);

        Files.copy(file.getInputStream(), destino);

        Declaracao doc = new Declaracao();
        doc.setNomeOriginal(file.getOriginalFilename());
        doc.setNomeArmazenado(nomeArmazenado);
        doc.setCaminho(destino.toString());
        doc.setDataUpload(LocalDateTime.now());

        return repository.save(doc);
    }

    public Declaracao buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Declaracao não encontrado"));
    }

    public byte[] baixar(Long id) throws IOException {
        Declaracao doc = buscar(id);
        return Files.readAllBytes(Paths.get(doc.getCaminho()));
    }
}
