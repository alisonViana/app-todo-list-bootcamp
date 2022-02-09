# To Do List

Olá, este é o repositório do meu aplicativo To Do List, projeto desenvolvido para o Bootcamp Carrefour Android Developer oferecido pela 
[Digital Innovation One](https://web.dio.me/) em parceria com o Carrefour!

O aplicativo permite que você crie uma lista de tarefas, com título e descrição detalhada para cada item, além de permitir que você selecione data, horário e 
cor de fundo, permitindo que você separe as tarefas por tipo ou crie um sistema de prioridades baseado na cor!

O To Do List traz no seu estilo, o novo Material Design 3, o que dá ao app e seus componentes uma aparência moderna e sofisticada. 

O app está baseado nos princípios de MVVM e Clean Architecture, além de utilizar a biblioteca Koin para realizar a injeção de dependência, o que torna o código 
mais organizado e traz um maior nível de desacoplamento, principalmente da UI com as regras de negócio, além da maior facilidade de manutenção e implementação de novas funções.

Para a persistência de dados, o app faz uso da biblioteca Room, que fornece uma abstração sobre o SQLite, aproveitando toda a sua capacidade.


## Toque pessoal

A partir do projeto base desenvolvido durante o bootcamp, resolvi implementar algumas modificações. Elas agregam novas funcionalidades e tornam o uso mais dinâmico, 
alterando elementos do layout, das funções já existentes e adicionando funções novas. Pretendo trazer melhorias, conforme eu for estudando e aprendendo, 
além de possíveis correções, por isso, sinta-se à vontade para enviar suas sugestões e críticas, elas serão muito bem-vindas!

Abaixo estão as melhorias já implementadas:

### Staggered Layout

Para deixar a aparência moderna e a experiência mais dinâmica, optei por utilizar o Staggered Layout, onde as tarefas são exibidas em duas colunas e o comprimento das views 
se adequa ao tamanho da tarefa, criando uma navegação mais fluida para os olhos do usuário.

### Persistência dos dados em banco de dados

Uma das principais alterações feitas em relação ao projeto proposto, é o armazenamento dos dados, que antes era feita apenas em cache, 
perdendo os dados assim que a aplicação era fechada. Para corrigir esse problema, implementei a persistência dos dados em um banco de dados utilizando a biblioteca Room, 
que abstrai o SQLite, permitindo adição, exclusão, atualização e pesquisa dos dados armazenados, que seriam as informações das tarefas criadas.

### Temas e cores

O app utiliza componentes do Material Design 3 e para manter as características do estilo, criei temas de cores que seguem o padrão do novo Material Design. 
Utilizando a própria ferramenta disponibilizada pelo Google, selecionei as cores e apliquei no projeto, trazendo as sutilezas e versatilidade desse novo padrão 
com uma rica tonalidade gerada automaticamente.

To Do List possui um tema claro e um tema escuro, que se adequa ao tema do dispositivo, e além das cores do próprio aplicativo,
as cores utilizadas como fundo das tarefas também se alteram conforme o tema utilizado, garantindo visibilidade e bom contraste na leitura para manter o conforto visual adequado à escolha do usuário.

### Filtro de cor

As cores são importantes na hora de se organizar, por isso o To Do List permite a criação das tarefas com cor de fundo, que pode ser selecionada entre 5 opções, 
4 cores e a opção de não ter cor, mantendo um fundo transparente. Por isso, inseri no aplicativo um filtro de cor na tela inicial, permitindo trazer apenas as tarefas 
com a cor selecionada, facilitando e objetificando a visualização das tarefas criadas.


Em breve, novas atualizações.
