# Sincronização de um banheiro utilizando Programação Concorrente

Este projeto tem como objetivo administrar a entrada e saída de pessoas em um banheiro unissex de um escritório. As regras de utilização do banheiro são: se um homem estiver no banheiro, outros homens podem entrar, porém eventuais mulheres que desejem utilizar o banheiro devem esperar ele ficar vazio. Se uma mulher estiver no banheiro, outras mulheres podem entrar, porém eventuais homens que desejem utilizar o banheiro deverão esperar ele ficar vazio. Cada pessoa (homem ou mulher) pode passar um determinado tempo no banheiro, que possui uma capacidade limitada de pessoas que podem utilizá-lo ao mesmo tempo.


# Solução

Para garantir o funcionamento correto da solução, foi necessário analisar quais problemas de programação concorrente poderiam surgir no decorrer do desenvolvimento. Analisando o problema, é possível notar complicações nas seguintes situações:


  - Se houverem mulheres no banheiro, os homens não podem entrar, porém, outras mulheres poderão continuar entrando no banheiro, fazendo com que a fila dos homens passe muito tempo aguardando e entre em starvation.
  - Se houverem homens no banheiro, as mulheres não poderão entrar, porém, outros homens poderão continuar entrando no banheiro, fazendo com que a fila das mulheres passe muito tempo aguardando e entre em starvation.