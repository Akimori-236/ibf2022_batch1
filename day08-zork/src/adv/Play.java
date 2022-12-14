package adv;

import java.io.Console;
import java.util.Optional;

public class Play {

    private final StoryFile storyFile;

    public Play(StoryFile storyFile) {
        this.storyFile = storyFile;
    }

    public void start() {
        Console cons = System.console();
        String userInput;
        Room currRoom = this.storyFile.getRooms().get(this.storyFile.getStartRoom());
        System.out.println(currRoom.getName() + "\n" + currRoom.getDescription());

        // Game loop
        while (true) {
            //
            userInput = cons.readLine("> ").trim();
            if (userInput.equalsIgnoreCase("exit")) {
                // exit program
                break;
            } else if (userInput.equalsIgnoreCase("look")) {
                // reprint description
                System.out.println(currRoom.getName() + "\n" + currRoom.getDescription());
            } else if (userInput.equalsIgnoreCase("directions")) {
                // prints possible directions
                System.out.println(currRoom.getDirections());
            } else {
                // try to get next room
                Optional<String> opt = currRoom.go(userInput);
                if (opt.isEmpty()) {
                    System.out.println("You can't get there...");
                } else {
                    String r = opt.get();
                    currRoom = this.storyFile.getRooms().get(r);
                    System.out.println(currRoom.getName() + "\n" + currRoom.getDescription());
                }
            }
        }
        System.out.println("Exiting...");
    }
}
