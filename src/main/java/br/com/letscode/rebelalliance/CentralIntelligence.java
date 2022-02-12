package br.com.letscode.rebelalliance;

import lombok.Cleanup;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CentralIntelligence {
    @Getter
    List<Rebel> rebels;

    public CentralIntelligence(){
        this.rebels = new ArrayList<>();
    }

    public boolean requestToJoinAlliance(Rebel rebel) {
        int randNumber = new Random().nextInt(10);

        if(randNumber <= 5) {
            this.rebels.add(rebel);
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
