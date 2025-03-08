package attestations.attestation01;

import attestations.attestation01.repositories.impl.UsersRepositoryFileImpl;

public class App {
    public static void main(String[] args) {
        var repo = new UsersRepositoryFileImpl();
        repo.deleteAll();
        System.out.println("Пользователи: " + repo.findAll());
    }
}
