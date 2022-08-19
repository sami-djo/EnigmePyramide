package io.enigme;

import org.apache.commons.numbers.fraction.Fraction;

public class MainClass {

    public static void main(String[] args) {

        int etages = 4;
        Fraction maxContenuBoite = Fraction.of(6);

        PyramidTriangle pyramidTriangle = new PyramidTriangle(etages, maxContenuBoite);
        PyramidSquare pyramidSquare = new PyramidSquare(etages, maxContenuBoite);
        Pyramid2D pyramid2D = new Pyramid2D(etages, maxContenuBoite);


        Fraction litresAjouterParIteration = Fraction.of(1, 60);

        Fraction sumLitreAjouter = Fraction.ZERO;

        while(pyramid2D.isAuMoinsUneBoiteRemplieAtEtage(etages)) {
            sumLitreAjouter = sumLitreAjouter.add(litresAjouterParIteration);
            pyramid2D.ajouterAuContenuDeLaPyramide(litresAjouterParIteration);
        }
        System.out.println(sumLitreAjouter);

        sumLitreAjouter = Fraction.ZERO;
        while(pyramidTriangle.isAuMoinsUneBoiteRemplieAtEtage(etages)) {
            sumLitreAjouter = sumLitreAjouter.add(litresAjouterParIteration);
            pyramidTriangle.ajouterAuContenuDeLaPyramide(litresAjouterParIteration);
        }
        System.out.println(sumLitreAjouter);

        sumLitreAjouter = Fraction.ZERO;
        while(pyramidSquare.isAuMoinsUneBoiteRemplieAtEtage(etages)) {
            sumLitreAjouter = sumLitreAjouter.add(litresAjouterParIteration);
            pyramidSquare.ajouterAuContenuDeLaPyramide(litresAjouterParIteration);
        }
        System.out.println(sumLitreAjouter);
    }
}