package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import java.util.HashSet;
import java.util.Set;

public class TiesuoPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:TiesuoPower";
    private static final PowerStrings powerStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // 用于记录当前攻击波次中已传播的生物
    private static final Set<AbstractCreature> linkedThisChain = new HashSet<>();

    public TiesuoPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        String path128 = "Liuyan/img/powers/TiesuolianhuanPower84.png";
        String path48 = "Liuyan/img/powers/TiesuolianhuanPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }


    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        // 只对非传导伤害（THORNS）做处理
        if (damageAmount > 0 && info.owner != null && info.type != DamageInfo.DamageType.THORNS) {
            // 新一波原始伤害到来：清空记录
            linkedThisChain.clear();

            // 如果自己还没被记录，就传播
            if (!linkedThisChain.contains(owner)) {
                linkedThisChain.add(owner);

                for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                    if (mo != owner && mo.hasPower(POWER_ID) && !mo.isDeadOrEscaped()) {
                        flash();
                        addToBot(new DamageAction(
                                mo,
                                new DamageInfo(info.owner, damageAmount, DamageInfo.DamageType.THORNS),
                                AbstractGameAction.AttackEffect.FIRE
                        ));
                    }
                }
            }
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
