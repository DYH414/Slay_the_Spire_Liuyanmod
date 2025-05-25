package Liuyanmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QinglongyanyuedaoPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = "Liuyanmod:QinglongyanyuedaoPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean hasTriggered = false;
    private int cardsSetToZero = 0;

    public QinglongyanyuedaoPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("accuracy");
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.hasTriggered = false;
        this.cardsSetToZero = 0;
        updateDescription();
    }

    @Override
    public void onUseCard(final AbstractCard card, final com.megacrit.cardcrawl.actions.utility.UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && !this.hasTriggered) {
            this.hasTriggered = true;
            int count = 0;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c != card && c.type == AbstractCard.CardType.ATTACK && c.costForTurn > 0 && this.cardsSetToZero < this.amount) {
                    c.setCostForTurn(0);
                    c.isCostModified = true;
                    c.superFlash();
                    c.applyPowers();
                    System.out.println("QinglongyanyuedaoPower set hand card " + c.name + " to 0 cost");
                    this.cardsSetToZero++;
                    count++;
                }
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
            this.flash();
            System.out.println("QinglongyanyuedaoPower triggered, set " + count + " cards to 0 cost, total: " + this.cardsSetToZero);
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (this.hasTriggered && this.cardsSetToZero < this.amount && card.type == AbstractCard.CardType.ATTACK && card.costForTurn > 0) {
            card.setCostForTurn(0);
            card.isCostModified = true;
            card.superFlash();
            card.applyPowers();
            this.cardsSetToZero++;
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
            System.out.println("QinglongyanyuedaoPower set drawn card " + card.name + " to 0 cost, total: " + this.cardsSetToZero);
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new QinglongyanyuedaoPower(owner, amount);
    }
}