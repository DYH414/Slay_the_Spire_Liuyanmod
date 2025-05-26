package Liuyanmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class NoBlockThisTurnPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:NoBlockThisTurnPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public NoBlockThisTurnPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.loadRegion("barricade"); // 或者自定义贴图
        this.updateDescription();
    }

    @Override
    public float modifyBlock(float blockAmount) {
        // 直接将所有格挡修改为0
        if (blockAmount > 0) {
            flash();
            return 0.0f;
        }
        return blockAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]; // 写一句类似 "本回合无法获得格挡"
    }
}