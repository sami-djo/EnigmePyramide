package io.enigme;

import org.apache.commons.numbers.fraction.Fraction;

import java.util.ArrayList;
import java.util.List;

public final class BoiteCylindrique {

    private final  List<BoiteCylindrique> meres;
    private final List<BoiteCylindrique> filles;
    private Fraction contenu;
    private final Fraction maxContenu;

    private Integer[] boitesRemplisParEtage;

    private final Integer etage;

    public BoiteCylindrique(Fraction maxContenu, Integer etage) {
        this.meres = new ArrayList<>();
        this.filles = new ArrayList<>();
        this.contenu = Fraction.ZERO;
        this.maxContenu = maxContenu;
        this.etage = etage;
    }

    public BoiteCylindrique(Fraction maxContenu, Integer etage, Integer[] boitesRemplisParEtage) {
        this.meres = new ArrayList<>();
        this.filles = new ArrayList<>();
        this.contenu = Fraction.ZERO;
        this.maxContenu = maxContenu;
        this.etage = etage;
        this.boitesRemplisParEtage = boitesRemplisParEtage;
    }

    public void ajouterFille(BoiteCylindrique fille) {
        this.filles.add(fille);
    }

    public void ajouterMere(BoiteCylindrique mere) {
        this.meres.add(mere);
    }

    public Fraction getContenu() {
        return this.contenu;
    }

    public void ajouterAuContenu(Fraction ajout) {

        int comparaison = contenu.add(ajout).compareTo(maxContenu);

        if (comparaison > 0) {
            if (!filles.isEmpty()) {
                Fraction ajoutFilles = ajout.subtract(maxContenu.subtract(contenu)).divide(filles.size());
                for (BoiteCylindrique fille : filles) {
                    fille.ajouterAuContenu(ajoutFilles);
                }
            }
            this.contenu = this.maxContenu;
            incrementBoiteRempliParEtage();
        } else if(comparaison == 0) {
            this.contenu = this.maxContenu;
            incrementBoiteRempliParEtage();
        } else {
            this.contenu = this.contenu.add(ajout);
        }
    }

    private void incrementBoiteRempliParEtage() {
        if (boitesRemplisParEtage != null && boitesRemplisParEtage.length > this.etage) {
            if (boitesRemplisParEtage[this.etage] == null) {
                boitesRemplisParEtage[this.etage] = 0;
            }
            (boitesRemplisParEtage[this.etage])++;
        }
    }
}
