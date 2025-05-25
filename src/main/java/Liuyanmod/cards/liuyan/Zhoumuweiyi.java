package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import Liuyanmod.powers.ZhoumuweiyiPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Zhoumuweiyi extends CustomCard {
    public static final String ID = "Liuyanmod:Zhoumuweiyi";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMG = "Liuyan/img/cards/Zhoumuweiyi_power.png";
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = EXAMPLE_GREEN;

    private static final int COST = 1;

    public Zhoumuweiyi() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(
                p, p, new ZhoumuweiyiPower(p), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
    public AbstractCard makeCopy() {
        return new Zhoumuweiyi();
    }
}
