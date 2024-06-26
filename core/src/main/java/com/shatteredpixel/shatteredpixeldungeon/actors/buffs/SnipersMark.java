/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.GoldenBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.NaturesBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.CorrosionBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.WindBow;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.utils.Bundle;

public class SnipersMark extends FlavourBuff implements ActionIndicator.Action {

	public int object = 0;
	public int level = 0;

	private static final String OBJECT    = "object";
	private static final String LEVEL    = "level";

	public static final float DURATION = 4f;

	{
		type = buffType.POSITIVE;
	}

	public void set(int object, int level){
		this.object = object;
		this.level = level;
	}

	@Override
	public boolean attachTo(Char target) {
		ActionIndicator.setAction(this);
		return super.attachTo(target);
	}

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( OBJECT, object );
		bundle.put( LEVEL, level );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		object = bundle.getInt( OBJECT );
		level = bundle.getInt( LEVEL );
	}

	@Override
	public int icon() {
		return BuffIndicator.MARK;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc");
	}

	@Override
	public String actionName() {
		SpiritBow bow = Dungeon.hero.belongings.getItem(SpiritBow.class);
		WindBow windBow = Dungeon.hero.belongings.getItem(WindBow.class);
		CorrosionBow corrosionBow = Dungeon.hero.belongings.getItem(CorrosionBow.class);
		GoldenBow goldenBow = Dungeon.hero.belongings.getItem(GoldenBow.class);
		NaturesBow naturesBow = Dungeon.hero.belongings.getItem(NaturesBow.class);

		if (bow != null && windBow == null && corrosionBow == null && goldenBow == null && naturesBow == null) {
			switch (bow.augment){
				case NONE: default:
					return Messages.get(this, "action_name_snapshot");
				case SPEED:
					return Messages.get(this, "action_name_volley");
				case DAMAGE:
					return Messages.get(this, "action_name_sniper");
			}
		} else if (bow == null && windBow != null && corrosionBow == null && goldenBow == null && naturesBow == null) {
			switch (windBow.augment){
				case NONE: default:
					return Messages.get(this, "action_name_snapshot");
				case SPEED:
					return Messages.get(this, "action_name_volley");
				case DAMAGE:
					return Messages.get(this, "action_name_sniper");
			}
		} else if (bow == null && windBow == null && corrosionBow != null && goldenBow == null && naturesBow == null) {
			switch (corrosionBow.augment){
				case NONE: default:
					return Messages.get(this, "action_name_snapshot");
				case SPEED:
					return Messages.get(this, "action_name_volley");
				case DAMAGE:
					return Messages.get(this, "action_name_sniper");
			}
		} else if (bow == null && windBow == null && corrosionBow == null && goldenBow != null && naturesBow == null) {
			switch (goldenBow.augment){
				case NONE: default:
					return Messages.get(this, "action_name_snapshot");
				case SPEED:
					return Messages.get(this, "action_name_volley");
				case DAMAGE:
					return Messages.get(this, "action_name_sniper");
			}
		} else if (bow == null && windBow == null && corrosionBow == null && goldenBow == null && naturesBow != null) {
			switch (naturesBow.augment){
				case NONE: default:
					return Messages.get(this, "action_name_snapshot");
				case SPEED:
					return Messages.get(this, "action_name_volley");
				case DAMAGE:
					return Messages.get(this, "action_name_sniper");
			}
		} else { // if hero doesn't have any bow
			return Messages.get(this, "no_bow");
		}
	}

	@Override
	public int actionIcon() {
		return HeroIcon.SNIPERS_MARK;
	}

	@Override
	public int indicatorColor() {
		return 0x444444;
	}

	@Override
	public void doAction() {

		Hero hero = Dungeon.hero;
		if (hero == null) return;

		SpiritBow bow = hero.belongings.getItem(SpiritBow.class);
		WindBow windBow = hero.belongings.getItem(WindBow.class);
		CorrosionBow corrosionBow = hero.belongings.getItem(CorrosionBow.class);
		GoldenBow goldenBow = hero.belongings.getItem(GoldenBow.class);
		NaturesBow naturesBow = hero.belongings.getItem(NaturesBow.class);

		if (bow == null && windBow == null && corrosionBow == null && goldenBow == null && naturesBow == null) return;

		if (bow != null && windBow == null && corrosionBow == null && goldenBow == null && naturesBow == null) {
				   SpiritBow.SpiritArrow arrow = bow.knockArrow();
				   if (arrow == null) return;

				   Char ch = (Char) Actor.findById(object);
				   if (ch == null) return;

				   int cell = QuickSlotButton.autoAim(ch, arrow);
				   if (cell == -1) return;

				   bow.sniperSpecial = true;
				   bow.sniperSpecialBonusDamage = level*Dungeon.hero.pointsInTalent(Talent.SHARED_UPGRADES)/10f;

				   arrow.cast(hero, cell);
				   detach();
		} else if (bow == null && windBow != null && corrosionBow == null && goldenBow == null && naturesBow == null) {
				   WindBow.SpiritArrow arrow = windBow.knockArrow();
				   if (arrow == null) return;

				   Char ch = (Char) Actor.findById(object);
				   if (ch == null) return;

				   int cell = QuickSlotButton.autoAim(ch, arrow);
				   if (cell == -1) return;

				   windBow.sniperSpecial = true;
				   windBow.sniperSpecialBonusDamage = level*Dungeon.hero.pointsInTalent(Talent.SHARED_UPGRADES)/10f;

				   arrow.cast(hero, cell);
				   detach();
		} else if (bow == null && windBow == null && corrosionBow != null && goldenBow == null && naturesBow == null) {
				   CorrosionBow.SpiritArrow arrow = corrosionBow.knockArrow();
				   if (arrow == null) return;

				   Char ch = (Char) Actor.findById(object);
				   if (ch == null) return;

				   int cell = QuickSlotButton.autoAim(ch, arrow);
				   if (cell == -1) return;

				   corrosionBow.sniperSpecial = true;
				   corrosionBow.sniperSpecialBonusDamage = level*Dungeon.hero.pointsInTalent(Talent.SHARED_UPGRADES)/10f;

				   arrow.cast(hero, cell);
				   detach();
		} else if (bow == null && windBow == null && corrosionBow == null && goldenBow != null && naturesBow == null) {
				   GoldenBow.SpiritArrow arrow = goldenBow.knockArrow();
				   if (arrow == null) return;

				   Char ch = (Char) Actor.findById(object);
				   if (ch == null) return;

				   int cell = QuickSlotButton.autoAim(ch, arrow);
				   if (cell == -1) return;

				   goldenBow.sniperSpecial = true;
				   goldenBow.sniperSpecialBonusDamage = level*Dungeon.hero.pointsInTalent(Talent.SHARED_UPGRADES)/10f;

				   arrow.cast(hero, cell);
				   detach();
		} else if (bow == null && windBow == null && corrosionBow == null && goldenBow == null && naturesBow != null) {
				   NaturesBow.SpiritArrow arrow = naturesBow.knockArrow();
				   if (arrow == null) return;

				   Char ch = (Char) Actor.findById(object);
				   if (ch == null) return;

				   int cell = QuickSlotButton.autoAim(ch, arrow);
				   if (cell == -1) return;

				   naturesBow.sniperSpecial = true;
				   naturesBow.sniperSpecialBonusDamage = level*Dungeon.hero.pointsInTalent(Talent.SHARED_UPGRADES)/10f;

				   arrow.cast(hero, cell);
				   detach();
		}
	}
}
