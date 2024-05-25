package com.zprmts.tcc.ecommerce.dto.foto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FotoDTO {

    @Schema(description = "Id da Foto", example = "1")
    private Integer idFoto;
    @Schema(description = "Nome do arquivo", example = "NomeArquivo")
    private String nome;
    @Schema(description = "Tipo do arquivo, formatos suportados: WEBP, JPG, JPEG, GIF, PNG, BMP", example = "JPG")
    private String tipo;
    @Schema(description = "Arquivo")
    private byte[] arquivo;
}
