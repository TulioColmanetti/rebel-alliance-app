package br.com.letscode.rebelalliance.controller;

import br.com.letscode.rebelalliance.domain.Rebel;
import lombok.Cleanup;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Random;

public class CentralIntelligence {
    @Getter
    LinkedList<Rebel> rebels;

    public CentralIntelligence(){
        this.rebels = new LinkedList<>();
    }

    public boolean requestToJoinAlliance(Rebel rebel) {
        int randNumber = new Random().nextInt(10);

        if(randNumber <= 5) {
            this.rebels.push(rebel);
            return true;
        }
        else
            return false;
    }

    public void generateRebelsReport() throws FileNotFoundException, UnsupportedEncodingException {
        @Cleanup PrintWriter writer = new PrintWriter("arquivo.txt", "UTF-8");
        writer.println("LISTA DE REBELDES:");
        for (Rebel rebel : this.rebels)
            writer.println(rebel.toString());
    }
}
