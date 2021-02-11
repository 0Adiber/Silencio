package at.adiber.main;

import at.adiber.beans.Config;
import at.adiber.bots.Bot;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Config config;
        ObjectMapper mapper = new ObjectMapper();

        try {
            config = mapper.readValue(new File("config.json"), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Bot.getInstance().setConfig(config);
        Bot.getInstance().build();
    }

}
