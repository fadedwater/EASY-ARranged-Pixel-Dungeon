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

package com.shatteredpixel.shatteredpixeldungeon.actors;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;
import static com.shatteredpixel.shatteredpixeldungeon.items.Item.updateQuickslot;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArcaneArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArrowEnhance;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ChampionEnemy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.DamageEnhance;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Daze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Demonization;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ExtraBullet;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FireImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Flurry;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FrostImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Fury;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.GodFury;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Iaido;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LifeLink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Preparation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SerialAttack;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sheathing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SoulMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Speed;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Stamina;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WantedTracker;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.ReinforcedArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.Riot;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.nurse.AngelWing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.planter.TreasureMap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.Awake;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.ShadowBlade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Tengu;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFury;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfRush;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfVampire;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfSirensSong;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.GoldenBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.NaturesBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.CorrosionBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.WindBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AntimaterRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AntimaterRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AntimaterRifleHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AssultRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AssultRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AssultRifleHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AutoHandgun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AutoHandgunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AutoHandgunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AutoRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AutoRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AutoRifleHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.BeamSaber;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Carbine;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.CarbineAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.CarbineHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.CrudePistol;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.CrudePistolAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.CrudePistolHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.DualPistol;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.DualPistolAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.DualPistolHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.FlameThrower;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.GildedShovel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Glaive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.GoldenPistol;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.GoldenPistolAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.GoldenPistolHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.GrenadeLauncher;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.GrenadeLauncherHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Handgun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HandgunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HandgunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HeavyMachinegun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HeavyMachinegunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HeavyMachinegunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HuntingRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HuntingRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HuntingRifleHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.IronHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.KSG;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.KSGAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.KSGHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Lance;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.LargeKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.LongKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Magnum;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagnumAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagnumHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MarksmanRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MarksmanRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MarksmanRifleHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MinersTool;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MiniGun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MiniGunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MiniGunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.NormalKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Pistol;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.PistolAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.PistolHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.PlasmaCannon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RPG7;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Revolver;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RevolverAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RevolverHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RocketLauncher;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RunicDagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SharpKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.ShortKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.ShotGun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.ShotGunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.ShotGunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sickle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SniperRifle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SniperRifleAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SniperRifleHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Spade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Spear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SubMachinegun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SubMachinegunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.SubMachinegunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.TacticalHandgun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.TacticalHandgunAP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.TacticalHandgunHP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.TrueRunicBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WA2000;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WA2000AP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WA2000HP;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornKatana_Energy;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Cross;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.ShockingDart;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class Char extends Actor {

	public int pos = 0;

	public CharSprite sprite;

	public int HT;
	public int HP;

	protected float baseSpeed	= 1;
	protected PathFinder.Path path;

	public int paralysed	    = 0;
	public boolean rooted		= false;
	public boolean flying		= false;
	public int invisible		= 0;

	//these are relative to the hero
	public enum Alignment{
		ENEMY,
		NEUTRAL,
		ALLY
	}
	public Alignment alignment;

	public int viewDistance	= 8;

	public boolean[] fieldOfView = null;

	private LinkedHashSet<Buff> buffs = new LinkedHashSet<>();

	@Override
	protected boolean act() {
		if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
			fieldOfView = new boolean[Dungeon.level.length()];
		}
		Dungeon.level.updateFieldOfView( this, fieldOfView );

		//throw any items that are on top of an immovable char
		if (properties().contains(Property.IMMOVABLE)){
			throwItems();
		}
		return false;
	}

	protected void throwItems(){
		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null && heap.type == Heap.Type.HEAP
				&& !(heap.peek() instanceof Tengu.BombAbility.BombItem)
				&& !(heap.peek() instanceof Tengu.ShockerAbility.ShockerItem)) {
			ArrayList<Integer> candidates = new ArrayList<>();
			for (int n : PathFinder.NEIGHBOURS8){
				if (Dungeon.level.passable[pos+n]){
					candidates.add(pos+n);
				}
			}
			if (!candidates.isEmpty()){
				Dungeon.level.drop( heap.pickUp(), Random.element(candidates) ).sprite.drop( pos );
			}
		}
	}

	public String name(){
		return Messages.get(this, "name");
	}

	public boolean canInteract(Char c){
		if (Dungeon.level.adjacent( pos, c.pos )){
			return true;
		} else if (c instanceof Hero
				&& alignment == Alignment.ALLY
				&& Dungeon.level.distance(pos, c.pos) <= 2*Dungeon.hero.pointsInTalent(Talent.ALLY_WARP)){
			return true;
		} else {
			return false;
		}
	}

	//swaps places by default
	public boolean interact(Char c){

		//don't allow char to swap onto hazard unless they're flying
		//you can swap onto a hazard though, as you're not the one instigating the swap
		if (!Dungeon.level.passable[pos] && !c.flying){
			return true;
		}

		//can't swap into a space without room
		if (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[c.pos]
			|| c.properties().contains(Property.LARGE) && !Dungeon.level.openSpace[pos]){
			return true;
		}

		//we do a little raw position shuffling here so that the characters are never
		// on the same cell when logic such as occupyCell() is triggered
		int oldPos = pos;
		int newPos = c.pos;

		//warp instantly with allies in this case
		if (c == Dungeon.hero && Dungeon.hero.hasTalent(Talent.ALLY_WARP)){
			PathFinder.buildDistanceMap(c.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));
			if (PathFinder.distance[pos] == Integer.MAX_VALUE){
				return true;
			}
			pos = newPos;
			c.pos = oldPos;
			ScrollOfTeleportation.appear(this, newPos);
			ScrollOfTeleportation.appear(c, oldPos);
			Dungeon.observe();
			GameScene.updateFog();
			return true;
		}

		//can't swap places if one char has restricted movement
		if (rooted || c.rooted || buff(Vertigo.class) != null || c.buff(Vertigo.class) != null){
			return true;
		}
		c.pos = oldPos;
		moveSprite( oldPos, newPos );
		move( newPos );

		c.pos = newPos;
		c.sprite.move( newPos, oldPos );
		c.move( oldPos );

		c.spend( 1 / c.speed() );

		if (c == Dungeon.hero){
			if (Dungeon.hero.subClass == HeroSubClass.FREERUNNER){
				Buff.affect(Dungeon.hero, Momentum.class).gainStack();
			}

			Dungeon.hero.busy();
		}

		if (c == hero && hero.hasTalent(Talent.QUICK_RELOAD)) {
			int chance = 2*hero.pointsInTalent(Talent.QUICK_RELOAD);
			KindOfWeapon wep = hero.belongings.attackingWeapon();
			KindOfWeapon eqWep = hero.belongings.weapon;
			if (wep instanceof CrudePistol && Random.Int(100) < chance) {
				((CrudePistol)eqWep).oneReload();

			} else if (wep instanceof Pistol && Random.Int(100) < chance) {
				((Pistol)eqWep).oneReload();

			} else if (wep instanceof GoldenPistol && Random.Int(100) < chance) {
				((GoldenPistol)eqWep).oneReload();

			} else if (wep instanceof Handgun && Random.Int(100) < chance) {
				((Handgun)eqWep).oneReload();

			} else if (wep instanceof Magnum && Random.Int(100) < chance) {
				((Magnum)eqWep).oneReload();

			} else if (wep instanceof TacticalHandgun && Random.Int(100) < chance) {
				((TacticalHandgun)eqWep).oneReload();

			} else if (wep instanceof AutoHandgun && Random.Int(100) < chance) {
				((AutoHandgun)eqWep).oneReload();

			} else if (wep instanceof DualPistol && Random.Int(100) < chance*3) {
				((DualPistol)eqWep).oneReload();

			} else if (wep instanceof SubMachinegun && Random.Int(100) < chance*3) {
				((SubMachinegun)eqWep).oneReload();

			} else if (wep instanceof AssultRifle && Random.Int(100) < chance*3) {
				((AssultRifle)eqWep).oneReload();

			} else if (wep instanceof HeavyMachinegun && Random.Int(100) < chance*3) {
				((HeavyMachinegun)eqWep).oneReload();

			} else if (wep instanceof MiniGun && Random.Int(100) < chance*3) {
				((MiniGun)eqWep).oneReload();

			} else if (wep instanceof Revolver && Random.Int(100) < chance/2) {
				((Revolver)eqWep).oneReload();

			} else if (wep instanceof HuntingRifle && Random.Int(100) < chance/2) {
				((HuntingRifle)eqWep).oneReload();

			} else if (wep instanceof Carbine && Random.Int(100) < chance/2) {
				((Carbine)eqWep).oneReload();

			} else if (wep instanceof SniperRifle && Random.Int(100) < chance/2) {
				((SniperRifle)eqWep).oneReload();

			} else if (wep instanceof AntimaterRifle && Random.Int(100) < chance/2) {
				((AntimaterRifle)eqWep).oneReload();

			} else if (wep instanceof WA2000 && Random.Int(100) < chance/2) {
				((WA2000)eqWep).oneReload();

			} else if (wep instanceof MarksmanRifle && Random.Int(100) < chance/2) {
				((MarksmanRifle)eqWep).oneReload();

			} else if (wep instanceof ShotGun && Random.Int(100) < chance/2) {
				((ShotGun)eqWep).oneReload();

			} else if (wep instanceof KSG && Random.Int(100) < chance/2) {
				((KSG)eqWep).oneReload();

			} else if (wep instanceof PlasmaCannon && Random.Int(100) < chance/2) {
				((PlasmaCannon)eqWep).oneReload();

			} else if (wep instanceof FlameThrower && Random.Int(100) < chance/2) {
				((FlameThrower)eqWep).oneReload();

			} else if (wep instanceof RocketLauncher && Random.Int(100) < chance/2) {
				((RocketLauncher)eqWep).oneReload();

			} else if (wep instanceof RPG7 && Random.Int(100) < chance/2) {
				((RPG7)eqWep).oneReload();

			}
			updateQuickslot();
		}

		if (c == Dungeon.hero){
			if (hero.subClass == HeroSubClass.SLAYER && hero.buff(Demonization.class) == null) {
				Buff.affect(hero, Demonization.class).indicate();
			}

			Dungeon.hero.busy();
		}


		return true;
	}

	protected boolean moveSprite( int from, int to ) {
		if (sprite.isVisible() && sprite.parent != null && (Dungeon.level.heroFOV[from] || Dungeon.level.heroFOV[to])) {
			sprite.move( from, to );
			return true;
		} else {
			sprite.turnTo(from, to);
			sprite.place( to );
			return true;
		}
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(Assets.Sounds.HIT, 1, pitch);
	}

	public boolean blockSound( float pitch ) {
		return false;
	}

	protected static final String POS       = "pos";
	protected static final String TAG_HP    = "HP";
	protected static final String TAG_HT    = "HT";
	protected static final String TAG_SHLD  = "SHLD";
	protected static final String BUFFS	    = "buffs";

	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		bundle.put( POS, pos );
		bundle.put( TAG_HP, HP );
		bundle.put( TAG_HT, HT );
		bundle.put( BUFFS, buffs );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {

		super.restoreFromBundle( bundle );

		pos = bundle.getInt( POS );
		HP = bundle.getInt( TAG_HP );
		HT = bundle.getInt( TAG_HT );

		for (Bundlable b : bundle.getCollection( BUFFS )) {
			if (b != null) {
				((Buff)b).attachTo( this );
			}
		}
	}

	final public boolean attack( Char enemy ){
		if (hero.buff(Awake.awakeTracker.class) != null) {
			return attack(enemy, 1.2f+0.2f*hero.pointsInTalent(Talent.AWAKE_LIMIT), 0f, 1f);
		} else {
			return attack(enemy, 1f, 0f, 1f);
		}
	}

	public boolean attack( Char enemy, float dmgMulti, float dmgBonus, float accMulti ) {

		if (enemy == null) return false;

		boolean visibleFight = Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[enemy.pos];

		if (enemy.isInvulnerable(getClass())) {

			if (visibleFight) {
				enemy.sprite.showStatus( CharSprite.POSITIVE, Messages.get(this, "invulnerable") );

				Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1f, Random.Float(0.96f, 1.05f));
			}

			return false;

		} else if (hit( this, enemy, accMulti, false )) {

			int dr = Math.round(enemy.drRoll() * AscensionChallenge.statModifier(enemy));

			Barkskin bark = enemy.buff(Barkskin.class);
			if (bark != null)   dr += Random.NormalIntRange( 0 , bark.level() );

			ReinforcedArmor.reinforcedArmorTracker rearmor = enemy.buff(ReinforcedArmor.reinforcedArmorTracker.class);
			if (rearmor != null)  dr += rearmor.blockingRoll();

			if (this.alignment == Alignment.ALLY && !(this instanceof Hero) && hero.hasTalent(Talent.CHARISMA)) {
				dr *= Math.pow(1.1f, hero.pointsInTalent(Talent.CHARISMA));
			}

			if (this instanceof Hero && enemy.buff(WantedTracker.Wanted.class) != null) {
				dmgMulti *= 1.1f + 0.05f * hero.pointsInTalent(Talent.JUSTICE_BULLET);
			}

			if (this instanceof Hero && hero.subClass == HeroSubClass.WEAPONMASTER) {
				if ( hero.belongings.attackingWeapon() instanceof Spear
					|| hero.belongings.attackingWeapon() instanceof Glaive
					|| hero.belongings.attackingWeapon() instanceof Lance ) {
					dr *= 1 - 0.1f*(Math.min(hero.belongings.weapon.buffedLvl(), 10));
				}
			}

			if (this instanceof Hero && Dungeon.isChallenged(Challenges.PYRO)) {
				Buff.affect(enemy, Burning.class).reignite(enemy, 8f);
			}

			if (this instanceof Hero) {
				Hero h = (Hero) this;
				KindOfWeapon wep = h.belongings.weapon();
				KindOfWeapon equippedWep = h.belongings.weapon;
				if (wep instanceof TrueRunicBlade) {
					dr = 0;
				}
				if (wep instanceof RunicDagger
						&& ((Mob) enemy).surprisedBy(hero)) {
					dr = 0;
				}
			}

			if (this instanceof Hero){
				Hero h = (Hero)this;
				KindOfWeapon wep = h.belongings.attackingWeapon();
				KindOfWeapon equippedWep = h.belongings.weapon;
				if (h.belongings.attackingWeapon() instanceof MissileWeapon
						&& h.subClass == HeroSubClass.SNIPER
						&& !Dungeon.level.adjacent(h.pos, enemy.pos)){
					dr = 0;
				}
				if (hero.buff(ShadowBlade.shadowBladeTracker.class) != null && Random.Int(2) == 0) {
					if (hero.hasTalent(Talent.CRITICAL_SHADOW)) {
						dmgBonus += Random.NormalIntRange(0, 5*hero.pointsInTalent(Talent.CRITICAL_SHADOW));
					}
					if (hero.hasTalent(Talent.HERBAL_SHADOW)) {
						hero.heal(hero.pointsInTalent(Talent.HERBAL_SHADOW));
					}
					dr = 0;
				}
				if ((wep instanceof CrudePistol.Bullet && (equippedWep instanceof CrudePistolAP))
					  || (wep instanceof Pistol.Bullet && (equippedWep instanceof PistolAP))
					  || (wep instanceof GoldenPistol.Bullet && (equippedWep instanceof GoldenPistolAP))
					  || (wep instanceof Handgun.Bullet && (equippedWep instanceof HandgunAP))
					  || (wep instanceof Magnum.Bullet && (equippedWep instanceof MagnumAP))
					  || (wep instanceof TacticalHandgun.Bullet && (equippedWep instanceof TacticalHandgunAP))
					  || (wep instanceof AutoHandgun.Bullet && (equippedWep instanceof AutoHandgunAP))

					  || (wep instanceof DualPistol.Bullet && (equippedWep instanceof DualPistolAP))
					  || (wep instanceof SubMachinegun.Bullet && (equippedWep instanceof SubMachinegunAP))
					  || (wep instanceof AssultRifle.Bullet && (equippedWep instanceof AssultRifleAP))
					  || (wep instanceof HeavyMachinegun.Bullet && (equippedWep instanceof HeavyMachinegunAP))
					  || (wep instanceof MiniGun.Bullet && (equippedWep instanceof MiniGunAP))
					  || (wep instanceof AutoRifle.Bullet && (equippedWep instanceof AutoRifleAP))

					  || (wep instanceof Revolver.Bullet && (equippedWep instanceof RevolverAP))
					  || (wep instanceof HuntingRifle.Bullet && (equippedWep instanceof HuntingRifleAP))
					  || (wep instanceof Carbine.Bullet && (equippedWep instanceof CarbineAP))
					  || (wep instanceof SniperRifle.Bullet && (equippedWep instanceof SniperRifleAP))
					  || (wep instanceof AntimaterRifle.Bullet && (equippedWep instanceof AntimaterRifleAP))
					  || (wep instanceof MarksmanRifle.Bullet && (equippedWep instanceof MarksmanRifleAP))
					  || (wep instanceof WA2000.Bullet && (equippedWep instanceof WA2000AP))

					  || (wep instanceof ShotGun.Bullet && (equippedWep instanceof ShotGunAP))
					  || (wep instanceof KSG.Bullet && (equippedWep instanceof KSGAP))) {
					dr = 0;
				}
				if ((wep instanceof CrudePistol.Bullet && (equippedWep instanceof CrudePistolHP))
						|| (wep instanceof Pistol.Bullet && (equippedWep instanceof PistolHP))
						|| (wep instanceof GoldenPistol.Bullet && (equippedWep instanceof GoldenPistolHP))
						|| (wep instanceof Handgun.Bullet && (equippedWep instanceof HandgunHP))
						|| (wep instanceof Magnum.Bullet && (equippedWep instanceof MagnumHP))
						|| (wep instanceof TacticalHandgun.Bullet && (equippedWep instanceof TacticalHandgunHP))
						|| (wep instanceof AutoHandgun.Bullet && (equippedWep instanceof AutoHandgunHP))

						|| (wep instanceof DualPistol.Bullet && (equippedWep instanceof DualPistolHP))
						|| (wep instanceof SubMachinegun.Bullet && (equippedWep instanceof SubMachinegunHP))
						|| (wep instanceof AssultRifle.Bullet && (equippedWep instanceof AssultRifleHP))
						|| (wep instanceof HeavyMachinegun.Bullet && (equippedWep instanceof HeavyMachinegunHP))
						|| (wep instanceof MiniGun.Bullet && (equippedWep instanceof MiniGunHP))
						|| (wep instanceof AutoRifle.Bullet && (equippedWep instanceof AutoRifleHP))

						|| (wep instanceof Revolver.Bullet && (equippedWep instanceof RevolverHP))
						|| (wep instanceof HuntingRifle.Bullet && (equippedWep instanceof HuntingRifleHP))
						|| (wep instanceof Carbine.Bullet && (equippedWep instanceof CarbineHP))
						|| (wep instanceof SniperRifle.Bullet && (equippedWep instanceof SniperRifleHP))
						|| (wep instanceof AntimaterRifle.Bullet && (equippedWep instanceof AntimaterRifleHP))
						|| (wep instanceof MarksmanRifle.Bullet && (equippedWep instanceof MarksmanRifleHP))
						|| (wep instanceof WA2000.Bullet && (equippedWep instanceof WA2000HP))) {
					dr *= 2;
				}
				if ((wep instanceof ShotGun.Bullet && (equippedWep instanceof ShotGunHP))
						|| (wep instanceof KSG.Bullet && (equippedWep instanceof KSGHP))) {
					dr *= 2;
				}

				if (h.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) != null){
					dr = 0;
				} else if (h.subClass == HeroSubClass.MONK) {
					//3 turns with standard attack delay
					Buff.prolong(h, MonkEnergy.MonkAbility.JustHitTracker.class, 4f);
				}
			}

			//we use a float here briefly so that we don't have to constantly round while
			// potentially applying various multiplier effects
			float dmg;
			Preparation prep = buff(Preparation.class);
			if (prep != null){
				dmg = prep.damageRoll(this);
				if (this == Dungeon.hero && Dungeon.hero.hasTalent(Talent.BOUNTY_HUNTER)) {
					Buff.affect(Dungeon.hero, Talent.BountyHunterTracker.class, 0.0f);
				}
			} else {
				dmg = damageRoll();
			}

			dmg = Math.round(dmg*dmgMulti);

			Berserk berserk = buff(Berserk.class);
			if (berserk != null) dmg = berserk.damageFactor(dmg);

			if (this instanceof Hero) {
				dmg *= RingOfRush.damageMultiplier(hero);
			}

			if (this.alignment == Alignment.ALLY && !(this instanceof Hero) && hero.hasTalent(Talent.POWERFUL_BOND)) {
				dmg *= Math.pow(1.1f, hero.pointsInTalent(Talent.POWERFUL_BOND));
			}

			if (Dungeon.isChallenged(Challenges.SUPERMAN) && this instanceof Hero) {
				dmg *= 3f;
			}

			if (this instanceof Hero && hero.buff(GodFury.class) != null) {
				dmg *= 3f;
			}

			if (this instanceof Hero && hero.subClass == HeroSubClass.WEAPONMASTER && hero.belongings.weapon != null) {
				if (Random.Int(100) < Math.min(hero.belongings.weapon.buffedLvl()+1, 10) && (
					hero.belongings.attackingWeapon() instanceof WornKatana
				 || hero.belongings.attackingWeapon() instanceof ShortKatana
				 || hero.belongings.attackingWeapon() instanceof NormalKatana
				 || hero.belongings.attackingWeapon() instanceof LongKatana
				 || hero.belongings.attackingWeapon() instanceof LargeKatana
				 || hero.belongings.attackingWeapon() instanceof SharpKatana
				 || hero.belongings.attackingWeapon() instanceof WornKatana_Energy)) {
					dmg *= 2f;
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
					hero.sprite.showStatus( CharSprite.NEUTRAL, "!" );
				}
			}

			if (this instanceof Hero && hero.buff(Bless.class) != null && hero.subClass == HeroSubClass.CRUSADER) {
				int heal = Math.max(Math.round(0.1f * dmg), 1);
				int effect = Math.min( hero.HT - hero.HP, heal );
				int shield = 0;
				if (hero.hasTalent(Talent.DIVINE_SHIELD)){
					shield = heal - effect;
					int maxShield = Math.round(hero.HT * 0.2f*hero.pointsInTalent(Talent.DIVINE_SHIELD));
					int curShield = 0;
					if (hero.buff(Barrier.class) != null) curShield = hero.buff(Barrier.class).shielding();
					shield = Math.min(shield, maxShield-curShield);
				}
				if (effect > 0 || shield > 0) {
					hero.HP += effect;
					if (shield > 0) Buff.affect(hero, Barrier.class).incShield(shield);
					hero.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
				}
			}

			if (this instanceof Hero && hero.belongings.attackingWeapon() instanceof Cross) {
				if (enemy.properties().contains(Char.Property.DEMONIC) || enemy.properties().contains(Char.Property.UNDEAD)){
					enemy.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10 );
					Sample.INSTANCE.play(Assets.Sounds.BURNING);

					dmg *= 1.3333f; //deals more damage to the demons and the undeads
				}
			}

			if (this instanceof Hero) {
				if (Dungeon.hero.belongings.attackingWeapon() instanceof AntimaterRifle.Bullet
						|| Dungeon.hero.belongings.attackingWeapon() instanceof AntimaterRifleAP.Bullet
						|| Dungeon.hero.belongings.attackingWeapon() instanceof AntimaterRifleHP.Bullet) {
					int distance = Dungeon.level.distance(hero.pos, enemy.pos) - 1;
					float multiplier = Math.min(4f, (float)Math.pow(1.2f, distance + 1));
					dmg = Math.round(dmg * multiplier);
				}
			}

			if (this instanceof Hero) {
				if (hero.buff(Iaido.class) != null) {
					dmg *= 1f + 0.1f * hero.buff(Iaido.class).getCount();
				}
			}

			if (this instanceof Hero && hero.hasTalent(Talent.BIOLOGY_PROJECT)) {
				if (!(enemy.properties().contains(Property.INORGANIC) || enemy.properties().contains(Property.UNDEAD))){
					enemy.sprite.emitter().start( ShadowParticle.UP, 0.05f, 3 );
					Sample.INSTANCE.play(Assets.Sounds.BURNING);

					dmg *= Math.pow(1.1f, hero.pointsInTalent(Talent.BIOLOGY_PROJECT));
				}
			}

			if (this instanceof Hero && hero.belongings.attackingWeapon() instanceof MissileWeapon && hero.hasTalent(Talent.SNARE) && hero.buff(Talent.SnareCooldown.class) == null) {
				Buff.prolong(enemy, Cripple.class, 5f);
				Buff.affect(hero, Talent.SnareCooldown.class, 10*(4-Dungeon.hero.pointsInTalent(Talent.SNARE)));
			}

			if (this instanceof Hero && Dungeon.level.map[hero.pos] == Terrain.WATER) {
				dmg += Random.NormalIntRange(1, hero.pointsInTalent(Talent.WATER_FRIENDLY));
				Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
			}

			if (this instanceof Hero && hero.buff(TreasureMap.GoldTracker.class) != null) {
				dmg *= 1 + 0.1f * hero.pointsInTalent(Talent.GOLD_HUNTER);
			}

			if (buff( Fury.class ) != null) {
				dmg *= 1.5f;
			}

			if (this instanceof Hero) {
				if (hero.buff(ShadowBlade.shadowBladeTracker.class) != null) {
					dmg *= 0.5f;
				}
			}

			if (this instanceof Hero && hero.hasTalent(Talent.VINE_WHIP) && hero.belongings.weapon instanceof MeleeWeapon) {
				dmg *= Math.pow(0.8, hero.pointsInTalent(Talent.VINE_WHIP));
			}

			if (this instanceof Hero && hero.belongings.weapon instanceof IronHammer && Random.Int(20) == 0) {
				Buff.affect(enemy, Paralysis.class, 1f);
			}

			if (this instanceof Hero && hero.belongings.weapon instanceof BeamSaber && Random.Int(40) == 0) {
				Buff.affect(enemy, Blindness.class, 2f);
			}

			if (this instanceof Hero
					&& hero.hasTalent(Talent.TAKEDOWN)
					&& hero.buff(Talent.TakeDownCooldown.class) == null
					&& (hero.belongings.attackingWeapon() instanceof Shovel || hero.belongings.attackingWeapon() instanceof GildedShovel || hero.belongings.attackingWeapon() instanceof Spade || hero.belongings.attackingWeapon() instanceof MinersTool)) {
				Buff.affect(hero, Talent.TakeDownCooldown.class, 15f);
			}

			if (this instanceof Hero) {
				if (hero.subClass == HeroSubClass.SLASHER) {
					if (hero.buff(SerialAttack.class) != null) {
						dmg *= 1f + 0.05f * hero.buff(SerialAttack.class).getCount();
						Buff.affect(hero, SerialAttack.class).hit(enemy);
					} else {
						dmg *= 1f;
						Buff.affect(hero, SerialAttack.class).hit(enemy);
					}
				}
			}
			if (this instanceof Hero) {
				if (this.buff(Talent.CriticalUpgradeTracker.class) != null) {
					dmg *= 1.5+1.5f*hero.pointsInTalent(Talent.CRITICAL_UPGRADE);
					this.buff(Talent.CriticalUpgradeTracker.class).detach();
				}
			}
			if (this instanceof Hero) {
				if (dmg >= enemy.HP
						&& hero.hasTalent(Talent.DEADS_BLESS)
						&& hero.buff(GodFury.class) != null
						&& !enemy.isImmune(AllyBuff.class)
						&& enemy.buff(AllyBuff.class) == null
						&& enemy instanceof Mob
						&& enemy.isAlive()){
					dmg = 0;
					int healAmt = enemy.HT-enemy.HP;
					enemy.HP += healAmt;
					enemy.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 5 );
					AllyBuff.affectAndLoot((Mob)enemy, hero, ScrollOfSirensSong.Enthralled.class);
					if (hero.pointsInTalent(Talent.DEADS_BLESS) > 1) {
						Buff.affect(enemy, Barrier.class).setShield(enemy.HT/5);
					}
					if (hero.pointsInTalent(Talent.DEADS_BLESS) > 2) {
						Buff.affect(enemy, Haste.class, 20f);
						Buff.affect(enemy, Adrenaline.class, 20f);
					}
				}
			}

			if (this instanceof Hero) {
				if (hero.hasTalent(Talent.DEFIBRILLATOR)) {
					if (!enemy.isAlive() && Random.Int(50) < hero.pointsInTalent(Talent.DEFIBRILLATOR)) {
						dmg = 0;
						int healAmt = 10;
						enemy.HP += healAmt;
						enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
						enemy.sprite.flash();
						AllyBuff.affectAndLoot((Mob)enemy, hero, ScrollOfSirensSong.Enthralled.class);
					}
				}
			}

			if (this instanceof Hero) {
				if (Dungeon.hero.belongings.attackingWeapon() instanceof GrenadeLauncher.Rocket) {
					Buff.prolong(enemy, Vulnerable.class, 5f);
				}
				if (Dungeon.hero.belongings.attackingWeapon() instanceof GrenadeLauncherHP.Rocket) {
					Buff.prolong(enemy, Paralysis.class, 3f);
				}
				//if (Dungeon.hero.belongings.attackingWeapon() instanceof SleepGun.Dart) {
				//	{actPriority = VFX_PRIO;}
				//	Buff.affect(enemy, MagicalSleep.class);
				//}
				//if (Dungeon.hero.belongings.attackingWeapon() instanceof FrostGun.Dart) {
				//	{actPriority = VFX_PRIO;}
				//	Buff.affect(enemy, Frost.class, 20f);
				//}
				//ParalysisGun paralysisGun = new ParalysisGun();
				//if (Dungeon.hero.belongings.attackingWeapon() instanceof ParalysisGun.Dart) {
				//	{actPriority = VFX_PRIO;}
				//	Buff.affect(enemy, Paralysis.class, 5f + paralysisGun.buffedLvl());
				//}
			}

			if (this instanceof Hero){
				Hero h = (Hero)this;
				KindOfWeapon wep = h.belongings.weapon();
				KindOfWeapon equippedWep = h.belongings.weapon;
				if ((wep instanceof CrudePistol.Bullet && equippedWep instanceof CrudePistolAP)
				 || (wep instanceof Pistol.Bullet && equippedWep instanceof PistolAP)
				 || (wep instanceof GoldenPistol.Bullet && equippedWep instanceof GoldenPistolAP)
				 || (wep instanceof Handgun.Bullet && equippedWep instanceof HandgunAP)
				 || (wep instanceof Magnum.Bullet && equippedWep instanceof MagnumAP)
				 || (wep instanceof TacticalHandgun.Bullet && equippedWep instanceof TacticalHandgunAP)
				 || (wep instanceof AutoHandgun.Bullet && equippedWep instanceof AutoHandgunAP)
				 || (wep instanceof DualPistol.Bullet && equippedWep instanceof DualPistolAP)
				 || (wep instanceof SubMachinegun.Bullet && equippedWep instanceof SubMachinegunAP)
				 || (wep instanceof AssultRifle.Bullet && equippedWep instanceof AssultRifleAP)
				 || (wep instanceof HeavyMachinegun.Bullet && equippedWep instanceof HeavyMachinegunAP)
				 || (wep instanceof MiniGun.Bullet && equippedWep instanceof MiniGunAP)
				 || (wep instanceof AutoRifle.Bullet && equippedWep instanceof AutoRifleAP)
				 || (wep instanceof Revolver.Bullet && equippedWep instanceof RevolverAP)
				 || (wep instanceof HuntingRifle.Bullet && equippedWep instanceof HuntingRifleAP)
				 || (wep instanceof Carbine.Bullet && equippedWep instanceof CarbineAP)
				 || (wep instanceof SniperRifle.Bullet && equippedWep instanceof SniperRifleAP)
				 || (wep instanceof AntimaterRifle.Bullet && equippedWep instanceof AntimaterRifleAP)
				 || (wep instanceof MarksmanRifle.Bullet && equippedWep instanceof MarksmanRifleAP)
				 || (wep instanceof WA2000.Bullet && equippedWep instanceof WA2000AP)
				) {
					dmg *= 0.80f;
				} else if ((wep instanceof CrudePistol.Bullet && equippedWep instanceof CrudePistolHP)
						|| (wep instanceof Pistol.Bullet && equippedWep instanceof PistolHP)
						|| (wep instanceof GoldenPistol.Bullet && equippedWep instanceof GoldenPistolHP)
						|| (wep instanceof Handgun.Bullet && equippedWep instanceof HandgunHP)
						|| (wep instanceof Magnum.Bullet && equippedWep instanceof MagnumHP)
						|| (wep instanceof TacticalHandgun.Bullet && equippedWep instanceof TacticalHandgunHP)
						|| (wep instanceof AutoHandgun.Bullet && equippedWep instanceof AutoHandgunHP)
						|| (wep instanceof DualPistol.Bullet && equippedWep instanceof DualPistolHP)
						|| (wep instanceof SubMachinegun.Bullet && equippedWep instanceof SubMachinegunHP)
						|| (wep instanceof AssultRifle.Bullet && equippedWep instanceof AssultRifleHP)
						|| (wep instanceof HeavyMachinegun.Bullet && equippedWep instanceof HeavyMachinegunHP)
						|| (wep instanceof MiniGun.Bullet && equippedWep instanceof MiniGunHP)
						|| (wep instanceof AutoRifle.Bullet && equippedWep instanceof AutoRifleHP)
						|| (wep instanceof Revolver.Bullet && equippedWep instanceof RevolverHP)
						|| (wep instanceof HuntingRifle.Bullet && equippedWep instanceof HuntingRifleHP)
						|| (wep instanceof Carbine.Bullet && equippedWep instanceof CarbineHP)
						|| (wep instanceof SniperRifle.Bullet && equippedWep instanceof SniperRifleHP)
						|| (wep instanceof AntimaterRifle.Bullet && equippedWep instanceof AntimaterRifleHP)
						|| (wep instanceof MarksmanRifle.Bullet && equippedWep instanceof MarksmanRifleHP)
						|| (wep instanceof WA2000.Bullet && equippedWep instanceof WA2000HP)
				) {
					dmg *= 1.3f;
				}
			}

			KindOfWeapon wep = hero.belongings.attackingWeapon();

			if (this instanceof Hero) {
				if (Dungeon.hero.buff(ExtraBullet.class) != null && wep != null) {
					if (wep.bullet) {
						dmg += 3;
					}
				}
			}

			if (Dungeon.hero.buff(Riot.riotTracker.class) != null && wep != null) {
				if (wep.bullet) {
					dmg *= 0.5f;
				}

			}

			if (this instanceof Hero) {
				if (hero.heroClass == HeroClass.GUNNER && wep != null) {
					if (wep.gun) {
						dmg += Random.NormalIntRange(0, hero.belongings.weapon.buffedLvl());
					}
				}
			}

			if (this instanceof Hero && hero.belongings.attackingWeapon() instanceof MissileWeapon && hero.hasTalent(Talent.SHOOTING_EYES) && enemy.buff(Talent.ShootingEyesTracker.class) == null) {
				if (Random.Int(3) < hero.pointsInTalent(Talent.SHOOTING_EYES)) {
					Buff.affect(enemy, Blindness.class, 2f);
				}
				Buff.affect(enemy, Talent.ShootingEyesTracker.class);
			}

			if (this instanceof Hero && hero.belongings.attackingWeapon() instanceof MissileWeapon && hero.hasTalent(Talent.TARGET_SPOTTING) && hero.buff(SnipersMark.class) != null && hero.buff(SnipersMark.class).object == enemy.id()) {
				dmg *= 1+0.1f*hero.pointsInTalent(Talent.TARGET_SPOTTING);
			}

			if (this instanceof Hero && hero.buff(DamageEnhance.class) != null) {
				dmg *= hero.buff(DamageEnhance.class).getDmg();
			}

			if (this instanceof Hero && hero.buff(ArrowEnhance.class) != null
					&& (hero.belongings.attackingWeapon() instanceof SpiritBow.SpiritArrow
						|| hero.belongings.attackingWeapon() instanceof GoldenBow.SpiritArrow
						|| hero.belongings.attackingWeapon() instanceof WindBow.SpiritArrow
						|| hero.belongings.attackingWeapon() instanceof CorrosionBow.SpiritArrow
						|| hero.belongings.attackingWeapon() instanceof NaturesBow.SpiritArrow)) {
				dmg *= 1.5f;
			}

			if (this instanceof Hero && hero.belongings.attackingWeapon() instanceof MissileWeapon) {
				int distance = Dungeon.level.distance(hero.pos, enemy.pos) - 1;
				float multiplier = Math.min(2f, (float)Math.pow(1 + 0.025f * hero.pointsInTalent(Talent.RANGED_SNIPING), distance));
				dmg = Math.round(dmg * multiplier);
			}

			if (this instanceof Hero && hero.hasTalent(Talent.TACKLE) && level.adjacent(enemy.pos, hero. pos) && hero.belongings.armor != null && hero.belongings.attackingWeapon() instanceof MeleeWeapon) {
				dmg += hero.belongings.armor.DRMax()*0.05f*hero.pointsInTalent(Talent.TACKLE);
			}

			if (this instanceof Hero && enemy.buff(Charm.class) != null && hero.pointsInTalent(Talent.APPEASE) >= 2) {
				int healAmt = Math.round(dmg/10f);
				healAmt = Math.min( healAmt, Dungeon.hero.HT - Dungeon.hero.HP );
				if (healAmt > 0 && Dungeon.hero.isAlive()) {
					Dungeon.hero.HP += healAmt;
					Dungeon.hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
					Dungeon.hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
				}
			}

			if (this instanceof Hero) {
				if (RingOfVampire.vampiricProc( hero ) < 0) {
					if (Random.Float() < -RingOfVampire.vampiricProc( hero )) {
						this.damage(Math.round(dmg/5f), hero);
						CellEmitter.get(this.pos).burst(ShadowParticle.UP, 5);
						Sample.INSTANCE.play(Assets.Sounds.CURSED);
						GLog.w(Messages.get(RingOfVampire.class, "damage"));
					}
				} else {
					if (Random.Float() < RingOfVampire.vampiricProc( hero )) {
						int healAmt = Math.round(dmg/5f);
						healAmt = Math.min( healAmt, Dungeon.hero.HT - Dungeon.hero.HP );
						if (healAmt > 0 && Dungeon.hero.isAlive()) {
							Dungeon.hero.HP += healAmt;
							Dungeon.hero.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
							Dungeon.hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
						}
					}
				}
			}

			if (this instanceof Hero) {
				dmg *= RingOfFury.dealingMultiplier( hero );
			}

			if (this instanceof Hero && enemy.buff(Charm.class) != null && hero.pointsInTalent(Talent.APPEASE) == 3 && Random.Int(10) == 0 && !enemy.properties().contains(Property.BOSS) && !enemy.properties().contains(Property.MINIBOSS)) {
				dmg = 0;
				int healAmt = enemy.HT;
				healAmt = Math.min( healAmt, enemy.HT - enemy.HP );
				if (healAmt > 0) {
					enemy.HP += healAmt;
					enemy.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
					enemy.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
				}
				AllyBuff.affectAndLoot(((Mob) enemy), hero, ScrollOfSirensSong.Enthralled.class);
			}

			for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
				dmg *= buff.meleeDamageFactor();
			}

			dmg *= AscensionChallenge.statModifier(this);

			//flat damage bonus is applied after positive multipliers, but before negative ones
			dmg += dmgBonus;

			//friendly endure
			Endure.EndureTracker endure = buff(Endure.EndureTracker.class);
			if (endure != null) dmg = endure.damageFactor(dmg);

			//enemy endure
			endure = enemy.buff(Endure.EndureTracker.class);
			if (endure != null){
				dmg = endure.adjustDamageTaken(dmg);
			}

			if (enemy.buff(ScrollOfChallenge.ChallengeArena.class) != null){
				dmg *= 0.67f;
			}

			if (enemy.buff(MonkEnergy.MonkAbility.Meditate.MeditateResistance.class) != null){
				dmg *= 0.2f;
			}

			if ( buff(Weakness.class) != null ){
				dmg *= 0.67f;
			}

			if ( buff(WantedTracker.Wanted.class) != null && hero.pointsInTalent(Talent.SURRENDER) > 1 && !(properties().contains(Char.Property.BOSS) || properties().contains(Property.MINIBOSS))) {
				dmg *= 0.67f;
			}

			if ( buff(SoulMark.class) != null && hero.hasTalent(Talent.MARK_OF_WEAKNESS)) {
				if (this.alignment != Alignment.ALLY) {
					dmg *= 1-0.1f*hero.pointsInTalent(Talent.MARK_OF_WEAKNESS);
				}
			}

			if (enemy != hero && enemy.alignment == Alignment.ALLY && Dungeon.level.heroFOV[enemy.pos] && hero.hasTalent(Talent.CHIVALRY)) {
				enemy = hero;
				if (hero.pointsInTalent(Talent.CHIVALRY) > 1) {
					dmg *= 0.5;
				}
			}

			int effectiveDamage = enemy.defenseProc( this, Math.round(dmg) );
			//do not trigger on-hit logic if defenseProc returned a negative value
			if (effectiveDamage >= 0) {
				effectiveDamage = Math.max(effectiveDamage - dr, 0);

				if (enemy.buff(Viscosity.ViscosityTracker.class) != null) {
					effectiveDamage = enemy.buff(Viscosity.ViscosityTracker.class).deferDamage(effectiveDamage);
					enemy.buff(Viscosity.ViscosityTracker.class).detach();
				}

				//vulnerable specifically applies after armor reductions
				if (enemy.buff(Vulnerable.class) != null) {
					effectiveDamage *= 1.33f;
				}

				effectiveDamage = attackProc(enemy, effectiveDamage);
			}

			effectiveDamage = attackProc( enemy, effectiveDamage );

			if (visibleFight) {
				if (effectiveDamage > 0 || !enemy.blockSound(Random.Float(0.96f, 1.05f))) {
					hitSound(Random.Float(0.87f, 1.15f));
				}
			}

			// If the enemy is already dead, interrupt the attack.
			// This matters as defence procs can sometimes inflict self-damage, such as armor glyphs.
			if (!enemy.isAlive()){
				return true;
			}

			enemy.damage( effectiveDamage, this );

			if (buff(FireImbue.class) != null)  buff(FireImbue.class).proc(enemy);
			if (buff(FrostImbue.class) != null) buff(FrostImbue.class).proc(enemy);

			if (enemy.isAlive() && enemy.alignment != alignment && prep != null && prep.canKO(enemy)){
				enemy.HP = 0;
				if (!enemy.isAlive()) {
					enemy.die(this);
				} else {
					//helps with triggering any on-damage effects that need to activate
					enemy.damage(-1, this);
					DeathMark.processFearTheReaper(enemy);
				}
				if (enemy.sprite != null) {
					enemy.sprite.showStatus(CharSprite.NEGATIVE, Messages.get(Preparation.class, "assassinated"));
					if (Random.Int(5) < hero.pointsInTalent(Talent.ENERGY_DRAW)) {
						CloakOfShadows cloak = hero.belongings.getItem(CloakOfShadows.class);
						if (cloak != null) {
							cloak.overCharge(1);
							ScrollOfRecharging.charge(Dungeon.hero);
							SpellSprite.show(hero, SpellSprite.CHARGE);
						}
					}
				}
			}

			Talent.CombinedLethalityTriggerTracker combinedLethality = buff(Talent.CombinedLethalityTriggerTracker.class);
			if (combinedLethality != null){
				if ( enemy.isAlive() && enemy.alignment != alignment && !Char.hasProp(enemy, Property.BOSS)
						&& !Char.hasProp(enemy, Property.MINIBOSS) && this instanceof Hero &&
						(enemy.HP/(float)enemy.HT) <= 0.4f*((Hero)this).pointsInTalent(Talent.COMBINED_LETHALITY)/3f) {
					enemy.HP = 0;
					if (!enemy.isAlive()) {
						enemy.die(this);
					} else {
						//helps with triggering any on-damage effects that need to activate
						enemy.damage(-1, this);
						DeathMark.processFearTheReaper(enemy);
					}
					if (enemy.sprite != null) {
						enemy.sprite.showStatus(CharSprite.NEGATIVE, Messages.get(Talent.CombinedLethalityTriggerTracker.class, "executed"));
					}
				}
				combinedLethality.detach();
			}

			if (enemy.sprite != null) {
				enemy.sprite.bloodBurstA(sprite.center(), effectiveDamage);
				enemy.sprite.flash();
			}

			if (!enemy.isAlive() && visibleFight) {
				if (enemy == Dungeon.hero) {

					if (this == Dungeon.hero) {
						return true;
					}

					if (this instanceof WandOfLivingEarth.EarthGuardian
							|| this instanceof MirrorImage || this instanceof PrismaticImage){
						Badges.validateDeathFromFriendlyMagic();
					}
					Dungeon.fail( this );
					GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );

				} else if (this == Dungeon.hero) {
					GLog.i( Messages.capitalize(Messages.get(Char.class, "defeat", enemy.name())) );
				}
			}

			return true;

		} else {

			if (enemy instanceof Hero) {
				Demonization demonization = hero.buff(Demonization.class);
				if (demonization != null
						&& demonization.isDemonated()
						&& Random.Int(5) == 0
				) {
					Buff.prolong(hero, Flurry.class, Flurry.DURATION);
				}
			}

			if (enemy instanceof Hero && hero.pointsInTalent(Talent.SWIFT_MOVEMENT) == 3) {
				Buff.prolong(hero, Invisibility.class, 1.0001f);
			}

			if (enemy instanceof Hero && Random.Int(5) < hero.pointsInTalent(Talent.COUNTER_ATTACK)) {
				Buff.affect(hero, Talent.CounterAttackTracker.class);
			}

			if (enemy instanceof Hero && hero.pointsInTalent(Talent.DETECTIVE_SLASHING) > 1 && enemy.buff(Sheathing.class) != null) {
				Buff.affect(hero, Talent.DetactiveSlashingTracker.class);
			}

			if (enemy instanceof Hero && hero.hasTalent(Talent.QUICK_PREP)) {
				Momentum momentum = hero.buff(Momentum.class);
				if (momentum != null) {
					momentum.quickPrep(hero.pointsInTalent(Talent.QUICK_PREP));
				}
			}

			if (enemy instanceof Hero && Random.Int(2) < hero.pointsInTalent(Talent.QUICK_RECOVER)) {
				int healAmt = 1;
				healAmt = Math.min( healAmt, hero.HT - hero.HP );
				if (healAmt > 0 && hero.isAlive()) {
					hero.HP += healAmt;
					hero.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1);
					hero.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
				}
			}

			enemy.sprite.showStatus( CharSprite.NEUTRAL, enemy.defenseVerb() );
			if (visibleFight) {
				//TODO enemy.defenseSound? currently miss plays for monks/crab even when they parry
				Sample.INSTANCE.play(Assets.Sounds.MISS);
			}

			return false;

		}
	}

	public static int INFINITE_ACCURACY = 1_000_000;
	public static int INFINITE_EVASION = 1_000_000;

	final public static boolean hit( Char attacker, Char defender, boolean magic ) {
		return hit(attacker, defender, magic ? 2f : 1f, magic);
	}

	public static boolean hit( Char attacker, Char defender, float accMulti, boolean magic ) {
		float acuStat = attacker.attackSkill( defender );
		float defStat = defender.defenseSkill( attacker );

		if (defender instanceof Hero && ((Hero) defender).damageInterrupt){
			((Hero) defender).interrupt();
		}

		//invisible chars always hit (for the hero this is surprise attacking)
		if (attacker.invisible > 0 && attacker.canSurpriseAttack()){
			acuStat = INFINITE_ACCURACY;
		}

		if (defender.buff(MonkEnergy.MonkAbility.Focus.FocusBuff.class) != null && !magic){
			defStat = INFINITE_EVASION;
			defender.buff(MonkEnergy.MonkAbility.Focus.FocusBuff.class).detach();
			Buff.affect(defender, MonkEnergy.MonkAbility.Focus.FocusActivation.class, 0);
		}

		//if accuracy or evasion are large enough, treat them as infinite.
		//note that infinite evasion beats infinite accuracy
		if (defStat >= INFINITE_EVASION){
			return false;
		} else if (acuStat >= INFINITE_ACCURACY){
			return true;
		}

		float acuRoll = Random.Float( acuStat );
		if (attacker.buff(Bless.class) != null) {
			if (attacker instanceof Hero) {
				if (hero.hasTalent(Talent.BLESS_ENHANCE)) {
					acuRoll *= 1 + 0.25f * (1 + 0.2f * hero.pointsInTalent(Talent.BLESS_ENHANCE));
				} else {
					acuRoll *= 1.25f;
				}
			} else {
				acuRoll *= 1.25f;
			}
		}
		if (attacker.buff(  Hex.class) != null) acuRoll *= 0.8f;
		if (attacker.buff( Daze.class) != null) acuRoll *= 0.5f;
		for (ChampionEnemy buff : attacker.buffs(ChampionEnemy.class)){
			acuRoll *= buff.evasionAndAccuracyFactor();
		}
		acuRoll *= AscensionChallenge.statModifier(attacker);

		float defRoll = Random.Float( defStat );
		if (defender.buff(Bless.class) != null) {
			if (defender instanceof Hero) {
				if (hero.hasTalent(Talent.BLESS_ENHANCE)) {
					defRoll *= 1 + 0.25f * (1 + 0.2f * hero.pointsInTalent(Talent.BLESS_ENHANCE));
				} else {
					defRoll *= 1.25f;
				}
			} else {
				defRoll *= 1.25f;
			}
		}
		if (defender.buff(  Hex.class) != null) defRoll *= 0.8f;
		if (defender.buff( Daze.class) != null) defRoll *= 0.5f;
		for (ChampionEnemy buff : defender.buffs(ChampionEnemy.class)){
			defRoll *= buff.evasionAndAccuracyFactor();
		}
		defRoll *= AscensionChallenge.statModifier(defender);

		return (acuRoll * accMulti) >= defRoll;
	}

	public int attackSkill( Char target ) {
		return 0;
	}

	public int defenseSkill( Char enemy ) {
		return 0;
	}

	public String defenseVerb() {
		return Messages.get(this, "def_verb");
	}

	public int drRoll() {
		int dr = 0;

		dr += Random.NormalIntRange( 0 , Barkskin.currentLevel(this) );

		return dr;
	}

	public int damageRoll() {
		return 1;
	}

	//TODO it would be nice to have a pre-armor and post-armor proc.
	// atm attack is always post-armor and defence is already pre-armor

	public int attackProc( Char enemy, int damage ) {
		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			buff.onAttackProc( enemy );
		}
		return damage;
	}

	public int defenseProc( Char enemy, int damage ) {

		Earthroot.Armor armor = buff( Earthroot.Armor.class );
		if (armor != null) {
			damage = armor.absorb( damage );
		}

		return damage;
	}

	public float speed() {
		float speed = baseSpeed;
		if ( buff( Cripple.class ) != null ) speed /= 2f;
		if ( buff( Stamina.class ) != null) speed *= 1.5f;
		if ( buff( Adrenaline.class ) != null) speed *= 2f;
		if ( buff( Haste.class ) != null) speed *= 3f;
		if ( buff( AngelWing.AngelWingBuff.class ) != null) speed *= 3f;
		if ( buff( Dread.class ) != null) speed *= 2f;
		return speed;
	}

	//currently only used by invisible chars, or by the hero
	public boolean canSurpriseAttack(){
		return true;
	}

	//used so that buffs(Shieldbuff.class) isn't called every time unnecessarily
	private int cachedShield = 0;
	public boolean needsShieldUpdate = true;

	public int shielding(){
		if (!needsShieldUpdate){
			return cachedShield;
		}

		cachedShield = 0;
		for (ShieldBuff s : buffs(ShieldBuff.class)){
			cachedShield += s.shielding();
		}
		needsShieldUpdate = false;
		return cachedShield;
	}

	public void damage( int dmg, Object src ) {

		if (!isAlive() || dmg < 0) {
			return;
		}

		if(isInvulnerable(src.getClass())){
			sprite.showStatus(CharSprite.POSITIVE, Messages.get(this, "invulnerable"));
			return;
		}

		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			dmg = (int) Math.ceil(dmg * buff.damageTakenFactor());
		}

		if (!(src instanceof LifeLink) && buff(LifeLink.class) != null){
			HashSet<LifeLink> links = buffs(LifeLink.class);
			for (LifeLink link : links.toArray(new LifeLink[0])){
				if (Actor.findById(link.object) == null){
					links.remove(link);
					link.detach();
				}
			}
			dmg = (int)Math.ceil(dmg / (float)(links.size()+1));
			for (LifeLink link : links){
				Char ch = (Char)Actor.findById(link.object);
				if (ch != null) {
					ch.damage(dmg, link);
					if (!ch.isAlive()) {
						link.detach();
					}
				}
			}
		}

		Terror t = buff(Terror.class);
		if (t != null){
			t.recover();
		}
		Dread d = buff(Dread.class);
		if (d != null){
			d.recover();
		}
		Charm c = buff(Charm.class);
		if (c != null){
			c.recover(src);
		}
		if (this.buff(Frost.class) != null){
			Buff.detach( this, Frost.class );
		}
		if (this.buff(MagicalSleep.class) != null){
			Buff.detach(this, MagicalSleep.class);
		}
		if (this.buff(Doom.class) != null && !isImmune(Doom.class)){
			dmg *= 1.67f;
		}
		if (alignment != Alignment.ALLY && this.buff(DeathMark.DeathMarkTracker.class) != null){
			dmg *= 1.25f;
		}

		Class<?> srcClass = src.getClass();
		if (isImmune( srcClass )) {
			dmg = 0;
		} else {
			dmg = Math.round( dmg * resist( srcClass ));
		}

		//TODO improve this when I have proper damage source logic
		if (AntiMagic.RESISTS.contains(src.getClass()) && buff(ArcaneArmor.class) != null){
			dmg -= Random.NormalIntRange(0, buff(ArcaneArmor.class).level());
			if (dmg < 0) dmg = 0;
		}

		if (buff(Sickle.HarvestBleedTracker.class) != null){
			if (isImmune(Bleeding.class)){
				sprite.showStatus(CharSprite.POSITIVE, Messages.titleCase(Messages.get(this, "immune")));
				buff(Sickle.HarvestBleedTracker.class).detach();
				return;
			}

			Bleeding b = buff(Bleeding.class);
			if (b == null){
				b = new Bleeding();
			}
			b.announced = false;
			b.set(dmg*buff(Sickle.HarvestBleedTracker.class).bleedFactor, Sickle.HarvestBleedTracker.class);
			b.attachTo(this);
			sprite.showStatus(CharSprite.WARNING, Messages.titleCase(b.name()) + " " + (int)b.level());
			buff(Sickle.HarvestBleedTracker.class).detach();
			return;
		}

		if (buff( Paralysis.class ) != null) {
			buff( Paralysis.class ).processDamage(dmg);
		}

		int shielded = dmg;
		//FIXME: when I add proper damage properties, should add an IGNORES_SHIELDS property to use here.
		if (!(src instanceof Hunger)){
			for (ShieldBuff s : buffs(ShieldBuff.class)){
				dmg = s.absorbDamage(dmg);
				if (dmg == 0) break;
			}
		}
		shielded -= dmg;
		HP -= dmg;

		if (HP > 0 && buff(Grim.GrimTracker.class) != null){

			float finalChance = buff(Grim.GrimTracker.class).maxChance;
			finalChance *= (float)Math.pow( ((HT - HP) / (float)HT), 2);

			if (Random.Float() < finalChance) {
				int extraDmg = Math.round(HP*resist(Grim.class));
				dmg += extraDmg;
				HP -= extraDmg;

				sprite.emitter().burst( ShadowParticle.UP, 5 );
				if (!isAlive() && buff(Grim.GrimTracker.class).qualifiesForBadge){
					Badges.validateGrimWeapon();
				}
			}
		}

		if (HP < 0 && src instanceof Char && alignment == Alignment.ENEMY){
			if (((Char) src).buff(Kinetic.KineticTracker.class) != null){
				int dmgToAdd = -HP;
				dmgToAdd -= ((Char) src).buff(Kinetic.KineticTracker.class).conservedDamage;
				dmgToAdd = Math.round(dmgToAdd * Weapon.Enchantment.genericProcChanceMultiplier((Char) src));
				if (dmgToAdd > 0) {
					Buff.affect((Char) src, Kinetic.ConservedDamage.class).setBonus(dmgToAdd);
				}
				((Char) src).buff(Kinetic.KineticTracker.class).detach();
			}
		}

		if (sprite != null) {
			sprite.showStatus(HP > HT / 2 ?
							CharSprite.WARNING :
							CharSprite.NEGATIVE,
					Integer.toString(dmg + shielded));
		}

		if (HP < 0) HP = 0;

		if (!isAlive()) {
			die( src );
		} else if (HP == 0 && buff(DeathMark.DeathMarkTracker.class) != null){
			DeathMark.processFearTheReaper(this);
		}
	}

	public void destroy() {
		HP = 0;
		Actor.remove( this );

		for (Char ch : Actor.chars().toArray(new Char[0])){
			if (ch.buff(Charm.class) != null && ch.buff(Charm.class).object == id()){
				ch.buff(Charm.class).detach();
			}
			if (ch.buff(Dread.class) != null && ch.buff(Dread.class).object == id()){
				ch.buff(Dread.class).detach();
			}
			if (ch.buff(Terror.class) != null && ch.buff(Terror.class).object == id()){
				ch.buff(Terror.class).detach();
			}
			if (ch.buff(SnipersMark.class) != null && ch.buff(SnipersMark.class).object == id()){
				ch.buff(SnipersMark.class).detach();
			}
			if (ch.buff(Talent.FollowupStrikeTracker.class) != null
					&& ch.buff(Talent.FollowupStrikeTracker.class).object == id()){
				ch.buff(Talent.FollowupStrikeTracker.class).detach();
			}
			if (ch.buff(Talent.DeadlyFollowupTracker.class) != null
					&& ch.buff(Talent.DeadlyFollowupTracker.class).object == id()){
				ch.buff(Talent.DeadlyFollowupTracker.class).detach();
			}
		}
	}

	public void die( Object src ) {
		destroy();
		if (src != Chasm.class) sprite.die();
	}

	//we cache this info to prevent having to call buff(...) in isAlive.
	//This is relevant because we call isAlive during drawing, which has both performance
	//and thread coordination implications
	public boolean deathMarked = false;

	public boolean isAlive() {
		return HP > 0 || deathMarked;
	}

	public boolean isActive() {
		return isAlive();
	}

	@Override
	protected void spendConstant(float time) {
		TimekeepersHourglass.timeFreeze freeze = buff(TimekeepersHourglass.timeFreeze.class);
		if (freeze != null) {
			freeze.processTime(time);
			return;
		}

		Swiftthistle.TimeBubble bubble = buff(Swiftthistle.TimeBubble.class);
		if (bubble != null){
			bubble.processTime(time);
			return;
		}

		super.spendConstant(time);
	}

	@Override
	protected void spend( float time ) {

		float timeScale = 1f;
		if (buff( Slow.class ) != null) {
			timeScale *= 0.5f;
			//slowed and chilled do not stack
		} else if (buff( Chill.class ) != null) {
			timeScale *= buff( Chill.class ).speedFactor();
		}
		if (buff( Speed.class ) != null) {
			timeScale *= 2.0f;
		}

		super.spend( time / timeScale );
	}

	public synchronized LinkedHashSet<Buff> buffs() {
		return new LinkedHashSet<>(buffs);
	}

	@SuppressWarnings("unchecked")
	//returns all buffs assignable from the given buff class
	public synchronized <T extends Buff> HashSet<T> buffs( Class<T> c ) {
		HashSet<T> filtered = new HashSet<>();
		for (Buff b : buffs) {
			if (c.isInstance( b )) {
				filtered.add( (T)b );
			}
		}
		return filtered;
	}

	@SuppressWarnings("unchecked")
	//returns an instance of the specific buff class, if it exists. Not just assignable
	public synchronized <T extends Buff> T buff( Class<T> c ) {
		for (Buff b : buffs) {
			if (b.getClass() == c) {
				return (T)b;
			}
		}
		return null;
	}

	public synchronized boolean isCharmedBy( Char ch ) {
		int chID = ch.id();
		for (Buff b : buffs) {
			if (b instanceof Charm && ((Charm)b).object == chID) {
				return true;
			}
		}
		return false;
	}

	public synchronized boolean add( Buff buff ) {

		if (buff(PotionOfCleansing.Cleanse.class) != null) { //cleansing buff
			if (buff.type == Buff.buffType.NEGATIVE
					&& !(buff instanceof AllyBuff)
					&& !(buff instanceof LostInventory)){
				return false;
			}
		}

		if (sprite != null && buff(Challenge.SpectatorFreeze.class) != null){
			return false; //can't add buffs while frozen and game is loaded
		}

		buffs.add( buff );
		if (Actor.chars().contains(this)) Actor.add( buff );

		if (sprite != null && buff.announced) {
			switch (buff.type) {
				case POSITIVE:
					sprite.showStatus(CharSprite.POSITIVE, Messages.titleCase(buff.name()));
					break;
				case NEGATIVE:
					sprite.showStatus(CharSprite.NEGATIVE, Messages.titleCase(buff.name()));
					break;
				case NEUTRAL:
				default:
					sprite.showStatus(CharSprite.NEUTRAL, Messages.titleCase(buff.name()));
					break;
			}
		}

		return true;

	}

	public synchronized boolean remove( Buff buff ) {
		buffs.remove( buff );
		Actor.remove( buff );

		return true;
	}

	public synchronized void remove( Class<? extends Buff> buffClass ) {
		for (Buff buff : buffs( buffClass )) {
			remove( buff );
		}
	}

	@Override
	protected synchronized void onRemove() {
		for (Buff buff : buffs.toArray(new Buff[buffs.size()])) {
			buff.detach();
		}
	}

	public synchronized void updateSpriteState() {
		for (Buff buff:buffs) {
			buff.fx( true );
		}
	}

	public float stealth() {
		return 0;
	}

	public final void move( int step ) {
		move( step, true );
	}

	//travelling may be false when a character is moving instantaneously, such as via teleportation
	public void move( int step, boolean travelling ) {

		if (travelling && Dungeon.level.adjacent( step, pos ) && buff( Vertigo.class ) != null) {
			sprite.interruptMotion();
			int newPos = pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
			if (!(Dungeon.level.passable[newPos] || Dungeon.level.avoid[newPos])
					|| (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[newPos])
					|| Actor.findChar( newPos ) != null)
				return;
			else {
				sprite.move(pos, newPos);
				step = newPos;
			}
		}

		if (Dungeon.level.map[pos] == Terrain.OPEN_DOOR) {
			Door.leave( pos );
		}

		pos = step;

		if (this != Dungeon.hero) {
			sprite.visible = Dungeon.level.heroFOV[pos];
		}

		Dungeon.level.occupyCell(this );
	}

	public int distance( Char other ) {
		return Dungeon.level.distance( pos, other.pos );
	}

	public boolean[] modifyPassable( boolean[] passable){
		//do nothing by default, but some chars can pass over terrain that others can't
		return passable;
	}

	public void onMotionComplete() {
		//Does nothing by default
		//The main actor thread already accounts for motion,
		// so calling next() here isn't necessary (see Actor.process)
	}

	public void onAttackComplete() {
		next();
	}

	public void onOperateComplete() {
		next();
	}

	protected final HashSet<Class> resistances = new HashSet<>();

	//returns percent effectiveness after resistances
	//TODO currently resistances reduce effectiveness by a static 50%, and do not stack.
	public float resist( Class effect ){
		HashSet<Class> resists = new HashSet<>(resistances);
		for (Property p : properties()){
			resists.addAll(p.resistances());
		}
		for (Buff b : buffs()){
			resists.addAll(b.resistances());
		}

		float result = 1f;
		for (Class c : resists){
			if (c.isAssignableFrom(effect)){
				result *= 0.5f;
			}
		}
		return result * RingOfElements.resist(this, effect);
	}

	protected final HashSet<Class> immunities = new HashSet<>();

	public boolean isImmune(Class effect ){
		HashSet<Class> immunes = new HashSet<>(immunities);
		for (Property p : properties()){
			immunes.addAll(p.immunities());
		}
		for (Buff b : buffs()){
			immunes.addAll(b.immunities());
		}

		for (Class c : immunes){
			if (c.isAssignableFrom(effect)){
				return true;
			}
		}
		return false;
	}

	//similar to isImmune, but only factors in damage.
	//Is used in AI decision-making
	public boolean isInvulnerable( Class effect ){
		return buff(Challenge.SpectatorFreeze.class) != null;
	}

	protected HashSet<Property> properties = new HashSet<>();

	public HashSet<Property> properties() {
		HashSet<Property> props = new HashSet<>(properties);
		//TODO any more of these and we should make it a property of the buff, like with resistances/immunities
		if (buff(ChampionEnemy.Giant.class) != null) {
			props.add(Property.LARGE);
		}
		return props;
	}

	public enum Property{
		BOSS ( new HashSet<Class>( Arrays.asList(Grim.class, GrimTrap.class, ScrollOfRetribution.class, ScrollOfPsionicBlast.class)),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class) )),
		MINIBOSS ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class) )),
		BOSS_MINION,
		UNDEAD,
		DEMONIC,
		INORGANIC ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(Bleeding.class, ToxicGas.class, Poison.class) )),
		FIERY ( new HashSet<Class>( Arrays.asList(WandOfFireblast.class, Elemental.FireElemental.class)),
				new HashSet<Class>( Arrays.asList(Burning.class, Blazing.class))),
		ICY ( new HashSet<Class>( Arrays.asList(WandOfFrost.class, Elemental.FrostElemental.class)),
				new HashSet<Class>( Arrays.asList(Frost.class, Chill.class))),
		ACIDIC ( new HashSet<Class>( Arrays.asList(Corrosion.class)),
				new HashSet<Class>( Arrays.asList(Ooze.class))),
		ELECTRIC ( new HashSet<Class>( Arrays.asList(WandOfLightning.class, Shocking.class, Potential.class, Electricity.class, ShockingDart.class, Elemental.ShockElemental.class )),
				new HashSet<Class>()),
		LARGE,
		IMMOVABLE;

		private HashSet<Class> resistances;
		private HashSet<Class> immunities;

		Property(){
			this(new HashSet<Class>(), new HashSet<Class>());
		}

		Property( HashSet<Class> resistances, HashSet<Class> immunities){
			this.resistances = resistances;
			this.immunities = immunities;
		}

		public HashSet<Class> resistances(){
			return new HashSet<>(resistances);
		}

		public HashSet<Class> immunities(){
			return new HashSet<>(immunities);
		}

	}

	public static boolean hasProp( Char ch, Property p){
		return (ch != null && ch.properties().contains(p));
	}

	public void heal(int amount) {
		amount = Math.min( amount, this.HT - this.HP );
		if (amount > 0 && this.isAlive()) {
			this.HP += amount;
			this.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
			this.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( amount ) );
		}
	}

	public void corrupt(Char attacker) {
		if (!this.isImmune(Corruption.class)
				&& this.buff(Corruption.class) == null
				&& this instanceof Mob
				&& this.isAlive()){

			Mob enemy = (Mob) this;
			Hero hero = (attacker instanceof Hero) ? (Hero) attacker : Dungeon.hero;

			Corruption.corruptionHeal(enemy);

			AllyBuff.affectAndLoot(enemy, hero, Corruption.class);
		}
	}
}
