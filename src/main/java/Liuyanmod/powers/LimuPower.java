// Liuyanmod.powers.NextTurnEntanglePower.java
package Liuyanmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class LimuPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:LimuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public LimuPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.loadRegion("blur"); // 或者你可以使用自定义贴图
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(owner, owner, this.ID));
        this.addToBot(new ApplyPowerAction(owner, owner, new EntanglePower(owner)));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]; // 在 power.json 中设置描述
    }
}
