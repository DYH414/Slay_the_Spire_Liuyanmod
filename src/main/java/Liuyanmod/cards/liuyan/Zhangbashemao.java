package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.ZhangbashemaoPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Zhangbashemao extends CustomCard {
    public static final String ID = "Liuyanmod:Zhangbashemao";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG_PATH = "Liuyan/img/cards/Zhangbashemao.jpg";
    private static final int COST = 3;

    public Zhangbashemao() {
        super(ID, CARD_STRINGS.NAME, IMG_PATH, COST,
                CARD_STRINGS.DESCRIPTION, CardType.POWER,
                EXAMPLE_GREEN, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("ZHANGBASHEMAO_1");
        CardCrawlGame.sound.play("ZHANGBASHEMAO_2");
        addToBot(new ApplyPowerAction(p, p, new ZhangbashemaoPower(p), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(2);
            this.isInnate = true;
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
    public AbstractCard makeCopy() {
        return new Zhangbashemao();
    }
}
