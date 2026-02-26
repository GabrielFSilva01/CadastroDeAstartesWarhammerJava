package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Missoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdMissao;
    private String NomeMissao;
    private String DescricaoMissao;
    private Calendar DataMissao;
    private String StatusMissao;
    private boolean  MissaoConcluida;
}
