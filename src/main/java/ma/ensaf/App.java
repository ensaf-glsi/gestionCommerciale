package ma.ensaf;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ma.ensaf.model.Personne;
import ma.ensaf.utils.Crud;

public class App {

    private List<Personne> personnes = new ArrayList<>();

    public App() {
        Personne p1 = new Personne(1L, "nom1", "prenom1", 21);
        personnes.add(p1);
        personnes.add(new Personne(2L, "nom2", "prenom2", 21));
        personnes.add(new Personne(3L, "nom3", "prenom3", 22));
        personnes.add(new Personne(4L, "nom4", "prenom4", 20));
        // personnes.add(new App());

        // ex1();
        // ex2();
        // ex2J17();
        // ex3J17(21);
        exOptional();
    }
    public static void main(String[] args) {
        new App();
    }

    /** recuperer la liste des prenoms de toutes les personnes */
    void ex1() {
        List<String> prenomsList = new ArrayList<>(personnes.size());
        for (Personne p : personnes) {
            prenomsList.add(p.getPrenom());
        }
        System.out.println(prenomsList);
    }
    /** recuperer la liste des prenoms de toutes les personnes en utilisant java17 */
    void ex1J17() {
        // List<String> prenomsList = personnes.stream().map(p -> {
        //     return p.getPrenom();
        // }).toList();
        List<String> prenomsList = personnes.stream().map(Personne::getPrenom).toList();
        System.out.println(prenomsList);
    }

    /** recuperer la liste des ages de toutes les personnes */
    void ex2() {
        List<Integer> list = new ArrayList<>(personnes.size());
        for (Personne p : personnes) {
            list.add(p.getAge());
        }
        System.out.println(list);
    }

    /** recuperer la liste des ages de toutes les personnes - java17 - */
    void ex2J17() {
        Function<Personne, Integer> f1 = p -> p.getAge(); // Personne::getAge
        Function<Personne, Integer> f2 = Personne::getAge;
        List<Integer> list = personnes.stream().map(f1).toList();
        System.out.println(list);
        List<String> nomCompletList = personnes.stream().map(Personne::getNomComplet).toList();
        System.out.println(nomCompletList);
        nomCompletList = personnes.stream().map(App::getNomComplet).toList();
        System.out.println(nomCompletList);
    }

    /** recuperer la liste des prenoms de des personnes qui ont un age superieur à un age donné en utilisant java17 */
    void ex3J17(Integer age) {
        List<String> prenomsList = personnes.stream()
                .filter(p -> p.getAge() >= age)
                .map(Personne::getPrenom).toList();
        System.out.println(prenomsList);
    }

    void exOptional() {
        // Personne p = null; //dao.findById(1);
        // Personne p2 = new Personne();
        // if (p != null) {
        //     System.out.println(p.getNom());
        //     if (p2 != null) {

        //     }
        // }
        Crud<Personne, Long> crud = new Crud<>();
        crud.add(personnes.get(0));
        crud.add(personnes.get(1));
        Optional<Personne> optionalPersonne = crud.findById(1L);
        // Personne p2 = crud.findById(2L);
        // if (p != null) {
        //     System.out.println(p.getNom());
        //     if (p2 != null) {

        //     }
        // }
        optionalPersonne.ifPresent(personne -> {
            System.out.println(personne.getNom());
        });
        Personne p3 = crud.findById(3L).orElse(null);
        String prenom3 = null;
        if (p3 != null && p3.getAge() > 30) {
            prenom3 = p3.getPrenom();
        }
        String prenom2 = crud.findById(2L).map(Personne::getPrenom).orElse(null);
        System.out.println(prenom2);
        System.out.println(crud.findById(2L)
            .filter(p -> p.getAge() > 30)
            .map(Personne::getPrenom)
            .orElse(null));
        System.out.println(crud.findById(10L).map(Personne::getPrenom).orElse(null));
    }

    public static String getNomComplet(Personne p) {
        return new StringBuilder(3).append(p.getNom()).append(' ').append(p.getPrenom()).toString(); 
    }

}
