## Sistema de Importação de Arquivos
Sistema desenvolvido utilizando Java 8, Spring Boot Integration e Docker.

####Instalação e Execução da Aplicação
```` 
git clone https://github.com/Arionildo/sistema-importacao-arquivo.git
cd sistema-importacao-arquivo
gradlew build
docker build -t sistema-importacao-arquivo .
docker run -p 80:80 sistema-importacao-arquivo
docker ps
docker cp <CAMINHO DO SEU ARQUIVO .dat> <CONTAINER ID OBTIDO>:/root/data/in
````