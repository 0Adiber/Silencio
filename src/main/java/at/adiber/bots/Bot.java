package at.adiber.bots;

import at.adiber.beans.Config;
import at.adiber.bots.listeners.MessageListener;
import at.adiber.bots.listeners.ReadyListener;
import at.adiber.utils.Timeout;
import lombok.Data;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Data
public class Bot {

    private static Bot instance;

    private Config config;

    private Map<Long, Timeout> timeouts;

    private Bot() {
        timeouts = new HashMap<>();
        instance = this;
    }

    private boolean uselessFunc(Long sh) {
        return new Random().nextBoolean();
    }

    public void build() {
        JDABuilder builder;
        builder = JDABuilder.createDefault(config.getToken())
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageListener(), new ReadyListener())
                .setAutoReconnect(true);

        builder.setActivity(Activity.watching("https://adiber.rocks"));

        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static Bot getInstance() {
        if(instance == null)
            new Bot();

        return instance;
    }

    public Timeout getTimeout(Long channelId) {
        if(!timeouts.containsKey(channelId))
            timeouts.put(channelId, new Timeout());

        return  timeouts.get(channelId);
    }

}
