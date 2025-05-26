package Liuyanmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChooseDiscardAction extends AbstractGameAction {
    private int maxDiscard;
    private int bonusDrawAmount;
    private boolean screenOpened = false;

    public ChooseDiscardAction(int maxDiscard, int bonusDrawAmount) {
        this.maxDiscard = maxDiscard;
        this.bonusDrawAmount = bonusDrawAmount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        // 第一步：打开选择界面
        if (!this.screenOpened) {
            AbstractDungeon.handCardSelectScreen.open(
                    "选择要弃置的牌",
                    this.maxDiscard,
                    true, // 可以选择0张
                    false
            );
            this.screenOpened = true;
            this.tickDuration();
            return;
        }

        // 第二步：等待选择完成并处理结果
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            // 获取选择的牌数量
            int discardedAmount = AbstractDungeon.handCardSelectScreen.selectedCards.group.size();

            // 手动将选择的牌移动到弃牌堆
            if (discardedAmount > 0) {
                for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToDiscardPile(card);
                    card.triggerOnManualDiscard();
                }
            }

            // 摸牌
            if (discardedAmount + this.bonusDrawAmount > 0) {
                addToBot(new DrawCardAction(AbstractDungeon.player, discardedAmount + this.bonusDrawAmount));
            }

            // 标记已处理并清理
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}