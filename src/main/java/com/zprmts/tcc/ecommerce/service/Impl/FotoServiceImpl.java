package com.zprmts.tcc.ecommerce.service.Impl;

import com.zprmts.tcc.ecommerce.domain.FotoEntity;
import com.zprmts.tcc.ecommerce.domain.Perfume;
import com.zprmts.tcc.ecommerce.dto.foto.FotoDTO;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
import com.zprmts.tcc.ecommerce.repository.FotoRepository;
import com.zprmts.tcc.ecommerce.utils.MediaTypeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FotoServiceImpl {

    private final FotoRepository fotoRepository;
    private final PerfumeServiceImpl perfumeService;
    private final MediaTypeUtil mediaTypeUtil;
    private final ObjectMapper objectMapper;

    public FotoDTO createFotoPerfume(Long idPerfume, MultipartFile arquivo) throws RegraDeNegocioException, IOException {
        Perfume perfume = perfumeService.getById(idPerfume);

        FotoEntity fotoEntity = gerarFotoEntity(arquivo);

        fotoEntity = fotoRepository.save(fotoEntity);

        perfume.setFoto(fotoEntity);
        perfumeService.save(perfume);
        FotoDTO fotoDTO = objectMapper.convertValue(fotoEntity, FotoDTO.class);
        return fotoDTO;
    }

    public FotoDTO update(Integer idFoto, MultipartFile arquivo) throws RegraDeNegocioException, IOException {
        FotoEntity fotoEntity = findById(idFoto);
        FotoEntity fotoUpdate = gerarFotoEntity(arquivo);

        fotoEntity.setNome(fotoUpdate.getNome());
        fotoEntity.setTipo(fotoUpdate.getTipo());
        fotoEntity.setArquivo(fotoUpdate.getArquivo());

        fotoRepository.save(fotoEntity);

        FotoDTO fotoDTO = objectMapper.convertValue(fotoEntity, FotoDTO.class);
        return fotoDTO;
    }


    public FotoDTO getById(Integer idFoto) throws RegraDeNegocioException {
        FotoEntity foto = findById(idFoto);
        FotoDTO fotoDTO = objectMapper.convertValue(foto, FotoDTO.class);
        return fotoDTO;
    }

    public void delete(Integer idFoto) throws RegraDeNegocioException {
        FotoEntity foto = findById(idFoto);

        fotoRepository.delete(foto);
    }

    public FotoEntity findById(Integer idFoto) throws RegraDeNegocioException {
        return fotoRepository.findById(idFoto)
                .orElseThrow(() -> new RegraDeNegocioException("Imagem não encontrada!"));
    }

    public FotoEntity gerarFotoEntity(MultipartFile arquivo) throws RegraDeNegocioException, IOException {
        String tipoArquivo = mediaTypeUtil.getTipoArquivo(arquivo);
        validarFormato(tipoArquivo);
        String nome = LocalDateTime.now() + "_" + arquivo.getOriginalFilename();
        if (nome.length() > 255) {
            throw new RegraDeNegocioException("Nome do arquivo é muito grande");
        }
        FotoEntity fotoEntity = new FotoEntity();
        fotoEntity.setArquivo(arquivo.getBytes());
        fotoEntity.setTipo(tipoArquivo);
        fotoEntity.setNome(nome);
        return fotoEntity;
    }

    private void validarFormato(String formato) throws RegraDeNegocioException {
        String formatoUpper = formato.toUpperCase();
        if (formatoUpper.equals("JPEG") || formatoUpper.equals("JPG") || formatoUpper.equals("WEBP") || formatoUpper.equals("PNG") || formatoUpper.equals("GIF") || formatoUpper.equals("BMP")) {
            return;
        }

        throw new RegraDeNegocioException("Formato de arquivo não suportado, formatos suportados: WEBP, JPG, JPEG, GIF, PNG, BMP");
    }

}
