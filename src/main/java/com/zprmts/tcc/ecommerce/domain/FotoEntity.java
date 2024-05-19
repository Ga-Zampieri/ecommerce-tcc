package com.zprmts.tcc.ecommerce.domain;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Hidden
@Entity(name = "FOTOS")
public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOTOS")
    @SequenceGenerator(name = "SEQ_FOTOS", sequenceName = "SEQ_FOTOS", allocationSize = 1)
    @Column(name = "ID_FOTO")
    private Integer idFoto;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO")
    private String tipo;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "ARQUIVO")
    private byte[] arquivo;
}
