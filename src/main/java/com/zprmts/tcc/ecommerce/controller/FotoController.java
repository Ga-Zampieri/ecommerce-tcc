package com.zprmts.tcc.ecommerce.controller;

import com.zprmts.tcc.ecommerce.controller.interfaces.FotoControllerInterface;
import com.zprmts.tcc.ecommerce.dto.foto.FotoDTO;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
import com.zprmts.tcc.ecommerce.service.Impl.FotoServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Tag(name = "Foto")
@Validated
@RequestMapping("/foto")
@RequiredArgsConstructor
public class FotoController implements FotoControllerInterface {
    private final FotoServiceImpl fotoService;

    @PostMapping("/perfume/{idPerfume}")
    public ResponseEntity<FotoDTO> createFotoPerfume(@PathVariable("idPerfume") Long idPerfume,
                                                    @RequestBody(required = true) MultipartFile arquivo) throws IOException, RegraDeNegocioException {
        return new ResponseEntity<>(fotoService.createFotoPerfume(idPerfume, arquivo), HttpStatus.CREATED);
    }

    @PutMapping("/{idFoto}")
    public ResponseEntity<FotoDTO> update(@PathVariable("idFoto") Integer idFoto,
                                          @RequestBody(required = true) MultipartFile arquivo) throws IOException, RegraDeNegocioException {
        return new ResponseEntity<>(fotoService.update(idFoto, arquivo), HttpStatus.OK);
    }

    @GetMapping("/{idFoto}")
    public ResponseEntity<FotoDTO> getById(@PathVariable("idFoto") Integer idFoto) throws RegraDeNegocioException {
        return new ResponseEntity<>(fotoService.getById(idFoto), HttpStatus.OK);
    }

    @DeleteMapping("/{idFoto}")
    public ResponseEntity<Void> delete(@PathVariable("idFoto") Integer idFoto) throws RegraDeNegocioException {
        fotoService.delete(idFoto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


