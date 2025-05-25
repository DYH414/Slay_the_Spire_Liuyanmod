package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import Liuyanmod.powers.ZhuqueYushanPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ZhuqueYushan extends CustomCard {
    public static final String ID = "Liuyanmod:ZhuqueYushan";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Zhuqueyushan_power.png";

    private static final int COST = 1;
    private static final int BURN = 1;

    public ZhuqueYushan() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.POWER, EXAMPLE_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = BURN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 给自己上被动 Power，而不是直接给敌人灼烧
        this.addToBot(new ApplyPowerAction(p, p,
                new ZhuqueYushanPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);            // 升级后 BURN = 2
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ZhuqueYushan();
    }
}
