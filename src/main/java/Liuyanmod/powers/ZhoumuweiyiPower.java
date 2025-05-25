package Liuyanmod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.*;

public class ZhoumuweiyiPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:ZhoumuweiyiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int skillCounter = 0;

    public ZhoumuweiyiPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("wraithForm"); // 可替换成其他图标
        updateDescription();
    }
    @Override
    public void atStartOfTurn() {
        skillCounter = 0;
    }
    @Override
    public void onUseCard(AbstractCard card, com.megacrit.cardcrawl.actions.utility.UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            skillCounter++;
            if (skillCounter >= 4) {
                skillCounter = 0;
                flash();
                addToBot(new GainEnergyAction(1));
                addToBot(new GainBlockAction(owner, owner, 5));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
