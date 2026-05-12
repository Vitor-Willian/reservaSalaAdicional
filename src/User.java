public class User implements Observer {
    private String name;
    private String role;
    private boolean error = false;

    public User(String name, String role) {
        this.name = name;
        if(role.equals("Professor") || role.equals("Aluno")) {
            this.role = role;
        } else {
            System.out.println("Atributo 'role' deve ser 'Professor' ou 'Aluno'\n");
            this.error = true;
        }
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean hasError() {
        return error;
    }

    @Override
    public void update(Subject subject, String message) {
        System.out.println("Usuário < " + this.name + " > recebeu notificação: " + message);
    }
}
