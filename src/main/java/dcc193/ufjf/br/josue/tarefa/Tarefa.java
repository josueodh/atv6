package dcc193.ufjf.br.josue.tarefa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @NotBlank(message = "É preciso um título!")
    private String titulo;
    @PositiveOrZero(message = "É preciso 0 ou mais tomatos")
    private Integer tomato;

    public Integer getTomato() {
        return tomato;
    }

    public void setTomato(Integer tomato) {
        this.tomato = tomato;
    }

    public Tarefa() {
        this(null, null, 0);
    }

    public Tarefa(String titulo) {
        this(null, titulo, 0);
    }

    public Tarefa(Long id, String titulo, Integer tomato) {
        this.titulo = titulo;
        this.id = id;
        this.tomato = tomato;
    }

    public Tarefa(Long id, String titulo) {
        this(id, titulo, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Tarefa [id=" + id + ", titulo=" + titulo + "]";
    }
}