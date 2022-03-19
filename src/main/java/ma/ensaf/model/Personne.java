package ma.ensaf.model;

import java.time.LocalDate;

import ma.ensaf.utils.Identifiable;

public class Personne implements Identifiable<Long> {
    private Long id;
    private String nom;
    private String prenom;
    private Integer age;
    
    public Personne() { }
    
    public Personne(Long id, String nom, String prenom, Integer age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public String getNomComplet() {
        return new StringBuilder(3).append(getNom()).append(' ').append(getPrenom()).toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // public LocalDate getNaissance() {
    //     return naissance;
    // }

    // public void setNaissance(LocalDate naissance) {
    //     this.naissance = naissance;
    // }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Personne [age=")
            .append(age)
            // .append(", naissance=")
            // .append(naissance)
            .append(", nom=")
            .append(nom)
            .append(", prenom=")
            .append(prenom)
            .append(']').toString();
    }

    
}
