package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class YuboJiaofeng extends CustomCard {
    public static final String ID = "Liuyanmod:YuboJiaofeng";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Yubojiaofeng_attack.png"; // 改为技能卡图片

    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL; // 改为技能卡
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF; // 改为自我目标
    private static final int BLOCK = 7; // 格挡值
    private static final int UPGRADE_PLUS_BLOCK = 3; // 升级增加格挡
    private static final int RETAIN_AMOUNT = 1; // 保留卡牌数量
    private static final int UPGRADE_PLUS_RETAIN = 1; // 升级增加保留数量

    public YuboJiaofeng() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = RETAIN_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得格挡
        this.addToBot(new GainBlockAction(p, p, this.block));
        System.out.println("YuboJiaofeng gained " + this.block + " block");

        // 选择保留手牌
        if (!p.hand.isEmpty()) {
            this.addToBot(new SelectCardToRetainAction(this.magicNumber));
        } else {
            System.out.println("YuboJiaofeng: No cards in hand to retain");
        }
    }

    // 自定义动作处理手牌选择
    private static class SelectCardToRetainAction extends AbstractGameAction {
        private final int amount;

        public SelectCardToRetainAction(int amount) {
            this.amount = amount;
            this.actionType = ActionType.CARD_MANIPULATION;
            this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
        }

        @Override
        public void update() {
            if (this.isDone) {
                return;
            }

            AbstractPlayer p = AbstractDungeon.player;
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : p.hand.group) {
                    validCards.addToTop(c);
                }

                String message = this.amount == 1 ? "选择一张手牌保留至下回合" : "选择 " + this.amount + " 张手牌保留至下回合";
                AbstractDungeon.gridSelectScreen.open(validCards, this.amount, message, false, false, false, false);
            } else {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    c.retain = true;
                    c.flash();
                    System.out.println("YuboJiaofeng retained card: " + c.name);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone = true;
            }

            this.tickDuration();
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_RETAIN);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YuboJiaofeng();
    }
}