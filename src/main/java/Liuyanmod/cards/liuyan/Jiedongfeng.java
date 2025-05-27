package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.powers.JiedongfengPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Jiedongfeng extends CustomCard {
    public static final String ID = "Liuyanmod:Jiedongfeng";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Jiedongfeng_power.png";

    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MULTIPLIER = 2;
    private static final int UPGRADE_MULTIPLIER = 3;

    public Jiedongfeng() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MULTIPLIER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("JIEDONGFENG");
        this.addToBot(new ApplyPowerAction(p, p,
                new JiedongfengPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1); // 从2倍变成3倍
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Jiedongfeng();
    }
}