import org.jspecify.annotations.NullMarked;

@NullMarked
module de.nplay.levelbot.events {
    requires net.dv8tion.jda;
    requires org.jspecify;

    exports de.nplay.levelbot.events.bus;
    exports de.nplay.levelbot.events.discord;
    exports de.nplay.levelbot.events.discord.message;
    exports de.nplay.levelbot.events.discord.reaction;
}
