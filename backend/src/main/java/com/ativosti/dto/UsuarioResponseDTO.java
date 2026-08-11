package com.ativosti.dto;

public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private Long perfilId;
    private String perfilNome;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Long id, String nome, String matricula, String email, Long perfilId, String perfilNome) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.perfilId = perfilId;
        this.perfilNome = perfilNome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getPerfilId() { return perfilId; }
    public void setPerfilId(Long perfilId) { this.perfilId = perfilId; }

    public String getPerfilNome() { return perfilNome; }
    public void setPerfilNome(String perfilNome) { this.perfilNome = perfilNome; }
}
