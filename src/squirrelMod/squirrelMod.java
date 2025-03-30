package squirrelmod;

import mindustry.ctype.ContentList;
import mindustry.mod.*;
import mindustry.type.Category;

public class SquirrelMod extends Mod {
    public static SquirrelReactor squirrelReactor;

    public SquirrelMod() {
        Log.info("Loaded SquirrelMod logic core.");
    }

    @Override
    public void loadContent() {
        squirrelReactor = new SquirrelReactor("squirrel-reactor") {{
            localizedName = "Squirrel Reactor";
            description = "Consumes any item to generate chaotic power.";
            size = 2;
            category = Category.power;
            alwaysUnlocked = true;
        }};
    }
}
