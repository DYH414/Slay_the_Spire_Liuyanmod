package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.powers.QinggangjianPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Qinggangjian extends CustomCard {
    public static final String ID = "Liuyanmod:Qinggangjian";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Qinggangjian.jpg";

    private static final int COST = 1;

    public Qinggangjian() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.POWER, EXAMPLE_GREEN,
                CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 无条件赋予能力
        addToBot(new ApplyPowerAction(p, p, new QinggangjianPower(p)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; // 使用升级后的描述
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Qinggangjian();
    }
}