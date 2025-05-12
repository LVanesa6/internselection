package com.uptc.bb.internselection.controller;

import com.uptc.bb.internselection.dto.InternDTO;
import com.uptc.bb.internselection.entity.Intern.State;
import com.uptc.bb.internselection.service.FileStorageService;
import com.uptc.bb.internselection.service.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interns")
@RequiredArgsConstructor
public class InternController {

    private final InternService internService;
    private final FileStorageService fileStorageService;

    // 🔹 Registrar practicante con archivo PDF (permitido sin autenticación)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InternDTO> registerIntern(
            @RequestPart("intern") InternDTO internDTO,
            @RequestPart("cv") MultipartFile cvFile
    ) {
        try {
            String path = fileStorageService.storeCV(cvFile, internDTO.getFirstName() + "_" + internDTO.getLastName());
            internDTO.setCvPath(path);
            InternDTO saved = internService.saveIntern(internDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 🔹 Obtener todos los practicantes (sin autenticación ahora)
    @GetMapping
    public ResponseEntity<List<InternDTO>> getAllInterns() {
        return ResponseEntity.ok(internService.getAllInterns());
    }

    // 🔹 Obtener practicante por ID (sin autenticación ahora)
    @GetMapping("/{id}")
    public ResponseEntity<InternDTO> getInternById(@PathVariable Integer id) {
        Optional<InternDTO> intern = internService.getInternById(id);
        return intern.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Actualizar estado del practicante (sin autenticación ahora)
    @PutMapping("/{id}/state")
    public ResponseEntity<Void> updateState(@PathVariable Integer id, @RequestParam State state) {
        boolean updated = internService.updateInternState(id, state);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 🔹 Descargar hoja de vida (sin autenticación ahora)
    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> downloadCV(@PathVariable Integer id) {
        Optional<InternDTO> internOpt = internService.getInternById(id);
        if (internOpt.isEmpty() || internOpt.get().getCvPath() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            byte[] fileData = fileStorageService.getCV(internOpt.get().getCvPath());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("CV_" + id + ".pdf")
                    .build());

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
