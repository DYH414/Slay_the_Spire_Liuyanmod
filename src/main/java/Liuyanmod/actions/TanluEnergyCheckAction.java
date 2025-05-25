package Liuyanmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import java.util.ArrayList;

public class TanluEnergyCheckAction extends AbstractGameAction {
    @Override
    public void update() {
        ArrayList<AbstractCard> drawn = DrawCardAction.drawnCards;
        int attackCount = 0;
        for (AbstractCard c : drawn) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                attackCount++;
            }
        }
        if (attackCount > 0) {
            this.addToTop(new GainEnergyAction(attackCount));
        }
        this.isDone = true;
    }
}
