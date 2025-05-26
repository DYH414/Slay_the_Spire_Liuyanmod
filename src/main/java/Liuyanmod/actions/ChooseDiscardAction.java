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
        this.actionType = ActionType.DISCARD;
    }

    @Override
    public void update() {
        // 第一步：打开选择界面
        if (!this.screenOpened) {
            if (this.maxDiscard <= 0) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(
                    "选择要弃置的牌",
                    this.maxDiscard,
                    true,  // 允许选择 0 到 maxDiscard 张牌
                    true,  // 允许选择 0 张牌
                    false,
                    false,
                    true   // 允许选择 0 到 maxDiscard 的任意数量
            );
            this.screenOpened = true;
            this.tickDuration();
            return;
        }

        // 第二步：检查选择是否完成
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) { // 改为检查是否仍在选择
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
            int drawAmount = discardedAmount + this.bonusDrawAmount;
            if (drawAmount > 0) {
                addToBot(new DrawCardAction(AbstractDungeon.player, drawAmount)); // 使用 addToBot
            }

            // 清理并结束
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }
}