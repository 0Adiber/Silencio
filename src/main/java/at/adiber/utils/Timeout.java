package at.adiber.utils;

import at.adiber.bots.Bot;
import sun.reflect.generics.tree.Tree;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Timeout {

    //Key = UserID | Value = EpochMilli
    private HashMap<Long, Set<Long>> users;

    public Timeout() {
        users = new HashMap<>();
    }

    public boolean hasTimeout(Long id) {
        Set<Long> timeout = users.get(id);
        //Set<Long> newTimeouts = users.get(id);

        if(timeout == null)
            return false;

        timeout.removeIf(t -> t + Bot.getInstance().getConfig().getTimespan()*1000 <= OffsetDateTime.now().toInstant().toEpochMilli());

        return timeout.size() >= Bot.getInstance().getConfig().getMessages();
    }

    public void setTimeout(Long id, Long now) {
        if(!users.containsKey(id)) {
            Set<Long> timeouts = new TreeSet<>();
            timeouts.add(now);
            users.put(id, timeouts);
        } else {
            users.get(id).add(now);
        }
    }

}
