package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Regeneration;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Rapier;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class SkillBook extends  Artifact {
    {
        image = ItemSpriteSheet.ARTIFACT_SKILLBOOK;

        levelCap = 10;

        charge = chargeCap + level() / 2;
        partialCharge = 0;
        chargeCap = chargeCap + level() / 2;

        defaultAction = AC_CAST;
    }

    public static final String AC_CHOOSE = "CHOOSE";
    public static final String AC_CAST = "CAST";
    public int SkillChosen = 0;
    public int CurrentCharge = 0;

    public int chargeCap() {
        return chargeCap;
    }

    public String SkillName(int SkillChosen) {
        switch (SkillChosen) {//武技全都必中
            default:
                return "旋风斩";//一回合A两下，每下为20%真伤+25%物理 1充能
            case 1:
                return "寄鹰斩";//一回合内向前移动一格，A一下，80%伤害 2充能
            case 2:
                return "飞渡浮舟";//二回合，A五下，每下10%真伤+10%物理 2充能
            case 3:
                return "寄鹰斩·反向回旋";//一回合内A一下，80%伤害，向后退一格 2充能
            case 4:
                return "叩拜连击拳·破魔";//二回合内A三下，分别为5~15、5~15和lvl/2~lvl伤害,不触发附魔，可触发武力之戒 2充能
            case 5:
                return "苇名一文字";//一回合内A一下，110%+lvl伤害 1充能
            case 6:
                return "苇名一文字·二连";//1.5回合内A两下，分别为110%+lvl和60%+lvl伤害 2充能
            case 7:
                return "苇名十文字";//一回合内移动最多三格，A两次，均为40%真实+70%物理伤害 3充能
            case 8:
                return "仙峰寺菩萨脚";//2.5回合内A六下，第一下lvl/2~lvl伤害，造成一回合麻痹；第二三下6~20伤害，第四五下70%物理伤害，第六下lvl~2*lvl伤害，造成一回合麻痹 3充能
            case 9:
                return "巨型忍者突刺";//一回合内移动最多7格，A一下100%物理伤害 3充能
            case 10:
                return "不死斩";//三回合A二下，每下造成100%真伤+100%物理 5充能
        }
    }

    @Override
    public Item upgrade() {
        chargeCap = Math.min(chargeCap + 1, 10);
        return super.upgrade();
    }
    @Override
    public String desc() {
        String desc = super.desc();

        if (isEquipped(Dungeon.hero)) {
            desc += "\n\n";
            if (cursed)
                desc += Messages.get(this, "desc_cursed");
            else
                desc += Messages.get(this, "desc_equipped", SkillName(SkillChosen));
        }
        return desc;
    }


    public void Skill(Hero hero, Integer target, Integer SkillNum) {
        KindOfWeapon wep = hero.belongings.weapon;
        hero.belongings.abilityWeapon = wep;
        if (target == null) {
        return;
    }
        Char enemy = Actor.findChar(target);
        if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
            GLog.w(Messages.get(this, "ability_no_target"));
            return;
        }
        switch (SkillNum) {//武技全都必中
            default:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                hero.belongings.abilityWeapon = null;
                hero.sprite.attack(enemy.pos, new Callback() {
                    @Override
                    public void call() {
                        if (hero.attack(enemy, 0.5f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        if (hero.attack(enemy, 0.5f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        Invisibility.dispel();
                        hero.spendAndNext(hero.attackDelay());
                        //CurrentCharge -= 1;
                        upgrade();
                    }
                });
                //return "旋风斩";//一回合A两下，每下为20%真伤+25%物理 1充能//50%伤害
            case 1:
                if (hero.rooted || Dungeon.level.distance(hero.pos, target) < 2
                        || Dungeon.level.distance(hero.pos, target)-1 > wep.reachFactor(hero)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    if (hero.rooted) PixelScene.shake( 1, 1f );
                    return;
                }

                int lungeCell = -1;
                for (int i : PathFinder.NEIGHBOURS8){
                    if (Dungeon.level.distance(hero.pos+i, target) <= wep.reachFactor(hero)
                            && Actor.findChar(hero.pos+i) == null
                            && (Dungeon.level.passable[hero.pos+i] || (Dungeon.level.avoid[hero.pos+i] && hero.flying))){
                        if (lungeCell == -1 || Dungeon.level.trueDistance(hero.pos + i, target) < Dungeon.level.trueDistance(lungeCell, target)){
                            lungeCell = hero.pos + i;
                        }
                    }
                }

                if (lungeCell == -1){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    return;
                }

                final int dest = lungeCell;
                hero.busy();
                Sample.INSTANCE.play(Assets.Sounds.MISS);
                hero.sprite.jump(hero.pos, dest, 0, 0.1f, new Callback() {
                    @Override
                    public void call() {
                        if (Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
                            Door.leave( hero.pos );
                        }
                        hero.pos = dest;
                        Dungeon.level.occupyCell(hero);
                        Dungeon.observe();

                        if (enemy != null && hero.canAttack(enemy)) {
                            hero.sprite.attack(enemy.pos, new Callback() {
                                @Override
                                public void call() {
                                    AttackIndicator.target(enemy);
                                    if (hero.attack(enemy, 0.8f, 0, Char.INFINITE_ACCURACY)) {
                                        Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                                    }
                                    Invisibility.dispel();
                                    hero.spendAndNext(hero.attackDelay());
                                    CurrentCharge -= 2;
                                }
                            });
                        }
                        else {
                            GLog.w(Messages.get(this, "ability_no_target"));
                            hero.spendAndNext(hero.speed());
                            CurrentCharge -= 2;
                        }
                    }
                });
            case 2:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                hero.belongings.abilityWeapon = null;
                hero.sprite.attack(enemy.pos, new Callback() {
                    @Override
                    public void call() {
                        if (hero.attack(enemy, 0.1f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        if (hero.attack(enemy, 0.15f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        if (hero.attack(enemy, 0.2f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        if (hero.attack(enemy, 0.25f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        if (hero.attack(enemy, 0.5f, 0, Char.INFINITE_ACCURACY)){
                            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                        }
                        Invisibility.dispel();
                        hero.spendAndNext(hero.attackDelay()*2);
                        CurrentCharge -= 2;
                    }
                });//二回合，A五下，每下10%真伤+10%物理 2充能
/*            case 3:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                return "寄鹰斩·反向回旋";//一回合内A一下，80%伤害，向后退一格 2充能
            case 4:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                return "叩拜连击拳·破魔";//二回合内A三下，分别为5~15、5~15和lvl/2~lvl伤害,不触发附魔，可触发武力之戒 2充能
            case 5:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                return "苇名一文字";//一回合内A一下，110%+lvl伤害 1充能
            case 6:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                return "苇名一文字·二连";//1.5回合内A两下，分别为110%+lvl和60%+lvl伤害 2充能
            case 7:
                return "苇名十文字";//一回合内移动最多三格，A两次，均为40%真实+70%物理伤害 3充能
            case 8:
                if (!hero.canAttack(enemy)){
                    GLog.w(Messages.get(this, "ability_bad_position"));
                    hero.belongings.abilityWeapon = null;
                    return;
                }
                return "仙峰寺菩萨脚";//2.5回合内A六下，第一下lvl/2~lvl伤害，造成一回合麻痹；第二三下6~20伤害，第四五下70%物理伤害，第六下lvl~2*lvl伤害，造成一回合麻痹 3充能
            case 9:
                return "巨型忍者突刺";//一回合内移动最多7格，A一下100%物理伤害 3充能
            case 10:if (!hero.canAttack(enemy)){
                GLog.w(Messages.get(this, "ability_bad_position"));
                hero.belongings.abilityWeapon = null;
                return;
            }
                return "不死斩";//三回合A二下，每下造成100%真伤+100%物理 5充能*/
        }
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        if (isEquipped(hero)
                && !cursed) {
            actions.add(AC_CHOOSE);
            actions.add(AC_CAST);
        }
        return actions;
    }

    public String targetingPrompt() {
        return null;
    }

    public boolean useTargeting() {
        return targetingPrompt() != null;
    }

    @Override
    public void execute(Hero hero, String action) {

        super.execute(hero, action);

        if (action.equals(AC_CHOOSE)) {
            if (!isEquipped(hero)) GLog.i(Messages.get(this, "need_to_equip"));
            else if (cursed) GLog.w(Messages.get(this, "cursed"));
            else {
                String[] options;
                if (level() == 0) {
                    options = new String[]{
                            "旋风斩"
                    };
                } else if (level() == 1) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩"
                    };
                } else if (level() == 2) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟"
                    };
                } else if (level() == 3) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋"
                    };
                } else if (level() == 4) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔"
                    };
                } else if (level() == 5) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔",
                            "苇名一文字"
                    };
                } else if (level() == 6) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔",
                            "苇名一文字",
                            "苇名一文字·二连"
                    };
                } else if (level() == 7) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔",
                            "苇名一文字",
                            "苇名一文字·二连",
                            "苇名十文字"
                    };
                } else if (level() == 8) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔",
                            "苇名一文字",
                            "苇名一文字·二连",
                            "苇名十文字",
                            "仙峰寺菩萨脚"
                    };
                } else if (level() == 9) {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔",
                            "苇名一文字",
                            "苇名一文字·二连",
                            "苇名十文字",
                            "仙峰寺菩萨脚",
                            "巨型忍者突刺"
                    };
                } else {
                    options = new String[]{
                            "旋风斩",
                            "寄鹰斩",
                            "飞渡浮舟",
                            "寄鹰斩·反向回旋",
                            "叩拜连击拳·破魔",
                            "苇名一文字",
                            "苇名一文字·二连",
                            "苇名十文字",
                            "仙峰寺菩萨脚",
                            "巨型忍者突刺",
                            "不死斩"
                    };
                }
                GameScene.show(new WndOptions(new ItemSprite(image),
                        Messages.titleCase(name()),
                        Messages.get(this, "select"),
                        options) {
                    @Override
                    protected void onSelect(int index) {
                        super.onSelect(index);
                        SkillChosen = index;
                        GLog.w("你现在的武技为" + SkillName(index));
                    }
                });
            }
        }
        else if (action.equals(AC_CAST)) {
            if (!isEquipped(hero)) GLog.i(Messages.get(this, "need_to_equip"));
            else if (cursed) GLog.w(Messages.get(this, "cursed"));
            else {
                usesTargeting = useTargeting();
                GameScene.selectCell(new CellSelector.Listener() {
                    @Override
                    public void onSelect(Integer cell) {
                        if (cell != null) {
                            Skill(hero, cell, SkillChosen);
                            updateQuickslot();
                        }
                    }

                    @Override
                    public String prompt() {
                        return targetingPrompt();
                    }
                });
            }

            updateQuickslot();
        }
    }
    @Override
    public void charge(Hero target, float amount) {
        if (cursed || target.buff(MagicImmune.class) != null) return;

        if (charge < chargeCap) {
            if (!isEquipped(target)) amount *= 0.75f*target.pointsInTalent(Talent.LIGHT_CLOAK)/3f;
            partialCharge += 0.25f*amount;
            if (partialCharge >= 1){
                partialCharge--;
                charge++;
                updateQuickslot();
            }
        }
    }

}
