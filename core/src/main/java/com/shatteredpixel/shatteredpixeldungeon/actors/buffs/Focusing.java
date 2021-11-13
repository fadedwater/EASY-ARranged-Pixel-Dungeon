/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent.ENHANCED_FOCUSING;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Focusing extends FlavourBuff {

    private float focusTime = 0f;
    private float maxFocusTime = 5 + 5 * Dungeon.hero.pointsInTalent(ENHANCED_FOCUSING);
    public float getFocusTime() {
        return focusTime;
    }

    @Override
    public int icon() {
        return BuffIndicator.MARK;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.resetColor();
    }

    @Override
    public float iconFadePercent() {
        return Math.max(0, (maxFocusTime - focusTime) / maxFocusTime);
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    public void hit(Char enemy) {
        if (focusTime < maxFocusTime) {
            focusTime += 2f;
        } else {
            focusTime = maxFocusTime;
        }
        BuffIndicator.refreshHero(); //refresh the buff visually on-hit
    }

    @Override
    public void detach() {
        super.detach();
    }

    @Override
    public boolean act() {
        focusTime -= TICK;
        spend(TICK);
        if (focusTime <= 0) {
            detach();
        }
        return true;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", dispTurns(focusTime));
    }
}