package Liuyanmod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class LeBuSiShuPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:LeBuSiShuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final float STUN_CHANCE = 0.75f; // 75%概率

    public LeBuSiShuPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true; // 设置为回合制，1回合后自动移除
        this.loadRegion("confusion"); // 使用已有的图标资源
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        // 在怪物回合开始时进行判定
        if (owner instanceof AbstractMonster && !((AbstractMonster) owner).isDeadOrEscaped()) {
            if (AbstractDungeon.miscRng.randomBoolean(STUN_CHANCE)) {
                flash();
                // 使目标进入晕眩状态
                addToBot(new StunMonsterAction((AbstractMonster) owner, owner));
                // 提示命中
                AbstractDungeon.effectList.add(new ThoughtBubble(
                        AbstractDungeon.player.dialogX,
                        AbstractDungeon.player.dialogY,
                        2.0f,
                        "乐不思蜀命中！",
                        true
                ));
            } else {
                // 提示未命中
                AbstractDungeon.effectList.add(new ThoughtBubble(
                        AbstractDungeon.player.dialogX,
                        AbstractDungeon.player.dialogY,
                        2.0f,
                        "乐不思蜀未命中！",
                        true
                ));
            }
        }
        // 移除能力
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
