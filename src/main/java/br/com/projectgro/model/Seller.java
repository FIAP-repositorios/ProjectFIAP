package br.com.projectgro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_sellers")
@DiscriminatorValue("SELLER")
public class Seller extends User{

    private String cnpj;

    private String adress;

    private String cep;

    @JsonIgnore
    @OneToMany(mappedBy= "seller")
    private List<Order> orders = new ArrayList<>();

    public Seller(Long id, String name, String username, String email, String phone, String password, String cnpj, String adress, String cep){
        super(id, name, username, email, phone, password);
        this.cnpj = cnpj;
        this.adress = adress;
        this.cep= cep;
    }
}


