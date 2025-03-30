package squirrelmod;

import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.meta.Env;
import arc.graphics.g2d.Draw;
import mindustry.gen.Building;
import mindustry.type.Item;

public class SquirrelReactor extends PowerGenerator {
    public SquirrelReactor(String name) {
        super(name);
        // Enable item input
        hasItems = true;
        itemCapacity = 20;
        consumesItems = true;
    }

    public class SquirrelReactorBuild extends Building {
        float charge = 0;

        @Override
        public void updateTile() {
            super.updateTile();

            // Check if we have any item
            if (items.total() > 0) {
                // Consume 1 item every 60 ticks
                if (timer(0, 60f)) {
                    // Remove 1 of any item
                    for (Item item : content.items()) {
                        if (items.has(item)) {
                            items.remove(item, 1);
                            charge += 5f; // Add 5 energy per item
                            break;
                        }
                    }
                }
            }

            // Slowly drain charge into power
            if (charge > 0) {
                productionEfficiency = 1f;
                charge -= edelta() * 1f;
            } else {
                productionEfficiency = 0f;
            }
        }

        @Override
        public float getPowerProduction() {
            return charge > 0 ? 3.0f : 0f;
        }

        @Override
        public void draw() {
            super.draw();
            Draw.alpha(charge / 20f);
            Draw.rect(region, x, y);
            Draw.reset();
        }
    }
}
