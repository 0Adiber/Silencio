package at.adiber.bots.listeners;

import at.adiber.bots.Bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if(event.getAuthor().isBot()) return;

        if(Bot.getInstance().getTimeout().hasTimeout(event.getAuthor().getIdLong()))
            event.getMessage().delete().queue();
        else
            Bot.getInstance().getTimeout().setTimeout(event.getAuthor().getIdLong(), event.getMessage().getTimeCreated().toEpochSecond());

    }
}
