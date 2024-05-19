package com.zprmts.tcc.ecommerce.utils;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MediaTypeUtil {

    public String getTipoArquivo(MultipartFile arquivo) {
        return MediaType.parseMediaType(arquivo.getContentType()).getSubtype();
    }
}
