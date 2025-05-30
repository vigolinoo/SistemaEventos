
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaEventos {
    private List<Evento> listaEventos = new ArrayList<>();
    private Usuario usuarioAtual;
    private final String ARQUIVO = "events.data";
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        carregarEventos();
        System.out.println("Bem-vindo ao Sistema de Eventos!");
        cadastrarUsuario();

        int opcao = -1;
        while (opcao != 7) {
            menu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEvento();
                case 2 -> listarEventos();
                case 3 -> confirmarParticipacao();
                case 4 -> cancelarParticipacao();
                case 5 -> usuarioAtual.listarEventosConfirmados();
                case 6 -> mostrarEventosPorStatus();
                case 7 -> {
                    salvarEventos();
                    System.out.println("Saindo...");
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void menu() {
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar evento");
        System.out.println("2. Listar eventos");
        System.out.println("3. Confirmar participação");
        System.out.println("4. Cancelar participação");
        System.out.println("5. Ver meus eventos confirmados");
        System.out.println("6. Mostrar eventos por status");
        System.out.println("7. Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarUsuario() {
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();
        System.out.print("Digite seu telefone: ");
        String telefone = sc.nextLine();
        usuarioAtual = new Usuario(nome, email, telefone);
    }

    private void cadastrarEvento() {
        System.out.print("Nome do evento: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Categoria (Festa, Esportivo, Show, etc): ");
        String categoria = sc.nextLine();
        System.out.print("Data e hora (yyyy-MM-ddTHH:mm): ");
        String dataHora = sc.nextLine();
        LocalDateTime horario = LocalDateTime.parse(dataHora);
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        Evento e = new Evento(nome, endereco, categoria, horario, descricao);
        listaEventos.add(e);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private void listarEventos() {
        listaEventos.sort(Comparator.comparing(Evento::getHorario));
        for (int i = 0; i < listaEventos.size(); i++) {
            System.out.println("ID: " + i);
            System.out.println(listaEventos.get(i));
            System.out.println("------------------");
        }
    }

    private void confirmarParticipacao() {
        listarEventos();
        System.out.print("Digite o ID do evento para confirmar: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (id >= 0 && id < listaEventos.size()) {
            usuarioAtual.confirmarParticipacao(listaEventos.get(id));
        } else {
            System.out.println("ID inválido.");
        }
    }

    private void cancelarParticipacao() {
        usuarioAtual.listarEventosConfirmados();
        System.out.print("Digite o nome do evento para cancelar: ");
        String nome = sc.nextLine();
        for (Evento e : listaEventos) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                usuarioAtual.cancelarParticipacao(e);
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    private void mostrarEventosPorStatus() {
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("\nEventos passados:");
        listaEventos.stream().filter(Evento::jaOcorreu).forEach(e -> System.out.println(e + "\n"));

        System.out.println("Eventos acontecendo agora:");
        listaEventos.stream().filter(Evento::estaAcontecendo).forEach(e -> System.out.println(e + "\n"));

        System.out.println("Eventos futuros:");
        listaEventos.stream().filter(e -> e.getHorario().isAfter(agora)).forEach(e -> System.out.println(e + "\n"));
    }

    private void salvarEventos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Evento e : listaEventos) {
                pw.println(e.toDataString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    private void carregarEventos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Evento e = Evento.fromDataString(linha);
                listaEventos.add(e);
            }
        } catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado, iniciando vazio.");
        }
    }
}
