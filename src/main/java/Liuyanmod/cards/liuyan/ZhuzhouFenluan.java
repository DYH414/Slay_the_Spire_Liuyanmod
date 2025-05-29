// Liuyanmod/cards/liuyan/ZhuzhouFenluan.java
package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.DoubleEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ZhuzhouFenluan extends CustomCard {
    public static final String ID = "Liuyanmod:ZhuzhouFenluan";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Zhuzhoufenluan_skill.jpg";

    private static final int COST = 1;

    public ZhuzhouFenluan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL,
                EXAMPLE_GREEN,
                CardRarity.UNCOMMON,
                CardTarget.SELF);

        this.exhaust = true; // 消耗属性
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得1层割据
        this.addToBot(new ApplyPowerAction(p, p, new GejuPower(p, 2)));



        // 获得双倍当前能量
        this.addToBot(new DoubleEnergyAction());
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0); // 升级后变为0费
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ZhuzhouFenluan();
    }
}
