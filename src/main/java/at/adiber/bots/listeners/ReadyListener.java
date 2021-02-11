package at.adiber.bots.listeners;

import at.adiber.main.Main;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;

public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        System.out.println(String.format("[BOT] %s started", event.getJDA().getSelfUser().getAsTag()));
        event.getJDA().getGuilds().forEach(g -> g.getSelfMember().modifyNickname("Silencio").queue());

        try {
            event.getJDA().getSelfUser().getManager().setAvatar(Icon.from(new URL("https://upload.wikimedia.org/wikipedia/commons/c/cc/Schweigefuchs.jpg").openStream())).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
