package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Yewang extends CustomCard {
    public static final String ID = "Liuyanmod:TusheYewang";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/TusheYewang.png";

    private static final int COST = 0;
    private static final int SCRY = 2;
    private static final int SCRY_UPGRADE = 1;

    public Yewang() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.SKILL, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = SCRY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScryAction(this.magicNumber));
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(SCRY_UPGRADE); // 预见数+1
            this.initializeDescription();
        }
    }
     public AbstractCard makeCopy() {
        return new Yewang();
    }
}
