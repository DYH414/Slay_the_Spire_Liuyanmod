package Liuyanmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

import java.util.ArrayList;

public class DiscardForEnergyAction extends AbstractGameAction {
    private AbstractPlayer player;

    public DiscardForEnergyAction() {
        this.player = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(
                    "选择要弃置的卡牌（每2张获得1点能量）",
                    player.hand.size(),
                    true, true
            );
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            ArrayList<AbstractCard> selected = AbstractDungeon.handCardSelectScreen.selectedCards.group;

            int count = selected.size();
            for (AbstractCard card : selected) {
                player.hand.moveToDiscardPile(card);
                card.triggerOnManualDiscard();
            }

            int energyGain = count / 2;
            if (energyGain > 0) {
                this.addToTop(new GainEnergyAction(energyGain));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            selected.clear();
        }

        this.tickDuration();
    }
}
