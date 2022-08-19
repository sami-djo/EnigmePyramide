package io.enigme;

import org.apache.commons.numbers.fraction.Fraction;

public class Pyramid2D {

    private final int etages;
    private final BoiteCylindrique[][][] espace;

    private final Fraction maxContenuBoite;

    private final Integer[] boitesRemplisParEtage;

    public Pyramid2D(int etages, Fraction maxContenuBoite) {

        if (etages < 1) {
            throw new IllegalArgumentException("Le nombre d'étages de la pyramide doit être supérieur ou égal à 1");
        } else if (maxContenuBoite.compareTo(Fraction.ZERO) <= 0) {
            throw new IllegalArgumentException("Le contenu maximal d'une boite cylindrique doit être supérieur ou égal à 0");
        }

        this.etages = etages;
        this.maxContenuBoite = maxContenuBoite;
        espace = new BoiteCylindrique[this.etages][this.etages][1];
        boitesRemplisParEtage = new Integer[this.etages];

        createPyramide();
    }

    private void createPyramide() {

        espace[0][0][0] = new BoiteCylindrique(maxContenuBoite, 0, boitesRemplisParEtage);

        for (int x = 1; x < this.etages; x++) {
            for (int y = 0; y <= x; y++) {
                espace[x][y][0] = new BoiteCylindrique(maxContenuBoite, x, boitesRemplisParEtage);
                BoiteCylindrique currentBoite = espace[x][y][0];

                BoiteCylindrique boiteMere1 = espace[x-1][y][0];
                if (boiteMere1 != null) {
                    boiteMere1.ajouterFille(currentBoite);
                    currentBoite.ajouterMere(boiteMere1);
                }

                if (y > 0) {
                    BoiteCylindrique boiteMere2 = espace[x-1][y-1][0];
                    if (boiteMere2 != null) {
                        boiteMere2.ajouterFille(currentBoite);
                        currentBoite.ajouterMere(boiteMere2);
                    }
                }
            }
        }
    }

    public void ajouterAuContenuDeLaPyramide(Fraction ajout) {
        espace[0][0][0].ajouterAuContenu(ajout);
    }

    public Integer[] getBoitesRemplisParEtage() {
        return boitesRemplisParEtage;
    }

    public boolean isAuMoinsUneBoiteRemplieAtEtage(int etage) {
        return (this.getBoitesRemplisParEtage())[etage - 1] == null;
    }
}
