package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import Liuyanmod.powers.LimuPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Gengshou extends CustomCard {
    public static final String ID = "Liuyanmod:Gengshou";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Gnegshou_skill.jpg";

    private static final int COST = 1;
    private static final int BASE_GOLD = 10;
    private static final int UPGRADE_GOLD_BONUS = 5;

    public Gengshou() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = BASE_GOLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int goldAmount = this.magicNumber;
        // 如果已有立牧
        AbstractPower limu = p.getPower(LimuPower.POWER_ID);
        if (limu != null) {
            goldAmount += 10;
        }
        // 获得金币
        AbstractDungeon.actionManager.addToBottom(new GainGoldAction(goldAmount));
        // 赋予立牧
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LimuPower(p)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_GOLD_BONUS); // 金币+5
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Gengshou();
    }
}
