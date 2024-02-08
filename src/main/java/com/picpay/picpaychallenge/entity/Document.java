package com.picpay.picpaychallenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Column(length = 11)
    private String cpf;

    @Column(length = 14)
    private String cnpj;

}
