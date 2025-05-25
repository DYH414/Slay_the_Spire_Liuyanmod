package Liuyanmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class XuezhaoAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final int magicNumber;
    private boolean selectScreenOpen;

    public XuezhaoAction(AbstractPlayer p, int magicNumber) {
        this.p = p;
        this.magicNumber = magicNumber;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.selectScreenOpen = false;
    }

    @Override
    public void update() {
        // 如果选择界面已经打开
        if (this.selectScreenOpen) {
            // 检查是否有选择的卡牌
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                    // 将卡牌设置费用为0并加入手牌
                    card.setCostForTurn(0);
                    p.hand.addToHand(card);
                    p.discardPile.removeCard(card);
                }

                // 清空选择
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.selectScreenOpen = false;
                this.isDone = true;
                return;
            }
            return;
        }

        // 从弃牌堆筛选攻击牌
        CardGroup attackCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : p.discardPile.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                attackCards.addToTop(c);
            }
        }

        // 如果没有攻击牌
        if (attackCards.isEmpty()) {
            this.isDone = true;
            return;
        }

        // 如果攻击牌数量少于需要选择的数量，直接全部加入
        if (attackCards.size() <= this.magicNumber) {
            for (AbstractCard card : attackCards.group) {
                card.setCostForTurn(0);
                p.hand.addToHand(card);
                p.discardPile.removeCard(card);
            }
            this.isDone = true;
            return;
        }

        // 打开选择界面
        AbstractDungeon.gridSelectScreen.open(
                attackCards,
                this.magicNumber,
                true,
                "选择要加入手牌的攻击牌"
        );

        // 标记选择界面已打开
        this.selectScreenOpen = true;
    }
}