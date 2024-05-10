package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class ExpBelt extends Artifact{
    private static  final  int BELT_UPGRADE_SCALE = 50;

    {
        image = ItemSpriteSheet.ARTIFACT_EXPBELT;

        levelCap = 10;
    }
    @Override
    public String desc() {
        String desc = super.desc();

        if (isEquipped( Dungeon.hero )){
            desc += "\n\n";
            if (cursed)
                desc += Messages.get(this, "desc_cursed");
            else
                desc += Messages.get(this, "desc_equipped", level()*BELT_UPGRADE_SCALE - exp);
        }
        return desc;
    }
    @Override
    protected ArtifactBuff passiveBuff() {
        return new ExpObtain();
    }
    public class ExpObtain extends ArtifactBuff {
        public void obtain(int expObt){
            exp += expObt;
            if (exp >= level()*BELT_UPGRADE_SCALE){
                while (exp >= level()*BELT_UPGRADE_SCALE) {
                    upgrade();
                    exp -= level()*BELT_UPGRADE_SCALE;
                }
                GLog.p(Messages.get(this, "levelup"));
            }
        }
    }
}
