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

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

import java.text.DecimalFormat;

public class RingOfVorpal extends Ring {

	{
		icon = ItemSpriteSheet.Icons.RING_VORPAL;
	}
	
	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats", new DecimalFormat("#").format(Math.min(100f, 100f * (Math.pow(1.05f, soloBuffedBonus()) - 1f))));
		} else {
			return Messages.get(this, "typical_stats", new DecimalFormat("#").format(3f));
		}
	}
	
	@Override
	protected RingBuff buff() {
		return new Vorpal();
	}
	
	public static float vorpalProc( Char target ){
		return (float)Math.min(1, Math.pow(1.05f, getBuffedBonus(target, Vorpal.class))-1);
	}
	
	public class Vorpal extends RingBuff {
	}
}
