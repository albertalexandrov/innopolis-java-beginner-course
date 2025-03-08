import repositories.impl.UsersRepositoryFileImpl;

public class App {
    public static void main(String[] args) {
        var repo = new UsersRepositoryFileImpl();
        repo.deleteAll();
        System.out.println("Пользователи: " + repo.findAll());
    }
}
