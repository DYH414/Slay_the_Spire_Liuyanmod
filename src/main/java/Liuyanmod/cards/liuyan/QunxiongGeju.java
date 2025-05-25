package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.QunxiongGejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class QunxiongGeju extends CustomCard {
    public static final String ID = "Liuyanmod:QunxiongGeju";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Qunxionggehjv_power.png";

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public QunxiongGeju() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.POWER,
                EXAMPLE_GREEN,
                CardRarity.RARE,
                CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new QunxiongGejuPower(p)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new QunxiongGeju();
    }
}
