package br.upf.musictrackr.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "musica")
public class MusicaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String nome;

    @Column(name = "duracao")
    private LocalTime duracao;

    @Column(name = "album")
    private String album;

    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private ArtistaEntity artista;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public ArtistaEntity getArtista() {
        return artista;
    }

    public void setArtista(ArtistaEntity artista) {
        this.artista = artista;
    }

    // equals e hashCode — NECESSÁRIOS para seleção no PrimeFaces

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MusicaEntity other = (MusicaEntity) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
