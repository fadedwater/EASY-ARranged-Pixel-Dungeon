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

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Bones {

	private static final String BONES_FILE	= "bones.dat";
	
	private static final String LEVEL	= "level";
	private static final String ITEM	= "item";

	private static int depth = -1;
	private static Item item;
	
	public static void leave() {

		//remains will usually drop on the floor the hero died on
		// but are capped at 5 floors above the lowest depth reached (even when ascending)
		depth = Math.max(Dungeon.depth, Statistics.deepestFloor-5);

		//daily runs do not interact with remains
		if (Dungeon.daily) {
			depth = -1;
			return;
		}

		item = pickItem(Dungeon.hero);

		Bundle bundle = new Bundle();
		bundle.put( LEVEL, depth );
		bundle.put( ITEM, item );

		try {
			FileUtils.bundleToFile( BONES_FILE, bundle );
		} catch (IOException e) {
			ShatteredPixelDungeon.reportException(e);
		}
	}

	private static Item pickItem(Hero hero){
		Item item = null;

		//seeded runs always leave gold
		//This is to prevent using specific seeds to transport items to regular runs
		if (!Dungeon.customSeedText.isEmpty()){
			if (Dungeon.gold > 100) {
				return new Gold( Random.NormalIntRange( 50, Dungeon.gold/2 ) );
			} else {
				return new Gold( 50 );
			}
		}

		if (Random.Int(3) != 0) {
			switch (Random.Int(7)) {
				case 0:
					item = hero.belongings.weapon;
					//if the hero has two weapons (champion), pick the stronger one
					if (hero.belongings.secondWep != null &&
							(item == null || hero.belongings.secondWep.trueLevel() > item.trueLevel())){
						item = hero.belongings.secondWep;
						break;
					}
					break;
				case 1:
					item = hero.belongings.armor;
					break;
				case 2:
					item = hero.belongings.artifact;
					break;
				case 3:
					item = hero.belongings.misc;
					break;
				case 4:
					item = hero.belongings.ring;
					break;
				case 5: case 6:
					item = Dungeon.quickslot.randomNonePlaceholder();
					break;
			}
			if (item == null || !item.bones) {
				return pickItem(hero);
			}
		} else {

			Iterator<Item> iterator = hero.belongings.backpack.iterator();
			Item curItem;
			ArrayList<Item> items = new ArrayList<>();
			while (iterator.hasNext()){
				curItem = iterator.next();
				if (curItem.bones)
					items.add(curItem);
			}

			if (Random.Int(3) < items.size()) {
				item = Random.element(items);
				if (item.stackable){
					item.quantity(Random.NormalIntRange(1, (item.quantity() + 1) / 2));
				}
			} else {
				if (Dungeon.gold > 100) {
					item = new Gold( Random.NormalIntRange( 50, Dungeon.gold/2 ) );
				} else {
					item = new Gold( 50 );
				}
			}
		}
		
		return item;
	}

	public static Item get() {
		//daily runs do not interact with remains
		if (Dungeon.daily){
			return null;
		}

		if (depth == -1) {

			try {
				Bundle bundle = FileUtils.bundleFromFile(BONES_FILE);

				depth = bundle.getInt( LEVEL );
				if (depth > 0) {
					item = (Item) bundle.get(ITEM);
				}

				return get();

			} catch (IOException e) {
				return null;
			}

		} else {
			if (depth == Dungeon.depth) {

				Bundle emptyBones = new Bundle();
				emptyBones.put(LEVEL, 0);
				try {
					FileUtils.bundleToFile( BONES_FILE, emptyBones );
				} catch (IOException e) {
					ShatteredPixelDungeon.reportException(e);
				}
				depth = 0;

				//challenged or seeded runs will always find 10 gold
				if (Dungeon.challenges != 0 || !Dungeon.customSeedText.isEmpty()){
					item = new Gold(10);
				}

				if (item == null) {
					item = new Gold(50);
				}

				//Enforces artifact uniqueness
				if (item instanceof Artifact){
					if (Generator.removeArtifact(((Artifact)item).getClass())) {
						
						//generates a new artifact of the same type, always +0
						Artifact artifact = Reflection.newInstance(((Artifact)item).getClass());
						
						if (artifact == null){
							return new Gold(item.value());
						}

						artifact.cursed = true;
						artifact.cursedKnown = true;

						return artifact;
						
					} else {
						return new Gold(item.value());
					}
				}
				
				if (item.isUpgradable() && !(item instanceof MissileWeapon)) {
					item.cursed = true;
					item.cursedKnown = true;
				}
				
				if (item.isUpgradable()) {
					//caps at +3
					if (item.level() > 3) {
						item.degrade( item.level() - 3 );
					}
					//thrown weapons are always IDed, otherwise set unknown
					item.levelKnown = item instanceof MissileWeapon;
				}
				
				item.reset();
				
				return item;
			} else {
				return null;
			}
		}
	}
}
