package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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

    // 是否永久保存（被"借东风"影响）
    public boolean isPermanent = false;

    public ZhuoshaoPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false; // 改为false，手动控制移除
        this.loadRegion("flameBarrier");

        // 检查玩家是否有借东风能力，如果有则设为永久
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(JiedongfengPower.POWER_ID)) {
            this.isPermanent = true;
        }

        updateDescription();
    }

    // 构造函数重载，允许直接指定是否永久
    public ZhuoshaoPower(AbstractCreature owner, int amount, boolean permanent) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        this.isPermanent = permanent;
        this.loadRegion("flameBarrier");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (isPermanent) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[3]; // 永久版本描述
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2]; // 临时版本描述
        }
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

    // 每回合结束时，如果不是永久的，则移除这个power
    @Override
    public void atEndOfRound() {
        if (!isPermanent) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    // 设置为永久保存
    public void makePermanent() {
        this.isPermanent = true;
        updateDescription();
    }
}