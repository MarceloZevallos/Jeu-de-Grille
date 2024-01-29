/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gg.sim.mzevallos.projet2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 *
 * @author X_Pet
 */
public class GrilleJeu {

    private String[][] tabLettres;
    private String[][] tabFinal;
    private ArrayList<Mot> arrMots = new ArrayList<Mot>();

    GrilleJeu(String strNomFichier) {
        BufferedReader brFichier = null;
        String strLigne;
        StringTokenizer st;
        Mot objMot;

        try {
            brFichier = new BufferedReader(new FileReader(strNomFichier));

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        try {
            String[] sizes = brFichier.readLine().split(", ");
            int x = Integer.parseInt(sizes[0]); //15
            int y = Integer.parseInt(sizes[1]);//10
            this.tabLettres = new String[y][x];

            // lis les lignes et les mets dans le Array
            for (int i = 0; i < y; i++) {
                String line = brFichier.readLine();
                String[] words = line.split(" ");
                for (int j = 0; j < x; j++) {
                    tabLettres[i][j] = words[j];
                }
            }

            while ((strLigne = brFichier.readLine()) != null) {
                st = new StringTokenizer(strLigne);
                objMot = new Mot(st.nextToken().trim());
                arrMots.add(objMot);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        tabFinal = tabLettres;

        System.out.println("\n");
        afficherGrilleInitiale();
        System.out.println("\n");
        trouverMots();
        afficherGrilleFinale();
        System.out.println();
        afficherMots();
        System.out.println();
        trouverMotMystere();
    }

    private void trouverMots() {
        // cherche les mot verticales sud
        // axe des x
        for (int x = 0; x < tabLettres[0].length; x++) {
            // axe des y
            for (int y = 0; y < tabLettres.length; y++) {
                // cherche par mots
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if (len <= tabLettres.length - y && (y + len - 1) <= tabLettres.length) {

                            // verifie si c'est le mot
                            for (int j = 1; j <= (len - 1); j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);
                                if (tabLettres[j + y][x].toLowerCase().equals(Character.toString(lettre)) || tabLettres[j + y][x].equals(Character.toString(lettre))) {
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().SUD);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// sud

        // cherche les mots verticales nord
        //axe des x de 11 à 0
        for (int x = tabLettres[0].length - 1; x >= 0; x--) {
            // axe des y de à 0
            for (int y = tabLettres.length - 1; y >= 0; y--) {
                // cherche par mots de 23 à 0
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0); //

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if ((y - len + 1) >= 0) {
                            // verifie si c'est le mot de 1 à len-1
                            for (int j = 1; j <= len - 1; j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);

                                if (tabLettres[y - j][x].toLowerCase().equals(Character.toString(lettre)) || tabLettres[y - j][x].equals(Character.toString(lettre))) {
                                    letter.add(lettre);
                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().NORD);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// nord

        // cherche les mot horizontale est
        // axe des x
        for (int x = 0; x < tabLettres[0].length; x++) {
            // axe des y
            for (int y = 0; y < tabLettres.length; y++) {
                // cherche par mots
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if (len <= tabLettres[0].length - x && (x + len - 1) <= tabLettres[0].length) {
                            // verifie si c'est le mot
                            for (int j = 1; j <= (len - 1); j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);
                                if (tabLettres[y][j + x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][j + x].equals(Character.toString(lettre)))) {
                                    //System.out.println("allo");
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().EST);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// est

        // cherche les mots horizontales ouest
        //axe des x de 11 à 0
        for (int x = tabLettres[0].length - 1; x >= 0; x--) {
            // axe des y de à 0
            for (int y = tabLettres.length - 1; y >= 0; y--) {
                // cherche par mots de 23 à 0
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if ((x - len + 1) >= 0) {
                            // verifie si c'est le mot de 1 à len-1
                            for (int j = 1; j <= len - 1; j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);

                                if (tabLettres[y][x - j].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x - j].equals(Character.toString(lettre)))) {
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().OUEST);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// ouest

        // cherche les mots diagonales nord est
        //axe des x de 11 à 0
        for (int x = tabLettres[0].length - 1; x >= 0; x--) {
            // axe des y de à 0
            for (int y = tabLettres.length - 1; y >= 0; y--) {
                // cherche par mots de 23 à 0
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if ((y - len + 1) >= 0 && len <= tabLettres[0].length - x && (x + len - 1) <= tabLettres[0].length) {
                            // verifie si c'est le mot de 1 à len-1
                            for (int j = 1; j <= len - 1; j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);

                                if (tabLettres[y - j][x + j].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y - j][x + j].equals(Character.toString(lettre)))) {
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().NORDEST);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// nord est

        //cherche les mots de diagonales nord ouest
        //axe des x de 11 à 0
        for (int x = tabLettres[0].length - 1; x >= 0; x--) {
            // axe des y de à 0
            for (int y = tabLettres.length - 1; y >= 0; y--) {
                // cherche par mots de 23 à 0
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if ((y - len + 1) >= 0 && (x - len + 1) >= 0) {
                            // verifie si c'est le mot de 1 à len-1
                            for (int j = 1; j <= len - 1; j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);

                                if (tabLettres[y - j][x - j].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y - j][x - j].equals(Character.toString(lettre)))) {
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().NORDOUEST);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// nord ouest

        //cherche les mots de diagonales sud est
        //axe des x de 11 à 0
        for (int x = tabLettres[0].length - 1; x >= 0; x--) {
            // axe des y de à 0
            for (int y = 0; y < tabLettres.length; y++) {
                // cherche par mots de 23 à 0
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if (len <= tabLettres.length - y && (y + len - 1) <= tabLettres.length/*sud*/ && len <= tabLettres[0].length - x && (x + len - 1) <= tabLettres[0].length/*est*/) {
                            // verifie si c'est le mot de 1 à len-1
                            for (int j = 1; j <= len - 1; j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);

                                if (tabLettres[y + j][x + j].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y + j][x + j].equals(Character.toString(lettre)))) {
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().SUDEST);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// sud est

        //Cherche les mots de la diagonale sud ouest
        //axe des x de 11 à 0
        for (int x = tabLettres[0].length - 1; x >= 0; x--) {
            // axe des y de à 0
            for (int y = 0; y < tabLettres.length; y++) {
                // cherche par mots de 23 à 0
                for (int i = 0; i < arrMots.size(); i++) {
                    ArrayList<Character> letter = new ArrayList<Character>();
                    letter.clear();
                    char lettre = arrMots.get(i).getStrMot().charAt(0);

                    // voir si la premiere lettre du tableau est la meme que celle du mot
                    if (tabLettres[y][x].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y][x].equals(Character.toString(lettre)))) {

                        int len = arrMots.get(i).getStrMot().length();
                        letter.add(lettre);

                        if (len <= tabLettres.length - y && (y + len - 1) <= tabLettres.length/*sud*/ && (x - len + 1) >= 0/*ouest*/) {
                            // verifie si c'est le mot de 1 à len-1
                            for (int j = 1; j <= len - 1; j++) {

                                lettre = arrMots.get(i).getStrMot().charAt(j);

                                if (tabLettres[y + j][x - j].toLowerCase().equals(Character.toString(lettre)) || (tabLettres[y + j][x - j].equals(Character.toString(lettre)))) {
                                    letter.add(lettre);

                                } else {
                                    break;
                                }
                                if (letter.size() == arrMots.get(i).getStrMot().length()) {
                                    arrMots.get(i).setPosition(x, y);
                                    arrMots.get(i).setDirection(arrMots.get(i).getDirection().SUDOUEST);
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }// sud est

    }// trouver mot

    private void trouverMotMystere() {
        System.out.print("Le mot mystère est: ");
        for (int i = 0; i < tabFinal.length; i++) {
            for (int j = 0; j < tabFinal[0].length; j++) {
                if (tabFinal[i][j].matches("-")) {
                    continue;
                } else {
                    System.out.print(tabFinal[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    public void afficherGrilleInitiale() {
        System.out.println("Grille initiale: ");

        for (int i = 0; i < tabLettres.length; i++) {
            System.out.print(String.format(Locale.US, "%-4s", i));
            for (int j = 0; j < tabLettres[0].length; j++) {
                System.out.print(tabLettres[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void afficherGrilleFinale() {
        System.out.println("Grille finale: ");
        for (int y = 0; y < tabFinal.length; y++) {
            for (int x = 0; x < tabFinal[0].length; x++) {
                // mots
                for (int i = 0; i < arrMots.size(); i++) {
                    if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().EST)) {
                        for (int j = 0; j < arrMots.get(i).getStrMot().length(); j++) {
                            tabFinal[arrMots.get(i).getPosition().y][arrMots.get(i).getPosition().x + j] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().OUEST)) {
                        for (int j = arrMots.get(i).getStrMot().length() - 1; j >= 0; j--) {
                            tabFinal[arrMots.get(i).getPosition().y][arrMots.get(i).getPosition().x - j] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().SUD)) {
                        for (int j = 0; j < arrMots.get(i).getStrMot().length(); j++) {
                            tabFinal[arrMots.get(i).getPosition().y + j][arrMots.get(i).getPosition().x] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().NORD)) {
                        for (int j = arrMots.get(i).getStrMot().length() - 1; j >= 0; j--) {
                            tabFinal[arrMots.get(i).getPosition().y - j][arrMots.get(i).getPosition().x] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().NORDEST)) {
                        for (int j = 0; j < arrMots.get(i).getStrMot().length(); j++) {
                            tabFinal[arrMots.get(i).getPosition().y - j][arrMots.get(i).getPosition().x + j] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().NORDOUEST)) {
                        for (int j = 0; j < arrMots.get(i).getStrMot().length(); j++) {
                            tabFinal[arrMots.get(i).getPosition().y - j][arrMots.get(i).getPosition().x - j] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().SUDEST)) {
                        for (int j = 0; j < arrMots.get(i).getStrMot().length(); j++) {
                            tabFinal[arrMots.get(i).getPosition().y + j][arrMots.get(i).getPosition().x + j] = "-";
                        }
                    } else if (arrMots.get(i).getDirection().equals(arrMots.get(i).getDirection().SUDOUEST)) {
                        for (int j = 0; j < arrMots.get(i).getStrMot().length(); j++) {
                            tabFinal[arrMots.get(i).getPosition().y + j][arrMots.get(i).getPosition().x - j] = "-";
                        }
                    }
                }
            }
        }
        for (int i = 0; i < tabFinal.length; i++) {
            System.out.print(String.format(Locale.US, "%-4s", i));
            for (int j = 0; j < tabFinal[0].length; j++) {
                System.out.print(tabFinal[i][j] + " ");
            }
            System.out.println();
        }
    }// afficher grille finale

    public void afficherMots() {
        for (Mot mot : arrMots) {
            System.out.println(mot);
        }
    } // afficher Mots

    public String[][] getTabLettres() {
        return tabLettres;
    }

    public void setTabLettres(String[][] tabLettres) {
        this.tabLettres = tabLettres;
    }

    public void setArrMots(ArrayList<Mot> arrMots) {
        this.arrMots = arrMots;
    }

}// grille du jeu

