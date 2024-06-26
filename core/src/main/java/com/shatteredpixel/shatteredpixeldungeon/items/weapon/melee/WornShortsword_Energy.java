/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class WornShortsword_Energy extends EnergyWeapon {

    {
        image = ItemSpriteSheet.WORN_SHORTSWORD_ENERGY;
        hitSoundPitch = 1.1f;

        tier = 1;

        bones = false;

        chargePerHit = 2;
        chargeUsePerHit = 4;
    }

    @Override
    public int max(int lvl) {
        return 6 * (tier + 2) +
                lvl;
    }

    @Override
    protected int baseChargeUse(Hero hero, Char target){
        if (hero.buff(Sword.CleaveTracker.class) != null){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String targetingPrompt() {
        return Messages.get(this, "prompt");
    }

    @Override
    protected void duelistAbility(Hero hero, Integer target) {
        Sword.cleaveAbility(hero, target, 1.33f, this);
    }

    public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

        {
            inputs = new Class[]{WornShortsword.class, ScrollOfUpgrade.class, LiquidMetal.class};
            inQuantity = new int[]{1, 1, 10};

            cost = 8;

            output = WornShortsword_Energy.class;
            outQuantity = 1;
        }
    }

}
