### Para o Android Studio funcinar no windows:
- https://www.oracle.com/br/java/technologies/downloads/
- https://www.oracle.com/br/java/technologies/downloads/#license-lightbox

### Instalar git flow no windows:
- [Instalar Node](https://nodejs.org/pt/download/package-manager)
   ```power shell
  # instala a fnm (Fast Node Manager, ou Gestor Rápido de Node)
  winget install Schniz.fnm
  
  # configurar o ambiente da fnm
  fnm env --use-on-cd | Out-String | Invoke-Expression
  
  # decarregar e instalar a Node.js
  fnm use --install-if-missing 22
  
  # verifica se a versão correta da Node.js está no ambiente
  node -v # deve imprimir `v22.9.0`
  
  # verifica se a versão correta da npm está no ambiente
  npm -v # deve imprimir `10.8.3`
  
    ```
- Depois execute
  ```sh
  npm i -g git-flow
  ```
