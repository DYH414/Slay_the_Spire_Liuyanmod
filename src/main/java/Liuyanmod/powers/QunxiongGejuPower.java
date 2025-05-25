package Liuyanmod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QunxiongGejuPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:QunxiongGejuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public QunxiongGejuPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1; // 不需要数量
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.loadRegion("burst"); // 使用原版图标（可替换为自定义图标）
        this.updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK && owner.hasPower(GejuPower.POWER_ID) && owner.getPower(GejuPower.POWER_ID).amount >= 2) {
            this.flash();
            int gejuAmount = owner.getPower(GejuPower.POWER_ID).amount;
            addToBot(new GainEnergyAction(gejuAmount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]; // 在 power.json 中设置描述
    }
}
