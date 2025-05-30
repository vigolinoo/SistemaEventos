
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private List<Evento> eventosConfirmados;

    public Usuario(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.eventosConfirmados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void confirmarParticipacao(Evento evento) {
        if (!eventosConfirmados.contains(evento)) {
            eventosConfirmados.add(evento);
            System.out.println("Participação confirmada!");
        }
    }

    public void cancelarParticipacao(Evento evento) {
        if (eventosConfirmados.contains(evento)) {
            eventosConfirmados.remove(evento);
            System.out.println("Participação cancelada!");
        }
    }

    public void listarEventosConfirmados() {
        System.out.println("Eventos confirmados:");
        for (Evento e : eventosConfirmados) {
            System.out.println(e);
            System.out.println("--------------------");
        }
    }
}
