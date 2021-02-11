package at.adiber.utils;

import at.adiber.bots.Bot;

import java.time.OffsetDateTime;
import java.util.HashMap;

public class Timeout {

    private HashMap<Long,Long> users;

    public Timeout() {
        users = new HashMap<>();
    }

    public boolean hasTimeout(Long id) {
        Long timeout = users.get(id);

        if(timeout == null)
            return false;

        if(timeout+Bot.getInstance().getConfig().getTimeout() > OffsetDateTime.now().toEpochSecond()) {
            return true;
        }

        users.remove(id);
        return false;
    }

    public void setTimeout(Long id, Long now) {
        users.put(id, now);
    }

}
