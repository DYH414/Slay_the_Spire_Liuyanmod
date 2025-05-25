package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
    public static final String IMG = "Liuyan/img/cards/Yubojiaofeng_attack.png";

    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    public YuboJiaofeng() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成伤害
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        System.out.println("YuboJiaofeng dealt " + this.damage + " damage to " + (m != null ? m.name : "null"));


        // 选择保留手牌
        if (!p.hand.isEmpty()) {
            this.addToBot(new SelectCardToRetainAction(1));
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

                AbstractDungeon.gridSelectScreen.open(validCards, this.amount, "选择一张手牌保留至下回合", false, false, false, false);
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
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YuboJiaofeng();
    }
}