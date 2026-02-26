package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Soldado {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSoldado;
    private String nomeSoldado;
    private String descricao;
    private double Altura;

}
