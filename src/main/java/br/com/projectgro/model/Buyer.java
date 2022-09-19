package br.com.projectgro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_buyers")
@DiscriminatorValue("BUYER")
public class Buyer extends User {

    private String cnpj;
    private String adress;
    private String cep;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer")
    private List<Order> orders = new ArrayList<>();

    public Buyer(Long id, String name, String username, String email, String phone, String password, String cnpj, String adress, String cep) {
        super(id, name, username, email, phone, password);
        this.cnpj = cnpj;
        this.adress = adress;
        this.cep = cep;
    }
}
