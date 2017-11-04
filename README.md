# Sincronização de um banheiro utilizando Programação Concorrente

Este projeto tem como objetivo administrar a entrada e saída de pessoas em um banheiro unissex de um escritório. As regras de utilização do banheiro são: se um homem estiver no banheiro, outros homens podem entrar, porém eventuais mulheres que desejem utilizar o banheiro devem esperar ele ficar vazio. Se uma mulher estiver no banheiro, outras mulheres podem entrar, porém eventuais homens que desejem utilizar o banheiro deverão esperar ele ficar vazio. Cada pessoa (homem ou mulher) pode passar um determinado tempo no banheiro, que possui uma capacidade limitada de pessoas que podem utilizá-lo ao mesmo tempo.

# Como rodar

Requisitos
- JDK
- Conhecimentos de linha de comando ou da IDE Eclipse.
- Baixar o código do github:   github.com/julianaabs/bathroom-with-threads

Usando o eclipse:
- Importe o projeto para o eclipse
- Clique com o botão direito sobre o main da versão que deseja executar
- Selecione a opção “Run As” e clique em “Run Configurations...”
- Vá na aba “arguments” e no campo de argumentos digite o número de usuarios simultáneos.
- Clique em “Run” e espere o programa executar

Usando linha de comando:

- Vá na pasta do projeto e entre na pasta “sdk/”
- Use o comando:
- - javac  *.java  lockVersion/*.java   monitorVersion/*.java
- Chame pelo main que você deseja executar seguido pelo numero máximo de usuários simultâneos do banheiro. POr exemplo, assim que se executa a versão de lock com 10 usuários simultâneos:
- - java MainLock 10

# Introdução

Para garantir o funcionamento correto da solução, foi necessário analisar quais problemas de programação concorrente poderiam surgir no decorrer do desenvolvimento. Analisando o problema, é possível notar complicações nas seguintes situações:


  - Se houverem mulheres no banheiro, os homens não podem entrar, porém, outras mulheres poderão continuar entrando no banheiro, fazendo com que a fila dos homens passe muito tempo aguardando e entre em starvation.
  - Se houverem homens no banheiro, as mulheres não poderão entrar, porém, outros homens poderão continuar entrando no banheiro, fazendo com que a fila das mulheres passe muito tempo aguardando e entre em starvation.

  Desse modo, o grupo optou pelo desenvolvimento em Java, a fim de utilizar Monitores na solução do problema.
  
# Solução com monitores

O mecanismo de sincronização escolhido para moldar a solução do problema foi o Monitor. Como é necessário o gerenciamento de algumas condições para o bom funcionamento da solução (capacidade do banheiro, tempo de permanência, entrada e saída de pessoas), o monitor em Java garante que a exclusão mútua é aplicada corretamente pela presença de lock em todos os objetos, além da utilização de métodos sincronizados, que acessam os objetos de acordo com os locks.

Dessa forma, a solução do problema foi estruturada com uma classe Monitor que controla o fluxo do banheiro, de modo a evitar os problemas relatados no tópico 2. Foram criadas também a classe “Bathroom”, que contém todos os atributos relativos ao funcionamento do banheiro (Filas de homens e mulheres, capacidade total, lotação atual do banheiro, o gênero que está no banheiro no dado momento, método para chamar a próxima pessoa para entrar, modificação do gênero que está ocupando o banheiro). O monitor é iniciado, executando as threads e populando randomicamente os humanos (Homem ou mulher) para utilizar o banheiro. Ele controla a entrada e saída do banheiro de acordo com o tempo máximo de permanência nele, para evitar que ocorra starvation na entrada de pessoas de outro gênero.

Contudo, o grupo não conseguiu executar com êxito a solução, pois apesar das threads estarem funcionando normalmente sem erros e sem lançar exceções, não ocorre a impressão na tela do andamento das threads (quais indivíduos entraram e saíram do banheiro).

# Solução com lock

O “lock” é um sistema simples utilizado para garantir que parte do programa (zona crítica) será executado apenas em uma thread por vez. O funcionamento é assim: quando a zona crítica vai ser executada uma função é chamada, essa função é chamada “lock()”, se ela foi chamada antes por outra thread então a thread atual precisará esperar a outra sair da zona crítica. Para indicar que ela saiu dessa zona ela chama a função “unlock()” que destrava a thread seguinte para executar a zona crítica.

O código escrito em java fez uso do “lock” na classe “Bathroom”, pois assim apenas uma pessoas entra na fila e sai do banheiro por vez. As classes “Bathroom”, “Human” e “GenerateHuman” e “GenderTimeBathroom” implementam a classe “Thread” para cumprir com suas funcionalidades. Para que a classe “Human” só começa a contar o tempo que está dentro do banheiro no momento que entrar no banheiro existe um loop “while” impedindo o avanço até o momento que o humano receber do banheiro o aviso de que está livre para entrar e assim começar a contar o tempo randômico em que ele está dentro do banheiro.

A simplicidade da estrutura “lock” é um ponto positivo no desenvolvimento de um software multithread.

# Comparação das implementações e problemas

As implementações foram trabalhosas e a linguagem não ajudou. O fato de um dos integrantes estar muito acostumado com a linguagem de programação “Go” provavelmente atrapalhou mais do que ajudou. Pois em tudo esse integrante via a necessidade de uma nova thread e isso aumentou consideravelmente a complexidade do código, visto que na linguagem “Go” a facilidade de criar, gerir e manipular threads é muito maior que em Java.

A implementação com a estrutura “lock” se demonstrou muito mais intuitiva e fácil que a implementação de monitor que resultou em dificuldades ao grupo, que inclusive não finalizou a implementação do monitor tendo talvez algum problema de “deadlock” ou “livelock”. A implementação com “lock” está com um defeito que não foi encontrado solução, o programa não consegue puxar os humanos das filas, isso faz com que os humanos apenas entre nas filas, mas não entram no banheiro (é raro mas percebemos que as vezes algum humano entra e sai do banheiro).




  
 
