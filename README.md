# Sistema de Cadastro e Notificação de Eventos

## Descrição

Sistema em Java desenvolvido para cadastro e gerenciamento de eventos em uma cidade. Permite o cadastro de usuários e eventos, confirmação e cancelamento de participação, além de listar eventos atuais, futuros e já ocorridos. Os dados são salvos e carregados automaticamente em arquivo local.

## Funcionalidades

- Cadastro de usuários com nome, email e telefone.
- Cadastro de eventos com nome, endereço, categoria, horário e descrição.
- Consulta de eventos ordenados por horário.
- Participação e cancelamento em eventos.
- Visualização dos eventos em que o usuário está participando.
- Identificação de eventos ocorrendo no momento e já ocorridos.
- Persistência dos dados em arquivo `events.data`.

## Tecnologias

- Java (Console)
- Serialização para salvar e carregar dados
- Uso de `LocalDateTime` para controle de horários

## Como usar

1. Compile e execute o arquivo `SistemaEventos.java`.
2. Utilize o menu no console para navegar pelas opções.
3. Os dados dos eventos são automaticamente carregados e salvos.
