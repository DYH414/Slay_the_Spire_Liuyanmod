package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Zhiheng extends CustomCard {
    public static final String ID = "Liuyanmod:Zhiheng";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Zhiheng_skill.png"; // 需创建对应图片

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int DRAW_BASE = 0; // 动态 X 张
    private static final int UPGRADE_PLUS_DRAW = 1;

    public Zhiheng() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, EXAMPLE_GREEN, RARITY, TARGET);
        this.baseMagicNumber = DRAW_BASE;
        this.magicNumber = DRAW_BASE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = p.hand.group.size(); // X 为当前手牌数量，玩家选择弃置的张数
        if (x > 0) {
            addToBot(new DiscardAction(p, p, x, false)); // 弃置 X 张牌
            addToBot(new DrawCardAction(p, this.magicNumber + x)); // 摸 X 张（升级后 X+1）
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Zhiheng();
    }
}