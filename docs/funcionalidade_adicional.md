# Funcionalidade Adicional: Controle de Acesso a Salas

## A Ideia
A nova funcionalidade consiste na implementação de um controle de acesso restrito para determinados tipos de salas. Ela estabelece que:
- **Salas Individuais:** Não podem ser reservadas por usuários com o papel "Professor".
- **Laboratórios:** Só podem ser reservados por usuários com o papel "Professor" (alunos são bloqueados).
- **Salas de Grupo:** Livres para qualquer tipo de usuário.

## Justificativa e Uso do Padrão Proxy
Para implementar essa funcionalidade sem ferir a arquitetura existente, optamos pela utilização do padrão de projeto estrutural **Proxy** (Proxy de Proteção).

### Por que isso é bom para o sistema?
1. **Separação de Responsabilidades (Separation of Concerns):** A lógica de verificar se um usuário tem ou não permissão não atrapalha o código da sala em si (`Individual_Room` ou `Lab_Room`). A sala continua tendo apenas o propósito de ser uma sala.
2. **Princípio Aberto-Fechado (Open/Closed Principle):** Conseguimos adicionar uma nova camada de segurança e comportamento (fechado para modificação) criando novas classes de Proxy (aberto para extensão), sem precisar alterar as classes das salas originais.
3. **Transparência:** Como o Proxy implementa a mesma interface (`Room` via `Room_Proxy`) que o objeto real, o cliente (o restante do sistema) interage com o Proxy achando que está interagindo diretamente com a sala. Apenas a `FactoryRoom` precisa saber que está "embrulhando" a sala verdadeira no Proxy.