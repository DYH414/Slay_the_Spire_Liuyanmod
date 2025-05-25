package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ZhuoshaoPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:ZhuoshaoPower";
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public ZhuoshaoPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true; // 标记为回合性能力，随回合减少
        this.loadRegion("flameBarrier");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }

    // 当持有者受到一次伤害后，触发额外灼烧伤害
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0
                && info.owner != this.owner
                && info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(
                            this.owner,
                            new DamageInfo(info.owner, this.amount, DamageInfo.DamageType.HP_LOSS),
                            AbstractGameAction.AttackEffect.FIRE)
            );
        }
        return damageAmount;
    }

    // 每回合结束时减少1层
    @Override
    public void atEndOfRound() {
        if (this.amount <= 0) {
            return;
        }
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }
}