
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean jaOcorreu() {
        return this.horario.isBefore(LocalDateTime.now());
    }

    public boolean estaAcontecendo() {
        LocalDateTime agora = LocalDateTime.now();
        return !agora.isBefore(horario) && agora.isBefore(horario.plusHours(2));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Nome: " + nome + "\nEndereço: " + endereco + "\nCategoria: " + categoria + 
               "\nHorário: " + horario.format(formatter) + "\nDescrição: " + descricao;
    }

    public String toDataString() {
        return nome + ";" + endereco + ";" + categoria + ";" + horario + ";" + descricao;
    }

    public static Evento fromDataString(String data) {
        String[] partes = data.split(";");
        LocalDateTime horario = LocalDateTime.parse(partes[3]);
        return new Evento(partes[0], partes[1], partes[2], horario, partes[4]);
    }
}
