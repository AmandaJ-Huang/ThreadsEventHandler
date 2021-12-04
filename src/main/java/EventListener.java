public class EventListener extends Thread {

    private String messageToListenFor;
    private String messageToReplyWith;
    private Tracker eventTracker;

    public EventListener(String message, String reply) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = EventTracker.getInstance();
    }

    public EventListener(String message, String reply, Tracker tracker) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = tracker;
    }

    public void run() {
        while (!readyToQuit()) {
            if (shouldReply()) {
                this.eventTracker.handle(messageToListenFor, new EventHandler() {
                    @Override
                    public void handle() {

                    }
                });
            }
        }
    }

    public Boolean readyToQuit() {
        return this.eventTracker.has("quit");
    }

    public Boolean shouldReply() {
        if (this.eventTracker.has(this.messageToListenFor)) {
            return true;
        }
        return false;
    }

    public void reply() {
    }
}