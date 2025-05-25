package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ShiruPozhu extends CustomCard {
    public static final String ID = "Liuyanmod:ShiruPozhu";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Shirupozhu_attack.jpg";

    public ShiruPozhu() {
        super(ID, NAME, IMG, -1, DESCRIPTION,
                CardType.SKILL,
                EXAMPLE_GREEN,
                CardRarity.RARE,
                CardTarget.SELF);


        this.exhaust = true; // 消耗特性
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int xValue = this.energyOnUse;

        // 动态计算割据层数
        int gejuAmount = upgraded ? (xValue - 1) : xValue;
        if(gejuAmount > 0){
            addToBot(new ApplyPowerAction(p, p,
                new GejuPower(p, gejuAmount), gejuAmount));
        }

        // 能量获取
        if(xValue > 0){
            p.gainEnergy(2 * xValue);
        }

        // 抽牌
        if(xValue > 0){
            AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.DrawCardAction(p,  xValue));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShiruPozhu();
    }
}
