Esta aplicação foi desenvolvida com Intellij IDEA.
Não é necessária instalação de nenhuma third party.

A aplicação incorpora o HSQLDB (http://hsqldb.org/) para BD em memória.
Após executar a aplicação, em modo debug ou não, navegue até http://localhost:8080/, nesse serão listados todos os endpoints, conforme:

  •	/Awards-Best-and-Worst
  REQ: Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido.

  •	/data-errors
  LISTA DE ERROS DE INTERPRETAÇÃO DE DADOS CSV (DB)

  •	/movies
  LISTA ENTIDADES MOVIE

  •	/movies/count
  TOTAL ENTIDADES MOVIE

  •	/movies/year-{year}
  PESQUISA PROP YEAR ENTIDADES MOVIE

  •	/movies/winners
  LISTA ENTIDADES MOVIE QUANDO PROP WINNER = TRUE

  •	/movies/winners/count
  TOTAL ENTIDADES MOVIE WINNER = TRUE

  •	/movies/winners/year-{year}
  PESQUISA PROP YEAR ENTIDADES MOVIE WINNER = TRUE

  •	/studios
  LISTA ENTIDADES STUDIO

  •	/studios/count
  TOTAL ENTIDADES STUDIO

  •	/producers
  LISTA ENTIDADES PRODUCERS

  •	/producers/count
  TOTAL ENTIDADES PRODUCERS

Divirta-se
=)
