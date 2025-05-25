package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.powers.RenwangdunPower;
import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Renwangdun extends CustomCard {
    public static final String ID = "Liuyanmod:Renwangdun";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Renwangdun_power.png";

    private static final int COST = 1;
    private static final int THRESHOLD = 6;
    private static final int UPGRADE_PLUS = 3;

    public Renwangdun() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER, EXAMPLE_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = THRESHOLD;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RenwangdunPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS);
            initializeDescription();
            this.isInnate=true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Renwangdun();
    }
}
