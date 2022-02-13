package br.com.letscode.rebelalliance.view;

import br.com.letscode.rebelalliance.controller.CentralIntelligence;
import br.com.letscode.rebelalliance.enums.OrderBy;
import br.com.letscode.rebelalliance.enums.Race;
import br.com.letscode.rebelalliance.domain.Rebel;
import br.com.letscode.rebelalliance.utils.CustomRebelLinkedList;
import lombok.Cleanup;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class RebelAllianceMainView {
    private Scanner scanner;
    private CentralIntelligence centralIntelligence = new CentralIntelligence();
    private Rebel rebel;
    private String name;
    private int age;
    private Race race;
    private OrderBy orderBy;

    public RebelAllianceMainView(){
        this.scanner = new Scanner(System.in);
    }

    private void askName() {
        System.out.println("Qual o nome do Rebelde?");
        String name = scanner.next();
        if (name.isBlank()) {
            System.out.println("Nome inválido!");
            askName();
        } else
            this.name = name;
    }

    private void askAge() {
        System.out.println("Qual a idade do Rebelde?");
        int age = scanner.nextInt();
        if (age <= 0) {
            System.out.println("Idade inválida!");
            askAge();
        } else
            this.age = age;
    }

    private void askRace() {
        System.out.println("Qual a raça do Rebelde?");
        for (Race race : Race.values()) {
            System.out.printf("%d - %s\n", race.ordinal(), race.name());
        }
        int raceIndex = scanner.nextInt();
        if (raceIndex < 0 || raceIndex >= Race.values().length) {
            System.out.println("Raça inválida!");
            askRace();
        } else
            this.race = Race.values()[raceIndex];
    }

    private void getRebelInputData() {
        askName();
        askAge();
        askRace();

        this.rebel = Rebel.builder()
                .Name(this.name)
                .Age(this.age)
                .Race(this.race)
                .build();
    }

    private void askOrderBy() {
        System.out.println("Por qual propriedade deseja ordenar?");
        for (OrderBy orderBy : OrderBy.values()) {
            System.out.printf("%d - %s\n", orderBy.ordinal(), orderBy.getDescription());
        }
        int orderByIndex = scanner.nextInt();
        if (orderByIndex < 0 || orderByIndex >= OrderBy.values().length) {
            System.out.println("Propriedade inválida!");
            askOrderBy();
        } else
            this.orderBy = OrderBy.values()[orderByIndex];
    }

    private void requestToJoinAllianceCI() {
        boolean joined = this.centralIntelligence.requestToJoinAlliance(this.rebel);

        if(joined)
            System.out.println("Rebelde '" + rebel.getName() + "' ingressou na Aliança!");
        else
            System.out.println("Rebelde '" + rebel.getName() + "' recusado!");
    }

    private void showRebelsCI() {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA DE REBELDES:\n");
        for (Rebel rebel : this.centralIntelligence.getRebels())
        {
            sb.append(rebel.toString());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private void generateRebelsReportCI() {
        try {
            this.centralIntelligence.generateRebelsReport();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void printAndGenerateReportOfSortedRebels() {
        askOrderBy();

        CustomRebelLinkedList customRebelLinkedList = new CustomRebelLinkedList();
        for (Rebel rebel :
                this.centralIntelligence.getRebels()) {
            customRebelLinkedList.push(rebel);
        }

        //Set property that will be used to sort the list
        customRebelLinkedList.setOrderBy(orderBy.name());

        //Sort the rebel list using the Merge Sort Algorithm
        customRebelLinkedList.mergeSort();

        //Print sorted list on console terminal
        System.out.println("LISTA DE REBELDES:\n");
        System.out.println(customRebelLinkedList.toString());

        //Write sorted list to file
        @Cleanup PrintWriter writer = null;
        try {
            writer = new PrintWriter("arquivo.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println("LISTA DE REBELDES:");
        writer.print(customRebelLinkedList.toString());

    }

    public void renderMenu() {
        System.out.println("<<<<< Menu da Aliança Rebelde >>>>>");
        System.out.println("Escolha uma opção abaixo:");
        System.out.println("'S' - Solicitar Ingresso na Aliança");
        System.out.println("'E' - Exibir Lista de Rebeldes");
        System.out.println("'R' - Gerar Relatório de Rebeldes");
        System.out.println("'P' - Imprimir e Gerar Relatório Ordenado");
        System.out.println("'X' - Sair");
    }

    public void showMenu() {
        renderMenu();

        String option;
        boolean status = true;
        do {
            option = scanner.next();
            switch (option.toUpperCase()) {
                case "S":
                    getRebelInputData();
                    requestToJoinAllianceCI();
                    renderMenu();
                    break;
                case "E":
                    showRebelsCI();
                    renderMenu();
                    break;
                case "R":
                    generateRebelsReportCI();
                    renderMenu();
                    break;
                case "P":
                    printAndGenerateReportOfSortedRebels();
                    renderMenu();
                    break;
                case "X":
                    status = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (status);
    }
}
