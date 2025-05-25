package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.LeBuSiShuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class LeBuSiShu extends CustomCard {
    public static final String ID = "Liuyanmod:LeBuSiShu";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Lebusishu_skill.png"; // 需要实际图片路径

    private static final int COST = 1;
    private static final int POWER_AMOUNT = 1;

    public LeBuSiShu() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.SKILL,
                EXAMPLE_GREEN,
                CardRarity.RARE,
                CardTarget.ENEMY);
        this.exhaust = true;
        this.baseMagicNumber = POWER_AMOUNT;
        this.magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p,
            new LeBuSiShuPower(m), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LeBuSiShu();
    }
}
