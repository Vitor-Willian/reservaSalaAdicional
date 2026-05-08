public class User {
    private String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        if(role.equals("Professor") || role.equals("Aluno")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Atributo 'role' deve ser 'Professor' ou 'Aluno'");
        }
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
