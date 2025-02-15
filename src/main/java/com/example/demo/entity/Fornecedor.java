package com.example.demo.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.FornecedorRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "fornecedores")
@Table(name = "fornecedores")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fornecedor implements UserDetails {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idFornecedor;
    
    private String senha;

    private String razao_social;

    private String nome_fantasia;

    private String cnpj;

    private UserRole role;

    public Fornecedor(FornecedorRequestDTO data) {
        this.idFornecedor = data.idFornecedor();
        this.senha = data.senha();
        this.razao_social = data.razao_social();
        this.nome_fantasia = data.nome_fantasia();
        this.cnpj = data.cnpj();
        this.role = data.role();
    }

    public Fornecedor(String idFornecedor, String senha, String razao_social, String nome_fantasia, String cnpj, UserRole role) {
        this.idFornecedor = idFornecedor;
        this.senha = senha;
        this.razao_social = razao_social;
        this.nome_fantasia = nome_fantasia;
        this.cnpj = cnpj;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return idFornecedor;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getters para cada atributo
    public String getId() {
        return id;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public String getSenha() {
        return senha;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public UserRole getRole() {
        return role;
    }

}

